package my_Interface_form;

/**
 * 자바 Set Interface입니다.
 * Set은 HashSet, LinkedHashSet, TreeSet에 의해 구현
 *
 * @author shadowcat0202
 * @param <E> the type of elements in this Set
 *
 * @version 0.1
 */


public interface Set<E> {
    /**
     * 요소 추가
     * @param e
     * @return
     */
    boolean add(E e);
    boolean remove(Object o);
    boolean contains(Object o);
    boolean equals(Object o);
    boolean isEmpty();
    boolean size();
    void clear();
}
