package com.himanshu.springpractice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.himanshu.springpractice.domain.User;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class SpringpracticeApplicationTests {

	@LocalServerPort
	private int generatedPort;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testGetHandling() throws Exception {
		String urlToTest = "http://localhost:" + generatedPort + "/sayHello";
		assertEquals("Hello World", restTemplate.getForObject(urlToTest, String.class));
	}

	@Test
	public void testPostHandling() throws Exception {
		String urlToTest = "http://localhost:" + generatedPort + "/setData";
		User requestObject = new User(123l, "Himanshu");
		String expectedResponse = "User Information : " + requestObject.getUserId() + "::" + requestObject.getUserName();
		assertEquals(expectedResponse, restTemplate.postForObject(urlToTest, requestObject, String.class));
	}

}
