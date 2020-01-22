import org.jgrapht.graph.DefaultWeightedEdge;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class StoryFive extends Graph{

    public StoryFive(Project project) throws IOException {
        super();
        makeGraph(project);
    }
    @Override
    public void makeGraph(Project project) throws IOException {
        for (File file : project.getFiles()) {
            List<String> a = new LinkedList<>();
            String b = file.getPath().split(file.getName())[0];
            b = b.split("src/")[1];
            a.addAll(Arrays.asList(b.split("/")));
            String c = file.getName();
            a.add(c);
            for (int x = 0; x < a.size() - 1; x++) {
                graph.addVertex(a.get(x));
                graph.addVertex(a.get(x + 1));
                graph.addEdge(a.get(x), a.get(x + 1), new MyWeightedEdge(0));
            }
        }
        Iterator iterator = project.getMethodDependencies().iterator();
        while(iterator.hasNext()){
            MethodDependency dependency = (MethodDependency) iterator.next();
            graph.addVertex(dependency.getMethodName());
            graph.addEdge(dependency.getFileName(), dependency.getMethodName(), new MyWeightedEdge(0));
        }
    }
}