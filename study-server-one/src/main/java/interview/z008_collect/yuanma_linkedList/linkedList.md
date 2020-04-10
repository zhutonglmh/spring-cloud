**LinkedList 简介**
    
    LinkedList 是一个实现了List 接口和Deque接口的双端链表。LinkedList的底层链表结构使它支持高效的插入和删除操作，另外它实现了Deque接口，
    使得LinkedList 也具有队列的特性；LinkedList 不是线程安全的，如果想使LinkedList 变成线程安全的，可以调用静态类Collection的SynchronizedList方法。
    
**内部结构**

    双向链表，尾插入方式。头插入方式