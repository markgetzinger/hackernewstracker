package com.newstracker.top;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpMethod.GET;


public class TrackerOperations {

    private static final String HACKER_URL = "https://hacker-news.firebaseio.com/v0";
    private static final RestTemplate restTemplate = new RestTemplate();
    private static final RestTemplate auctionTemplate = new RestTemplate();
    private static int currentClientIteration = 0;
    private static final String clientPrefix = "CLIENT_";
    private static final String envName = "_NAME";
    private static final String envSecret = "_SECRET";
    private static String currentEnvName ="";
    private static String currentEnvSecret ="";


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


    private void collectAHJson(){
        ParameterizedTypeReference<List<String>> listOfStringsTypeRef = new ParameterizedTypeReference<List<String>>(){};
        //USES A KEY/SECRET

        //this JUST gets the JSON of the current server, nothing more

        //collect then parse the current server you are on
        // character byreferences and all of the information contained based on that
        // wil be processed before moving to the next auction house server

    }

    private void getAHUsers(){
        //USES A KEY/SECRET

        // PARSES CollectAHJson into a LIST/MAP of Users, sends off to get rid of duplicates

    }


    private void getUsersGuilds(){
        //USES A KEY/SECRET

        //PARSES the AHuserList/MAP for a API QUERY for the name of the guild the person is in
        // KILL DUPLICATE GUILD NAMES

    }

    private void getGuildMembers(){
        //USES A KEY/SECRET

        //ITERATE over each guild and place each into a list of users to parse into the database
        //you cannot kill duplicates because of the naming aspect and it will be necessary to find the alts

    }

    private void getMemberInfo(){
        //USES A KEY/SECRET(S)

        //this will be where the primary amount of data will have to be captured


        // currently two queries per person and to be locked up into the sql under that character name

        // pet count, pvp level, possibly more information that comes with those things

    }

    private void findAllMatches(){
        //this can potentially be done via a mysql command that would find users with matching data across all
        //servers within the threasholds set by the user... it seems this is better than pulling data out
        // and trying to parse it.

        //tldr;;
        // SELECT * FROM CHARACTERS WHERE PVP_LEVEL.EQUALS 95 AND WHERE PET_COUNT.EQUALS 949;

        // this would mean the search cannot be based only on one server... and that the user is actually even in a guild right now
        // which isnt guarentesed.... fml.....
    }

    private List killDuplicates(ArrayList userGuildMemberList){
        ArrayList newList = new ArrayList();
        for(int listIndex = 0;listIndex < userGuildMemberList.size();listIndex++){
            if(userGuildMemberList.contains(listIndex)){
                // fix this shit...  kill duplicates, consider a map

            }

        }


        return null;
    }


    // pending the current limitations of the clients and their queries - this will iterate over 50 env set clients and their secret keys
    // this will allow for load balancing the 36,000 requests an hour / 100 requests a second,

    // keep in mind to possibly make a client just for AH pulls, Guild Pulls, and then individual pulls, load balance based
    // on the iterations necessary to achieve that goal


    private void nextClient(String currentEnvKey){
        //this will check if the final client key is being used, if it is, cycle to the first then continue the operations
        if(currentEnvKey.equals(System.getenv(clientPrefix+currentClientIteration+envName))){
            resetIterationClient();
            currentEnvName = System.getenv(clientPrefix+currentClientIteration+envName);
            currentEnvSecret = System.getenv(clientPrefix+currentClientIteration+envSecret);

        }else{
            // increment the midddle number by one, from the current
            currentClientIteration++;
            currentEnvName = (clientPrefix+currentClientIteration+currentEnvName);
            currentEnvSecret = (clientPrefix+currentClientIteration+currentEnvSecret);
        }

    }

    private void resetIterationClient(){
        currentClientIteration = 0;
    }

}
