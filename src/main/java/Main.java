import java.io.*;

public class Main {
    public static void main(String[] args) {
        Project project = new Project();
        project.loadFiles();
        for (String name : project.getFileNames()){
            String tmp[] = name.split("\\.");
            for (File file : project.getFiles()){
                int i=0;
                if (file.getName().equals(name)) continue;
                else{
                    try {
                        BufferedReader reader = new BufferedReader(new FileReader(file));
                        String line;
                        while ((line = reader.readLine()) != null){
                            if (line.contains(tmp[0])){
                                i++;
                            }
                        }
                        System.out.println(name + " in " + file.getName() + ": " + i);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
