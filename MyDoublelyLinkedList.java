package DateStruckStudy;

/**
 * 基于双向链表存取的容器
 * @param <E>
 */
public class MyDoublelyLinkedList<E> implements MyList<E> {

    class  Node<E>{
        E item;//存入元素
        Node<E> next;//后继节点
        Node<E> front;//前驱节点

        public Node(E item, Node<E> next, Node<E> front) {
            this.item = item;
            this.next = next;
            this.front = front;
        }
    }
    private Node<E> head;//记录头节点
    private Node<E> tail;//记录尾节点
    private int size;//记录元素个数
    /**
     * 添加元素
     * @param element
     */
    @Override
    public void add(E element) {
        this.linkLast(element);
    }

    /**
     * 将节点插入到尾部
     */
    private void linkLast(E element){
        //获取尾节点
        Node t = this.tail;
        //创建节点
        Node<E> node = new Node<E>(element,null,t);
        //尾节点更新
        this.tail = node;
        //尾节点为空
        if(t==null){
            this.head = node;
        }else {
            t.next = node;
        }
        this.size++;
    }
    /**
     * 得到指定位置元素
     * @param index
     * @return
     */
    @Override
    public E get(int index) {
        //校验
        checkIndex(index);
        //查找节点
        Node<E> node = getNode(index);

        return node.item;
    }
    private Node<E> getNode(int index){
        //判断从头还是从尾查找
         if((this.size>>1)>index){
             Node node=this.head;
             for(int i = 0;i<index;i++){
                 node= node.next;
             }
             return node;
         }else {
             Node node = this.tail;
             for (int i = size-1;i>index;i--){
                 node = node.front;
             }
             return node;
         }

    }
    /**
     * 校验Index
     */
    private void checkIndex(int index){
        if(!(index >= 0 && index<this.size)){
            throw new IndexOutOfBoundsException("Index:"+index+"Size:"+size);
        }
    }
    /**
     * 获取链表元素个数
     * @return
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * 删除元素
     * @param index
     * @return
     */
    @Override
    public E remove(int index) {
        //校验
        checkIndex(index);
        //查找
        Node<E> node = getNode(index);
        E item = node.item;
        //判断是否为头节点
        if(node.front ==null)
        {
            this.head = node.next;
        }else {
            node.front.next = node.next;
        }
        //判断是否为尾节点
        if(node.next == null){
            this.tail = node.front;

        }else {
            node.next.front = node.front;
        }
        node.next = null;
        node.front = null;
        node.item = null;
        //链表元素个数更新
        this.size--;

        return item;
    }

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {
        MyList<String > myList = new MyDoublelyLinkedList<>();
        myList.add("a");
        myList.add("b");
        myList.add("c");
        myList.add("d");
        System.out.println(myList.size());
        for (int i = 0;i<myList.size();i++){
            System.out.println(myList.get(i));
        }
        System.out.println("------------------");
        System.out.println(myList.remove(0));
        System.out.println(myList.size());
        for (int i = 0;i<myList.size();i++){
            System.out.println(myList.get(i));
        }
    }
}
