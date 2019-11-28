import javax.swing.*;
import java.io.*;
import java.util.*;

public class Project {
    private List<File> files = new LinkedList<>();
    private List<FileDependency> dependencies = new LinkedList<>();
    private List<String> methods = new LinkedList<>();
    private List<MethodDependency> methodDependencies = new LinkedList<>();



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
                    dependencies.add(new FileDependency(file));
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

    public List<FileDependency> getDependencies() {
        return dependencies;
    }

    public static void listFilesRecursive(String rootDir) {
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
                dependencies.add(new FileDependency(file));
            }
        }
    }

public Data findDependencies() throws IOException {
        Data data;
        List<String> list1=new LinkedList<>();
        List<String> list2= new LinkedList<>();
        List<Integer>list3=new LinkedList<>();

        for (String name : getFileNames()){
            String []tmp = name.split("\\.");
            if(!tmp[1].equals("java"))
                continue;
            for (File file : files){
                int i=0;
                if (file.getName().equals(name)|| !file.getName().contains(".java")) {
                    continue;
                }
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                
                while((line=reader.readLine()) != null){
                if (line.contains(tmp[0])){
                    i++;
                }
                    }
                list1.add(name);
                list2.add(file.getName());
                list3.add(i);
            }
        }
        data=new Data(list1,list2,list3);
        return data;
    }

    public void findFileDependency() throws IOException {
        Iterator iterator = dependencies.iterator();
        while (iterator.hasNext()){
            FileDependency dependency = (FileDependency) iterator.next();
            String fileName = dependency.getFile().getName();
            String[] tmp = fileName.split("\\.");

            for (File file : files){
                if (file.getName().equals(fileName)) continue;

                BufferedReader reader = new BufferedReader(new FileReader(file));
                int i=0;
                String line;
                while((line = reader.readLine()) != null){
                    if (line.contains(tmp[0])) i++;
                }
                if (i>0) dependency.addToMap(file, i);
            }

        }
    }

    public List<String> findMethods() throws IOException {
        List<String> methodList = new LinkedList<>();
        for (File file : files){
            BufferedReader reader = new BufferedReader(new FileReader(file));
            int i=0;
            String line;
            while((line = reader.readLine()) != null){
                if (line.contains("(") && line.contains(")") && line.contains("{")){
                    String[] tmp1 = line.split("\\(");
                    String[] tmp2 = tmp1[0].split("\\s");
                    String methodName = null;
                    if(tmp2[tmp2.length-1].charAt(0) <= 'A'  || tmp2[tmp2.length-1].charAt(0) >= 'Z') methodName = tmp2[tmp2.length-1];
                    if (line.contains("}")) continue;
                    int bracketCounter=1;
                    while ((line = reader.readLine()) != null && bracketCounter > 0){
                        if (line.contains("{")) bracketCounter++;
                        if (line.contains("}")) bracketCounter--;
                    }
                    if(methodName!=null) {
                        methodList.add(methodName);
                        MethodDependency metDep = new MethodDependency(file.getName(), methodName);
                        methodDependencies.add(metDep);
                    }
                }
            }
        }
        return methodList;
    }

    public void findMethodDependency(List<String> methodList) throws IOException {
        for (MethodDependency metDep : methodDependencies) {
            for (File file : files) {
                if (!metDep.getFileName().equals(file.getName())) continue;
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.contains(metDep.getMethodName()) && line.contains("(") && line.contains(")") && line.contains("{")) {
                        int bracketCounter = 1;
                        while (bracketCounter > 0 && (line = reader.readLine()) != null) {
                            for (int j = 0; j < methodDependencies.size(); j++) {
                                if (line.contains(methodList.get(j) + "(")) {
                                    if (metDep.getOtherMethods().containsKey(methodList.get(j))) {
                                        metDep.addToMap(methodList.get(j), metDep.getOtherMethods().get(methodList.get(j)) + 1);
                                    } else {
                                        metDep.addToMap(methodList.get(j), 1);
                                    }
                                }
                            }
                            if (line.contains("{")) bracketCounter++;
                            if (line.contains("}")) bracketCounter--;
                        }
                    }
                }
            }
        }
    }


    public List<MethodDependency> getMethodDependencies() {
        return methodDependencies;
    }

    public List<String> getMethods() {
        return methods;
    }
}
