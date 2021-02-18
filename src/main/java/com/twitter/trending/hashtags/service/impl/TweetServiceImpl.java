package com.twitter.trending.hashtags.service.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	 */
	@Override
	public Tweet newTweet(Tweet tweet) {

		Set<HashTag> hashTags = new HashSet<>();
		HashTag hashTag;

		LOGGER.info("Received tweet: {}", tweet.getTweet());
		Set<String> extractedHashTags = extractHashTagsFromTweet(tweet.getTweet());

		for (String hashTagName : extractedHashTags) {
			if (hashTagService.isHashTagExists(hashTagName)) {
				hashTag = hashTagService.getByTagName(hashTagName);
				hashTag.setTagCount(hashTag.getTagCount() + 1);
			} else {
				hashTag = new HashTag(hashTagName);
			}
			hashTags.add(hashTag);
		}
		tweet.setHashTags(hashTags);
		LOGGER.info("HashTags retrieved from the tweet: {}", Arrays.toString(hashTags.toArray()));
		return tweetRepository.saveAndFlush(tweet);
	}

	/**
	 * Extracts the hashtags from the given tweet
	 * 
	 * @param tweet
	 * @return Set of extracted Hash tag names
	 */
	private Set<String> extractHashTagsFromTweet(String tweet) {

		Set<String> hashTags = new HashSet<>();

		Matcher matcher = EXTRACT_HASHTAGS_PATTERN.matcher(tweet);

		while (matcher.find()) {
			hashTags.add(matcher.group(1));
		}
		return hashTags;
	}

}
