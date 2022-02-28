import my_Interface_form.Queue;

import java.util.NoSuchElementException;

//Array로 '원형 큐' 구조를 활용해 작성
public class ArrayQueue<E> implements Queue<E> {
    private static final int DEFAULT_CAPACITY = 64;

    private Object[] array; //배열을 무한정으로 늘릴수 없기때문에 '원형 큐(Circular Queue)'구조를 사용한다
    private int size;
    
    private int front;  //시작 인덱스를 가리키는 변수(빈 공간임을 유의)
    private int back;   //마지막 인덱스를 가리키는 변수


    public ArrayQueue(){
        this.array = new Object[DEFAULT_CAPACITY];
        this.size = 0;
        this.front = 0;
        this.back = 0;
    }

    public ArrayQueue(int capacity){
        this.array = new Object[capacity];
        this.size = 0;
        this.front = 0;
        this.back = 0;
    }

    private void resize(int newCapacity){
        int arrayCapacity = array.length;

        Object[] newArray = new Object[newCapacity];

        for(int i = 1, j = front + 1; i <= size; i++, j++){
            newArray[i] = array[j % arrayCapacity];
        }
        this.array = null;
        this.array = newArray;  //새 배열을 기존 array 배열로 덮어씌움

        front = 0;
        back = size;
    }

    @Override
    public boolean offer(E item) {
        //용적이 부족한 경우 2배 늘려준다
        if((back + 1) % array.length == front)
            resize(array.length * 2);

        //back을 다음 index로 갱신
        back = (back + 1) % array.length;

        array[back] = item;
        size++;
        return true;
    }

    @Override
    public E poll() {
        if(size == 0)   return null;

        front = (front + 1) % array.length; //index는 무조건 + 방향으로

        @SuppressWarnings("unchecked")  //컴파일러가 일반적으로 경고하는 내용 중 이건 하지말라고 제외 시킬때 사용
        E item = (E) array[front];

        array[front] = null;
        size--;

        //DEFAULT_CAPACITY(64) < array.length < (array.length / 4)인 경우
        if(array.length > DEFAULT_CAPACITY && size < (array.length / 4))
            resize(Math.max(DEFAULT_CAPACITY, array.length / 2));   //최소 용적 미만으로 줄이지는 않도록 한다
        return item;
    }

    public E remove(){
        E item = poll();
        if(item == null)    throw new NoSuchElementException();

        return item;
    }

    @Override
    public E peek() {
        if(size == 0)   return null;

        @SuppressWarnings("unchecked")
        E item = (E) array[(front + 1) % array.length];
        return item;
    }

    public E element(){
        E item = peek();

        if(item == null)    throw new NoSuchElementException();

        return item;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public boolean contains(Object value){
        int start = (front + 1) % array.length;
        
        //i: 요소 개수
        //idx : 원소 위치
        for(int i = 0, idx = start; i < size; i++, idx = (idx + 1) % array.length){
            if(array[idx].equals(value))    return true;
        }
        return false;
    }

    public void clear(){
        for(int i = 0; i < array.length; i++){
            array[i] = null;
        }
        //Arrays.fill(array, null);를 사용해도 무방하다
        
        front = back = size = 0;
    }

}
