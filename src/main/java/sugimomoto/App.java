package sugimomoto;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;

import com.sun.net.httpserver.HttpServer;
import sugimomoto.withings4j.*;
import sugimomoto.withings4j.model.Scope;


import java.awt.Desktop;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {

        Desktop desktop = Desktop.getDesktop();
        desktop.browse(new URI("https://www.google.com/?hl=ja"));

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

