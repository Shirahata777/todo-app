import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.client.WebTarget;

import org.junit.jupiter.api.Test;

import io.helidon.microprofile.tests.junit5.HelidonTest;

@HelidonTest
class postTest {
	@Inject
	WebTarget webTarget;

	@Test
	void testDefaultGreeting() {
		JsonObject jsonObject = webTarget.path("/api/save").request().get(JsonObject.class);

		String expected = "Hello World!";
		String actual = jsonObject.getString("message");
		assertEquals(expected, actual, "Message in JSON");
	}
}