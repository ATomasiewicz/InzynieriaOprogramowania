import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;

public abstract class Graph {
    protected DefaultDirectedWeightedGraph graph;

    Graph(){
        graph = new DefaultDirectedWeightedGraph(DefaultWeightedEdge.class);
    };

    public abstract void makeGraph(Project project);

    public void addEdge(String source, String target, int weight){
        graph.addEdge(source, target, new MyWeightedEdge(weight));
    }

    public DefaultDirectedWeightedGraph getGraph(){
        return graph;
    }
}
