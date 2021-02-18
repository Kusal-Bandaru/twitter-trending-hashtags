package com.twitter.trending.hashtags.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;

/**
 * This class is the entity for HashTags
 * 
 * @author Kusal
 *
 */

@Entity
@Table(name = "hashtag")
public class HashTag {

	/**
	 * Hashtag name is the primary key for the table hashtag
	 */
	@Id
	@Column(name = "tag_name", nullable = false)
	@ApiModelProperty(notes = "HashTag name")
	String tagName;

	/**
	 * tagCount stores the frequency of the tagged hashtags in tweets
	 */
	@Column(name = "tag_count")
	@ApiModelProperty(notes = "Number of times the hashtag is used")
	int tagCount;

	/**
	 * Many to many mapping for tweet_tag table
	 */
	@JsonIgnore
	@ManyToMany(mappedBy = "hashTags", cascade = CascadeType.ALL)
	@ApiModelProperty(notes = "Tweets which used this hashtag")
	private Set<Tweet> tweets = new HashSet<>();

	/**
	 * Default no params constructor
	 */
	public HashTag() {
	}

	/**
	 * Constructor to create a hashtag with default count: 1
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

	/**
	 * default equals method for HashTag 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HashTag other = (HashTag) obj;
		if (tagCount != other.tagCount)
			return false;
		if (tagName == null) {
			if (other.tagName != null)
				return false;
		} else if (!tagName.equals(other.tagName))
			return false;
		return true;
	}

	/**
	 * toString method for HashTag
	 */
	@Override
	public String toString() {
		return "HashTag [tagName=" + tagName + ", tagCount=" + tagCount + "]";
	}

}
