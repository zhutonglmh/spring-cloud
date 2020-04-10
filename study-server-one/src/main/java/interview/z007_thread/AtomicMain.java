package interview.z007_thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhutong
 * @date 2020/1/14 16:11
 */
public class AtomicMain {
    
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(1);
        final int[] ik = {1};
        
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(50,100,4000, TimeUnit.HOURS,new LinkedBlockingQueue());
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    int j = ik[0] + 1;
                    ik[0] = j;
                    System.out.println(ik[0]);
                }
            }
        });
        
    }
}
