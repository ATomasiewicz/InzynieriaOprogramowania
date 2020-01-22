import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Project project = new Project();
        project.loadFiles();
        try {
            project.findFileDependency();
            project.findMethodDependency(project.findMethods());
            //project.findDependencies();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Graph methodGraph = new MethodGraph(project);
        Graph fileGraph = new FileGraph(project);
        Graph fileMethodsGraph = new FileMethodsGraph(project);
        StoryFive fileMethodPacketRelation = new StoryFive(project);
        try {
            GraphVisualiser.drawGraphToFile(methodGraph, "graf_metod.png");
            GraphVisualiser.drawGraphToFile(fileGraph, "graf_plikow.png");
            GraphVisualiser.drawGraphToFile(fileMethodsGraph, "graf_metod_w_plikach.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
