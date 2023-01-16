package pl.put.poznan.transformer.logic.tools;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.put.poznan.transformer.logic.domain.JSONException;

public class JsonParser {
    /**
     * Parse string into JsonNode object
     *
     * @param json string with data in JSON format
     * @return JsonNode
     * @throws JSONException if json is invalid or is not the object
     */
    static public JsonNode parse(String json) throws JSONException {
        final ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode node = objectMapper.readTree(json);
            if (node.isObject()) return node;
        } catch (Exception exception) {
            throw new JSONException("Invalid JSON" + exception.getMessage());
        }
        throw new JSONException("Invalid JSON - input is not object");
    }

    static public String[] getTexts(JsonNode json) throws JSONException {
        String[] texts = new String[2];
        try {
            JsonNode jsonText1 = json.get("text1");
            if (!jsonText1.isTextual()) throw new JSONException("Invalid JSON - key 'text1' is not a text");
            texts[0] = jsonText1.asText();

            JsonNode jsonText2 = json.get("text2");
            if (!jsonText2.isTextual()) throw new JSONException("Invalid JSON - key 'text2' is not a text");
            texts[1] = jsonText2.asText();
            return texts;
        } catch (Exception exception) {
            throw new JSONException("Invalid JSON" + exception.getMessage());
        }
    }
}
