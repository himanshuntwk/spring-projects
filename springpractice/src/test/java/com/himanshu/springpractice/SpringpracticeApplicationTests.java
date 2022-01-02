package com.himanshu.springpractice;

import com.himanshu.springpractice.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class SpringpracticeApplicationTests {

  @LocalServerPort
  private int generatedPort;

  @Autowired
  private TestRestTemplate restTemplate;

  /**
   * Method to test GET requests
   */
  @Test
  public void testGetHandling() {
    String urlToTest = "http://localhost:" + generatedPort + "/getData";
    String expectedResponse = "GET:: User Information : 123::Himanshu";
    assertEquals(expectedResponse, restTemplate.getForObject(urlToTest, String.class));
  }

  /**
   * Method to test POST requests
   */
  @Test
  public void testPostHandling() {
    String urlToTest = "http://localhost:" + generatedPort + "/setData";
    User requestObject = new User(123l, "Himanshu");
    String expectedResponse = "POST:: User Information : " + requestObject.getUserId() + "::" + requestObject.getUserName();
    assertEquals(expectedResponse, restTemplate.postForObject(urlToTest, requestObject, String.class));
  }

  /**
   * Method to test PUT requests
   */
  @Test
  public void testPutHandling() {
    String urlToTest = "http://localhost:" + generatedPort + "/setData";
    User requestObject = new User(123l, "Himanshu");
    String expectedResponse = "PUT:: User Information : " + requestObject.getUserId() + "::" + requestObject.getUserName();
    HttpHeaders headers = new HttpHeaders();
    HttpEntity<User> entity = new HttpEntity<>(requestObject, headers);
    ResponseEntity<String> response = restTemplate.exchange(urlToTest, HttpMethod.PUT, entity, String.class, 123l);
    assertEquals(expectedResponse, response.getBody());
  }

  /**
   * Method to test DELETE requests
   */
  @Test
  public void testDeleteHandling() {
    String urlToTest = "http://localhost:" + generatedPort + "/deleteData";
    User requestObject = new User(123l, "Himanshu");
    String expectedResponse = "DELETE:: User Information : " + requestObject.getUserId() + "::" + requestObject.getUserName();
    HttpHeaders headers = new HttpHeaders();
    HttpEntity<User> entity = new HttpEntity<>(requestObject, headers);
    ResponseEntity<String> response = restTemplate.exchange(urlToTest, HttpMethod.DELETE, entity, String.class, 123l);
    assertEquals(expectedResponse, response.getBody());
  }

}