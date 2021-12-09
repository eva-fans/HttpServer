import java.io.*;
import java.net.*;
import java.util.Map.Entry;
import java.util.regex.*;
public class HttpServer{
    public static final int port=16405;
    public static void main(String[] args) throws IOException{
        ServerSocket serverSocket=new ServerSocket(port);
        while(true)
        {
            Socket socket=serverSocket.accept();
            HttpServerAgent agent=new HttpServerAgent(socket);
            agent.start();
        }
        serverSocket.close();
    }
}
