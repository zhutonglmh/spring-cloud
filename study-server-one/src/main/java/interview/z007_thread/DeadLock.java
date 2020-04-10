package interview.z007_thread;

/**
 * @author zhutong
 * @date 2020/1/3 14:53
 */
public class DeadLock {
    
    private static volatile String  ss = new String("sss");
    private static String ss1 = new String("sss");
    public static void main(String[] args) throws InterruptedException {
    
        for (int i = 0; i < 100; i++) {
           new Thread( new Runnable() {
               /**
                * When an object implementing interface <code>Runnable</code> is used
                * to create a thread, starting the thread causes the object's
                * <code>run</code> method to be called in that separately executing
                * thread.
                * <p>
                * The general contract of the method <code>run</code> is that it may
                * take any action whatsoever.
                *
                * @see Thread#run()
                */
               @Override
               public void run() {
                   synchronized (ss){
    
                       try {
                           Thread.currentThread().sleep(1000);
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
                       synchronized (ss1){
                           System.out.println("haha11");
                       }
                   }
        
                   synchronized (ss1){
                       try {
                           Thread.currentThread().sleep(1000);
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
                       synchronized (ss){
                           System.out.println("haha11");
                       }
                   }
               }
           }){}.start();
        }
    }
       
}
