package com.twitter.trending.hashtags.service;

import static org.mockito.Mockito.doReturn;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.twitter.trending.hashtags.model.HashTag;
import com.twitter.trending.hashtags.repository.HashTagRepository;

/**
 * HashTagServiceTest to test the HashTagService functionality
 * 
 * @author Kusal
 *
 */
@SpringBootTest
public class HashTagServiceTest {

	/**
	 * Actual Service to make the calls for testing
	 */
	@Autowired
	private HashTagService hashTagService;

	/**
	 * Mocking the Tweet Repository to return the desired response when called from
	 * actual service
	 */
	@MockBean
	private HashTagRepository hashTagRepository;

	/**
	 * Test the isHashTag exists method if hash tag exists in DB
	 */
	@Test
	public void testIsHashTagExists() {
		// Setting hashtags for testing
		HashTag javaHashTag = new HashTag("java");
		HashTag springHashTag = new HashTag("spring");

		// Mocking repository response
		doReturn(Boolean.TRUE).when(hashTagRepository).existsById(javaHashTag.getTagName());
		doReturn(Boolean.FALSE).when(hashTagRepository).existsById(springHashTag.getTagName());

		// Assert the response
		Assertions.assertTrue(hashTagService.isHashTagExists(javaHashTag.getTagName()));
		Assertions.assertFalse(hashTagService.isHashTagExists(springHashTag.getTagName()));
	}

	/**
	 * Test the get HashTag By TagName method
	 */
	@Test
	public void testGetByTagName() {
		// Setting hashtags for testing
		HashTag javaHashTag = new HashTag("java");

		// Mocking repository response
		doReturn(javaHashTag).when(hashTagRepository).getOne(javaHashTag.getTagName());

		// Fetching response from service
		HashTag response = hashTagService.getByTagName(javaHashTag.getTagName());

		// Assert statements
		Assertions.assertEquals(response, javaHashTag);

	}

	/**
	 * Test the Top10TrendingHashTags method
	 */
	@Test
	public void testTop10TrendingHashTags() {
		// setting up hashtags
		String[] hashTagNames = { "java", "spring", "maven", "web", "dev", "ide", "sql", "git", "ui", "js" };
		Set<HashTag> hashTagSet = new HashSet<HashTag>();
		for (String hashTagName : hashTagNames) {
			hashTagSet.add(new HashTag(hashTagName));
		}

		// Mocking repository response
		doReturn(hashTagSet).when(hashTagRepository).findTop10ByOrderByTagCountDesc();

		// Fetching response from service
		Set<HashTag> response = hashTagService.getTop10TrendingHashTags();

		// Assert statements
		Assertions.assertTrue(hashTagSet.toString().equals(response.toString()));

	}
}
