package com.twitter.trending.hashtags.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.twitter.trending.hashtags.model.Tweet;

/**
 * This interface is the repository for Tweets
 * 
 * @author Kusal
 *
 */

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {

}
