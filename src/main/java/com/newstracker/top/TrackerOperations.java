package com.newstracker.top;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONObject;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

//import com.json.JSONObject;
//import jdk.nashorn.internal.parser.JSONParser;
//import org.json.simple.JSONArray;

public class TrackerOperations {

    static final String sURL = "https://hacker-news.firebaseio.com/v0/topstories.json";
    List<String> topTenList = new ArrayList<>();
    List<String> topTenStories = new ArrayList<>();


    public String retrieveTop500() {


        try {
            //System.out.println(formatJsonArray(getText(sURL)));
            setTopTenStories();
            for(int i = 0;i<10;i++){
                System.out.println(topTenStories.get(i)+"\n");
            }
            HackerNews newsItem = jacksonReadAndWrite();
            System.out.println(newsItem.getTitle());
            System.out.println(newsItem.getUrl());



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

    public void setTopTenStories(){

        String nextStory;
        String str = "";
        List<String> top500List = new ArrayList<>();
        try{
            top500List = formatJsonArray(getText(sURL));
        }catch(Exception e){

        }
        setTopTenIds(top500List);

        try {
            for (int i = 0;i<10;i++){
                nextStory = topTenList.get(i);
                URL url = new URL("https://hacker-news.firebaseio.com/v0/item/"+nextStory+".json");
                BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
                String appender = "";

                while (null != (str = br.readLine())) {
                    appender += str;
                    //System.out.println(str);
                }
                topTenStories.add(appender);
            }
            //return str;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //return null;
    }

    private static List formatJsonArray(String string) {
        String[] strings = string.replace("[", "").replace("]", "").split(", ");
        List<String> storyList = Arrays.asList(strings[0].split(","));
        return storyList;
    }

    private static ArrayList<HackerNews> hackerNewsTopStories() {
        ArrayList<HackerNews> theNews = null;
        return theNews;
    }

    private void setTopTenIds(List<String> storyList){
        for(int i = 0; i<10;i++){
            topTenList.add(storyList.get(i));
        }
    }

    private HackerNews jacksonReadAndWrite(){
        SimpleModule module =
                new SimpleModule("CarDeserializer", new Version(3, 1, 8, null, null, null));
        module.addDeserializer(HackerNews.class, new NewsDeserialize(HackerNews.class));
        HackerNews car = null;
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(module);

        try{
             car = mapper.readValue(topTenStories.get(0), HackerNews.class);
        }catch(Exception e){}


        return car;
    }

    private void buildFTLTemplate(){
        try {
            //Instantiate Configuration class
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
            cfg.setDirectoryForTemplateLoading(new File("ftl"));
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

            //Create Data Model
            Map<String, Object> map = new HashMap<>();
            map.put("blogTitle", "Freemarker Template Demo");
            map.put("message", "Getting started with Freemarker.<br/>Find a simple Freemarker demo.");
            List<URL> references = new ArrayList<>();
            references.add(new URL("http://url1.com"));
            references.add(new URL("http://url2.com"));
            references.add(new URL("http://url3.com"));
            map.put("references", references);

            //Instantiate template
            Template template = cfg.getTemplate("template.ftl");

            //Console output
            Writer console = new OutputStreamWriter(System.out);
            template.process(map, console);
            console.flush();

            // File output
            Writer file = new FileWriter (new File("ftl/template.html"));
            template.process(map, file);
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }

    }

}
