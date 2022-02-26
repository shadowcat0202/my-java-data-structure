import my_Interface_form.List;
import java.util.Arrays;

public class ArrayList<E> implements List<E>{
    private static final int DEFAULT_CAPACITY = 10; //최소 용적 크기
    private static final Object[] EMPTY_ARRAY = {}; //빈 배열

    private int size;

    Object[] array; //요소를 담을 배열

    public ArrayList(){
        this.array = EMPTY_ARRAY;
        this.size = 0;
    }

    public ArrayList(int capacity){
        this.array = new Object[capacity];
        this.size = 0;
    }

    //배열크기 최적화
    private void resize(){
        int array_capacity = array.length;

        //if array's capacity is 0
        if(Arrays.equals(array, EMPTY_ARRAY)){
            array = new Object[DEFAULT_CAPACITY];
            return;
        }

        //용량이 부족한 경우
        if(size == array_capacity){
            int new_capacity = array_capacity * 2;

            array = Arrays.copyOf(array, new_capacity); //copyOf(original_array, new_array_size);
            return;
        }

        //용량이 남는경우
        if(size < (array_capacity / 2)){
            int new_capacity = array_capacity / 2;

            array = Arrays.copyOf(array, Math.max(new_capacity, DEFAULT_CAPACITY));
            return;
        }
    }

    //뒤에 추가
    @Override    
    public boolean add(E value){
        addLast(value);
        return true;
    }

    private void addLast(E value) {
        if(size == array.length)    resize();
        array[size] = value;
        size++;
    }

    //특정 위치에 추가
    @Override
    public void add(int index, E value){
        if(index > size || index < 0)   throw new IndexOutOfBoundsException();
        if(index == size)   addLast(value);
        else{
            if(size == array.length)    resize();

            for(int i = size; i > index; i--){
                array[i] = array[i - 1];
            }
            array[index] = value;
            size++;
        }
    }

    @Override
    public E remove(int index) {
        if(index >= size || index < 0)  throw new IndexOutOfBoundsException();

        E element = (E) array[index];   //삭제할 요소 반환용 변수
        array[index] = null;

        for(int i = index; i < size; i++){
            array[i] = array[i+1];
            array[i+1] = null;
        }
        size--;
        resize();

        return element;
    }

    @Override
    public boolean remove(Object value) {
        int index  = indexOf(value);    //삭제하고자 하는 요소의 인덱스 찾기

        if(index == -1) return false;

        remove(index);
        return true;
    }

    public void addFirst(E value){
        add(0, value);
    }

    @SuppressWarnings("unchecked")
    @Override
    public E get(int index){
        //범위 예외
        if(index >= size || index < 0)  throw new IndexOutOfBoundsException();
        
        //array는 Object타입으로 되어있기 때문에 <E>타입으로 캐시팅 하여 반환
        return (E) array[index];
    }

    @Override
    public void set(int index, E value){
        if(index >= size || index < 0)  throw new IndexOutOfBoundsException();
        else{
            array[index] = value;
        }
    }

    @Override
    public boolean contains(Object value) {
        if(indexOf(value) >= 0)  return true;
        else    return false;
    }

    @Override
    public int indexOf(Object value) {
        for(int i = 0; i < size; i++){
            if(array[i].equals(value))  return i;
        }
        return -1;
    }

    public int lastIndexOf(Object value){
        for(int i = size -1; i >= 0; i--){
            if(array[i].equals(value))  return i;
        }
        return -1;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for(int i = 0; i < size; i++){
            array[i] = null;
        }
        size = 0;
        resize();
    }



}
