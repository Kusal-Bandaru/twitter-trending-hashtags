/**
 * 
 */
package com.twitter.trending.hashtags.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.twitter.trending.hashtags.model.HashTag;

/**
 * HashTagRepository is the repository for HashTags
 * 
 * @author Kusal
 *
 */
@Repository
public interface HashTagRepository extends JpaRepository<HashTag, String> {

	/**
	 * Retrieves the Top 10 Trending Hashtags.
	 * 
	 * @return Set of HashTags containing top 10 trending hashtags.
	 */
	Set<HashTag> findTop10ByOrderByTagCountDesc();

}
