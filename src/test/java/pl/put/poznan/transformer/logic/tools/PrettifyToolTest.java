package pl.put.poznan.transformer.logic.tools;

import org.junit.jupiter.api.Test;
import pl.put.poznan.transformer.logic.BaseTest;
import pl.put.poznan.transformer.logic.domain.JSONException;
import pl.put.poznan.transformer.logic.domain.JSONObject;

import static org.junit.jupiter.api.Assertions.*;


class JSONToolPrettifyTest extends BaseTest {
    @Test
    void testIsPrettufyDecoratorThrowingExepcion(){
        DecoratorTool pretty = new PrettifyTool();
        assertThrows(JSONException.class,()->pretty.decorate(new JSONObject(notAJson)));
    }
}