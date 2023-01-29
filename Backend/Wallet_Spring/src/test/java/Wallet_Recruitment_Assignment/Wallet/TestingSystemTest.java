package Wallet_Recruitment_Assignment.Wallet;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import Wallet_Recruitment_Assignment.Wallet.model.Wallet;
import io.restassured.RestAssured;
import io.restassured.response.Response;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)

@RunWith(SpringRunner.class)
public class TestingSystemTest {

	int port = 8080;

	@Before
	public void setUp() {
		RestAssured.port = port;
		RestAssured.baseURI = "http://localhost";
		RestAssured.given()
				.contentType("application/json")
				.body(new Wallet("test", "test"))
				.when()
				.post("/wallet/register");
	}
	
	@Test
	public void walletTest(){		
		/********GET Test********/
		//GET balance
		Response get_balance_1 = RestAssured.given()
				.contentType("application/json")
				.param("username", "test")
				.when()
				.get("/wallet/balance");
		double returnValue = Double.parseDouble(get_balance_1.getBody().asString());
		assertEquals(0d, returnValue);
		
		/********PUT Test - different scenarios********/
		// PUT - add amount
		Response put_response_1 = RestAssured.given()
				.contentType("application/json")
				.pathParam("amount", 100)
				.param("username", "test")
				.when()
				.put("/wallet/add/{amount}");

		// Check status code
		int put_statusCode = put_response_1.getStatusCode();
		assertEquals(200, put_statusCode);
		
		//GET balance
		Response get_balance_2 = RestAssured.given()
				.contentType("application/json")
				.param("username", "test")
				.when()
				.get("/wallet/balance");
		returnValue = Double.parseDouble(get_balance_2.getBody().asString());
		assertEquals(100d, returnValue);

		// PUT - withdraw amount
		Response put_response_2 = RestAssured.given()
			.contentType("application/json")
			.pathParam("amount", 25)
			.param("username", "test")
			.when()
			.put("/wallet/withdraw/{amount}");

		// Check status code
		put_statusCode = put_response_2.getStatusCode();
		assertEquals(200, put_statusCode);
		
		//GET balance
		Response get_balance_3 = RestAssured.given()
			.contentType("application/json")
			.param("username", "test")
			.when()
			.get("/wallet/balance");
		returnValue = Double.parseDouble(get_balance_3.getBody().asString());
		assertEquals(75, returnValue);
		
		//PUT - add negative amount
				Response put_response_3 = RestAssured.given()
						.contentType("application/json")
						.pathParam("amount", -10)
						.param("username", "test")
						.when()
						.put("/wallet/add/{amount}");

				// Check status code
				put_statusCode = put_response_3.getStatusCode();
				assertEquals(200, put_statusCode);
				
				//GET balance
				Response get_balance_4 = RestAssured.given()
						.contentType("application/json")
						.param("username", "test")
						.when()
						.get("/wallet/balance");
				returnValue = Double.parseDouble(get_balance_4.getBody().asString());
				assertEquals(75d, returnValue);

		//PUT - withdraw amount more than balance
				Response put_response_4 = RestAssured.given()
					.contentType("application/json")
					.pathParam("amount", 1000)
					.param("username", "test")
					.when()
					.put("/wallet/withdraw/{amount}");

				// Check status code
				put_statusCode = put_response_4.getStatusCode();
				assertEquals(200, put_statusCode);
				
				//GET balance
				Response get_balance_5 = RestAssured.given()
					.contentType("application/json")
					.param("username", "test")
					.when()
					.get("/wallet/balance");
				returnValue = Double.parseDouble(get_balance_5.getBody().asString());
				assertEquals(75, returnValue);

		//GET transaction history
				List<String> wallet_history = RestAssured.given()
						.contentType("application/json")
						.param("username", "test")
						.when()
						.get("/wallet/history")
						.jsonPath().getList(".", String.class);
				
				assertEquals(2, wallet_history.size());
				assertEquals("+"+100d, wallet_history.get(0));
				assertEquals("-"+25d, wallet_history.get(1));
				
		/********DELETE Test********/
		// DELETE - Send request and receive response
				Response del_response = RestAssured.given()
						.contentType("application/json")
						.param("username", "test")
						.when()
						.delete("/wallet/delete");
				
				// Check status code
				int del_statusCode = del_response.getStatusCode();
				assertEquals(200, del_statusCode);
	}

}
