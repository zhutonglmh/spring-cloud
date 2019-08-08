package studyserverone.internet.Socket;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author zhutong
 * @date 2019/6/25 16:55
 */
public class SocketServer {
    
    public static void main(String[] args) throws Exception {
        
        //用于建立一个负责监控端口8189 的服务器
        ServerSocket socketServer = new ServerSocket(8189);
        
        //用于告诉程序不停的等待，直到有客户端连接到这个端口、一旦有人通过网络发送了正确的连接请求，并以此连接到这个端口上，该方法就会
        //返回一个表示链接已建立的socket对象，你可以使用这个对象来得到输入流和输出流
        Socket socket = socketServer.accept();
        InputStream inputStream = socket.getInputStream(); //客户端的输出都会成为服务端的输入
        OutputStream outputStream = socket.getOutputStream();//服务器的输出都会成为客户端的输入
        Scanner scanner = new Scanner(inputStream, "UTF-8");
        PrintWriter out = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"),true);
        out.println("hello! Enter bey to exit");
        
        boolean done = false;
        
        while(!done && scanner.hasNextLine()){
            String line = scanner.nextLine();
            System.out.println("Echo : " + line);
            if (line.trim().equals("BEY")) {
                done = true;
            }
        }
        System.out.println("我来证明它有没有在一直跑");
    
        
    }
}
