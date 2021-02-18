package com.twitter.trending.hashtags.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.twitter.trending.hashtags.exception.ServiceException;
import com.twitter.trending.hashtags.model.HashTag;
import com.twitter.trending.hashtags.model.Tweet;
import com.twitter.trending.hashtags.repository.TweetRepository;

/**
 * TweetServiceTest to test the TweetService functionality
 * 
 * @author Kusal
 *
 */

@SpringBootTest
public class TweetServiceTest {

	/**
	 * Actual Service to make the calls for testing
	 */
	@Autowired
	private TweetService tweetService;

	/**
	 * Mocking the HashTagService to return the desired response when called from
	 * actual service
	 */
	@Mock
	private HashTagService hashTagService;

	/**
	 * Mocking the Tweet Repository to return the desired response when called from
	 * actual service
	 */
	@MockBean
	private TweetRepository tweetRepository;

	/**
	 * Tweet object for testing
	 */
	Tweet tweet;

	/**
	 * HashTag object for testing
	 */
	HashTag hashTag;

	@BeforeEach
	void setUp() {
		// setting hashTag and tweet object for testing
		tweet = new Tweet();
		tweet.setTweet("I love web development with java #java");
		hashTag = new HashTag("java");

		// setting optional hashTag object for testing
		Optional<HashTag> hashTagOptional = Optional.of(hashTag);

		// Mocking the response from dependencies
		doReturn(hashTagOptional).when(hashTagService).getByTagName(any());
		doReturn(tweet).when(tweetRepository).save(any());
	}

	/**
	 * Testing the creation of new tweet functionality in TweetService
	 * 
	 * @throws ServiceException
	 */
	@Test
	void testAddingNewTweet() throws ServiceException {
		// creating Set of hashtags
		Set<HashTag> hashTagSet = new HashSet<HashTag>();
		hashTagSet.add(hashTag);

		// get response from service
		Tweet createdTweet = tweetService.newTweet(tweet);

		// Assert the response
		Assertions.assertEquals(createdTweet.getTweet(), tweet.getTweet());
		Assertions.assertTrue(createdTweet.getHashTags().toString().equals(hashTagSet.toString()));
	}

	/**
	 * Test to verify duplicate tags are not being saved
	 * 
	 * @throws ServiceException
	 */
	@Test
	void testDuplicateTagsInTweet() throws ServiceException {
		// setting a hashTagSet with only one tag with tagged count=1
		Set<HashTag> hashTagSet = new HashSet<HashTag>();
		hashTagSet.add(hashTag);

		// setting a tweet with dupicate tags
		tweet.setTweet("I love web development with java #java #java #java");

		// get response from service
		Tweet createdTweet = tweetService.newTweet(tweet);

		// Assert the response
		Assertions.assertEquals(createdTweet.getTweet(), tweet.getTweet());
		// tagged count will also be compared
		Assertions.assertTrue(createdTweet.getHashTags().toString().equals(hashTagSet.toString()));

	}
}
