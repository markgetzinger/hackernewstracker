package com.newstracker.top;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpMethod.GET;


public class TrackerOperations {

    private static final String HACKER_URL = "https://hacker-news.firebaseio.com/v0";
    private static final RestTemplate restTemplate = new RestTemplate();

    public List<Integer> getTopIds(int numberOfIds) {
        ParameterizedTypeReference<List<Integer>> listOfIntegersTypeRef = new ParameterizedTypeReference<List<Integer>>(){};

        List<Integer> idList = restTemplate
                .exchange(HACKER_URL + "/topstories.json", GET, null, listOfIntegersTypeRef)
                .getBody();

        return idList.subList(0, numberOfIds);
    }

    public HackerNews getStory(int storyId) {
        return restTemplate.getForObject(HACKER_URL + "/item/" + storyId + ".json", HackerNews.class);
    }

    public List<HackerNews> getTopStories(int storyCount) {
        List<Integer> idList = getTopIds(storyCount);
        List<HackerNews> stories = new ArrayList<>(storyCount);
        for (Integer currentId : idList) {
            stories.add(getStory(currentId));
        }
        return stories;
    }

}
