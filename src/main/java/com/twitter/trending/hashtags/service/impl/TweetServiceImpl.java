package com.twitter.trending.hashtags.service.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twitter.trending.hashtags.exception.ServiceException;
import com.twitter.trending.hashtags.model.HashTag;
import com.twitter.trending.hashtags.model.Tweet;
import com.twitter.trending.hashtags.repository.TweetRepository;
import com.twitter.trending.hashtags.service.HashTagService;
import com.twitter.trending.hashtags.service.TweetService;

/**
 * TweetServiceImpl is the implementation class for TweetService. It provides
 * the business logic for all the TweetService service methods.
 * 
 * @author Kusal
 *
 */

@Service
public class TweetServiceImpl implements TweetService {

	/**
	 * Logger for this class
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(TweetServiceImpl.class);

	/**
	 * Regex pattern to extract the hash tags from the given tweet
	 */
	private static final Pattern EXTRACT_HASHTAGS_PATTERN = Pattern.compile("#(\\w+)");

	/**
	 * Configuring spring to autowire the TweetRepository
	 */
	@Autowired
	TweetRepository tweetRepository;

	/**
	 * Configuring spring to autowire the HashTag service
	 */
	@Autowired
	HashTagService hashTagService;

	/**
	 * Adds the new tweet to the DB along with hashtags, extracts the hashtags from
	 * the tweet and updates the count of the hashtags
	 * 
	 * @param tweet
	 * @return status of the creation along with the created tweet object
	 * @throws ServiceException
	 */
	@Override
	public Tweet newTweet(Tweet tweet) throws ServiceException {

		Set<HashTag> hashTags = new HashSet<>();
		Tweet createdTweet = null;
		HashTag hashTag;

		try {
			LOGGER.info("Received tweet: {}", tweet.getTweet());
			Set<String> extractedHashTags = extractHashTagsFromTweet(tweet.getTweet());

			/*
			 * Iterating through the set of hashtags and fetching it from the DB if present
			 * already to avoid duplicate creation of Hashtags.
			 */
			for (String hashTagName : extractedHashTags) {
				Optional<HashTag> hashTagOptional = hashTagService.getByTagName(hashTagName);
				if (hashTagOptional.isPresent()) { 					// check if hashtag is already present in DB
					hashTag = hashTagOptional.get(); 				// fetch the hashtag from DB
					hashTag.setTagCount(hashTag.getTagCount() + 1); // Incrementing the tagged count for hashtag
				} else {
					hashTag = new HashTag(hashTagName);				// Creating a new Hashtag with default count: 1
				}
				hashTags.add(hashTag);
			}
			tweet.setHashTags(hashTags);
			LOGGER.info("HashTags retrieved from the tweet: {}", Arrays.toString(hashTags.toArray()));
			createdTweet = tweetRepository.save(tweet); 			// saving the tweet
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}

		return createdTweet;
	}

	/**
	 * Extracts the hashtags from the given tweet
	 * 
	 * @param tweet
	 * @return Set of extracted Hash tag names
	 */
	private Set<String> extractHashTagsFromTweet(String tweet) {

		Set<String> hashTags = new HashSet<>();

		Matcher matcher = EXTRACT_HASHTAGS_PATTERN.matcher(tweet); 	// performing match operations for tweet with the
																	// pattern
		while (matcher.find()) {
			hashTags.add(matcher.group(1)); // adding the found hashtag from the tweet to the set of hashtags
		}
		return hashTags;
	}

}
