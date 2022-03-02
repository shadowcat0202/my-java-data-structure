import java.util.Comparator;

public class Main {



    public Main() {
    }

    public void arrayList_stub(){
        ArrayList<Integer> arr = new ArrayList<>();

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

    public void Heap_Stub(){
        Heap<Student> heap1 = new Heap<>();
        Heap<Student> heap2 = new Heap<>(comparator);

        heap1.add(new Student("김자바", 40));
        heap2.add(new Student("김자바", 40));

        heap1.add(new Student("이씨프", 27));
        heap2.add(new Student("이씨프", 27));

        heap1.add(new Student("조파이", 48));
        heap2.add(new Student("조파이", 48));

        heap1.add(new Student("김자바", 18));
        heap2.add(new Student("김자바", 18));

        heap1.add(new Student("상스윕", 32));
        heap2.add(new Student("상스윕", 32));

        heap1.add(new Student("양씨샵", 27));
        heap2.add(new Student("양씨샵", 27));

        System.out.println("[Heap 1] : 이름순(같을 경우 나이 오름차순)");
        while(!heap1.isEmpty()) {
            System.out.println(heap1.remove());
        }
        System.out.println();

        System.out.println("[Heap 2] : 나이 내림차순(같을 경우 이름순)");
        while(!heap2.isEmpty()) {
            System.out.println(heap2.remove());
        }
        System.out.println();
    }
    private static Comparator<Student> comparator = new Comparator<Student>() {
        @Override
        public int compare(Student o1, Student o2) {
            if(o1.age == o2.age)    return o1.name.compareTo(o2.name);
            return o2.age - o1.age; //나이 내림차순
        }
    };
    private static class Student implements Comparable<Student>{

        String name;
        int age;

        public Student(String name, int age){
            this.name = name;
            this.age = age;
        }

        @Override
        public int compareTo(Student o) {
            if(this.name.compareTo(o.name) == 0)    //이름이 같다면 나이순
                return this.age - o.age;

            return this.name.compareTo(o.name);
        }

        public String toString(){
            return "이름 : " + this.name + "\t나이 : " + this.age;
        }
    }

    public static void main(String[] args){
        //https://st-lab.tistory.com/category/%EC%9E%90%EB%A3%8C%EA%B5%AC%EC%A1%B0

        Main test = new Main();
        //test.arrayList_stub();
        //test.Stack_stub();
        //test.Heap_Stub();

    }


}
