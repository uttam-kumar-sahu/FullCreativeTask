package utils;

import io.restassured.response.Response;
import java.util.Map;
import java.util.HashMap;

public class ScenarioContext {
    public Response response;
    public Map<String, Object> vars = new HashMap<String, Object>();
}

