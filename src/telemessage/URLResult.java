package telemessage;

import java.util.HashMap;
import java.util.Map;

/**
 * Result contains Map of parameters to be used with sending message via URL
 */
public class URLResult extends Result {
    Map<String, String> params = new HashMap<String, String>();

    public URLResult(boolean result) {
        super(result);
    }

    public Map<String, String> getParams() {
        return params;
    }

    void addParam(String name, String value) {
        params.put(name, value);
    }
}
