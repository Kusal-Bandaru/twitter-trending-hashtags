/**
 * 
 */
package com.twitter.trending.hashtags.service;

import com.twitter.trending.hashtags.exception.ServiceException;
import com.twitter.trending.hashtags.model.Tweet;

/**
 * TweetService interface provides the method for to add new Tweeet
 * 
 * @author Kusal
 *
 */
public interface TweetService {

	/**
	 * Adds the new tweet to the DB
	 * 
	 * @param tweet
	 * @return status of the creation along with the created tweet object
	 * @throws ServiceException
	 */
	public Tweet newTweet(Tweet tweet) throws ServiceException;

}
