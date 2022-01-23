package com.ezypay.backendapi;

import org.junit.jupiter.api.Test;
import io.restassured.RestAssured.*;
import io.restassured.matcher.RestAssuredMatchers.*;
import org.hamcrest.Matchers.*;

public class SampleTest {

	@Test
	public void return_200_with_expected_inputs() {
		get("/subscription/create?totalAmount=10&subscriptionType=weekly&typeOfDay=THURSDAY&startDate=01/03/2022&endDate=01/05/2022")
		.then()
		.statusCode(200)
		.assertThat()
		.body("amount",equalTo(10))
		.body("subscriptionType",equalTo("WEEKLY"))
		.log().all();
	}

}
