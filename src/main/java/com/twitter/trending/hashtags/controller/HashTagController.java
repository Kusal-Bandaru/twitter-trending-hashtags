package com.twitter.trending.hashtags.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twitter.trending.hashtags.model.HashTag;
import com.twitter.trending.hashtags.service.HashTagService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * The HashTagContoller class provides Rest API end point to retrieve 
 * Top 10 Trending HashTags
 * 
 * @author Kusal
 *
 */

@RestController
@RequestMapping("/api/v1/twitter/hashtags")
@Api(value = "HashTag Controller", tags={"API for retrieving Top 10 Trending HashTags"})	// API info for swagger
public class HashTagController {

	/**
	 * Configuring Spring to autowire the HashTagService
	 */
	@Autowired
	private HashTagService hashTagService;

	/**
	 * API to Retrieve the top 10 trending hashtags
	 * 
	 * @return Set of top 10 trending hashtags
	 */
	@GetMapping(value = "/top10Trending")
	@ApiOperation(value = "Get top 10 trending hashtags", httpMethod = "GET", consumes = "application/json")	//API Operation info for swagger
	public Set<HashTag> getTop10TrendingHashTags() {
		return hashTagService.getTop10TrendingHashTags();
	}
}
