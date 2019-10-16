
import java.util.LinkedList;
import java.util.List;

public class Data {
    List<String> name1 = new LinkedList<>();
    List<String> name2 = new LinkedList<>();
    List<Integer> name3 = new LinkedList<>();

    public Data(List<String> name1, List<String> name2, List<Integer> name3) {
        this.name1 = name1;
        this.name2 = name2;
        this.name3 = name3;
    }

    public List<String> getName1() {
        return name1;
    }

    public void setName1(List<String> name1) {
        this.name1 = name1;
    }

    public List<String> getName2() {
        return name2;
    }

    public void setName2(List<String> name2) {
        this.name2 = name2;
    }

    public List<Integer> getPower() {
        return name3;
    }

    public void setPower(List<Integer> power) {
        this.name3 = name3;
    }
}
