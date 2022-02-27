package sugimomoto;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException, InterruptedException
    {
        System.out.println( "Hello World!" );
        HttpServer server = HttpServer.create(new InetSocketAddress(8000),0);
        server.createContext("/",new MyHandler());
        System.out.println("MyServer wakes up: port=8000");
        server.start();

        Thread.sleep(10000);

        server.stop(0);
        System.out.println("MyServer was stopped.");
    }
}

