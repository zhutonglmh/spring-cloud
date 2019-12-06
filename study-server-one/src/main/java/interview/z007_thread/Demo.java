package interview.z007_thread;

import java.util.concurrent.locks.LockSupport;

/**
 * @author zhutong
 * @date 2019/12/3 14:52
 */
public class Demo {
    
    public static void main(String[] args) throws Exception {

        /**
         * 线程的六种状态
         *
         * 1、new    初始状态，线程被构建，但是还没有调用start()方法
         *
         * 2、running 运行状态，Java 线程将操作系统中的就绪和运行两种状态笼统的称作"运行中"
         *
         * 3、blocked  阻塞状态，标识线程阻塞于锁
         *
         * 4、waiting  等待状态，表示线程进入等待状态，进入该状态标识当前线程需要等待其他线程去做出一些特定动作(通知或中断)
         *
         * 5、time_waiting 等待超时状态，该状态不同于waiting，它是可以在指定的时间后自行返回的
         *
         * 6、terminated  终止状态，标识当前线程已经执行完毕。
         * */
    
    
        //https://github.com/Snailclimb/JavaGuid                                                                                                                                                                                                                                                                                                    e/blob/master/docs/java/Java%E5%9F%BA%E7%A1%80%E7%9F%A5%E8%AF%86.md
        //运行 -----就绪   ready() 放弃cup片区，然后根据优先级重新竞争
        Thread.yield();
        //运行 ------等待
        Thread.currentThread().wait();   //释放锁,  不带超时时间只能被环境 notify notiyfall，唤醒了不一定立即执行，需要先拿锁，必须有对象级的锁，synchronized 方法或者 synchronized 代码块     
        Thread.currentThread().join();   //释放锁   不带超时时间只能等join线程执行完才能执行，，如果有锁的情况下唤醒了不一定立即执行，需要先拿锁  实际上是调用调用join方法的对象的wait方法
        LockSupport.park();              //不释放锁  只能等待LockSupport.unpark()才能执行，唤醒了一定去执行
        //等待 ------ 运行
    
        //每个对象都有一个锁池   和一个等待池    锁池可以争抢锁、但是等待池不行  等待池只能先被唤醒/超时才能进入锁池去争抢锁
        //假如两个线程需要同一把锁  则没获取到的进入锁池等待
        //如果一个线程拿到了锁，因为各种原因调用了锁对象的wait方法，则该线程它会进入等待池
        //notify  根据一定算法 从等到池中选取一个线程放到锁池
        //notifyAll 将所有等待池中的线程都放入到 锁池中，并争抢锁
        new Object().notify();  //有一定算法，看上去不是随机的从等待池拿一个到锁池  https://blog.csdn.net/liuzhixiong_521/article/details/86677057
        new Object().notifyAll();
        LockSupport.unpark(Thread.currentThread()); //释放许可
    
        //运行 ----- 超时等待，它是可以在指定时间后自行返回的
        Thread.sleep(5000);     //不释放锁
        Thread.currentThread().join(2000);  //释放锁
        Thread.currentThread().wait(5000); //释放锁
        LockSupport.parkNanos(5000);                //等待  不释放锁  入参是相对时间，即再过多少多少毫秒
        LockSupport.parkUntil(5000);     //等待 不释放锁    入参是绝对时间，即相当于可以定时...CaoTm
    
        //超时等待 -----运行
        Thread.currentThread().notify();
        Thread.currentThread().notifyAll();
        LockSupport.unpark(Thread.currentThread());
    
        //运行  ------ 阻塞
        //等待进入synchronized 方法
        //等待进入 synchronized 块
    
        //阻塞 ---- 运行
        //获取到锁
    
        //运行 ----- 终止
        //执行完成
    }
}
