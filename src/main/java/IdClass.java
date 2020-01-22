import java.util.HashMap;
import java.util.Map;

public class IdClass {
    String fileName;
    String id;
    String diagID;
    private Map<String, String> connections;

    public IdClass(String fileName, String id) {
        this.fileName = fileName;
        this.id=id;
        connections=new HashMap<>();
    }

    public String getProject() {
        return fileName;
    }

    public String getId() {
        return id;
    }

    public String getDiagID() {
        return diagID;
    }

    public void setDiagID(String diagID) {
        this.diagID = diagID;
    }

    public Map<String, String> getConnections() {
        return connections;
    }

    public void addToMap(String fileName)
    {
        connections.put(fileName, PasswordGenerator.generatePassword(12));
    }
}
