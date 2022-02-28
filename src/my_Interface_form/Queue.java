package my_Interface_form;

/**
 * 나만의 queue Interface
 * queue는 ArrayQueue, LinkedQueue, Deque, PriorityQueue에 의해 구현
 *
 * @author shadowcat0202
 * @param <E> the type of elements in this stack
 *
 * @version 0.1
 */

public interface Queue<E> {

    /**
     * 큐의 마지막에 요소 추가
     * @param e 추가할 원소
     * @return  정상적으로 추가되었을 경우 {@code true} 반환
     */
    boolean offer(E e);

    /**
     * 큐의 첫번째 요소를 삭제하고 요소를 반환
     * @return  큐의 삭제된 요소 반환
     */
    E poll ();

    /**
     * 큐의 첫번째 요소를 삭제하지 않고 반환
     * @return  큐의 첫번째 요소 반환
     */
    E peek();

}
