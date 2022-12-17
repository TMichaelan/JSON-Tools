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
}
