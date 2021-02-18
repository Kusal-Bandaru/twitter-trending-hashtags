package com.twitter.trending.hashtags.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;

/**
 * This class is the entity for HashTags
 * 
 * @author Kusal
 *
 */

@Entity
public class HashTag {

	@Id
	@Column(name = "tag_name", nullable = false)
	@ApiModelProperty(notes = "HashTag name")
	String tagName;

	@Column(name = "tag_count")
	@ApiModelProperty(notes = "Number of times the hashtag is used")
	int tagCount;

	@JsonIgnore
	@ManyToMany(mappedBy = "hashTags", cascade = CascadeType.ALL)
	@ApiModelProperty(notes = "Tweets which used this hashtag")
	private Set<Tweet> tweets = new HashSet<>();

	/**
	 * 
	 */
	public HashTag() {
	}

	/**
	 * 
	 */
	public HashTag(String tagName) {
		this.tagName = tagName;
		this.tagCount = 1;
	}

	/**
	 * @return the tagName
	 */
	public String getTagName() {
		return tagName;
	}

	/**
	 * @param tagName the tagName to set
	 */
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	/**
	 * @return the tagCount
	 */
	public int getTagCount() {
		return tagCount;
	}

	/**
	 * @param tagCount the tagCount to set
	 */
	public void setTagCount(int tagCount) {
		this.tagCount = tagCount;
	}

	/**
	 * @return the tweets
	 */
	public Set<Tweet> getTweets() {
		return tweets;
	}

	/**
	 * @param tweets the tweets to set
	 */
	public void setTweets(Set<Tweet> tweets) {
		this.tweets = tweets;
	}

}
