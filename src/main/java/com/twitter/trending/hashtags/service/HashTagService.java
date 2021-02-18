/**
 * 
 */
package com.twitter.trending.hashtags.service;

import java.util.Optional;
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
	 * Retrieve the HashTag object from the DB if present
	 * 
	 * @param hashTagName Hashtag to be fetched
	 * @return Option<HashTag> object
	 */
	public Optional<HashTag> getByTagName(String hashTagName);

	/**
	 * Retrieves the Top 10 Trending Hashtags.
	 * 
	 * @return Set of HashTags containing top 10 trending hashtags.
	 */
	public Set<HashTag> getTop10TrendingHashTags();
}
