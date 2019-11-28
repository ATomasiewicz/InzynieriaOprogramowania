import java.io.File;
import java.util.Iterator;
import java.util.Map;

public class FileGraph extends Graph{
    public FileGraph(Project project){
        super();
        makeGraph(project);
    }

    public void makeGraph(Project project){
        for (File file : project.getFiles()){
            graph.addVertex(file.getName() + "\n" + file.length());
        }

        Iterator iterator = project.getDependencies().iterator();
        while(iterator.hasNext()){
            FileDependency dependency = (FileDependency) iterator.next();
            for (Map.Entry<File, Integer> entry : dependency.getOtherFiles().entrySet()){
                graph.addEdge(dependency.getFile().getName() + "\n" + dependency.getFile().length(), entry.getKey().getName() + "\n" + entry.getKey().length(), new MyWeightedEdge(entry.getValue()));

            }
        }
    }
}
