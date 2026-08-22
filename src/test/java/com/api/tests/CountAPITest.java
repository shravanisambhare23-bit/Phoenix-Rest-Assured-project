package com.api.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import com.api.constants.Role;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import io.restassured.module.jsv.JsonSchemaValidator;
import static io.restassured.RestAssured.*;

public class CountAPITest {
	@Test
	public void verifyCountApiResponse() {
given()
	.baseUri(ConfigManager.getProperty("BASE_URI"))
	.and()
	.header("Authorization", AuthTokenProvider.getToken(Role.FD))
	.log().uri()
	.log().method()
	.log().headers()
	.when()
	.get("/dashboard/count")
	.then()
	.log().all()
	
	.statusCode(200)
	.body("message", Matchers.matchesPattern("Success"))
	.time(Matchers.lessThan(1000L))
	.body("data",Matchers.notNullValue())
	.body("data.size()", Matchers.equalTo(3))
	.body("data.count", Matchers.everyItem(Matchers.greaterThanOrEqualTo(0)))
    .body("data.label",Matchers.everyItem(Matchers.not(Matchers.blankOrNullString())))
    .body("data.key", Matchers.containsInAnyOrder("pending_fst_assignment","created_today","pending_for_delivery"))
.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schema/CountAPIResponseSchema-FD.json"));


	}
public void CountAPITest_MissingAuthToken() {
	given()
	.baseUri(ConfigManager.getProperty("BASE_URI"))
	.and()
	.log().uri()
	.log().method()
	.log().headers()
	.when()
	.get("/dashboard/count")
	.then()
	.log().all()
	.statusCode(401);
}
}
