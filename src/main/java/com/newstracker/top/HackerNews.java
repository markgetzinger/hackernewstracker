package com.newstracker.top;

import java.util.List;

public class HackerNews {

    public String url;
    public String title;
    public String storyId;

    public String by;
    public int id;
    public int score;
    public String type;

    public HackerNews(String url, String title) {
        this.url = url;
        this.title = title;
    }

    public HackerNews() {
    }

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

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "HackerNews{" +
                "url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", storyId='" + storyId + '\'' +
                ", by='" + by + '\'' +
                ", id=" + id +
                ", score=" + score +
                ", type='" + type + '\'' +
                '}';
    }
}
