import java.util.List;
import java.util.Map;
//Story: Parse the code
//This is a test main for my own purposes so I can run it and see what its getting
public class Main {
    public static void main(String[] args) {
        // Replace "your_file_path_here" with the actual path to your file
        String filePath = "ParseTest";

        // Create an instance of the Parser class
        Parser parser = new Parser();

        // Call the parse method and get the result
        Map<String, Info> result = parser.parse(filePath);

        // Print or inspect the result
        for (Map.Entry<String, Info> entry : result.entrySet()) {
            //String className = entry.getKey();
            Info classInfo = entry.getValue();

            if (classInfo.getClassName() != null) {
                System.out.println("Class: " + classInfo.getClassName());
            } else {
                System.out.println("Interface: " + classInfo.getInterfaceName());
                List<String> functions = classInfo.getFunctions();
                if (!functions.isEmpty()) {
                    System.out.println("   Functions: " + String.join(", ", functions));
                }
            }
            System.out.println("   Lines of Code: " + classInfo.getLinesOfCode());
            System.out.println("   Parent Class: " + classInfo.getParentClass());
            System.out.println("   Implements Interface: " + classInfo.getImplementer());
            System.out.println("   Global Variables: " + classInfo.getGlobalVariables());
            System.out.println("   Local Variables: " + classInfo.getLocalVariables());
            System.out.println();
        }
    }
}