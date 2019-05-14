package com.newstracker.top;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

import static org.springframework.http.HttpMethod.GET;


public class TrackerOperations {

    private static final String HACKER_URL = "https://hacker-news.firebaseio.com/v0";
    private static final RestTemplate restTemplate = new RestTemplate();

    public List<Integer> getTopIds(int numberOfIds) {
        // go get list from web
        ParameterizedTypeReference<List<Integer>> listOfIntegersTypeRef = new ParameterizedTypeReference<List<Integer>>(){};

        List<Integer> idList = restTemplate
                .exchange(HACKER_URL + "/topstories.json", GET, null, listOfIntegersTypeRef)
                .getBody();

        // pair down to size requested
        return idList.subList(0, numberOfIds);
    }

    public HackerNews getStory(int storyId) {
        // go get story from web
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



    //
    // old stuff
    //
//    private static final int STORY_COUNT = 10;
//
//
//    static final String sURL = "https://hacker-news.firebaseio.com/v0/topstories.json";
//
//    List<String> topTenList = new ArrayList<>();
//    List<String> topTenStories = new ArrayList<>();
//
//    public void retrieveTop10() {
//        try {
//            setTopTenStories();
//            buildFTLTemplate();
//        } catch (Exception e) {
//        }
//    }
//
//
//    public static String getText(String url) throws Exception {
//        System.out.println("Processing URL");
//
//        URL website = new URL(url);
//        URLConnection connection = website.openConnection();
//        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF8"));
//        StringBuilder response = new StringBuilder();
//        String inputLine;
//        while ((inputLine = in.readLine()) != null)
//            response.append(inputLine);
//        in.close();
//        return response.toString();
//    }
//
//    public void setTopTenStories(){
//        String nextStory;
//        String str = "";
//        List<String> top500List = new ArrayList<>();
//        try{
//            top500List = formatJsonArray(getText(sURL));
//        }catch(Exception e){
//
//        }
//        setTopTenIds(top500List);
//
//        try {
//            for (int storyIndex = 0 ; storyIndex < STORY_COUNT ; storyIndex++){
//                nextStory = topTenList.get(storyIndex);
//                URL url = new URL("https://hacker-news.firebaseio.com/v0/item/"+nextStory+".json");
//                BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
//                String appender = "";
//
//                while ((str = br.readLine())!= null) {
//                    appender += str;
//                }
//                topTenStories.add(appender);
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    public static List formatJsonArray(String string) {
//        String[] strings = string.replace("[", "").replace("]", "").split(", ");
//        List<String> storyList = Arrays.asList(strings[0].split(","));
//        return storyList;
//    }
//
//    public static ArrayList<HackerNews> hackerNewsTopStories() {
//        ArrayList<HackerNews> theNews = null;
//        return theNews;
//    }
//
//    public void setTopTenIds(List<String> storyList){
//        for(int storyIndex = 0; storyIndex < STORY_COUNT ; storyIndex++){
//            topTenList.add(storyList.get(storyIndex));
//        }
//    }
//
//    public ArrayList<HackerNews> jacksonStringParser(){
//        SimpleModule module = new SimpleModule("NewsDeserializer", new Version(3, 1, 8, null, null, null));
//        module.addDeserializer(HackerNews.class, new NewsDeserialize(HackerNews.class));
//        ArrayList <HackerNews> hackerNews = new ArrayList<>();
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.registerModule(module);
//        try{
//            for(int storyIndex = 0; storyIndex < STORY_COUNT ; storyIndex++){
//                hackerNews.add(mapper.readValue(topTenStories.get(storyIndex), HackerNews.class));
//            }
//        }catch(Exception e){}
//        return hackerNews;
//    }

    public void buildFTLTemplate(List<HackerNews> topStories){
        try {
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
            cfg.setDirectoryForTemplateLoading(new File("src/main/resources"));
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

            //List<HackerNews> newsItems = getTopStories(10);


            Map<String, Object> map = new HashMap<>();
            map.put("blogTitle", "Hacker News - Best In the West");
            List<HackerNews> references = new ArrayList<>();
            for(HackerNews newsItem : topStories){
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

    public ArrayList<HackerNews> retrieveTopTen(){
        ArrayList<HackerNews> daNews = new ArrayList<>();
        //setTopTenStories();
        return daNews;
    }
}
