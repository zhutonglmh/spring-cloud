**ArrayList 的 扩容 机制**

   一、先从ArrayList 的三种构造函数说起
    
        1、public ArrayList(){}
        1、public ArrayList(int initialCapacity) {}
        3、public ArrayList(Collection<? extends E> c){}
        
        以无参数构造方法创建 ArrayList 时，实际上初始化赋值的是一个空数组。当真正对数组进行添加元素操作时，才真正分配容量。
    即向数组中添加第一个元素时，数组容量扩为10。
    
    
   二、一步一步分析ArrayList 的扩容机制
    
        1、先看add方法
        
            /**
              * 将指定的元素追加到此列表的末尾。 
              */
             public boolean add(E e) {
            //添加元素之前，先调用ensureCapacityInternal方法
                 ensureCapacityInternal(size + 1);  // Increments modCount!!
                 //这里看到ArrayList添加元素的实质就相当于为数组赋值
                 elementData[size++] = e;
                 return true;
             }   
            
         2. 再来看看 ensureCapacityInternal() 方法
         可以看到 add 方法 首先调用了ensureCapacityInternal(size + 1)
         
            //得到最小扩容量
             private void ensureCapacityInternal(int minCapacity) {
                 if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
                       // 获取默认的容量和传入参数的较大值
                     minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
                 }
         
                 ensureExplicitCapacity(minCapacity);
             }
         当 要 add 进第1个元素时，minCapacity为1，在Math.max()方法比较后，minCapacity 为10。
         
         3. ensureExplicitCapacity() 方法
         如果调用 ensureCapacityInternal() 方法就一定会进过（执行）这个方法，下面我们来研究一下这个方法的源码！
         
           //判断是否需要扩容
             private void ensureExplicitCapacity(int minCapacity) {
                 modCount++;
         
                 // overflow-conscious code
                 if (minCapacity - elementData.length > 0)
                     //调用grow方法进行扩容，调用此方法代表已经开始扩容了
                     grow(minCapacity);
             }
         我们来仔细分析一下：
         
         当我们要 add 进第1个元素到 ArrayList 时，elementData.length 为0 （因为还是一个空的 list），因为执行了 ensureCapacityInternal() 方法 ，所以 minCapacity 此时为10。此时，minCapacity - elementData.length > 0 成立，所以会进入 grow(minCapacity) 方法。
         当add第2个元素时，minCapacity 为2，此时e lementData.length(容量)在添加第一个元素后扩容成 10 了。此时，minCapacity - elementData.length > 0 不成立，所以不会进入 （执行）grow(minCapacity) 方法。
         添加第3、4···到第10个元素时，依然不会执行grow方法，数组容量都为10。
         直到添加第11个元素，minCapacity(为11)比elementData.length（为10）要大。进入grow方法进行扩容。
         
         4. grow() 方法
             /**
              * 要分配的最大数组大小
              */
             private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
         
             /**
              * ArrayList扩容的核心方法。
              */
             private void grow(int minCapacity) {
                 // oldCapacity为旧容量，newCapacity为新容量
                 int oldCapacity = elementData.length;
                 //将oldCapacity 右移一位，其效果相当于oldCapacity /2，
                 //我们知道位运算的速度远远快于整除运算，整句运算式的结果就是将新容量更新为旧容量的1.5倍，
                 int newCapacity = oldCapacity + (oldCapacity >> 1);
                 //然后检查新容量是否大于最小需要容量，若还是小于最小需要容量，那么就把最小需要容量当作数组的新容量，
                 if (newCapacity - minCapacity < 0)
                     newCapacity = minCapacity;
                // 如果新容量大于 MAX_ARRAY_SIZE,进入(执行) `hugeCapacity()` 方法来比较 minCapacity 和 MAX_ARRAY_SIZE，
                //如果minCapacity大于最大容量，则新容量则为`Integer.MAX_VALUE`，否则，新容量大小则为 MAX_ARRAY_SIZE 即为 `Integer.MAX_VALUE - 8`。
                 if (newCapacity - MAX_ARRAY_SIZE > 0)
                     newCapacity = hugeCapacity(minCapacity);
                 // minCapacity is usually close to size, so this is a win:
                 elementData = Arrays.copyOf(elementData, newCapacity);
             }
             
             我们再来通过例子探究一下grow() 方法 ：
             
             当add第1个元素时，oldCapacity 为0，经比较后第一个if判断成立，newCapacity = minCapacity(为10)。但是第二个if判断不会成立，即newCapacity 不比 MAX_ARRAY_SIZE大，则不会进入 hugeCapacity 方法。数组容量为10，add方法中 return true,size增为1。
             当add第11个元素进入grow方法时，newCapacity为15，比minCapacity（为11）大，第一个if判断不成立。新容量没有大于数组最大size，不会进入hugeCapacity方法。数组容量扩为15，add方法中return true,size增为11。
             以此类推······
             这里补充一点比较重要，但是容易被忽视掉的知识点：
             
             java 中的 length 属性是针对数组说的,比如说你声明了一个数组,想知道这个数组的长度则用到了 length 这个属性.
             java 中的 length() 方法是针对字符串说的,如果想看这个字符串的长度则用到 length() 这个方法.
             java 中的 size() 方法是针对泛型集合说的,如果想看这个泛型有多少个元素,就调用此方法来查看!
             
             5. hugeCapacity() 方法。
             从上面 grow() 方法源码我们知道： 如果新容量大于 MAX_ARRAY_SIZE,进入(执行) hugeCapacity() 方法来比较 minCapacity 和 MAX_ARRAY_SIZE，如果minCapacity大于最大容量，则新容量则为Integer.MAX_VALUE，否则，新容量大小则为 MAX_ARRAY_SIZE 即为 Integer.MAX_VALUE - 8。
             
                 private static int hugeCapacity(int minCapacity) {
                     if (minCapacity < 0) // overflow
                         throw new OutOfMemoryError();
                     //对minCapacity和MAX_ARRAY_SIZE进行比较
                     //若minCapacity大，将Integer.MAX_VALUE作为新数组的大小
                     //若MAX_ARRAY_SIZE大，将MAX_ARRAY_SIZE作为新数组的大小
                     //MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
                     return (minCapacity > MAX_ARRAY_SIZE) ?
                         Integer.MAX_VALUE :
                         MAX_ARRAY_SIZE;
                 }
   三、System.arraycopy() 和 Arrays.copyOf()方法
   
            1、System.arraycopy() 方法
                  /**
                   * 在此列表中的指定位置插入指定的元素。 
                   *先调用 rangeCheckForAdd 对index进行界限检查；然后调用 ensureCapacityInternal 方法保证capacity足够大；
                   *再将从index开始之后的所有成员后移一个位置；将element插入index位置；最后size加1。
                   */
                  public void add(int index, E element) {
                      rangeCheckForAdd(index);
              
                      ensureCapacityInternal(size + 1);  // Increments modCount!!
                      //arraycopy()方法实现数组自己复制自己
                      //elementData:源数组;index:源数组中的起始位置;elementData：目标数组；index + 1：目标数组中的起始位置； size - index：要复制的数组元素的数量；
                      System.arraycopy(elementData, index, elementData, index + 1, size - index);
                      elementData[index] = element;
                      size++;
                  }
            2、 Arrays.copyOf()方法
                /**
                  以正确的顺序返回一个包含此列表中所有元素的数组（从第一个到最后一个元素）; 返回的数组的运行时类型是指定数组的运行时类型。 
                  */
                 public Object[] toArray() {
                 //elementData：要复制的数组；size：要复制的长度
                     return Arrays.copyOf(elementData, size);
                 }
   四、ensureCapacity方法
   
            ArrayList 源码中有一个 ensureCapacity 方法不知道大家注意到没有，这个方法 ArrayList 内部没有被调用过，所以很显然是提供给用户调用的，那么这个方法有什么作用呢？
            
                /**
                如有必要，增加此 ArrayList 实例的容量，以确保它至少可以容纳由minimum capacity参数指定的元素数。
                 *
                 * @param   minCapacity   所需的最小容量
                 */
                public void ensureCapacity(int minCapacity) {
                    int minExpand = (elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA)
                        // any size if not default element table
                        ? 0
                        // larger than default for default empty table. It's already
                        // supposed to be at default size.
                        : DEFAULT_CAPACITY;
            
                    if (minCapacity > minExpand) {
                        ensureExplicitCapacity(minCapacity);
                    }
                }
            最好在 add 大量元素之前用 ensureCapacity 方法，以减少增量重新分配的次数
            
            我们通过下面的代码实际测试以下这个方法的效果：
            
            public class EnsureCapacityTest {
            	public static void main(String[] args) {
            		ArrayList<Object> list = new ArrayList<Object>();
            		final int N = 10000000;
            		long startTime = System.currentTimeMillis();
            		for (int i = 0; i < N; i++) {
            			list.add(i);
            		}
            		long endTime = System.currentTimeMillis();
            		System.out.println("使用ensureCapacity方法前："+(endTime - startTime));
            
            		list = new ArrayList<Object>();
            		long startTime1 = System.currentTimeMillis();
            		list.ensureCapacity(N);
            		for (int i = 0; i < N; i++) {
            			list.add(i);
            		}
            		long endTime1 = System.currentTimeMillis();
            		System.out.println("使用ensureCapacity方法后："+(endTime1 - startTime1));
            	}
            }
            运行结果：
            
            使用ensureCapacity方法前：4637
            使用ensureCapacity方法后：241
            通过运行结果，我们可以很明显的看出向 ArrayList 添加大量元素之前最好先使用ensureCapacity 方法，以减少增量重新分配的次数