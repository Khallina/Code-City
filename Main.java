import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Replace "your_file_path_here" with the actual path to your file
        String filePath = "ParseTest";

        // Create an instance of the Parser class
        Parser parser = new Parser();

        // Call the parse method and get the result
        Map<String, ClassInfo> result = parser.parse(filePath);

        // Print or inspect the result
        for (Map.Entry<String, ClassInfo> entry : result.entrySet()) {
            String className = entry.getKey();
            ClassInfo classInfo = entry.getValue();

            System.out.println("Class: " + className);
            System.out.println("   Lines of Code: " + classInfo.getLinesOfCode());
            System.out.println("   Parent Class: " + classInfo.getParentClass());
            System.out.println("   Global Variables: " + classInfo.getGlobalVariables());
            System.out.println("   Local Variables: " + classInfo.getLocalVariables());
            System.out.println();
        }
    }
}