import java.util.HashMap;
import java.util.Map;

public class UMLdata {

    String metodName;
    String fileName;
    String code;

    public UMLdata(String metodName, String fileName) {
        this.metodName = metodName;
        this.fileName = fileName;
        code=PasswordGenerator.generatePassword(12);
    }

    public String getMetodName() {
        return metodName;
    }

    public void setMetodName(String metodName) {
        this.metodName = metodName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }




}
