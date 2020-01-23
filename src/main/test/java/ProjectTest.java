
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;


public class ProjectTest {
    Project project = new Project();

    @Before
    public void setUp(){
        project.loadFiles();
    }

    @Test
    public void testGraphFiles() {
        try {
            project.findFileDependency();
            project.findMethodDependency(project.findMethods());
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        File myObj = new File("graf_metod.png");
        File myObj1 = new File("graf_plikow.png");
        File myObj2 = new File("graf_metod_w_plikach.png");
        assertTrue((myObj.exists() && myObj1.exists()) && myObj2.exists());
    }

    @Test
    public void fileAmountShouldMatch() {
        assertEquals(16, project.getFiles().size());
    }

    @Test
    public void methodAmountShouldMatch() throws IOException {
        assertEquals(46, project.findMethods().size());
    }

    @Test
    public void fileDependenciesAmountShouldMatch() {
        assertEquals(16, project.getDependencies().size());
    }

    @Test
    public void methodDependenciesAmountShouldMatch() {
        assertNotEquals(20, project.getMethodDependencies().size());
    }

    @Test
    public void shouldNotReferToTheSameObject() throws IOException {
        assertNotSame(project.findMethods(),project.getFileNames());
    }

    @Test
    public void AmountShouldBeTheSame(){
        assertSame(project.getFiles().size(),project.getFileNames().size());
    }



    @Test
    public void testFiles() throws IOException {
        XmlConverter xmlConverter = new XmlConverter(project);
        File myObj = new File("graf_plikow.xml");
        File myObj1 = new File("graf_metod.xml");
        File myObj2 = new File(("graf_metod_w_plikach.xml"));
        assertTrue((myObj.exists() && myObj1.exists()) && myObj2.exists());
    }

    @Test
    public void graph() throws FileNotFoundException {
        try {
            XmlConverter xmlConverter = new XmlConverter(project);
        } catch (IOException e) {
            e.printStackTrace();
        }
        File myObj = new File("graf_plikow.xml");
        String line = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
        BufferedReader r = new BufferedReader(new FileReader("C:\\Users\\HP\\Downloads\\graf_plikow.xml"));
        try {
            if (line.equals(r.readLine())){
                assertTrue(true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void FileDepenMethodDepenAndFileAmountMethodAmount(){
        try {
            if ((project.getDependencies().size() <= project.getMethodDependencies().size())
                    && (project.findMethods().size() >= project.getFiles().size()))
            {
                assertTrue(true);
            }
            else {
                fail();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
