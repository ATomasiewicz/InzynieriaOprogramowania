import java.util.Iterator;
import java.util.Map;

public class MethodGraph extends Graph {

    public MethodGraph(Project project){
        super();
        makeGraph(project);
    }

    public void makeGraph(Project project){
        for (MethodDependency metDep : project.getMethodDependencies()){
            graph.addVertex(metDep.getMethodName());
        }

        Iterator iterator = project.getMethodDependencies().iterator();
        while (iterator.hasNext()){
            MethodDependency dependency = (MethodDependency) iterator.next();
            for (Map.Entry<String, Integer> entry : dependency.getOtherMethods().entrySet()){
                graph.addEdge(entry.getKey(), dependency.getMethodName(), new MyWeightedEdge(entry.getValue()));
            }
        }
    }
}
