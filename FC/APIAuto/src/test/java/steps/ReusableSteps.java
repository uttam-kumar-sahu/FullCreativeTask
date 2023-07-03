package steps;

import io.cucumber.java.en.Then;
import utils.ScenarioContext;
import utils.Utils;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isOneOf;

public class ReusableSteps extends BaseStepDef {

    public ReusableSteps(ScenarioContext context) {
        super(context);
    }

    @Then("^the operation should be successful$")
    public void the_operation_should_be_successful() {
        context.response.then().statusCode(isOneOf(200, 201));
    }

    @Then("^the content must be deleted with 204 status code$")
    public void the_content_must_be_deleted() {
        context.response.then().statusCode(isOneOf(204));
    }

    @Then("^should include the following values$")
    public void should_include_the_following_values(Map<String, String> responseFields) {
        Utils.assertResponseJsonFields(context.response, responseFields);
    }

    @Then("^I store the \"([^\"]*)\" of the response as \"([^\"]*)\"$")
    public void i_store_the_x_of_the_response(String item, String name) {
        Integer responseCheck = context.response.jsonPath().get(item);
        context.vars.put(name, responseCheck);
    }


    @Then("^The \"([^\"]*)\" of the response as string should be equal to the stored \"([^\"]*)\"$")
    public void theXOftheResponseasStringShouldBeEqualToTheStoredY(String path, String key) {
        final Integer responseCheck = context.response.jsonPath().get(path);
        final Integer stored = (Integer) context.vars.get(key);
        assertThat(responseCheck, is(stored));
    }
}
