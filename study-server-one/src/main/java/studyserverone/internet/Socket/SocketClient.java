package studyserverone.internet.Socket;

import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author zhutong
 * @date 2019/6/25 16:44
 */
public class SocketClient {
    
    public static void main(String[] args) throws Exception{
    
        
        Socket socket = new Socket("localhost",8189);
        OutputStream outputStream = socket.getOutputStream();
        Scanner in = new Scanner(socket.getInputStream(),"UTF-8");
        while (in.hasNextLine()){
            String line = in.nextLine();
            System.out.println(line);
            System.out.println("please Input:");
            int b;
            while ((b = System.in.read()) != -1) {
                outputStream.write(b);
                outputStream.flush();
            }
        }
     
    }
}
