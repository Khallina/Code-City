import javax.swing.*;
import java.awt.*;

class SpiderPanel extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        // Draw 5x5 grid with gray outline
        int gridCellSize = 75;
        int gridX = 0;
        int gridY = 200;
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                int x = gridX + col * gridCellSize;
                int y = gridY + row * gridCellSize;
                g.setColor(Color.BLACK);
                g.fillRect(x, y, gridCellSize, gridCellSize);
                g.setColor(Color.GRAY);
                g.drawRect(x, y, gridCellSize, gridCellSize);
            }
        }

        // Draw Spider
        // for now the spider is a dot, will change this later
        g.setColor(Color.BLACK);
        g.fillRect(panelWidth / 2 - 10, panelHeight / 2 - 10, 20, 20);

        // Draw "Spider World" text
        g.setColor(Color.BLACK);
        g.drawString("Spider World", 10, 20);
    }
}

public class SpiderWorldJPanel {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Spider World");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(5000, 5000);

        SpiderPanel spiderPanel = new SpiderPanel();
        frame.add(spiderPanel);

        frame.setVisible(true);
    }
}
