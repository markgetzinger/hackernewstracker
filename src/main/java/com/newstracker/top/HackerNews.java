package com.newstracker.top;

import java.util.List;

public class HackerNews {

    public String url;
    public String title;
    public String storyId;

    public String by;
    public int descendants;
    public int id;
    public List<Integer> kids;
    public int score;
    public int time;
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

    public int getDescendants() {
        return descendants;
    }

    public void setDescendants(int descendants) {
        this.descendants = descendants;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Integer> getKids() {
        return kids;
    }

    public void setKids(List<Integer> kids) {
        this.kids = kids;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
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
                ", descendants=" + descendants +
                ", id=" + id +
                ", kids=" + kids +
                ", score=" + score +
                ", time=" + time +
                ", type='" + type + '\'' +
                '}';
    }
}
