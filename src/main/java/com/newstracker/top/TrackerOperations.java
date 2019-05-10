package com.newstracker.top;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//import com.json.JSONObject;
//import jdk.nashorn.internal.parser.JSONParser;
//import org.json.simple.JSONArray;

public class TrackerOperations {

    static final String sURL = "https://hacker-news.firebaseio.com/v0/topstories.json";


    public String retrieveTop500() {


        try {
            System.out.println(fromString(getText(sURL)));
            getObjectJson();


        } catch (Exception e) {

        }
        return "hi";

    }


    public static String getText(String url) throws Exception {
        System.out.println("Processing URL");

        URL website = new URL(url);
        URLConnection connection = website.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF8"));

        StringBuilder response = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null)
            response.append(inputLine);

        in.close();

        return response.toString();
    }

    public String getObjectJson(){
        try {
            URL url = new URL("https://hacker-news.firebaseio.com/v0/item/19882004.json");
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String str = "";
            String appender = "";

            while (null != (str = br.readLine())) {
                appender += str;
                System.out.println(str);
            }
            System.out.println("time to print");
            System.out.println(appender);
            return str;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "well shit";
    }

    private static String fromString(String string) {
        String[] strings = string.replace("[", "").replace("]", "").split(", ");
        List<String> storyList = Arrays.asList(strings[0].split(","));
        System.out.println(storyList.get(0));

        return storyList.get(0);
    }

    private static ArrayList<HackerNews> hackerNewsTopStories() {
        ArrayList<HackerNews> theNews = null;
        return theNews;
    }

}
