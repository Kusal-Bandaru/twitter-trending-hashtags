/**
 * 
 */
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

/**
 * Controller Test to verify the API endnpoints
 * 
 * @author Kusal
 *
 */

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HashTagControllerTest {

	/**
	 * Logger for the class
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(HashTagControllerTest.class);

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
	 * Test to verify that proper response is returned from top10Trending API
	 */
	@Test
	public void testTop10TrendingHashTags() {
		ResponseEntity<String> response = null;
		try {
			response = restTemplate.getForEntity(
					new URL("http://localhost:" + port + "/api/v1/twitter/hashtags/top10Trending").toString(),
					String.class);
		} catch (RestClientException | MalformedURLException e) {
			LOGGER.error(e.getMessage());
		}
		Assertions.assertEquals(HttpStatus.OK, response != null ? response.getStatusCode() : null);
	}
}
