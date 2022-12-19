package pl.put.poznan.transformer.logic.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.put.poznan.transformer.logic.domain.JSONException;
import pl.put.poznan.transformer.logic.domain.JSONObject;

import static pl.put.poznan.transformer.logic.tools.JsonParser.parse;
/**
 * The type Json tool Minify.
 */
public class MinifyTool extends DecoratorTool {
    private static final Logger logger = LoggerFactory.getLogger(MinifyTool.class);
    public MinifyTool() {
    }

    /**
     * Instantiates a new Json tool minify.
     *
     * @param tool the IJSONTool to wraparound
     */
    public MinifyTool(IJsonTool tool) {
        super(tool);
    }

    @Override
    public JSONObject decorate(JSONObject json) throws JSONException {
        logger.debug("Decorating!");
        if (wrappee != null) {
            logger.debug("I am a wrapper.");
            return minify(super.decorate(json));
        } else {
            logger.debug("I am a Starting point");
            return minify(json);
        }
    }

    /**
     * Minify json object.
     *
     * @param json the JSONObject for minification
     * @return the minified json object, throws JOSNExeption if json is invalid
     */
    public JSONObject minify(JSONObject json) throws JSONException {
        logger.info("minifying:\n" + json.getJson());
        JSONObject tmpJson = new JSONObject(parse(json.getJson()).toString());
        logger.info("Minification returned:\n" + tmpJson.getJson());
        return tmpJson;
    }
}