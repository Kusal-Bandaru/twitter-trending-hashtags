/**
 * 
 */
package com.twitter.trending.hashtags.service.impl;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twitter.trending.hashtags.model.HashTag;
import com.twitter.trending.hashtags.repository.HashTagRepository;
import com.twitter.trending.hashtags.service.HashTagService;

/**
 * HashTagServiceImpl is the implementation class for HashTagService. It
 * provides the business logic for all the Hashtag service methods.
 * 
 * @author Kusal
 *
 */
@Service
public class HashTagServiceImpl implements HashTagService {

	/**
	 * Configuring Spring to autowire the HashTagRepository
	 */
	@Autowired
	HashTagRepository hashTagRepository;

	/**
	 * Retrieve the HashTag object from the DB if present
	 * 
	 * @param hashTagName Hashtag to be fetched
	 * @return Optional<HashTag> object
	 */
	@Override
	public Optional<HashTag> getByTagName(String hashTagName) {
		return hashTagRepository.findById(hashTagName);
	}

	/**
	 * Retrieves the Top 10 Trending Hashtags.
	 * 
	 * @return Set of HashTags containing top 10 trending hashtags.
	 */
	@Override
	public Set<HashTag> getTop10TrendingHashTags() {
		return hashTagRepository.findTop10ByOrderByTagCountDesc();
	}

}
