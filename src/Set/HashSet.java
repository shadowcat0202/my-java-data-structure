package Set;
import my_Interface_form.Set;
public class HashSet<E> implements Set<E> {
    private final static int DEFAULT_CAPACITY = 1 << 4;

    //용적의 3/4를 넘을 경우 resize 해서 메모리 효율 증가
    private final static float LOAD_FACTOR = 0.75f;
    
    Node<E>[] table;    //요소의 정보를 담고 있는 Node type의 배열
    private int size;   //요소 개수

    @SuppressWarnings("unchecked")
    public HashSet(){
        table = (Node<E>[])  new Node[DEFAULT_CAPACITY];
        size = 0;
    }

    //보조 해시 함수(상속 방지를 위해 private static final 선언) method를 final로 선언이 가능했던가?
    private static final int hash(Object key){
        int hash;
        if(key == null)
            return 0;
        //해시 코드는 음수가 될수 없으므로 abs()를 통한 절댓값으로 변환해준다
        else
            return Math.abs(hash = key.hashCode()) ^ (hash >>> 16);
        //>>>, <<< 연산자는 해당 방향으로 시프트 시킨후에 빈공간을 모두 0으로 채운다
        //이것은 Java11과 유사구현하여 key와 hashCode()의 절댓값과 그 값을 16비트 왼쪽으로 밀어낸 값하고의 XOR 값을 반환하도록 만들었다.
        //Tip) and 연산을 할 경우 abs를 사용 할 필요가 없다
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean add(E e) {
        return add(hash(e), e) == null;
    }

    private E add(int hash, E key){
        int idx = hash % table.length;

        if(table[idx] == null)  table[idx] = new Node<E>(hash, key, null);
        else{
            Node<E> temp = table[idx];
            Node<E> prev = null;

            while(temp != null){
                if(temp.hash == hash && (temp.key == key || temp.key.equals(key)))  return key;

                prev = temp;
                temp = temp.next;
            }

            prev.next = new Node<E>(hash, key, null);
        }
        size++;

        if(size >= LOAD_FACTOR * table.length)  resize();

        return null;

    }

    @SuppressWarnings("unchecked")
    private void resize() {
        int newCapacity = table.length * 2;

        final Node<E>[] newTable = (Node<E>[]) new Node[newCapacity];

        for(int i = 0; i < table.length; i++){
            Node<E> value = table[i];   //original table을 하나씩 돌면서 확인

            if(value == null)   continue;   //비어있는 hash index라면 패스

            table[i] = null;    //gc

            Node<E> nextNode;
            while(value != null){  //노드가 존재한다면
                int idx = value.hash % newCapacity; //새로운 테이블에 맞게 이식시키기 위한 idx 변환

                if(newTable[idx] != null){  //새로 들어갈 위치에 노드가 존재한다면 해당 index의 가장 뒤쪽에 추가한다
                    Node<E> tail = newTable[idx];   //해당 인덱스의 가장 마지막 노드를 특정하기 위해 

                    while(tail.next != null){   //다음 노드가 없는 노드까지 반복
                        tail = tail.next;
                    }

                    nextNode = value.next;  //tail 노드를 특정했다면
                    value.next = null;
                    tail.next = value;
                }
                else{   //노드가 존재하지 않는다면(해당 인덱스에서 처음으로 들어간다면 추가)
                    nextNode = value.next;
                    value.next = null;
                    newTable[idx] = value;
                }
                value = nextNode;
            }
        }
        table = null;
        table = newTable;

    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean size() {
        return false;
    }

    @Override
    public void clear() {

    }
}
