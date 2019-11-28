import org.jgrapht.graph.DefaultWeightedEdge;

public class MyWeightedEdge extends DefaultWeightedEdge {
    private int weight;

    public MyWeightedEdge(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return Integer.toString(weight);
    }
}
