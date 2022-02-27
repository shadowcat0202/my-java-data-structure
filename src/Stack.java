import my_Interface_form.StackInterface;
import java.util.Arrays;
import java.util.EmptyStackException;

public class Stack<E> implements StackInterface{
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_ARRAY = {};

    private Object[] array;
    private int size;

    public Stack(){
        this.array = EMPTY_ARRAY;
        this.size = 0;
    }
    public Stack(int capacity){
        this.array = new Object[capacity];
        this.size = 0;
    }

    private void resize(){
        if(Arrays.equals(array, EMPTY_ARRAY)){
            array = new Object[DEFAULT_CAPACITY];
            return;
        }

        int arrayCapacity = array.length;

        //용적이 가득 찬 경우
        if(size == arrayCapacity){
            int newCapacity = arrayCapacity * 2;

            array = Arrays.copyOf(array, newCapacity);
            return;
        }

        //용적이 남는경우
        if(size < (arrayCapacity /2)){
            int newCapacity = arrayCapacity / 2;

            array = Arrays.copyOf(array, Math.max(DEFAULT_CAPACITY, newCapacity));
            return;
        }
    }

    @Override
    public Object push(Object item) {
        //용적이 가득 차있다면 용적 재할당
        if(size == array.length)    resize();

        array[size] = item;
        size++;
        return item;
    }

    @Override
    public Object pop() {
        if(size == 0)   throw new EmptyStackException();

        E obj = (E) array[size - 1];
        array[size - 1] = null;
        size--;

        resize();
        return obj;
    }

    @Override
    public Object peek() {
        if(size == 0)   throw new EmptyStackException();
        return (E) array[size - 1];
    }

    @Override
    public int search(Object o) {
        for(int idx = size - 1; idx >= 0; idx--){
            if(array[idx].equals(o))    return size - idx;
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        for(int i = 0; i < size; i++){
            array[i] = null;
        }
        size = 0;
        resize();
    }

    @Override
    public boolean empty() {
        return size == 0;
    }
}
