import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Dependency {
    private File file;
    private Map<File, Integer> otherFiles;

    public Dependency(File file) {
        this.file = file;
        otherFiles = new HashMap<>();
    }

    public void addToMap(File file, int value){
        otherFiles.put(file, value);
    }

    public Map<File, Integer> getOtherFiles() {
        return otherFiles;
    }

    public File getFile() {
        return file;
    }
}
