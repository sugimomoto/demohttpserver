package sugimomoto;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Util {
    
    public static Map<String, String> getParamMap(String query) {

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
