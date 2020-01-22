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




}