import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Project project = new Project();
        project.loadFiles();
        try {
            //project.findDependencies();
            project.findFileDependency();
            project.findMethodDependency(project.findMethods());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Graph methodGraph = new MethodGraph(project);
        Graph fileGraph = new FileGraph(project);
        try {
            GraphVisualiser.drawGraphToFile(methodGraph, "graf_metod.png");
            GraphVisualiser.drawGraphToFile(fileGraph, "graf_plikow.png");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
