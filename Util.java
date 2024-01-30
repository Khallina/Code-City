import java.lang.reflect.Method;
import java.lang.reflect.Field;

public class Util {
    public static int countMethods(Object obj) {
        // Get the class of the object
        Class<?> clazz = obj.getClass();
        // Get all declared methods of the class
        Method[] methods = clazz.getDeclaredMethods();
        // Return the number of methods
        return methods.length;
    }

    public static int countAttributes(Object obj) {
        // Get the class of the object
        Class<?> clazz = obj.getClass();
        // Get all declared fields of the class
        Field[] fields = clazz.getDeclaredFields();
        // Return the number of fields
        return fields.length;
    }
}
