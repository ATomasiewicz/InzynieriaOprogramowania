import java.io.*;

public class Main {
    public static void main(String[] args) {
        Project project = new Project();
        project.loadFiles();
        try {
            project.findDependencies();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
