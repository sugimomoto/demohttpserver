package sugimomoto;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;


public class MyHandler implements HttpHandler {

    Authorization auth;

    public MyHandler(Authorization auth) {
        this.auth = auth;
    }

    /*
    http://localhost:33333/?code=XXXXX&state=12345
    */
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        InputStream is = exchange.getRequestBody();

        String response =  "Your request " + exchange.getRequestURI();

        Map<String, String> params = getParamMap(exchange.getRequestURI().getQuery());

        auth.setCode(params.get("code"));
        auth.setState(params.get("state"));

        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private Map<String, String> getParamMap(String query) {

        if(query == null || query.isBlank()){
            return Collections.emptyMap();
        }

        Map<String,String> results = new HashMap<>();

        for(String param : query.split("&")){
            String[] entry = param.split("=");
            results.put(entry[0], entry[1]);
        }

        return results;
    }
}
