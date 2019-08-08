package studyserverone.base.data_structure03;

/**
 * @author zhutong
 * 
 * @date 2019/7/29 14:11
 *队列有哪些常见的操作呢?
 *
 *
 * enqueue(element)：向队列尾部添加一个（或多个）新的项。
 *
 * dequeue()：移除队列的第一（即排在队列最前面的）项，并返回被移除的元素。
 *
 * front()：返回队列中第一个元素——最先被添加，也将是最先被移除的元素。队列不做任何变动（不移除元素，只返回元素信息——与Stack类的peek方法非常类似）。
 *
 * isEmpty()：如果队列中不包含任何元素，返回true，否则返回false。
 *
 * size()：返回队列包含的元素个数，与数组的length属性类似。
 *
 *
 * 现在, 我们来实现这些方法. (其实已经比较简单了)
 */
public class QueueMy<T> {
    
    private T[] queue;
}
