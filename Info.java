import java.util.*;

public class Info {
    private String className;
    private int LinesOfCode;
    private String parentClass;
    private List<String> globalVariables;
    private List<String> localVariables;
    private String interfaceName;

    private List<String> functions;

    private String implementer;
    //Story: Parse the code
    public Info() {
        this.className = null;
        this.LinesOfCode = 0;
        this.parentClass = null;
        this.interfaceName = null;
        this.globalVariables = new ArrayList<>();
        this.localVariables = new ArrayList<>();
        this.functions = new ArrayList<>();
        this.implementer = null;

    }

    public void setImplementer(String implementer) {
        this.implementer = implementer;
    }

    public String getImplementer() {
        return implementer;
    }

    public void setInterfaceName(String interfaceName) {this.interfaceName = interfaceName;}

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setClassName(String name) {
        this.className = name;
    }

    public String getClassName() {
        return className;
    }

    public void addLinesOfCode(int num) {
        LinesOfCode += num;
    }
    public void setParentClass(String parentClass) {
        this.parentClass = parentClass;
    }

    //public Map<String, String> getVariables() {
    //    return variables;
    //}

    //public void addVariable(String variableName, String scope) {
    //    variables.put(variableName, scope);
    //}
    public void addGlobalVariable(String globalVariableName) {globalVariables.add(globalVariableName);}
    public void addLocalVariable(String localVariableName) {localVariables.add(localVariableName);}
    public void addFunction(String functionName) {
        functions.add(functionName);
    }
    public List<String> getFunctions() {
        return functions;
    }

    public int getLinesOfCode() {
        return LinesOfCode;
    }

    public String getParentClass() {
        return parentClass;
    }

    public List<String> getGlobalVariables() {return globalVariables;}
    public List<String> getLocalVariables() {return localVariables;}

  //  public Set<String> getGlobalVariables() {
  //      Set<String> globalVariables = new HashSet<>();
  //      for (Map.Entry<String, String> entry : variables.entrySet()) {
  //          if (entry.getValue().equals("global")) {
   //             globalVariables.add(entry.getKey());
   //         }
   //     }
  //      return globalVariables;
  //  }

  //  public Set<String> getLocalVariables() {
  //      Set<String> localVariables = new HashSet<>();
  //      for (Map.Entry<String, String> entry : variables.entrySet()) {
  //          if (entry.getValue().equals("local")) {
  //              localVariables.add(entry.getKey());
   //         }
   //     }
   //     return localVariables;
   // }
}
