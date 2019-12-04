**HashMap 与 HashTable 的区别**

    1、线程是否安全：
     
        HashTable 中的方法都是同步的，所以HashTable 是线程安全的
        HashNap 是非线程安全的，如果需要请使用 ConcurrentHashMap
     
    2、效率
    
        HashTable 中的方法都是同步的，所以HashTable 效率较低。另外hashTable 基本要淘汰，不要在代码中使用它。
        HashMap 相对于 HashTable 来说效率高一点。
    
    3、初始容量和扩容
    
        HashTable 初始容量11,如果指定容量则初始容量为指定的数值，每次扩容为之前的2n+1
        
        HashMap 初始容量16，如果指定容量则会将其扩充为2的幂次方大小，每次扩容为之前的2倍，也就是说hashMap 总是以2的幂次方作为容量。
    
    4、底层数据结构
    
        HashTable  链表加数组
        
        HashMap    链表、数组、红黑树   1.8之后，当链表的长度大于阀值(默认为8)时，将链表转化为红黑树，以减少搜索时间。HashTable 没有这样的机制。
        
    5、对null key 和 null value 的支持
        
        HashTable  不支持键值对 任何一个为null。
        HashMap    支持键值为null。

**HashMap 与 HashSet 的区别**

    1、HashSet 的底层就是基于hashMap 实现的。(HashSet 的源码非常少，因为除了clone(),writeObject(),readObject())是HashSet 不得不实现之外，其他的
    方法都是直接调用HashMap 中的方法。
    
    2、HashSet 如何检查重复？
    先判断两个值的hashCode()方法看是否一致，再调用对象的equals() 方法判断是否相等
    
    3、hashCode 和 equals 的相关规定
    
        两个对象相等，则hashcode 相等 且 equals 方法返回true
        两个对象的hashcode 相等，也不一定是相等的
        综上，重写hashcode 方法 必须 重写equlas 方法
        hashcode 的默认行为是对堆上的对象产生独特值，如果没有重写hashcode 方法，则该class的两个对象无论如何都不会相等，即使这两个对象指向相同的数据。
        
**HashMap 的底层实现**

    https://zhuanlan.zhihu.com/p/21673805
    JDK8之前
        jdk1.8 之前hashMap的底层是数组和链表结合在一起使用的也就是 链表散列 。hashMap 通过key 的hashcode 经过扰动函数处理过后得到hash值，然后通过(n-1)
    & hash判断当前元素存放的位置(这里的n指的是数组长度),如果当前位置存在元素的话，就判断该元素与要存入的元素的hash值以及key是否相同，直接覆盖，
    不相同就通过拉链法解决冲突。
        所谓的扰动函数指的就是hashMap的hash方法。使用hash方法也就是扰动函数是为了防止一些实现比较差的hashcode()方法，换句话说使用扰动函数之后可以减少
      碰撞。
      
      HashMap的put 方法
      判断是不是第一次 ----> 初始化大小16 ----->经过扰动函数hash后--------->将得到的值进行取模运算
      --------》先插入数据再扩容
      
      
      