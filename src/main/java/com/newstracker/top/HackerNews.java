package com.newstracker.top;

public class HackerNews {

    String url;

    public HackerNews(String url, String title) {
        this.url = url;
        this.title = title;
    }

    public HackerNews() {
    }
    String title;
    String storyId;

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
