import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class Building {
    private int base;
    private int height;

    public Building(int base, int height) {
        this.base = base;
        this.height = height;
    }

    public int getBase() {
        return base;
    }

    public int getHeight() {
        return height;
    }

    public boolean canStack(Building otherBuilding) {
        return this.base == otherBuilding.getBase();
    }
}

class BuildingPanel extends JPanel {
    private List<Building> buildings;

    public BuildingPanel(List<Building> buildings) {
        this.buildings = buildings;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int x = 20; // Starting x-coordinate for the first building
        for (Building building : buildings) {
            g.drawRect(x, getHeight() - building.getHeight(), building.getBase(), building.getHeight());
            x += building.getBase();
        }
    }
}

public class CodeCity {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Building Stacker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);

        List<Building> buildings = new ArrayList<>();
        buildings.add(new Building(50, 100));
        buildings.add(new Building(30, 80));
        buildings.add(new Building(50, 120));
        buildings.add(new Building(40, 90));

        BuildingPanel buildingPanel = new BuildingPanel(buildings);
        frame.add(buildingPanel);

        frame.setVisible(true);
    }
}
