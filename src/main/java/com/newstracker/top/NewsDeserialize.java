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
        HackerNews car = new HackerNews();
        while(!parser.isClosed()){
            JsonToken jsonToken = parser.nextToken();

            if(JsonToken.FIELD_NAME.equals(jsonToken)){
                String fieldName = parser.getCurrentName();
                //System.out.println(fieldName);

                jsonToken = parser.nextToken();

                if("id".equals(fieldName)){
                    System.out.println("id found");
                    car.setStoryId(parser.getValueAsString());
                } else if ("title".equals(fieldName)){
                    System.out.println("title found");
                    car.setTitle(parser.getValueAsString());
                }else if ("url".equals(fieldName)){
                    System.out.println("url found");
                    car.setUrl(parser.getValueAsString());
                }
            }
        }
        //System.out.println(car);
        return car;
    }
}
