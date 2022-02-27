package LinkedList;

import java.util.Arrays;
import java.util.NoSuchElementException;

import my_Interface_form.List;

/**
 *
 * 자료구조 이해를 위한 Single Linked List입니다.
 *
 * @author shadowcat0202
 * @param <E> the type of elements in this list
 *
 * @version 0.1
 *
 */

public class SLinkedList<E> implements List<E>, Cloneable{
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public SLinkedList(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    private Node<E> search(int index){
        if(index < 0 || index >= size)  throw new IndexOutOfBoundsException();

        Node<E> x = head;

        for(int i = 0; i < index; i++){
            x = x.next;
        }

        return x;
    }

    public void addFirst(E value){
        Node<E> newNode = new Node<E>(value);
        newNode.next = head;
        head = newNode;
        size++;

        if(head.next == null)   tail = head;
    }

    @Override
    public boolean add(E value){
        addLast(value);
        return true;
    }

    @Override
    public void add(int index, E value){
        if(index < 0 || index > size )  throw new IndexOutOfBoundsException();

        if(index == 0){
            addFirst(value);
            return;
        }

        if(index == size){
            addLast(value);
            return;
        }

        Node<E> prev_Node = search(index -1);   //추가 하려는 노드의 전 노드
        Node<E> next_Node = prev_Node.next; //추가하려는 위치의 노드
        Node<E> newNode = new Node<E>(value);   //새로운 노드

        /**
         * 이전 노드가 가리키는 노드 끊고
         * 새 노드를 연결
         * 새 노드가 가리키는 다음 노드를 next_Node로 설정
         */
        prev_Node.next = null;
        prev_Node.next = newNode;
        newNode.next = next_Node;
        size++;

    }

    public void addLast(E value){
        Node<E> newNode = new Node<E>(value);

        if(size == 0){
            addFirst(value);
            return;
        }

        /**
         * 마지막 노드(tail)의 다음 노드(next)가 새 노드를 가리키도록 하고
         * tail이 가리키는 노드를 새 노드로 바꿔준다
         */
        tail.next = newNode;   
        tail = newNode;
        size++;
    }

    public E remove(){
        if(head == null)    throw new NoSuchElementException();

        E element = head.data;
        Node<E> nextNode = head.next;

        head.data = null;
        head.next = null;

        head = nextNode;
        size--;

        /**
         * 삭제한 요소가 리스트의 유일한 요소였을 경우
         * 그 요소는 head == tail이므로
         * tail도 null로 변환
         */
        if(size == 0)   tail = null;

        return element;
    }

    @Override
    public E remove(int index){
        //삭제할 노드가 head노드일 경우
        if(index == 0)  return remove();

        if(index < 0 || index >= size)  throw new IndexOutOfBoundsException();

        Node<E> prevNode = search(index - 1);
        Node<E> removeNode = prevNode.next;
        Node<E> nextNode= removeNode.next;

        E element = removeNode.data;

        prevNode.next = nextNode;
        
        //삭제할 노드가 tail인 경우
        if(prevNode.next == null)   tail = prevNode;

        removeNode.data = null;
        removeNode.next = null;
        size--;

        return element;
    }

    @Override
    public boolean remove(Object value){
        Node<E> prevNode = head;
        boolean hasValue = false;
        Node<E> x = head;

        for(; x != null; x = x.next){
            if(value.equals(x.data)){
                hasValue = true;
                break;
            }
            prevNode = x;
        }
        
        //일치하는 요소가 없는 경우
        if(x == null)   return false;

        if(x.equals(head)){
            remove();
        }
        else{
            prevNode.next = x.next;

            //삭제했던 노드가 마지막 노드였다면 tail 갱신
            if(prevNode.next == null)   tail = prevNode;
            
            x.data = null;
            x.next = null;
            size--;
        }
        return true;
    }

    @Override
    public E get(int index) {
        return search(index).data;
    }

    @Override
    public void set(int index, E value)  {
        Node<E> replaceNode = search(index);
        replaceNode.data = null;
        replaceNode.data = value;
    }

    @Override
    public boolean contains(Object value) {
        return indexOf(value) >= 0;
    }

    @Override
    public int indexOf(Object value) {
        int index = 0;

        for(Node<E> x = head; x != null; x = x.next, index++){
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
            x = nextNode;
        }

        head = tail = null;
        size = 0;
    }
}
