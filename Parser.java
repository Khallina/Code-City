import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//Story: Parse the code

//I defined global variables as "not in a method"
//Local variables are in a method
//not sure if thats right but wasn't sure what else to do

public class Parser {
    public Map<String, Info> parse(String file) {
        Map<String, Info> classInfoMap = new HashMap<>();

        try (Scanner scan = new Scanner(new File(file))) {
            String line;
            String currentClass = null;
            Info currentClassInfo = null;
            boolean insideMethod = false;
            int wordCount = 3;

            while (scan.hasNextLine()) {
                line = scan.nextLine().trim();
                if (line.startsWith("public abstract class")) {
                    wordCount = 3;
                } else {
                    wordCount = 2;
                }
                if (line.startsWith("public class") || line.startsWith("class") ||
                        line.startsWith("public abstract class")) {
                    insideMethod = false;
                    if (currentClassInfo != null) {
                        classInfoMap.put(currentClass, currentClassInfo);
                    }
                    String[] lineParts = line.split(" "); //splits each line into array of words
                    if (lineParts.length >= 3) { //errors are thrown without these checks
                        int braceIndex = lineParts[wordCount].indexOf('{');
                        String className;
                        if (braceIndex != -1) {
                            className = lineParts[wordCount].substring(0, braceIndex);
                        } else {
                            className = lineParts[wordCount];
                        }
                        currentClass = className;
                        currentClassInfo = new Info();
                        currentClassInfo.setClassName(className); //all of the above block is to get the class name

                        int extendIndex = line.indexOf("extends"); //gets the parent class if there's an extend
                        if (extendIndex != -1) {
                            String parentClass = line.substring(extendIndex + 8).split(" ")[0].replaceAll("\\{", "");
                            currentClassInfo.setParentClass(parentClass);
                        }
                        int interfaceIndex = line.indexOf("implements"); //implements function from interface
                        if (interfaceIndex != -1) {
                            String implement = line.substring(interfaceIndex + 11).split(" ")[0].replaceAll("\\{", "");
                            currentClassInfo.setImplementer(implement);
                        }
                    }
                } else if (line.startsWith("public interface") || line.startsWith("interface")) { //adds interface
                    if (currentClassInfo != null) {
                        classInfoMap.put(currentClass, currentClassInfo);
                    }
                    String[] lineParts = line.split(" ");
                    if (lineParts.length >= 3) {
                        int braceIndex = lineParts[2].indexOf('{');
                        String interfaceName;
                        if (braceIndex != -1) {
                            interfaceName = lineParts[2].substring(0, braceIndex);
                        } else {
                            interfaceName = lineParts[2];
                        }
                        currentClass = interfaceName;
                        currentClassInfo = new Info();
                        currentClassInfo.setInterfaceName(interfaceName);
                    }
                } else if (currentClassInfo != null && currentClassInfo.getInterfaceName() != null){
                    if (line.contains("(") && line.contains(")")) {
                        String[] functionParts = line.split("\\(");
                        String[] functionDeclare = functionParts[0].trim().split(" ");
                        String functionName = functionDeclare[functionDeclare.length-1];
                        currentClassInfo.addFunction(functionName);
                    }
                } else if (line.contains("{")) { //flags if it's in a method currently or not while reading
                    if (!insideMethod) {
                        insideMethod = true;
                    }
                } else if (line.contains("}")) {
                    if (insideMethod) {
                        insideMethod = false;
                    }
                } else if ((line.contains("private") || line.contains("public")) && !insideMethod &&
                        !line.startsWith("//") && !line.contains("(")) { //"(" check for functions getting caught
                    String[] lineParts = line.split(" ");
                    if (lineParts.length >= 2) {
                            currentClassInfo.addGlobalVariable(lineParts[2].split(";")[0]);
                           // currentClassInfo.addVariable(lineParts[2].split(";")[0], "global");
                        }

                } else if (line.contains("=") && line.contains(";") && !line.startsWith("//")) {
                    String[] lineParts = line.split(" ");
                    if (lineParts.length >= 2) {
                        if (insideMethod & !lineParts[0].contains("this")) {
                            currentClassInfo.addLocalVariable(lineParts[1].split(";")[0]);
                            //currentClassInfo.addVariable(lineParts[1].split(";")[0], "local");
                        }
                    }
                }


                if (!line.isEmpty() && currentClassInfo != null && !line.startsWith("import")) {
                    currentClassInfo.addLinesOfCode(1); //only counts lines INSIDE the class not the entire file
                }                                               //as in, no import lines counted
            }
            if (currentClassInfo != null) {
                classInfoMap.put(currentClass, currentClassInfo);
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
        return classInfoMap;
    }
}