import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.util.mxCellRenderer;
import org.jgrapht.graph.DefaultWeightedEdge;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GraphVisualiser {

    public static void drawGraphToFile(Graph graph, String fileName) throws IOException {
        org.jgrapht.ext.JGraphXAdapter<String, DefaultWeightedEdge> graphAdapter = new  org.jgrapht.ext.JGraphXAdapter<String, DefaultWeightedEdge>(graph.getGraph());
        com.mxgraph.layout.mxIGraphLayout layout = new mxCircleLayout(graphAdapter);
        layout.execute(graphAdapter.getDefaultParent());

        BufferedImage image = mxCellRenderer.createBufferedImage(graphAdapter, null, 2, Color.WHITE, true, null);
        File imageFile = new File(fileName);
        ImageIO.write(image, "PNG", imageFile);
    }

}
