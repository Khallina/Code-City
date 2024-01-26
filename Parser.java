import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Parser {
    public Map<String, ClassInfo> parse(String file) {
        Map<String, ClassInfo> classInfoMap = new HashMap<>();

        try (Scanner scan = new Scanner(new File(file))) {
            String line;
            String currentClass = null;
            ClassInfo currentClassInfo = null;
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
                    String[] lineParts = line.split(" ");
                    if (lineParts.length >= 3) {
                        int braceIndex = lineParts[wordCount].indexOf('{');
                        String className;
                        if (braceIndex != -1) {
                            className = lineParts[wordCount].substring(0, braceIndex);
                        } else {
                            className = lineParts[wordCount];
                        }
                        currentClass = className;
                        currentClassInfo = new ClassInfo();
                        currentClassInfo.setClassName(className);

                        int extendIndex = line.indexOf("extends");
                        if (extendIndex != -1) {
                            String parentClass = line.substring(extendIndex + 8).split(" ")[0].replaceAll("\\{", "");
                            currentClassInfo.setParentClass(parentClass);
                        }
                    }
                } else if (line.contains("{")) {
                    if (!insideMethod) {
                        insideMethod = true;
                    }
                } else if (line.contains("}")) {
                    if (insideMethod) {
                        insideMethod = false;
                    }

                } else if ((line.contains("private") || line.contains("public")) && !insideMethod && !line.startsWith("//")) {
                    String[] lineParts = line.split(" ");
                    if (lineParts.length >= 2) {
                            currentClassInfo.addVariable(lineParts[1].split(";")[0], "global");
                        }

                } else if (line.contains("=") && line.contains(";") && !line.startsWith("//")) {
                    String[] lineParts = line.split(" ");
                    if (lineParts.length >= 2) {
                        if (insideMethod & !lineParts[0].contains("this")) {
                            currentClassInfo.addVariable(lineParts[1].split(";")[0], "local");
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