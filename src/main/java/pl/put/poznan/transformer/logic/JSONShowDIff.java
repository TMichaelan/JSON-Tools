package pl.put.poznan.transformer.logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * The type Json show diff.
 */
public class JSONShowDIff {
    private final static Logger logger = LoggerFactory.getLogger(JSONShowDIff.class);

    public JSONShowDIff(){
    }

    public String compare(String text1, String text2) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String j1 = null;
        String j2 = null;
        try {
            j1 = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(text1);
            j2 = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(text2);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        TypeReference<Map<String, Object>> type = new TypeReference<>() {
        };

        Map<String, Object> map1 = objectMapper.readValue(j1, type);
        Map<String, Object> map2 = objectMapper.readValue(j2, type);
//        MapDifference<String, Object> difference = Maps.difference(map1, map2);

        StringBuilder cmpString = new StringBuilder();
        cmpString.append("Keys and values in common:\n");

//        difference.entriesInCommon().forEach((key, value) -> cmpString.append("\t- " + key + ": " + value + "\n"));
//        cmpString.append("\nKeys only in the first JSON:\n");
//        difference.entriesOnlyOnLeft().forEach((key, value) -> cmpString.append("\t- " + key + ": " + value + "\n"));
//        cmpString.append("\nKeys only in the second JSON:\n");
//        difference.entriesOnlyOnRight().forEach((key, value) -> cmpString.append("\t- " + key + ": " + value + "\n"));
//        cmpString.append("\nKeys the same but different value:\n");
//        difference.entriesDiffering().forEach((key, value) -> cmpString.append("\t- " + key + ": " + value + "\n"));

        return cmpString.toString();
    }
}
