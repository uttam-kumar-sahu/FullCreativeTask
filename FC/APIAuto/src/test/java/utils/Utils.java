package utils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;

public class Utils {

    public static void assertResponseJsonFields(Response response, Map<String, String> responseFields) {
        JsonPath jsonPath = response.then().extract().jsonPath();
        assertFields(jsonPath, responseFields);
    }

    private static void assertFields(JsonPath jsonPath, Map<String, String> responseFields) {
        responseFields.entrySet().forEach((field) -> {
            String fieldValue = (field.getValue());
            String fieldKey = field.getKey();
            if (StringUtils.isNumeric(fieldValue)) {
                assertThat(jsonPath.get(fieldKey).toString(), is(fieldValue));
            } else if (fieldValue.equals("true") || fieldValue.equals("false")) {
                assertThat(jsonPath.get(fieldKey), is(Boolean.parseBoolean(fieldValue)));
            } else if (fieldValue.equals("null")) {
                assertThat(jsonPath.get(fieldKey), isEmptyOrNullString());
            } else {
                assertThat(jsonPath.get(fieldKey), is(fieldValue));
            }
        });
    }
}
