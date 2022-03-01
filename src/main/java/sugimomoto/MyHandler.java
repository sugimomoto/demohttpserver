package sugimomoto;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;


public class MyHandler implements HttpHandler {


    /*
    http://localhost:33333/?code=XXXXX&state=12345
    */
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        InputStream is = exchange.getRequestBody();

        String response =  "Your request " + exchange.getRequestURI();

        String authorizationCode = (String)exchange.getAttribute("code");
        String state = (String)exchange.getAttribute("state");

        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
