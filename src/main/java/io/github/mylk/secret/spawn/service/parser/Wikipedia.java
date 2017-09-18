package io.github.mylk.secret.spawn.service.parser;

import org.json.*;
import java.util.Iterator;

public class Wikipedia
{
    public String parse(String json)
    {
        String content = "";

        JSONObject jsonObj = new JSONObject(json);
        JSONObject pagesElement = jsonObj.getJSONObject("query").getJSONObject("pages");

        Iterator<String> keys = pagesElement.keys();
        // get the first key of the json object
        JSONObject item = pagesElement.getJSONObject(keys.next());
        content = item.getString("extract");

        return content;
    }
}
