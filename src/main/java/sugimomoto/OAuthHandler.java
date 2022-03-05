package sugimomoto;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;


public class OAuthHandler implements HttpHandler {

    private String code;

    private Authorization authorization;

    public OAuthHandler() {
        this.authorization = new Authorization();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        String response =  "Success!";

        Map<String, String> params = Util.getParamMap(exchange.getRequestURI().getQuery());

        authorization.setCode(params.get("code").toString());
        authorization.setState(params.get("state").toString());

        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    public Authorization getAuthorization() {
        return authorization;
    }

    public void setAuthorization(Authorization authorization) {
        this.authorization = authorization;
    }

}
