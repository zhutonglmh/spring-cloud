package interview.z001_base;

/**
 * @author zhutong
 * @date 2019/12/6 20:31
 */
public class Algorithm {
    
    //java 程序设计  实现最小值函数
    
    public static void main(String[] args) {
       
    }
    
    /**
     * 一个泛型的获取数组最小值的函数.并且这个方法只能接受Number的子类并且实现了Comparable接口。
     * <p>
     * <? super T>  模拟逆变 <? extends T> 模拟协变
     */
    private static <T extends Number & Comparable<? super T>> T min(T[] values) {
        if (null == values) return null;
        T min = values[0];
        for (int i = 1; i < values.length; i++) {
            T t = values[i];
            if (min.compareTo(t) > 0) {
                min = t;
            }
        }
        return min;
    }
    
    /**
     * 使用数组实现栈
     * 自己实现一个栈，要求这个栈具有push()、pop()（返回栈顶元素并出栈）、peek() （返回栈顶元素不出栈）、isEmpty()、size()这些基本的方法。
     */
    private static void myStack(){
        MyStack myStack = new MyStack(3);
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        myStack.push(4);
        myStack.push(5);
        myStack.push(6);
        myStack.push(7);
        myStack.push(8);
        System.out.println(myStack.peek());//8
        System.out.println(myStack.size());//8
        for (int i = 0; i < 8; i++) {
            System.out.println(myStack.pop());
        }
        System.out.println(myStack.isEmpty());//true
        myStack.pop();//报错：java.lang.IllegalArgumentException: Stack is empty.
    }
}
