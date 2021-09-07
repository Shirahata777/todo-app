package com.gethub.shirahata777.api.todo;

import org.json.JSONObject;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.client.WebTarget;
import static io.restassured.RestAssured.given;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestTodoService {

	@Order(1)
	@Inject
	WebTarget webTarget;

	@Test
	void testPosting() {
		JSONObject jsonObject = new JSONObject().put("content", "テスト").put("name", "お名前");

		Response res = given().contentType(ContentType.JSON).contentType("application/json").body(jsonObject.toString())
				.when().post("/v1/todo/save");
		res.then().statusCode(201).extract().as(Response.class); // 指定したクラスにデシリアライズ

		String actual = jsonObject.getString("Save OK!");
		assertEquals(res, actual, "Post Test OK");
//		JsonObject jsonObject = webTarget.path("/v1/todo/save").request().get(JsonObject.class);

	}

}
