package DateStruckStudy;

/**
 * 基于链表存取结构的方法接口定义
 * @param <E>
 */
public interface MyList<E> {
    void add(E element);
    E get(int index);
    int size();
    E remove(int index);
}
