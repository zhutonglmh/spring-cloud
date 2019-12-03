package studyserverone.TCP_UDP.tcp.socket.demo1;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author zhutong
 * @date 2019/12/2 17:14
 */
public class ScoketProducer {
    public static void main(String[] args) throws Exception{
        //要先开启服务端
        Socket socket = new Socket("127.0.0.1",9090);

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("客户端启动成功！！");
                while (true){
                    try {
                        socket.getOutputStream().write("hello word".getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                    
              
            }
        }).start();
    }
}
