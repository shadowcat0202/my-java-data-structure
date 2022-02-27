public class Main {
    public void arrayList_stub(){
        ArrayList<Integer> arr = new ArrayList<Integer>();

        arr.add(1);
        arr.add(4);
        arr.add(20);
        arr.add(45);
        arr.add(100);
        System.out.println(arr.size());
        if(arr.contains(45))    System.out.println("45 포함됨");

    }

    public void Stack_stub(){
        Stack<String> st = new Stack<>();

        st.push("안녕하세요");
        st.push("저의");
        st.push("이름은");
        st.push("shadowcat0202 입니다");

        System.out.println(st.peek());
        System.out.println(st.size());
    }

    public static void main(String[] args){
        //https://bangu4.tistory.com/194
        Main test = new Main();
        //test.arrayList_stub();
        test.Stack_stub();

    }
}