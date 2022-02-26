import my_Interface_form.List;

public class main {
    static void arrayList_stub(){
        ArrayList<Integer> arr = new ArrayList<Integer>();

        arr.add(1);
        arr.add(4);
        arr.add(20);
        arr.add(45);
        arr.add(100);
        System.out.println(arr.size());
        if(arr.contains(45))    System.out.println("45 포함됨");

    }

    public static void main(String[] args){
        arrayList_stub();

    }
}
