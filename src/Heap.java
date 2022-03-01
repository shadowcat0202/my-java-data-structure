import java.util.Comparator;
import java.util.NoSuchElementException;


public class Heap<E> {
    private final Comparator<? super E> comparator; //객체의 정렬을 임의의 순서로 정렬하고 싶을때 Comparator를 파라미터로 받아 설정
    private static final int DEFAULT_CAPACITY = 10; //최소 용적

    private int size;

    private Object[] array;

    public Heap(){
        this(null);
    }

    public Heap(Comparator<? super E> comparator) {
        this.array = new Object[DEFAULT_CAPACITY];
        this.size = 0;
        this.comparator = comparator;
    }

    public Heap(int capacity){
        this(capacity, null);
    }

    public Heap(int capacity, Comparator<? super E> comparator) {
        this.array = new Object[capacity];
        this.size = 0;
        this.comparator = comparator;
    }

    /*
    특징
    1. 구현의 용이함을 위해 시작 인덱스(root)는 1 부터 시작한다.
    2. 각 노드와 대응되는 배열의 인덱스는 '불변한다'

    위 특징을 기준으로 각 인덱스별로 채워넣으면 특이한 성질이 나오는데 이는 다음과 같다.

    [성질]
    1. 부모 노드 인덱스 = 자식 노드 인덱스 / 2
    2. 왼쪽 자식 노드 인덱스 = 부모 노드 인덱스 × 2
    3. 오른쪽 자식 노드 인덱스 = 부모 노드 인덱스 × 2 + 1
     */
    private int getParent(int index){
        return index / 2;
    }
    private int getLeftChild(int index){
        return index * 2;
    }
    private int getRightChild(int index){
        return index * 2 + 1;
    }

    private void resize(int newCapacity){
        Object[] newArray = new Object[newCapacity];

        for(int i = 1; i <= size; i++){
            newArray[i] = array[i];
        }
        //아래를 사용해도 무방하다
        /*
        if (size >= 0)
            System.arraycopy(array, 1, newArray, 1, size);
         */


        //GC 처리를 위해 null로 처리한 뒤,
        //새 배열을 연결
        this.array = null;
        this.array = newArray;
    }

    public void add(E value){
        if(size + 1 == array.length)    //배열 용적이 가득 찼을 경우 2배 늘린다
            resize(array.length * 2);

        siftUp(size + 1, value);
        size++;
    }

    /**
     * 
     * @param idx   추가할 노드의 인덱스
     * @param target    재배치 할 노드
     */
    private void siftUp(int idx, E target) {
        if(comparator != null)  siftUpComparator(idx, target, comparator);
        else siftUpComparable(idx, target);
    }

    //  Comparator을 이용한 sift-up
    @SuppressWarnings("unchecked")
    private void siftUpComparator(int idx, E target, Comparator<? super E> comp) {
        //root노드보다 클 때까지만 탐색(배열을 -로 보낼수는 없는 노릇이니까)
        while(idx > 1){
            int parent = getParent(idx);
            Object parentVal = array[parent];
            
            //타겟 노드값이 부모노드보다 크면 반복문 종료
            if(comp.compare(target, (E) parentVal) >= 0)    break;

            array[idx] = parentVal;
            idx = parent;
        }
        array[idx] = target;
    }

    @SuppressWarnings("unchecked")
    private void siftUpComparable(int idx, E target){
        Comparable<? super E> comp = (Comparable<? super E>) target;

        while(idx > 1){
            int parent = getParent(idx);
            Object parentVal  = array[parent];

            if(comp.compareTo((E)parentVal) >= 0)   break;

            array[idx] = parentVal;
            idx = parent;
        }
        array[idx] = comp;
    }

    public E remove(){
        if(array[1] == null)    throw new NoSuchElementException();
        E result = (E) array[1];
        E target = (E) array[size];
        array[size] = null;

        siftDown(1, target);

        return result;
    }

    private void siftDown(int idx, E target){
        if(comparator != null)  siftDownComparator(idx, target, comparator);
        else    siftDownComparable(idx, target);
    }

    private void siftDownComparator(int idx, E target, Comparator<? super E> comp){
        array[idx] = null;
        size--;

        int parent = idx;
        int child;

        //배열의 size를 넘어가지 않을때까지 반복
        while((child = getLeftChild(parent)) <= size){   //left가 size보다 작은지를 판단하는 것은 자식노드의 여부를 판단하는것과 같다
            int right = getRightChild(parent);
            
            Object childVal = array[child]; //왼쪽 자식의 값

            if(right <= size && comp.compare((E) childVal, (E) array[right]) > 0){   //size와 비교하는것은 해당 자식 노드 존재 여부 파악
                child = right;
                childVal = array[child];
            }

            //재배치 할 노드가 자식 노드보다 작을경우 반복 종료
            if(comp.compare(target, (E) childVal) <= 0) break;

            array[parent] = child;
            parent = child;
        }
        array[parent] = target;

        if(array.length > DEFAULT_CAPACITY && size < array.length / 4) {
            resize(Math.max(DEFAULT_CAPACITY, array.length / 2));
        }
    }

    private void siftDownComparable(int idx, E target) {
    }


}
