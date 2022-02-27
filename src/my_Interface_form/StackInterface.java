package my_Interface_form;

/**
 * 나만의 stack Interface
 * StackInterface는 Stack에 의해 구현
 *
 * @author shadowcat0202
 * @param <E> the type of elements in this stack
 *
 * @version 0.1
 */
public interface StackInterface<E> {
    /**
     * 스택의 맨 위의 요소를 추가합니다
     * @param item 스택에 추가할 요소
     * @return 스택에 추가된 요소
     */
    E push(E item);

    /**
     * 스택의 맨 위의 요소를 제거하고 제거 된 요소를 반환
     * @return 제거된 요소 반환
     */
    E pop();

    /**
     * 스택의 맨 위의 요소를 제거 하지 않고 반환
     * @return 상단의 요소 반환
     */
    E peek();

    /**
     * 스택의 top으로부터 특정 요소가 몇 번째 위치에 있는지 반환
     * 중복되는 원소가 있는 경우 top에서 가까운 원소 위치 반환
     * @param o 스택에서 위치를 찾을 요소
     * @return  스택의 top부터 처음으로 요소와 일치하는 위치 반환
     *          없을경우 {@code -1} 반환
     */
    int search(Object o);

    /**
     * 스택의 요소 개수 반환
     * @return  스택 요소 개수 반환
     */
    int size();

    /**
     * 스택의 모든 요소 삭제
     */
    void clear();

    /**
     * 스택이 비어있는지 여부 반환
     * @return 스택이 비어있는 경우{@code true}, 그 외의 경우 {@code false} 반환
     */
    boolean empty();
}
