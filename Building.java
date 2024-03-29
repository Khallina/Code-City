import java.awt.*;

public class Building {
    
    private String name; // name of building is just name of class
    private int x;
    private int height;
    private int base;
    private Color color;
    private int transparency;
    private String family;

    public Building(String name, int base, int height, int x) {
        this.name = name;
        this.height = height; // number of lines
        this.base = base; // number of attributes
        this.x = x;
        this.color = null;
        this.transparency = 0;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHeight(int num) {
        this.height = num;
    }

    public void setBase(int num) {
        this.base = num;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setTransparency(int num) {
        this.transparency = num;
    }    
    
    public void setFamily(String family) {
        this.family = family;
    }

    public void classToBuilding(Object obj) { // takes a class and sets the buildings attributes to the proper dimensions
        this.height = Util.countMethods(obj);
        this.base = Util.countAttributes(obj);
    }

    public int getHeight() {
        return height;
    }

    public int getBase() {
        return base;
    }

    public Color getColor() {
        return color;
    }

    public int getTransparency() {
        return transparency;
    }

    public String getFamily() {
        return family;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public String getName() {
        return name;
    }
}