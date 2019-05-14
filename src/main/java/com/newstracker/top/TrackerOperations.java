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

    public void retrieveTop10() {
        try {
            setTopTenStories();
            buildFTLTemplate();
        } catch (Exception e) {
        }
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
                }
                topTenStories.add(appender);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static List formatJsonArray(String string) {
        String[] strings = string.replace("[", "").replace("]", "").split(", ");
        List<String> storyList = Arrays.asList(strings[0].split(","));
        return storyList;
    }

    public static ArrayList<HackerNews> hackerNewsTopStories() {
        ArrayList<HackerNews> theNews = null;
        return theNews;
    }

    public void setTopTenIds(List<String> storyList){
        for(int i = 0; i<10;i++){
            topTenList.add(storyList.get(i));
        }
    }

    public ArrayList<HackerNews> jacksonReadAndWrite(){
        SimpleModule module =
                new SimpleModule("NewsDeserializer", new Version(3, 1, 8, null, null, null));
        module.addDeserializer(HackerNews.class, new NewsDeserialize(HackerNews.class));
        ArrayList <HackerNews> hackerNews = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(module);

        try{
            for(int storyIndex =0;storyIndex<10;storyIndex++){
                hackerNews.add(mapper.readValue(topTenStories.get(storyIndex), HackerNews.class));
            }

        }catch(Exception e){}
        return hackerNews;
    }

    public void buildFTLTemplate(){
        try {
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
            cfg.setDirectoryForTemplateLoading(new File("src/main/resources"));
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

            ArrayList<HackerNews> newsItems = jacksonReadAndWrite();


            Map<String, Object> map = new HashMap<>();
            map.put("blogTitle", "Hacker News - Best In the West");
            List<HackerNews> references = new ArrayList<>();
            for(HackerNews newsItem : newsItems){
                references.add(newsItem);
                map.put("references", references);
            }

            Template template = cfg.getTemplate("template.ftl");

            Writer console = new OutputStreamWriter(System.out);
            template.process(map, console);
            console.flush();

            Writer file = new FileWriter (new File("src/main/resources/public/index.html"));
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
