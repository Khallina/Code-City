import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class BuildingPanel extends JPanel {
    private List<Building> buildings;
    private int maxHeight; // Maximum height of buildings in the city

    public BuildingPanel(List<Building> buildings, int maxHeight) {
        this.buildings = buildings;
        this.maxHeight = maxHeight;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int y = getHeight() - maxHeight; // Calculate the starting y-coordinate based on the panel height and maximum building height

        //make 1 city platform
        for (Building building : buildings) {
            int y_copy = y;
            if (building.getFamily() != null) {
                for (Building b : buildings) {
                    // make buildings with the same family stack on top of each other with a gap of 10 in between
                    if (b.getName().equals(building.getFamily())) {
                        building.setX(b.getX());
                        y_copy = y_copy + b.getHeight() + 10;
                    }
                }
            }
            //draw building
            g.setColor(building.getColor());
            g.drawRect(building.getX(), y_copy, building.getBase(), building.getHeight());
        }
    }
}

public class CodeCity {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Building Stacker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));


        //get github repo
        String repoPath;

        Scanner scanner = new Scanner(System.in);
        // prompt end user to enter repo url
        try (scanner) {
            System.out.print("Enter GitHub repository URL: ");
            String repoUrl = scanner.nextLine().trim();

            repoPath = GetFile.downloadAndExtractRepository(repoUrl); //saves repo into repoPath

            File repo = new File(repoPath);

            if (repo.exists() && repo.isDirectory()) {
                File[] repoFiles = repo.listFiles(); //list of repo contents
                List<BuildingPanel> buildingPanels = new ArrayList<>(); // List to hold each platform / building panel
                for (File directory : repoFiles) {

                    //if file is a directory, make a platform (city)

                    if (directory.isDirectory()) {
                        File[] city = directory.listFiles();
                        List<Building> buildings = new ArrayList<>();
                        int x = 20;
                        int maxHeight = 0;
                        //for each building in a city / each file in the directory
                        for (File file : city) {
                            if (file.isFile()) {
                                // Create an instance of the Parser class
                                Parser parser = new Parser();

                                // Call the parse method and get the result
                                Map<String, Info> result = parser.parse(file.getName());
                                //make building for each file
                                int height = 50;
                                int base = 50;
                                int global_var = 0;
                                int local_var = 0;
                                String family = "";
                                String name = "";
                                for (Map.Entry<String, Info> entry : result.entrySet()) {
                                    name = entry.getKey();
                                    Info classInfo = entry.getValue();
                                    height += classInfo.getLinesOfCode();
                                    global_var += classInfo.getGlobalVariables().size();
                                    local_var += classInfo.getLocalVariables().size();
                                    family = classInfo.getParentClass();
                                }
                                base += 10*global_var;
                                Building b = new Building(name,base,height,x);
                                x = x + base + 50;
                                //set building family
                                if (family != null) {
                                    b.setFamily(family);
                                }
                                //set building color
                                //case 1: has global vars: set to red
                                //case 2: has global and local vars: set to green
                                //case 3: has local vars only: set to blue
                                if (global_var > 0) {
                                    if (local_var > 0) {
                                        b.setColor(Color.GREEN);
                                    }
                                    else {
                                        b.setColor(Color.RED);
                                    }
                                } else {
                                    if (local_var > 0) {
                                        b.setColor(Color.BLUE);
                                    }
                                }
                                buildings.add(b); //add the building to the list of buildings in the city platform
                                if (height > maxHeight) {
                                    maxHeight = height; // Update the maximum height if necessary
                                }
                            }
                        }
                        //draw the platform for city
                        BuildingPanel buildingPanel = new BuildingPanel(buildings, maxHeight);
                        buildingPanels.add(buildingPanel);
                    }
                    for (BuildingPanel buildingPanel : buildingPanels) {
                        frame.add(buildingPanel); // Add each building panel to the frame
                    }
                }
            }

        } catch (IOException e) {
            System.err.println("Error downloading repository: " + e.getMessage());
        }
        frame.setVisible(true);
    }
}
