
import org.jgrapht.graph.DefaultEdge;

import javax.swing.*;
import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Project {
    private List<File> files = new LinkedList<>();

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    void loadFiles(){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JFileChooser fileChooser = new JFileChooser();
        //frame.setSize(new Dimension(400, 400));
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
        List<String> names = new LinkedList<>();
        for (File file : files){
            names.add(file.getName());
        }
        return names;
    }


    public static void listFilesRecursive(String rootDir)
    {
        Queue<File> queue = new LinkedList<>();

        // add root directory to the queue
        queue.add(new File(rootDir));

        // loop until queue is empty - all files and directories present
        // inside the root directory are processed
        while (!queue.isEmpty())
        {
            // get next file/directory from the queue
            File current = queue.poll();

            // get list of all files and directories in 'current'
            File[] listOfFilesAndDirectory = current.listFiles();

            // listFiles() returns non-null array if 'current' denotes a dir
            if (listOfFilesAndDirectory != null)
            {
                // iterate over the names of the files and directories in
                // the current directory
                for (File file : listOfFilesAndDirectory)
                {
                    // if file denotes a directory
                    if (file.isDirectory()) {
                        queue.add(file);
                    }
                    // if file denotes a file, print it
                    else {
                        System.out.println(file);
                    }
                }
            }
        }
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
