package LinkedList;

import my_Interface_form.List;

import java.util.NoSuchElementException;

public class DLinkedList<E> implements List<E>{
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public DLinkedList(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    private Node<E> search(int index){
        if(index < 0 || index >= size) throw new IndexOutOfBoundsException();

        //tail에서부터 검색
        Node<E> x;
        if(index > size / 2){
            x = tail;
            for(int i = size - 1; i > index; i--){
                x = x.prev;
            }
        }
        //head에서부터 검색
        else{
            x = head;
            for(int i = 0; i < index; i++){
                x = x.next;
            }
        }
        return x;
    }

    public void addFirst(E value){
        Node<E> newNode = new Node<E>(value);
        newNode.next = head;

        if(head != null)    head.prev = newNode;
        head = newNode;
        size++;

        //size == 0일때(리스트에 아무것도 없는 상태에서 처음으로 Node를 추가할때)
        if(head == null)    tail = head;
    }

    @Override
    public boolean add(E value) {
        addLast(value);
        return true;
    }

    public void addLast(E value){
        if(size == 0){
            addFirst(value);
            return;
        }

        Node<E> newNode = new Node<E>(value);
        tail.next = newNode;
        newNode.prev = tail;
        tail = newNode;
        size++;
    }

    public void add(int index, E value){
        if(index < 0 || index > size)   throw new IndexOutOfBoundsException();

        if(index == 0){
            addFirst(value);
            return;
        }

        if(index == size){
            addLast(value);
            return;
        }

        Node<E> prev_Node = search(index -1);
        Node<E> next_Node = prev_Node.next;
        Node<E> newNode = new Node<E>(value);

        //연결 끊고
        prev_Node.next = null;
        next_Node.prev = null;

        //새로운 노드 연결
        prev_Node.next = newNode;

        newNode.prev = prev_Node;
        newNode.next = next_Node;

        next_Node.prev = newNode;

        size++;
    }

    public E remove(){
        Node<E> headNode = head;

        if(headNode == null)   throw new NoSuchElementException();

        E element = headNode.data;

        Node<E> nextNode = head.next;

        head.data = null;
        head.next = null;

        if(nextNode != null)    nextNode.prev = null;

        head = nextNode;
        size--;

        if(size == 0)   tail = null;

        return element;
    }
    @Override
    public E remove(int index) {
        if(index < 0 || index >= size)  throw new IndexOutOfBoundsException();

        if(index == 0){
            E element = head.data;
            remove();
            return element;
        }

        Node<E> prevNode = search(index - 1);
        Node<E> removeNode = prevNode.next;
        Node<E> nextNode = removeNode.next;

        E element = removeNode.data;

        prevNode.next = null;

        removeNode.next = null;
        removeNode.prev = null;
        removeNode.data = null;

        if(nextNode != null){   //지워야 하는 노드가 마지막 노드인경우
            nextNode.prev = null;
            nextNode.prev = prevNode;
            prevNode.next = nextNode;
        }else{
            tail = prevNode;
        }
        size--;

        return element;
    }

    @Override
    public boolean remove(Object value) {
        Node<E> prevNode = head;
        Node<E> x = head;

        for(;x != null; x = x.next){
            if(value.equals(x.data))    break;

            prevNode = x;
        }
        
        if(x == null)   return false;   //일치하는 요소가 없을경우

        if(x.equals(head)){
            remove();
        }else{
            Node<E> nextNode = x.next;

            prevNode.next = null;
            x.data = null;
            x.next = null;
            x.prev = null;

            if(nextNode != null){
                nextNode.prev = null;
                nextNode.prev = prevNode;
                prevNode.next = nextNode;
            }else{
                tail = prevNode;
            }
            size--;
        }
        return true;
    }

    @Override
    public E get(int index) {
        return search(index).data;
    }

    @Override
    public void set(int index, E value) {
        Node<E> replaceNode = search(index);
        replaceNode.data = null;
        replaceNode.data = value;
    }

    @Override
    public boolean contains(Object value) {
        return indexOf(value) >= 0;
    }

    @Override
    public int indexOf(Object value) {  //정방향
        int index = 0;
        for(Node<E> x = head; x != null; x = x.next){
            if(value.equals(x.data))    return index;
            index++;
        }
        return -1;
    }

    public int lastIndexOf(Object value){   //역방향
        int index = size;
        for(Node<E> x = tail; x != null; x = x.prev){
            index--;
            if(value.equals(x.data))    return index;
        }
        return -1;
    }
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for(Node<E> x = head; x != null;){
            Node<E> nextNode = x.next;
            x.data = null;
            x.next = null;
            x.prev = null;
            x = nextNode;
        }
        head = tail = null;
        size = 0;
    }


}
