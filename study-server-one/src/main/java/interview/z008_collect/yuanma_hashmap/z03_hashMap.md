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
    
        HashTable  单向链表加数组
        
        HashMap    单向链表、数组、红黑树   1.8之后，当链表的长度大于阀值(默认为8)时，将链表转化为红黑树，以减少搜索时间。HashTable 没有这样的机制。
        
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
      
      map计算index
      System.out.println((a.hashCode() ^ (a.hashCode() >>> 16)) & 15);
      
      
      
**HashMap 在多线程下的问题**

    一、jdk1.7 并发下的rehash() 可能会导致元素之间形成一个循环链表，不过jdk1.8修复了这个问题
    
        1、问题描述
        多线程后到了线上，发现程序经常占了100%的CPU，查看堆栈，你会发现程序都Hang在了HashMap.get()这个方法上了
        2、原因
        jdk 1.7 链表采用头插法
    
    二、多线程下hashMap 数据丢失问题
    
**jdk1.8 和 jdk1.7 HashMap 有什么区别**

    一、相同点
    1、默认初始容量都是16，默认加载因子都是0.75.容量必须是2的倍数
    2、扩容时都增加一倍
    3、根据hash值得到通的索引方法都一样，都是i=hash&(cap-1)
    4、初始时表为空，都是懒加载，在插入第一个元素时进行初始化
    5、键为null 的hash值都为0，都会放在哈希表的第一个桶中
    
    
    二、不同点、主要是思想上的不同
    1、最为重要的一点，底层数据结构不一样，1.7 是 数组加链表  1.8是数组加链表加红黑树 阀值8
    2、插入键值对的方式不同，1.7 采用头插法，即将链表的第一个元素放在指定下标位置 1.8 采用尾插法，即将链表的最后一个元素放在指定下标位置。
    3、jdk1.7 的扩容发生在插入前，jdk1.8的扩容发生在插入后 
    4、jdk 1.7 rehash 后链表里的数据顺序是反过来的，因为它是一个一个取出来放进去的，单链表的头插入方式。即把开始的链表元素放到数组指定的下标位置。
       jdk1.8 rehash  后是尾插入方式  即把末尾的链表元素放在数组指定下标位置。也是为了解决循环链表问题
    5、jdk1.7 扩容是resize()有可能改变元素的hash值，jdk1.8 不会改变元素的哈希值
    6、jdk1.8是通过hash & oldCap == 0,(==0 保持原下标值不变，！= 0则新下标 =老下标 + oldCap) 将链表分散，而jdk1.7 是通过更新hashSeed方法来改变hash值达到分散的目的。
    
**开放定址法 和 拉链法**

    开放定址法：
    
        将发生hash冲突的元素，放到下一个为空的位置，一直如此循环。
    
    拉链法：
        
        将发生hash冲突的元素，以链表的方式存储。