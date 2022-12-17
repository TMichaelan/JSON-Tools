package pl.put.poznan.transformer.logic.tools;

import com.fasterxml.jackson.databind.ObjectMapper;
import pl.put.poznan.transformer.logic.domain.JSONException;
import pl.put.poznan.transformer.logic.domain.JSONObject;

/**
 * The type Json tool decorator.
 */
abstract class DecoratorTool implements IJsonTool {

    /**
     * The Object mapper.
     */
    protected final ObjectMapper objectMapper = new ObjectMapper();
    /**
     * The Wrappee.
     */
    protected IJsonTool wrappee = null;

    public DecoratorTool() {

    }

    /**
     * Instantiates a new Json tool decorator.
     *
     * @param tool the tool
     */
    public DecoratorTool(IJsonTool tool) {
        this.wrappee = tool;
    }

    public JSONObject decorate(JSONObject json) throws JSONException {
        if (wrappee != null)
            return wrappee.decorate(json);
        else return json;
    }




}