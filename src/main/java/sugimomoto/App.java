package sugimomoto;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;

import com.sun.net.httpserver.HttpServer;
import sugimomoto.withings4j.*;
import sugimomoto.withings4j.model.AccessToken;
import sugimomoto.withings4j.model.AccessTokenBase;
import sugimomoto.withings4j.model.Scope;
import sugimomoto.withings4j.model.SleepBase;
import sugimomoto.withings4j.query.SleepGetQueryParameters;

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

        Scope[] scopes = {Scope.USER_ACTIVITY, Scope.USER_METRICS};
        WithingsOAuthClient oauth = new WithingsOAuthClient(clientId, clientSecret, "http://localhost:33333", scopes);

        Desktop desktop = Desktop.getDesktop();
        desktop.browse(new URI(oauth.getAuthorizationUrl("12345")));

        OAuthHandler handler = new OAuthHandler();

        System.out.println( "Hello World!" );
        HttpServer server = HttpServer.create(new InetSocketAddress(33333),0);
        server.createContext("/", handler);
        System.out.println("MyServer wakes up: port=33333");
        server.start();

        Thread.sleep(10000);

        AccessTokenBase accessTokenBase = oauth.getAccessToken(handler.getAuthorization().getCode());

        WithingsAPIClient client = new WithingsAPIClient(accessTokenBase.getBody());

        SleepGetQueryParameters param = new SleepGetQueryParameters();
        param.setStartDate(1641740400);
        param.setEndDate(1641826800);
        param.setDataFileds("hr,rr");


        SleepBase sleepBase =  client.sleepGet(param);

        System.out.println(sleepBase.getBody().getSeries().size());
        System.out.println(sleepBase.getBody().getModel());

        server.stop(0);

    }
}

