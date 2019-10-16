import javax.swing.*;
import java.io.File;
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
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fileChooser.showOpenDialog(frame);
        if(fileChooser.getSelectedFiles() != null) {
            for (File file : fileChooser.getSelectedFiles()){
                if (file.isDirectory()){
                    loadFilesFromDirectory(file);
                }
                else{
                    files.add(file);
                }
            }
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

    private void loadFilesFromDirectory(File directory){
        for (File file : directory.listFiles()){
            if(file.isDirectory()){
                loadFilesFromDirectory(file);
            }
            else{
                files.add(file);
            }
        }
    }
}
