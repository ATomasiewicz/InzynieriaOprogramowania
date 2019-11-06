import org.jgrapht.graph.DefaultWeightedEdge;

public class WEdge extends DefaultWeightedEdge {
        int weight;

    public WEdge(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return Integer.toString(weight);
    }
}
