package LinkedList;

/**
 * 싱글(next) 더블(prev, next) Linked List에 모두 사용 가능한 Node
 * @param <E>   the type of elements in this list
 */
class Node<E> {
    E data;
    Node<E> prev;   //이전 노드를 가리키는 래퍼런스 변수
    Node<E> next;   //다음 노드를 가리키는 래퍼런스 변수

    Node(E data){
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}
