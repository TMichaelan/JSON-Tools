package pl.put.poznan.transformer.logic.tools;

import org.junit.jupiter.api.Test;
import pl.put.poznan.transformer.logic.BaseTest;
import pl.put.poznan.transformer.logic.domain.JSONException;
import pl.put.poznan.transformer.logic.domain.JSONObject;

import static org.junit.jupiter.api.Assertions.*;

class MinifyToolTest extends BaseTest {
    @Test
    void testIsMinifyDecoratorWorking(){
        DecoratorTool pretty = new MinifyTool();
        JSONObject ouput = new JSONObject("pap");
        JSONObject input = new JSONObject(prettyJson1);
        try {
            ouput = pretty.decorate(input);
        } catch (JSONException e) {
            System.err.println(e.getMessage());
        }
        assertEquals(ouput.getJson(), miniJson1);
    }

    @Test
    void testDoesMinifyDecoratorThrowingException(){
        DecoratorTool mini = new MinifyTool();
        assertThrows(JSONException.class,()->mini.decorate(new JSONObject(notAJson)));
    }
}