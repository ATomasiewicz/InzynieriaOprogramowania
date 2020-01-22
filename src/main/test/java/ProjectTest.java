import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class ProjectTest {
    Project project = new Project();
    @Before
    public void setUp(){
        project.loadFiles();
    }
    @Test
    public void fileAmountShouldMatch(){
        assertEquals(12, project.getFiles().size());
    }
    @Test
    public void methodAmountShouldMatch() throws IOException {
        assertEquals(34,project.findMethods().size());
    }
    @Test
    public void fileDependenciesAmountShouldMatch() {
        assertEquals(15,project.getDependencies().size());
    }
    @Test
    public void methodDependenciesAmountShouldMatch() {
        assertEquals(20,project.getMethodDependencies().size());
    }
     @Test
    public void testFiles() throws IOException {
        XmlConverter xmlConverter = new XmlConverter(project);
        File myObj = new File("graf_plikow.xml");
        File myObj1 = new File("graf_metod.xml");
        File myObj2 = new File(("graf_metod_w_plikach"));
        assertTrue((myObj.exists() && myObj1.exists()) && myObj2.exists());
    }

    @Test
    public void testGraphFiles() {
        Graph methodGraph = new MethodGraph(project);
        Graph fileGraph = new FileGraph(project);
        Graph fileMethodsGraph = new FileMethodsGraph(project);
        try {
            GraphVisualiser.drawGraphToFile(methodGraph, "graf_metod.png");
            GraphVisualiser.drawGraphToFile(fileGraph, "graf_plikow.png");
            GraphVisualiser.drawGraphToFile(fileMethodsGraph, "graf_metod_w_plikach.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        File myObj = new File("graf_metod.xml");
        File myObj1 = new File("graf_plikow.xml");
        File myObj2 = new File("graf_metod_w_plikach.xml");
        assertTrue((myObj.exists() && myObj1.exists()) && myObj2.exists());
    }




}
