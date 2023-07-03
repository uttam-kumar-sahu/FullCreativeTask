package endpoints;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class UserManipulationEndpoints {
    static String dummyTestBaseURI = System.getProperty("sys.demo.dummyBaseURI");
    
    public static Response createRepository(String json, Map<String, String> headers) {
        RequestSpecification req = given().headers(headers);
        if (json != null) {
            req.body(json);
        }
        return req.when().post(dummyTestBaseURI + "/user/repos");
    }

    public static Response getRepository(String owner, String repo, Map<String, String> headers) {
        RequestSpecification req = given().headers(headers);
        return req.pathParam("owner",owner).pathParam("repo",repo).when().get(dummyTestBaseURI + "/repos/{owner}/{repo}");
    }

    public static Response deleteRepository(String owner, String repo, Map<String, String> headers) {
        RequestSpecification req = given().headers(headers);
        return req.pathParam("owner",owner).pathParam("repo",repo).when().delete(dummyTestBaseURI + "/repos/{owner}/{repo}");
    }

    public static Response createIssue(String owner, String repo, Map<String, String> headers, String json) {
        RequestSpecification req = given().headers(headers);
        return req.pathParam("owner",owner).pathParam("repo",repo).body(json).when().post(dummyTestBaseURI + "/repos/{owner}/{repo}/issues");
    }

    public static Response updateIssue(String owner, String repo, Map<String, String> headers,Integer issue_Number, String json) {
        RequestSpecification req = given().headers(headers);
        return req.pathParam("owner",owner).pathParam("repo",repo).pathParam("issue_number",issue_Number).body(json).when().patch(dummyTestBaseURI + "/repos/{owner}/{repo}/issues/{issue_number}");
    }


    public static Response getData() {
        return given().when().get("https://httpstat.us/503");
    }

    public static Response login() {
        Map<String,String> authPayload = new HashMap<String,String>();
        authPayload.put("email", "eve.holt@reqres.in");
        authPayload.put("password", "cityslicka");
        RequestSpecification req = given().contentType("application/json");
        req.body(authPayload);
        return req.when().post(dummyTestBaseURI+ "/api/login");
    }
}
