package Queue;

import my_Interface_form.Queue;

import java.util.Comparator;
import java.util.NoSuchElementException;

public class PriorityQueue<E> implements Queue<E> {
    private final Comparator<? super E> comparator;
    private static final int DEFAULT_CAPACITY = 10;

    private int size;
    private Object[] array;

    public PriorityQueue(){
        this(null);
    }
    public PriorityQueue(Comparator<? super E> comparator) {
        this.array = new Object[DEFAULT_CAPACITY];
        this.size = 0;
        this.comparator = comparator;
    }
    public PriorityQueue(int capacity){
        this(capacity, null);
    }
    public PriorityQueue(int capacity, Comparator<? super E> comparator) {
        this.array = new Object[capacity];
        this.size = 0;
        this.comparator = comparator;
    }

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

        this.array = null;
        this.array = newArray;
    }



    @Override
    public boolean offer(E value) {
        if(size + 1 == array.length)    resize(array.length * 2);

        siftUp(size + 1, value);
        size++;
        return true;
    }

    private void siftUp(int idx, E target){
        if(comparator != null)  siftUpComparator(idx, target, comparator);
        else    siftUpComparable(idx, target);
    }

    private void siftUpComparable(int idx, E target) {
        Comparable<? super E> comp = (Comparable<? super E>) target;

        while(idx > 1){
            int parent = getParent(idx);
            Object parentVal = array[parent];

            if(comp.compareTo((E)parentVal) >= 0)   break;

            array[idx] = parentVal;
            idx = parent;
        }
        array[idx] = comp;
    }

    private void siftUpComparator(int idx, E target, Comparator<? super E> comp) {
        while(idx > 1){
            int parent = getParent(idx);
            Object parentVal = array[parent];

            if(comp.compare(target, (E) parentVal) >= 0)    break;

            array[idx] = parentVal;
            idx = parent;
        }
        array[idx] = target;
    }

    @Override
    public E poll() {
        if(array[1] == null)    return null;
        return remove();
    }
    @SuppressWarnings("unchecked")
    public E remove(){
        if(array[1] == null)    throw new NoSuchElementException();

        E result = (E) array[1];
        E target = (E) array[size];
        array[size] = null;

        size--;

        siftDown(1, target);

        return result;

    }


    private void siftDown(int idx, E target) {
        if(comparator != null)  siftDownComparator(idx, target, comparator);
        else siftDownComparable(idx, target);

    }

    @SuppressWarnings("unchecked")
    private void siftDownComparable(int idx, E target) {
        Comparable<? super E> comp = (Comparable<? super E>) target;
        array[idx] = null;
        int parent_idx = idx;
        int child_idx;

        while((child_idx = getLeftChild(parent_idx)) <= size){
            int right_idx = getRightChild(parent_idx);
            Object childVal = array[child_idx];

            if(right_idx <= size && ((Comparable<? super E>) childVal).compareTo((E)array[right_idx]) > 0){
                child_idx = right_idx;
                childVal = array[child_idx];
            }

            if(comp.compareTo((E) childVal) <= 0)   break;

            array[parent_idx] = childVal;
            parent_idx = child_idx;
        }
        array[parent_idx] = comp;

        if(array.length > DEFAULT_CAPACITY && size < array.length / 4)
            resize(Math.max(DEFAULT_CAPACITY, array.length/2));

    }

    @SuppressWarnings("unchecked")
    private void siftDownComparator(int idx, E target, Comparator<? super E> comp) {
        array[idx] = null;
        int parent_idx = idx;
        int child_idx;

        while((child_idx = getLeftChild(parent_idx)) <= size){
            int right_idx = getRightChild(parent_idx);
            Object childVal = array[child_idx];

            if(right_idx <= size && (comp.compare((E) childVal, (E) array[right_idx])) > 0){
                child_idx = right_idx;
                childVal = array[right_idx];
            }

            if(comp.compare(target, (E) childVal) <= 0) break;  //재배치 할 노드가 자식 노드보다 작을경우 == 움직일 필요 없는 경우

            array[parent_idx] = childVal;
            parent_idx = child_idx;
        }
        array[parent_idx] = target;

        if(array.length > DEFAULT_CAPACITY && size < array.length / 4)
            resize(Math.max(DEFAULT_CAPACITY, array.length/2));
    }

    public int size(){
        return size;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E peek() {
        if(array[1] == null)    throw new NoSuchElementException();
        return (E) array[1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(Object value) {
        for(int i = 1; i <= size; i++) {
            if(array[i].equals(value)) {
                return true;
            }
        }
        return false;
    }


    public void clear() {
        for(int i = 0; i < array.length; i++) {
            array[i] = null;
        }
        size = 0;
    }

}
