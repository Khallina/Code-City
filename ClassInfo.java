import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ClassInfo {
    private String className;
    private int LinesOfCode;
    private String parentClass;
    private Map<String, String> variables;

    public ClassInfo() {
        this.className = null;
        this.LinesOfCode = 0;
        this.parentClass = null;
        this.variables = new HashMap<>();
    }
    public void setClassName(String name) {
        this.className = name;
    }

    public void addLinesOfCode(int num) {
        LinesOfCode += num;
    }
    public void setParentClass(String parentClass) {
        this.parentClass = parentClass;
    }

    public Map<String, String> getVariables() {
        return variables;
    }

    public void addVariable(String variableName, String scope) {
        variables.put(variableName, scope);
    }

    public int getLinesOfCode() {
        return LinesOfCode;
    }

    public String getParentClass() {
        return parentClass;
    }

    public Set<String> getGlobalVariables() {
        Set<String> globalVariables = new HashSet<>();
        for (Map.Entry<String, String> entry : variables.entrySet()) {
            if (entry.getValue().equals("global")) {
                globalVariables.add(entry.getKey());
            }
        }
        return globalVariables;
    }

    public Set<String> getLocalVariables() {
        Set<String> localVariables = new HashSet<>();
        for (Map.Entry<String, String> entry : variables.entrySet()) {
            if (entry.getValue().equals("local")) {
                localVariables.add(entry.getKey());
            }
        }
        return localVariables;
    }
}
