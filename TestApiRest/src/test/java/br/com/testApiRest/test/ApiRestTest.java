package br.com.testApiRest.test;
import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.containsString;

import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class ApiRestTest {

	@BeforeClass
	public static void setup() {
		RestAssured.baseURI = "http://177.71.114.188";
	}
	
	@Test
	public void successRequestBodyDefaultUri() {
		get("/59")
			.then()
			.body(containsString("cinquenta e nove"));
	}

	@Test
	public void successRequestBodyEnglishUri() {
		get("/en/59")
			.then()
			.body(containsString("fifty nine"));
	}
	
	@Test
	public void contentTypeResponseBody() {
		get("/59")
			.then()
			.contentType(ContentType.JSON);
	}

	@Test
	public void successRequestStatusCode() {
		get("/59")
			.then()
			.statusCode(200);
	}

	@Test
	public void invalidDataRequestBody() {
		get("/abcd")
			.then()
			.body(containsString("Invalid data"));
	}

	@Test
	public void invalidDataRequestStatusCode() {
		get("/abcd")
			.then()
			.statusCode(400);
	}

	@Test
	public void equivalencePartitioningMinimumValueValid() {
		get("/-99999")
			.then()
			.body(containsString("menos noventa e nove mil e novecentos e noventa e nove"));
	}

	@Test
	public void equivalencePartitioningMinimumValueInvalid() {
		get("/-100000")
			.then()
			.body(containsString("Invalid range"));
	}

	@Test
	public void equivalencePartitioningMaximumValueValid() {
		get("/99999")
			.then()
			.body(containsString("noventa e nove mil e novecentos e noventa e nove"));
	}

	@Test
	public void equivalencePartitioningMaximumValueInvalid() {
		get("/100000")
			.then()
			.body(containsString("Invalid range"));
	}

	@Test
	public void equivalencePartitioningStatusCodeInvalid() {
		get("/100000")
			.then()
			.statusCode(400);
	}

}
