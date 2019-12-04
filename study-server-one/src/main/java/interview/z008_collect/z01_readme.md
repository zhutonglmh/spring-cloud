**List、Set、Map 三者的区别**

    List: 有序的
    Set:  不允许重复的集合
    Map:  键值对存储
    
**ArraryList 与 LinkedList的区别**

    1、线程安全？ 
    
        ArrayList 和 LinkedList 都是不同步的，也就是说都不是线程安全的
        
    2、底层数据结构
    
        ArrayList 底层使用的object 数组;
        linkedList 底层使用的 双向链表 数据结构
        
    3、插入和删除是否受元素位置的影响
    
        ArrayList 采用数组存储，所以插入和删除元素的时间复杂度受元素位置的影响。比如执行add(E)方法的时候，ArrayList会默认在指定的元素追加到此列表的
    末尾，这种情况的时间复杂度就为O(1)。但是要在指定的位置i插入和删除元素的话，时间复杂度就为o(n-i).因为在进行上述操作的时候集合的第i个元素和之后的
    元素都要执行向后/向前移一位的操作。
        LinkedList 采用双向链表存储，所以对于元素的新增和删除，时间复杂度不受元素位置的影响，近似O（1）,如果要是在指定位置插入和删除元素的话，时间复杂
    度近似为O（n）,因为要先移动到指定位置再插入/删除。
    
    4、是否支持快速随机访问
        LinkedList 不支持高效的随机元素访问，而ArrayList 支持，快速随机访问就是通过元素的序号快速的获取元素对象(对应于 get(I))方法。
        
    5、内存空间占用
    
        ArrayList 的空间浪费主要体现在list列表的结尾会预留一定的容量空间，而linkedList的空间花费则体现在它的每一个元素的存储都要比ArrayList花费更多的
     空间（因为要存放直接后继和直接前驱以及数据），ArrayList LinkedList  主要原因在于存储结构不同。
     
    6、补充
        RandomAccess接口  该接口的作用仅仅是标识实现这个接口的类拥有随机访问功能，ArrayList 有 ，LinkedList 没有
        
**ArrayList 和 Vector区别，为什么要用ArrayList 替代Vector呢？**

    1、ArrayList 中的方法不是同步的，Vector中的方法都是同步的。所以 ArrayList 不是线程安全的，但是Vetcor是线程安全的。
    
    2、因为Vector 中方法都是同步的所以效率比较低下。故一般在不需要同步的情况下推荐使用ArrayList 而不是 LinkedList