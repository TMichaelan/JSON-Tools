package pl.put.poznan.transformer.rest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.domain.JSONException;
import pl.put.poznan.transformer.logic.domain.JSONObject;
import pl.put.poznan.transformer.logic.tools.MinifyTool;
import pl.put.poznan.transformer.logic.JSONShowDIff;

import java.util.List;
import java.util.Map;

import static pl.put.poznan.transformer.logic.tools.JsonParser.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/")
public class JSONToolsController {
    private static final Logger logger = LoggerFactory.getLogger(JSONToolsController.class);

    @RequestMapping(value="minify", method = { RequestMethod.POST }, produces = "application/json")
    public ResponseEntity<String> minify(
            @RequestBody String payload
    ) {
        final MinifyTool tool = new MinifyTool();

        logger.info("POST /api/v1/minify");
        JsonNode jsonNode;

        try{
            jsonNode = parse(payload);
        }catch(JSONException exception){
            return ResponseEntity.status(400).body("{\"error\":\"Payload is not valid JSON!\"}");
        }

        try{
            return ResponseEntity.status(200).body(tool.decorate(new JSONObject(jsonNode.toString())).getJson());
        }catch(Exception exception){
            logger.error(exception.toString());
            return ResponseEntity.status(500).body("{\"error\":\"Internal error!\"}");
        }
    }

    @RequestMapping(value="compare", method = { RequestMethod.POST }, produces = "application/json")
    public ResponseEntity<String> compare(
            @RequestBody String payload
    ) {
        final ObjectMapper objectMapper = new ObjectMapper();
        final JSONShowDIff tool = new JSONShowDIff();
        logger.info("POST /api/v1/compare");

        JsonNode jsonNode;

        try{
            jsonNode = parse(payload);
        }catch(JSONException exception){
            return ResponseEntity.status(400).body("{\"error\":\"Payload is not valid JSON!\"}");
        }

        if (jsonNode.size() != 2) {
            return ResponseEntity.status(400).body("{\"error\":\"Request body should contain only 'text1' and 'text2' properties!\"}");
        }

        String[] texts;
        try{
            texts = getTexts(jsonNode);
        }catch(JSONException exception){
            return ResponseEntity.status(400).body("{\"error\":\"Missing or broken 'text1' or 'text2' properties!\"}");
        }

        try{
            return ResponseEntity.status(200).body(objectMapper.writeValueAsString(Map.of("differences", tool.compare(texts[0], texts[1]))));
        }catch(Exception exception){
            logger.error(exception.toString());
            return ResponseEntity.status(500).body("{\"error\":\"Internal error!\"}");
        }
    }
}


