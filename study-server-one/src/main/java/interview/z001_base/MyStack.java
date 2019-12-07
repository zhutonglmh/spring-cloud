package interview.z001_base;

import java.util.Arrays;

/**
 * @author zhutong
 * @date 2019/12/7 15:21
 * 
 * 使用数组实现栈
 * 自己实现一个栈，要求这个栈具有push()、pop()（返回栈顶元素并出栈）、peek() （返回栈顶元素不出栈）、isEmpty()、size()这些基本的方法。
 *
 * 提示：每次入栈之前先判断栈的容量是否够用，如果不够用就用Arrays.copyOf()进行扩容；
 * 栈：先进后出  后进先出
 * 
 */
public class MyStack {
    
    private int[] storage;
    
    private int capacity;
    
    private int count;
    
    private static final int GROW_FACTOR = 2;
    
    public MyStack(){
        
        this.capacity = 8;
        this.storage = new int[8];
        this.count = 0;
    }
    
    public MyStack(int initCapacity) {
        
        if(initCapacity < 1){
            throw new IllegalArgumentException("initCapaCity too small");
        }
        this.capacity = initCapacity;
        this.storage = new int[initCapacity];
        this.count = 0;
    }
    
    public void push(int value){
        
        if(this.count == capacity){
            //扩容
            capacity = capacity * GROW_FACTOR;
            storage = Arrays.copyOf(storage,capacity);
        }
        storage[count++] = value;
    }
    
    public int pop(){
        count--;
        if(count < 0){
           throw  new IllegalArgumentException("stack is empty"); 
        }
        return storage[count];
    }
    
    public int peek(){
        if (count == 0){
            throw new IllegalArgumentException("initCapaCity too small");
        }
        return storage[count-1];
    }
    
    public boolean isEmpty(){
        return count == 0;
    }
    
    public int size(){
        return count;
    }
}
