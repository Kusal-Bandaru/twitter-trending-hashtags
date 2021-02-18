package com.twitter.trending.hashtags.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.twitter.trending.hashtags.exception.ServiceException;
import com.twitter.trending.hashtags.model.Tweet;
import com.twitter.trending.hashtags.service.TweetService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * The TweetController class provides Rest API end points to add new tweets and
 * to retrieve tweets by tags
 * 
 * @author Kusal
 *
 */

@RestController
@RequestMapping("/api/v1/twitter/tweets")
@Api(value = "Tweet Controller", tags = { "API for adding new tweets" }) // API info for swagger
public class TweetController {

	/**
	 * Logger for this class
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(TweetController.class);

	@Autowired
	private TweetService tweetService;

	/**
	 * Simple API method to return a welcome message.
	 * 
	 * @return Welcome Message
	 */
	@GetMapping
	@ApiOperation(value = "Welcome message", httpMethod = "GET", consumes = "application/json")
	public String welcomeMessage() {
		return "Welcome to Twitter API service";
	}

	/**
	 * API endpoint to add a new tweet.
	 * 
	 * @param tweet
	 * @return status of creation along with the created tweet object
	 */
	@PostMapping(value = "/new")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Add new tweet", httpMethod = "POST", consumes = "application/json")
	public Tweet newTweet(@RequestBody Tweet tweet) {
		Tweet responseTweet = null;
		try {
			responseTweet = tweetService.newTweet(tweet);
		} catch (ServiceException e) {
			LOGGER.error("Service exception occurred while saving the tweet Exception Message : {}", e.getMessage());
		}
		return responseTweet;
	}

}
