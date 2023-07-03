package steps;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
//import io.cucumber.messages.types.DataTable;
import utils.ScenarioContext;
import io.cucumber.datatable.DataTable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static endpoints.UserManipulationEndpoints.*;


public class UserManipulationStepDefs extends BaseStepDef {
    public UserManipulationStepDefs(ScenarioContext context) {
        super(context);
    }

    Map<String, String> headerparams = new HashMap<String, String>();

    @When("^I try to create a repository with the following data and headers")
    public void i_try_to_create_a_repo(String json) {
        context.response = createRepository(json, headerparams);
        context.response.jsonPath().prettyPrint();
    }

    @Given("I stored the headers")
    public void iStoredTheHeaders(DataTable h) throws Throwable {
        List<Map<String, String>> header = h.asMaps(String.class, String.class);
        for(Map<String, String> an: header) {

            for (Map.Entry<String, String> entry : an.entrySet()) {
                headerparams.put(entry.getKey(), entry.getValue().toString());
                System.out.println(entry.getKey() + ":" + entry.getValue().toString());
            }
        }

    }

    @When("I try to get the repository with name {string} and Owner {string}")
    public void ITryToGetTheRepositoryWithNameAndOwner(String repo, String owner) {
        context.response = getRepository(owner, repo, headerparams);
        context.response.jsonPath().prettyPrint();
    }

    @When("I try to delete the repository with name {string} and Owner {string}")
    public void ITryToDeleteTheRepositoryWithNameAndOwner(String repo, String owner) {
        context.response = deleteRepository(owner, repo, headerparams);
        //context.response.jsonPath().prettyPrint();
    }

    @When("I create an issue with repo name {string} and Owner {string} and the following body")
    public void iCreateAnIssueWithRepoNameAndOwnerAndTheFollowingBody(String repo, String owner, String json) {
        context.response = createIssue(owner, repo, headerparams,json);
        context.response.jsonPath().prettyPrint();
    }

    @When("I try to update an Issue with repo name {string} , Owner {string}, issue number {int} and the following body")
    public void iTryToUpdateAnIssueWithRepoNameOwnerIssueNumberAndTheFollowingBody(String repo, String owner, int issue_number, String json) {
        context.response = updateIssue(owner, repo, headerparams,issue_number, json);
        context.response.jsonPath().prettyPrint();
    }
}
