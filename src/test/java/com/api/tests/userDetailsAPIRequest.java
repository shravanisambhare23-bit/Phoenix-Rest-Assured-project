package com.api.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.utils.ConfigManager;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;

import java.io.IOException;

public class userDetailsAPIRequest {

	@Test
	public void userdetails() throws IOException {
		//ConfigManager2 configmanager = new ConfigManager2();
		Header authHeader = new Header("Authorization","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6NCwiZmlyc3RfbmFtZSI6ImZkIiwibGFzdF9uYW1lIjoiZmQiLCJsb2dpbl9pZCI6ImlhbWZkIiwibW9iaWxlX251bWJlciI6Ijg4OTk3NzY2NTUiLCJlbWFpbF9pZCI6Im1hcmtAZ21haWwuY29tIiwicGFzc3dvcmQiOiI1ZjRkY2MzYjVhYTc2NWQ2MWQ4MzI3ZGViODgyY2Y5OSIsInJlc2V0X3Bhc3N3b3JkX2RhdGUiOm51bGwsImxvY2tfc3RhdHVzIjowLCJpc19hY3RpdmUiOjEsIm1zdF9yb2xlX2lkIjo1LCJtc3Rfc2VydmljZV9sb2NhdGlvbl9pZCI6MSwiY3JlYXRlZF9hdCI6IjIwMjEtMTEtMDNUMDg6MDY6MjMuMDAwWiIsIm1vZGlmaWVkX2F0IjoiMjAyMS0xMS0wM1QwODowNjoyMy4wMDBaIiwicm9sZV9uYW1lIjoiRnJvbnREZXNrIiwic2VydmljZV9sb2NhdGlvbiI6IlNlcnZpY2UgQ2VudGVyIEEiLCJpYXQiOjE3ODM1ODc4Nzl9.EM84Kj7I7Fic9-3rVGkM-ia86B3j_gc2LLz6PkcFWSU");
	 given()
	 .baseUri(ConfigManager.getProperty("BASR_URI"))
	 .and()
	 .header(authHeader)
	 .accept(ContentType.JSON)
	 .when()
	 .get("userdetails")
	 
	 .then()
	 .log().all()
	 .statusCode(200)
	 .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schema/userDetailsSchema.json"))
	 .time(Matchers.lessThan(1500L));
	 
	 
	
	 
	}
}
