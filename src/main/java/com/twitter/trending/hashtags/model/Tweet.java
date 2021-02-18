package com.twitter.trending.hashtags.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;

/**
 * This class is the entity for Tweets
 * 
 * @author Kusal
 *
 */

@Entity
public class Tweet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tweet_id")
	@ApiModelProperty(notes = "The database generated tweet ID")
	private int id;

	@Column(name = "user_name")
	@ApiModelProperty(notes = "Username of the user that submitted the tweet")
	private String userName;

	@Column(name = "tweet")
	@ApiModelProperty(notes = "Tweet string")
	private String tweet;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "tweet_tag", 
	joinColumns = @JoinColumn(name = "tweet_id", referencedColumnName = "tweet_id"), 
	inverseJoinColumns = @JoinColumn(name = "tag_name", referencedColumnName = "tag_name"))
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ApiModelProperty(notes = "Tweet string")
	private Set<HashTag> hashTags = new HashSet<>();

	/**
	 * 
	 */
	public Tweet() {
	}

	/**
	 * @param id
	 * @param userName
	 * @param tweet
	 * @param hashTags
	 */
	public Tweet(int id, String userName, String tweet, Set<HashTag> hashTags) {
		this.id = id;
		this.userName = userName;
		this.tweet = tweet;
		this.hashTags = hashTags;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the tweet
	 */
	public String getTweet() {
		return tweet;
	}

	/**
	 * @param tweet the tweet to set
	 */
	public void setTweet(String tweet) {
		this.tweet = tweet;
	}

	/**
	 * @return the hashTags
	 */
	public Set<HashTag> getHashTags() {
		return hashTags;
	}

	/**
	 * @param hashTags the hashTags to set
	 */
	public void setHashTags(Set<HashTag> hashTags) {
		this.hashTags = hashTags;
	}

}
