package com.newstracker.top;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class NewsDeserialize extends StdDeserializer<HackerNews> {

    public NewsDeserialize(Class<?> vc) {
        super(vc);
    }

    @Override
    public HackerNews deserialize(JsonParser parser, DeserializationContext deserializer) throws IOException {
        HackerNews hackerNews = new HackerNews();
        while(!parser.isClosed()){
            JsonToken jsonToken = parser.nextToken();

            if(JsonToken.FIELD_NAME.equals(jsonToken)){
                String fieldName = parser.getCurrentName();
                //System.out.println(fieldName);

                jsonToken = parser.nextToken();

                if("id".equals(fieldName)){
                    hackerNews.setStoryId(parser.getValueAsString());
                } else if ("title".equals(fieldName)){
                    hackerNews.setTitle(parser.getValueAsString());
                }else if ("url".equals(fieldName)){
                    hackerNews.setUrl(parser.getValueAsString());
                }
            }
        }
        //System.out.println(car);
        return hackerNews;
    }
}
