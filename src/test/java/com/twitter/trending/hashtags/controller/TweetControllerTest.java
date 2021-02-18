package com.twitter.trending.hashtags.controller;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import com.twitter.trending.hashtags.model.Tweet;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TweetControllerTest {

	/**
	 * Logger for the class
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(TweetControllerTest.class);

	/**
	 * bind the RANDOM_PORT generated in SpringBootTest annotation above
	 */
	@LocalServerPort
	private int port;

	/**
	 * Similar to RestTemplate and is suitable for integration tests
	 */
	@Autowired
	private TestRestTemplate restTemplate;

	/**
	 * Test method to test the Welcome Message API endpoint
	 */
	@Test
	public void testWelcomeMessage() {
		ResponseEntity<String> response = null;
		try {
			response = restTemplate.getForEntity(
					new URL("http://localhost:" + port + "/api/v1/twitter/tweets/").toString(), String.class);
		} catch (RestClientException | MalformedURLException e) {
			LOGGER.error(e.getMessage());
		}
		Assertions.assertEquals(HttpStatus.OK, response != null ? response.getStatusCode() : null);
		Assertions.assertEquals("Welcome to Twitter API service", response != null ? response.getBody() : null);
	}

	/**
	 * Test method for adding a new tweet and check the created status
	 */
	@Test
	public void testAddingNewTweet() {
		ResponseEntity<String> response = null;
		try {
			Tweet tweet = new Tweet();
			tweet.setTweet("I love java #java"); // creating a new tweet object to post
			response = restTemplate.postForEntity(
					new URL("http://localhost:" + port + "/api/v1/twitter/tweets/new").toString(), tweet, String.class);
		} catch (RestClientException | MalformedURLException e) {
			LOGGER.error(e.getMessage());
		}
		LOGGER.info(response.getBody());
		Assertions.assertEquals(HttpStatus.CREATED, response != null ? response.getStatusCode() : null);
	}

}
