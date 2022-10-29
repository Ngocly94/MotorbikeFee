package stepdifinition;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.ApiUtils;
import common.JsonUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;




public class MotorLiabilityInsuranceFeeApiSteps {
	String url, method, requestBodyJson;
	int actualStatusCode ;
	String actualCode;
	@Given("I have Url and Method and Request body motorbike insurance liability fee api")
	public void i_have_url_and_method_and_request_body_motorbike_insurance_liability_fee_api(List<Map<String, String>> dataRequest) {
		for(Map<String, String> data: dataRequest) {
	    	url = data.get("Url");
	    	System.out.print(url);
	    	method = data.get("Method");
	    	requestBodyJson = data.get("RequestBodyFileName");
	    }   
	}

  @When("I send motorbike insurance liability fee api with {string} and {string} and {string} and {string}")
  public void i_send_motorbike_insurance_liability_fee_api_with_and_and_and(String fieldName1, String fieldName2, String value1, String value2) {
	  HashMap<String, String> fieldNameValues = new HashMap<String, String>();
		fieldNameValues.put(fieldName1, value1);
		fieldNameValues.put(fieldName2, value2);
		HttpResponse<String> response = null;
		JsonUtils jsonUtils = new JsonUtils();
		ApiUtils apiUtils = new ApiUtils();
		String projectPath = System.getProperty("user.dir");
		File sourceFile = new File(projectPath + "/src/main/resources/MotorFeeRequestApi/" + requestBodyJson);
		File destinationFile = new File(
				projectPath + "/src/main/resources/MotorFeeRequestApi/" + "copy" + requestBodyJson);
		System.out.print(destinationFile);
		jsonUtils.copyJsonFile(sourceFile, destinationFile);
		String requestBodyString = jsonUtils.changeValueOfMultiField(fieldNameValues, destinationFile);
		try {
			requestBodyString = Files.readString(destinationFile.toPath());
			response = apiUtils.sendPostRequest(url, requestBodyString);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (response != null) {
			actualStatusCode = response.statusCode();
			actualCode = jsonUtils.getValueByKey(response.body(), "code");
		}

	}

  @Then("I verify the {string} and {string} and {string} and {string} and {string}")
  public void i_verify_the_and_and_and_and(String expectedCode, String expectedStatusCode, String expectedTotalFee, String expectedFee, String expectedVAT) {
	    assertEquals(actualStatusCode, Integer.parseInt(expectedStatusCode));
	    assertEquals(actualCode, expectedCode);
	}

}
