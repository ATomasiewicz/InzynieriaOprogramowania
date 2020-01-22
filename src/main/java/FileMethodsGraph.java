import org.jgrapht.graph.DefaultWeightedEdge;

import java.io.File;
import java.util.Iterator;

public class FileMethodsGraph extends Graph {
    public FileMethodsGraph(Project project){
        super();
        makeGraph(project);
    }

    @Override
    public void makeGraph(Project project) {
        for (File file : project.getFiles()){
            graph.addVertex(file.getName());
            System.out.println(file.getName());
        }

        Iterator iterator = project.getMethodDependencies().iterator();
        while(iterator.hasNext()){
            MethodDependency dependency = (MethodDependency) iterator.next();
            graph.addVertex(dependency.getMethodName());
            graph.addEdge(dependency.getFileName(), dependency.getMethodName(), new MyWeightedEdge(0));
        }
    }
}
