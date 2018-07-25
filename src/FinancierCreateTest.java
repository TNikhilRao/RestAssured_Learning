import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * @author Nikhil.Tirmanwar
 *
 */
public class FinancierCreateTest {

	private RestAssured getRestAssured() {
		RestAssured restAssured = new RestAssured();
		restAssured.baseURI = "http://localhost:8090/financier-api/financier";

		return restAssured;
	}

	@Test
	public void createFinancier() throws JSONException {
		RestAssured restAssured = getRestAssured();
		RequestSpecification httpRequest = restAssured.given();

		JSONObject requestParams = new JSONObject();
		requestParams.put("financierId", "32"); // Cast
		requestParams.put("financierName", "RestAssured");

		requestParams.put("financierLocation", "RestAssuredLocation");

		httpRequest.header("Content-Type", "application/json");

		httpRequest.body(requestParams.toString());

		Response response = httpRequest.post("/");

		String responseBody = response.getBody().asString();
		System.out.println("Response Body is =>  " + responseBody);

		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 201);
		String successCode = response.jsonPath().get("SuccessCode");
		// Assert.assertEquals( "Correct Success code was returned", successCode,
		// "OPERATION_SUCCESS");

	}

}
