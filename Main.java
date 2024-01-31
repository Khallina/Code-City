import java.util.List;
import java.util.Map;
import javax.swing.*;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;

//Story: Parse the code
//This is a test main for my own purposes so I can run it and see what its getting
public class Main extends JFrame {
    public Main() {

    }
    public static void main(String[] args) {
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
                for (File cities : repoFiles) {
                    //if file is a directory, make a platform (city)
                    if (cities.isDirectory()) {
                        File[] city = cities.listFiles();
                        //for each building in a city
                        for (File building : city) {
                            if (building.isFile()) {
                                // Create an instance of the Parser class
                                Parser parser = new Parser();

                                // Call the parse method and get the result
                                Map<String, Info> result = parser.parse(building.getName());

                            }
                        }
                    }
                }
            }

        } catch (IOException e) {
            System.err.println("Error downloading repository: " + e.getMessage());
        }
    }
}