import java.lang.reflect.Method;

public class Building {
    
    private int height;
    private int base;
    private String color;
    private int transparency;

    public Building(int height, int base) {
        this.height = height; // number of methods
        this.base = base; // number of attributes
        this.color = null;
        this.transparency = 0;
    }

    public void setHeight(int num) {
        this.height = num;
    }

    public void setBase(int num) {
        this.base = num;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setTransparency(int num) {
        this.transparency = num;
    }    
    
    public void classToBuilding(Object obj) { // takes a class and sets the buildings attributes to the proper dimensions
        this.height = Util.countMethods(obj);
        this.base = Util.countAttributes(obj);
    }

}