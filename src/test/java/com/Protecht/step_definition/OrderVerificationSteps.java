package com.Protecht.step_definition;

import com.Protecht.utilities.Authenticator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;
import java.util.List;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class OrderVerificationSteps {
    String token;
    Response response;
    @Given("the Authentication API is available")
    public void the_authentication_api_is_available() {
        token = Authenticator.getToken();
        Assert.assertNotNull("Token was null", token);
        Assert.assertNotEquals("Token was empty", "",token);

    }
    @When("I retrieve an order with oder number {string}")
    public void i_retrieve_an_order_with_oder_number(String orderNumber) {
        String baseUrl = "https://connect-sandbox.ticketguardian.net/api/v2/orders/";
        String finalUrl = baseUrl + orderNumber;

        response = given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .when()
                .get(finalUrl)
                .prettyPeek();


    }
    @Then("I should receive a valid order response")
    public void i_should_receive_a_valid_order_response() {
        response.then()
                .statusCode(200);

    }
    @Then("the sum of item costs should equal the order subtotal")
    public void the_sum_of_item_costs_should_equal_the_order_subtotal() {
        List<Map<String, Float>> items = response.jsonPath().getList("items");
        float sumOfItemCosts = 0;
        for (Map<String, Float> item : items) {
            sumOfItemCosts += item.get("cost");
        }
        float subtotal = response.jsonPath().getFloat("subtotal");
        assertEquals("The sum of item costs does not equal the order subtotal.", subtotal, sumOfItemCosts, 0.0);

    }
    @Then("the policy_numbers for each policy should follow the format {string}")
    public void the_policy_numbers_for_each_policy_should_follow_the_format(String string) {
        List<Map<String, String>> policies = response.jsonPath().getList("policies");
        for (Map<String, String> policy : policies) {
            String policyNumber = policy.get("policy_number");
            assertTrue("Policy number " + policyNumber + " does not follow the expected format.",
                    policyNumber.matches("^TGFS-.+$"));

        }

    }
    @Then("each item should include a customer object that matches the top-level customer object for the order")
    public void each_item_should_include_a_customer_object_that_matches_the_top_level_customer_object_for_the_order() {
        Map<String, String> customerObj = response.jsonPath().getMap("customer");
        List<Map<String, Map<String, String>>> items = response.jsonPath().getList("items");

        for (Map<String, Map<String, String>> item : items) {
            Map<String, String> itemCustomer = item.get("customer");
            assertEquals("Customer ID does not match.", customerObj.get("id"), itemCustomer.get("id"));
            assertEquals("Customer email does not match.", customerObj.get("email"), itemCustomer.get("email"));
            assertEquals("Customer first name does not match.", customerObj.get("first_name"), itemCustomer.get("first_name"));
            assertEquals("Customer last name does not match.", customerObj.get("last_name"), itemCustomer.get("last_name"));
        }

    }
    @Then("each item should have an associated policy under the policies array")
    public void each_item_should_have_an_associated_policy_under_the_policies_array() {
        List<Map<String, Object>> items = response.jsonPath().getList("items");
        List<Map<String, Object>> policies = response.jsonPath().getList("policies");

        for (Map<String, Object> item : items) {
            boolean policyFound = false;
            String itemId = (String) item.get("id");

            for (Map<String, Object> policy : policies) {
                Map<String, String> policyItem = (Map<String, String>) policy.get("item");
                if (policyItem != null && policyItem.get("id").equals(itemId)) {
                    policyFound = true;
                    break;
                }
            }

            assertTrue("No associated policy found for item with ID " + itemId, policyFound);
        }
    }
}
