import java.util.LinkedList;
import java.util.List;

public class Date {
    List<String> list1=new LinkedList<>();
    List<String> list2= new LinkedList<>();
    List<Integer>list3=new LinkedList<>();

    public Date(List<String> list1, List<String> list2, List<Integer> list3) {
        this.list1 = list1;
        this.list2 = list2;
        this.list3 = list3;
    }

    public List<String> getList1() {
        return list1;
    }

    public void setList1(List<String> list1) {
        this.list1 = list1;
    }

    public List<String> getList2() {
        return list2;
    }

    public void setList2(List<String> list2) {
        this.list2 = list2;
    }

    public List<Integer> getList3() {
        return list3;
    }

    public void setList3(List<Integer> list3) {
        this.list3 = list3;
    }
}
