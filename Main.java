import java.util.List;
import java.util.Map;
import javax.swing.*;
import java.io.IOException;
import java.util.Scanner;

//Story: Parse the code
//This is a test main for my own purposes so I can run it and see what its getting
public class Main extends JFrame {
    public Main() {

    }
    public static void main(String[] args) {
        //get github repo
        String filePath;

        Scanner scanner = new Scanner(System.in);
        // prompt end user to enter repo url
        try (scanner) {
            System.out.print("Enter GitHub repository URL: ");
            String repoUrl = scanner.nextLine().trim();

            filePath = GetFile.downloadAndExtractRepository(repoUrl); //get file

        } catch (IOException e) {
            System.err.println("Error downloading repository: " + e.getMessage());
        }

        // Replace "your_file_path_here" with the actual path to your file

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

        //display world
    }
}