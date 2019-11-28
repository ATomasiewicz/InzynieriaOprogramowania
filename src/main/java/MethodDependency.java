import java.util.HashMap;
import java.util.Map;

public class MethodDependency {
    private String fileName;
    private String methodName;
    private Map<String, Integer> otherMethods;

    public MethodDependency(String filename, String methodName){
        this.fileName = filename;
        this.methodName = methodName;
        otherMethods = new HashMap<>();
    }

    public String getFileName(){
        return fileName;
    }

    public String getMethodName() {
        return methodName;
    }

    public Map<String, Integer> getOtherMethods() {
        return otherMethods;
    }

    public void addToMap(String methodName, int value){
        otherMethods.put(methodName, value);
    }
}
