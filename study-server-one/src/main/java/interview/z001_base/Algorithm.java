package interview.z001_base;

/**
 * @author zhutong
 * @date 2019/12/6 20:31
 */
public class Algorithm {
    
    //java 程序设计  实现最小值函数
    
    public static void main(String[] args) {
    
        int[] nums1 = {1};
        int[] nums2 = {2,3,4};
        System.out.println(findMedianSortedArrays(nums1,nums2));
//        bubbleSort();
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
    
    /**
     * 冒泡排序
     */
    public static void bubbleSort() {
    
            int[] sss = {9,8,7,6,4,8,7,2,2,4,5,9,8,85,5,5,5,5,5,5,5,5,5,8,7,7,854,4,97,64,7,86,48,65,4,8,98,1};
            int[] ss = ss(sss.length,sss);
        for (int i = 0; i < ss.length; i++) {
            System.out.println(ss[i]);
        }
    } 
    
    public static int[] ss(int length,int[] ss){
        
        for (int i = 1; i < ss.length; i++) {
            if(ss[i] < ss[i-1]){
                int temp = ss[i];
                ss[i] = ss[i-1];
                ss[i - 1] = temp;
            }
        }
        if(length - 1 == 0){
            return ss;
        }else {
            return ss(length - 1,ss);
        }
       
    }
    
    /** 两个有序数组找寻中位数*/
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
    
        int size1 = nums1.length;
        int size2 = nums2.length;
    
        if(size1 == 0 && size2 == 0){
           return 0;
        }
        if(size1 == 0){
            if(size2 % 2 != 0){
                return nums2[(size2 / 2 -1) + 1 ];
            }else {
                return ((double)(nums2[(size2 / 2) - 1 ] +  nums2[(size2 / 2)]) )/ 2;
            }
        }
        if(size2 == 0){
            if(size1 % 2 != 0){
                return nums1[(size1 / 2 - 1) + 1 ];
            }
            else {
                return ((double)(nums1[(size1 / 2) - 1 ] +  nums1[(size1 / 2)])) / 2;
            }
        }
        int MIN = Integer.MIN_VALUE;
        int MAX = Integer.MAX_VALUE;
        int l1;
        int r1;
        int l2;
        int r2;
        //奇数
        l1 = size1 / 2 -1;
        r1 = l1 + 1;
        l2 = size2 / 2 - 1;
        r2 = l2 + 1;
        while (true){
            int lv1 = l1 < 0 ? MIN : l1 >= nums1.length ? MAX:nums1[l1];
            int rv1 = r1 >= nums1.length ? MAX : r1 < 0 ? MIN:nums1[r1];
            int lv2 = l2 < 0 ? MIN : l2 >= nums2.length ? MAX:nums2[l2];
            int rv2 = r2 >= nums2.length ? MAX : r2 < 0 ? MIN:nums2[r2];
            if(lv1 <= rv2 && lv2 <= rv1){
                if(size1 % 2 != 0 && size2 % 2 != 0){
                    return ((double)(rv1 + rv2))/2;
                }
                if(size1 % 2 == 0 && size2 % 2 == 0){
                    return ((double) (Math.max(lv1,lv2) + Math.min(rv1,rv2))) / 2;
                }
                return Math.min(rv1,rv2);
            }
            if(lv1 > rv2 ){
                l1 = l1 - 1;
                r1 = r1 - 1;
                l2 = l2 + 1;
                r2 = r2 + 1;
            }
            if(lv2 > rv1 ){
                l1 = l1 + 1;
                r1 = r1 + 1;
                l2 = l2 - 1;
                r2 = r2 - 1;
            }
        }
    }
    
}
