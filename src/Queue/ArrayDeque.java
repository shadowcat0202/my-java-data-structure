package Queue;

import my_Interface_form.Queue;

import java.util.NoSuchElementException;

public class ArrayDeque<E> implements Queue<E> {

    private static final int DEFAULT_CAPACITY = 64; //최소(기본) 용적

    private Object[] array;
    private int size;

    private int front;
    private int back;

    public ArrayDeque(){
        this.array = new Object[DEFAULT_CAPACITY];
        size = 0;
        front = 0;
        back = 0;
    }

    public ArrayDeque(int capacity){
        this.array = new Object[capacity];
        size = 0;
        front = 0;
        back = 0;
    }

    private void resize(int newCapacity){
        int arrayCapacity = array.length;

        Object[] newArray = new Object[newCapacity];    //복사해서 array에 덮어씌우는 방식 당연히 새로운 Array가 필요하다

        for(int i = 1, j = front + 1; i <= size; i++, j++){
            newArray[i] = array[j % arrayCapacity];
        }
        
        this.array = null;  //메모리 관리를 안정성 있게 사용하기 위해서는 메모리 할당을 풀어주는것이 좋다
        this.array = newArray;

        front = 0;
        back = size;
    }

    @Override
    public boolean offer(E item) {  //기본적으로 뒤에 차곡차곡 넣는걸 default로 작용한다
        return offerLast(item);
    }
    public boolean offerLast(E item){
        //용적이 가득 았을 경우 2배로 늘린다
        if((back + 1) % array.length == front)
            resize(array.length * 2);

        //순서는 index를 옮기고 -> 값을 넣는다
        back = (back + 1) % array.length;
        array[back] = item;
        size++;
        return true;
    }
    public boolean offerFirst(E item){
        //front = 0인 경우 -1 % array.length를 해버리면 -1이기 때문에 나머지 연산 전에 array.length를 더해주면 된다
        if((front - 1 + array.length) % array.length == back)
            resize(array.length * 2);

        array[front] = item;    //Array를 활용한 큐에서 front는 빈 공간으로 해야하기 때문에 데이터를 넣은 후 index를 이동한다
        front = (front - 1 + array.length) % array.length;
        size++;
        return true;
    }

    @Override
    public E poll() {
        return pollFirst();
    }
    public E pollFirst(){
        if(size == 0)   return null;

        front = (front + 1) % array.length;

        @SuppressWarnings("nuchecked")
        E item = (E) array[front];

        array[front] = null;
        size--;

        if(array.length > DEFAULT_CAPACITY && size < (array.length / 4))
            resize(Math.max(DEFAULT_CAPACITY, array.length/2));

        return item;
    }

    public E remove(){
        return removeFirst();
    }
    public E removeFirst(){
        E item = pollFirst();
        if(item == null)    throw new NoSuchElementException();
        return item;
    }

    public E pollLast(){
        if(size == 0)   return null;

        E item = (E) array[back];
        array[back] = null;
        back = (back - 1 + array.length) % array.length;
        size--;

        if(array.length > DEFAULT_CAPACITY && size < (array.length / 4))
            resize(Math.max(DEFAULT_CAPACITY, array.length/2));
        return item;
    }
    public E removeLast(){
        E item = pollLast();
        if(item == null)    throw new NoSuchElementException();
        return item;
    }



    @Override
    public E peek() {
        return peekFirst();
    }

    public E peekFirst() {
        if(size == 0)   return null;

        @SuppressWarnings("unchecked")
        E item = (E) array[(front + 1) % array.length];
        return item;
    }
    public E peekLast(){
        if(size == 0)   return null;

        @SuppressWarnings("unchecked")
        E item = (E) array[back];
        return item;
    }

    public E element(){
        return getFirst();
    }
    public E getFirst(){
        E item = peek();
        if(item == null)    throw new NoSuchElementException();
        return item;
    }
    public E getLast(){
        E item = peekLast();
        if(item == null)    throw new NoSuchElementException();
        return item;
    }

}
