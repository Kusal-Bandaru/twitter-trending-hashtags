/**
 * 
 */
package com.twitter.trending.hashtags.service;

import java.util.Set;

import com.twitter.trending.hashtags.model.HashTag;

/**
 * HashTagService interface provides the methods for retrieving tags and top 10
 * trending hashtags.
 * 
 * @author Kusal
 *
 */
public interface HashTagService {

	/**
	 * Checks if the given hashtag exits in the DB
	 * 
	 * @param hashTagName HashTag to be searched
	 * @return boolean representing true if HashTag exits and false it it doesn't
	 */
	public boolean isHashTagExists(String hashTagName);

	/**
	 * Retrieve the HashTag object from the DB
	 * 
	 * @param hashTagName Hashtag to be fetched
	 * @return HashTag object
	 */
	public HashTag getByTagName(String hashTagName);

	/**
	 * Retrieves the Top 10 Trending Hashtags.
	 * 
	 * @return Set of HashTags containing top 10 trending hashtags.
	 */
	public Set<HashTag> getTop10TrendingHashTags();
}
