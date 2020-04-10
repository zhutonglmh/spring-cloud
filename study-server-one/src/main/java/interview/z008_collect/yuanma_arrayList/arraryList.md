**ArrayList 简介**

         ArrayList 的底层是数组队列，相当于动态数组。与java 中的数组相比，它的容量能动态增长。在添加大量元素前，应用程序可以使用ensuerCapacity
    操作来增加ArrayList的容量。这可以减少递增式再分配的数量。
    
    
**继承与实现**

    它继承于AbstractList，实现了 List，randomAccess,Cloneable,java.io.Serializable这些接口。
        
        1、ArrayList 继承了 AbstractList，实现了list。它是一个数组队列，提供了相关的添加、删除、修改、遍历等功能。
        
        2、ArrayList 实现了RandomAccess接口，RandomAccess是一个标志接口，表明这个接口的List集合是支持快速随机访问的。在ArrayList中，我们即可以通过元
    素的序号快速获取元素对象，这就是快速随机访问。
    
        3、ArrayList 实现了Cloneable接口，即覆盖了函数clone()，能被克隆。
        
        4、ArrayList实现了java.io.Serializable 接口，这意味着ArrayList 支持序列化，能够通过序列化去传输。
        
        5、和Vector 不同，ArrayList中的操作不是线程安全的！所以，建议在单线程中才使用ArrayList，而在多线程中可以选择Vector 或者 copyOnWriteArrayList;
        
**内部类**

         (1)private class Itr implements Iterator<E>  
         (2)private class ListItr extends Itr implements ListIterator<E>  
         (3)private class SubList extends AbstractList<E> implements RandomAccess  
         (4)static final class ArrayListSpliterator<E> implements Spliterator<E>  
        
       
**modCount 的作用**

            在ArrayList,LinkedList,HashMap等等的内部实现增，删，改中我们总能看到modCount的身影，modCount字面意思就是修改次数，但为什么要记录modCount的修改次数呢？
        大家发现一个公共特点没有，所有使用modCount属性的全是线程不安全的，这是为什么呢？说明这个玩意肯定和线程安全有关系喽，那有什么关系呢
        在一个迭代器初始的时候会赋予它调用这个迭代器的对象的mCount，如何在迭代器遍历的过程中，一旦发现这个对象的mcount和迭代器中存储的mcount不一样那就抛异常
        在迭代过程中，判断 modCount 跟 expectedModCount 是否相等，如果不相等就表示已经有其他线程修改了，注意到 modCount 声明为 volatile，保证线程之间修改的可见性。
        
**Fail-Fast 机制**

        我们知道 java.util.HashMap 不是线程安全的，因此如果在使用迭代器的过程中有其他线程修改了map，那么将抛出ConcurrentModificationException，这就是所谓
        fail-fast策略。这一策略在源码中的实现是通过 modCount 域，modCount 顾名思义就是修改次数，对HashMap 内容的修改都将增加这个值，那么在迭代器初始化过程
        中会将这个值赋给迭代器的 expectedModCount。
  
  
**SubList的坑**
    
        1，该方法返回的是父list的一个视图，从fromIndex（包含），到toIndex（不包含）。fromIndex=toIndex 表示子list为空
        2，父子list做的非结构性修改（non-structural changes）都会影响到彼此：所谓的“非结构性修改”，是指不涉及到list的大小改变的修改。
        相反，结构性修改，指改变了list大小的修改。
        3，对于结构性修改，子list的所有操作都会反映到父list上(调用父list的方法)。但父list的修改将会导致返回的子list失效。
        （不能对子list进行修改，因为对父list 修改了modcount+ 1,但是对于子list(SubList)而言，没有增加modcount，再对视图进行操作就会报错）
        4，tips：如何删除list中的某段数据：
        
        
**时间复杂度**
        
        线性表的顺序存储，因为支持随机访问，所以查询元素的时间复杂度为O(1)，插入删除要移动数组元素所以时间复杂度为O(n)。
        
**ArrayList 在并发情况下的坑**

        list.add() 方法在多线程下可能会出现 add的元素为null，和indexOutException;
        https://blog.csdn.net/u010010428/article/details/51258783/