package interview.z007_thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhutong
 * @date 2020/1/7 14:41
 */
public class ReenTrantLock {
    
    private static ReentrantLock lock = new ReentrantLock(false);
    private static int count = 0;
    public static void main(String[] args) throws Exception{
    
        Condition empty = lock.newCondition();
        // Executors 返回线程池对象的弊端如下：
        // FixedThreadPool 和 SingleThreadExecutor ： 允许请求的队列长度为 Integer.MAX_VALUE ，可能堆积大量的请求，从而导致OOM。
        // CachedThreadPool 和 ScheduledThreadPool ： 允许创建的线程数量为 Integer.MAX_VALUE ，可能会创建大量线程，从而导致OOM。
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 50; i++) {
            executorService.execute(()->{
                    ++count;
                System.out.println(Thread.currentThread().getName()+":"+count);
            });
        }
        Thread.sleep(2000);
        System.out.println(count);
    }
    
}
