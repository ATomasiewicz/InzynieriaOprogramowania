import javax.swing.*;
import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Project {
    private List<File> files = new LinkedList<File>();

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public void loadFiles(){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JFileChooser fileChooser = new JFileChooser();
        //frame.setSize(new Dimension(400, 400));
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.showOpenDialog(frame);
        if(fileChooser.getSelectedFiles() != null) {
            files = Arrays.asList(fileChooser.getSelectedFiles());
        }
        frame.dispose();
    }

    public List<String> getFileNames(){
        List<String> names = new LinkedList<String>();
        for (File file : files){
            names.add(file.getName());
        }
        return names;
    }
}
