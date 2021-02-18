/**
 * 
 */
package com.twitter.trending.hashtags.controller;



import java.net.URL;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

/**
 * @author Kusal
 *
 */

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HashTagControllerTest {

	// bind the above RANDOM_PORT
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getHello() throws Exception {

        ResponseEntity<String> response = restTemplate.getForEntity(
            new URL("http://localhost:" + port + "/api/v1/twitter/tweets/").toString(), String.class);
        Assertions.assertEquals("Welcome to Twitter API service", response.getBody());

    }
}
