package com.newstracker.top;

import java.util.ArrayList;

public class HackerNews {

    @Override
    public String toString() {
        return "HackerNews{" +
                "url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", storyId='" + storyId + '\'' +
                '}';
    }

    String url;
    String title;
    String storyId;

    ArrayList<HackerNews> hackerList;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStoryId() {
        return storyId;
    }

    public void setStoryId(String storyId) {
        this.storyId = storyId;
    }


}
