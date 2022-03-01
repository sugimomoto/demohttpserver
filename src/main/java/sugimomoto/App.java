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

        PropertyFileManager property = new PropertyFileManager("oauth.properties");
        String clientId = property.GetPropertyValue("system.oauth.clientid");
        String clientSecret = property.GetPropertyValue("system.oauth.clientsecret");

        String url = "https://account.withings.com/oauth2_user/authorize2?response_type=code&client_id=" + clientId + "&state=12345&scope=user.metrics,user.activity&redirect_uri=http://localhost:33333";

    

        Desktop desktop = Desktop.getDesktop();
        desktop.browse(new URI(url));

        System.out.println( "Hello World!" );
        HttpServer server = HttpServer.create(new InetSocketAddress(33333),0);
        server.createContext("/",new MyHandler());
        System.out.println("MyServer wakes up: port=8000");
        server.start();

        Thread.sleep(10000);

        server.stop(0);
        System.out.println("MyServer was stopped.");
    }
}

