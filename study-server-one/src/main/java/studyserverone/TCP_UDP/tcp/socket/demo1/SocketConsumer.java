package studyserverone.TCP_UDP.tcp.socket.demo1;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author zhutong
 * @date 2019/12/2 17:14
 */
public class SocketConsumer {
    
    
    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket = new ServerSocket(9090);
        System.out.println("服务器启动成功，端口：" + 9090);
        //accept() 是一个阻塞的方法
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Socket socket = serverSocket.accept();
                    InputStream inputStream = socket.getInputStream();
                    while (true){
                        byte[] data = new byte[200];
                        int len;
                        while ((len = inputStream.read(data)) != -1){
                            String message = new String(data,0,len);
                            System.out.println("客户端传来消息：" + message);
//                            socket.getOutputStream().write(data);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
     }
}
