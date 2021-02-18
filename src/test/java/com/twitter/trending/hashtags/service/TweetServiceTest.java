///**
// * 
// */
//package com.twitter.trending.hashtags.service;
//
//import static org.mockito.Mockito.doReturn;
//
//import java.util.Arrays;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import com.twitter.trending.hashtags.model.HashTag;
//import com.twitter.trending.hashtags.model.Tweet;
//import com.twitter.trending.hashtags.repository.TweetRepository;
//
///**
// * @author Kusal
// *
// */
//
//@SpringBootTest
//public class TweetServiceTest {
//
//	@Autowired
//	private TweetService tweetService;
//
//	@MockBean
//	private TweetRepository tweetRepository;
//
//	@Test
//	@DisplayName("Test findById Success")
//	void testFindById() {
//		// Setup our mock repository
//		HashTag hashTag = new HashTag("java");
//		Set<HashTag> hashTags = new HashSet<HashTag>(Arrays.asList(hashTag));
//		List<Tweet> tweetList = Arrays.asList(new Tweet(1, "Kusal", "I love java #java", hashTags));
//		doReturn(tweetList).when(tweetRepository).findByHashTags(hashTag);
//
//		// Execute the service call
//		List<Tweet> returnedTweetList = tweetService.getTweetByHashTag(hashTag);
//
//		// Assert the response
//		Assertions.assertEquals(returnedTweetList.get(0).getTweet(), "I love java #java");
//		Assertions.assertSame(returnedTweetList, tweetList, "The widget returned was not the same as the mock");
//	}
////
////	@Test
////	@DisplayName("Test findById Not Found")
////	void testFindByIdNotFound() {
////		// Setup our mock repository
////		doReturn(Optional.empty()).when(repository).findById(1l);
////
////		// Execute the service call
////		Optional<Widget> returnedWidget = service.findById(1l);
////
////		// Assert the response
////		Assertions.assertFalse(returnedWidget.isPresent(), "Widget should not be found");
////	}
////
////	@Test
////	@DisplayName("Test findAll")
////	void testFindAll() {
////		// Setup our mock repository
////		Widget widget1 = new Widget(1l, "Widget Name", "Description", 1);
////		Widget widget2 = new Widget(2l, "Widget 2 Name", "Description 2", 4);
////		doReturn(Arrays.asList(widget1, widget2)).when(repository).findAll();
////
////		// Execute the service call
////		List<Widget> widgets = service.findAll();
////
////		// Assert the response
////		Assertions.assertEquals(2, widgets.size(), "findAll should return 2 widgets");
////	}
////
////	@Test
////	@DisplayName("Test save widget")
////	void testSave() {
////		// Setup our mock repository
////		Widget widget = new Widget(1l, "Widget Name", "Description", 1);
////		doReturn(widget).when(repository).save(any());
////
////		// Execute the service call
////		Widget returnedWidget = service.save(widget);
////
////		// Assert the response
////		Assertions.assertNotNull(returnedWidget, "The saved widget should not be null");
////		Assertions.assertEquals(2, returnedWidget.getVersion(), "The version should be incremented");
////	}
//}
