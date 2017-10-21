package io.github.mylk.secret.spawn.service.parser;

import org.json.*;
import java.util.Iterator;
import io.github.mylk.secret.spawn.model.Secret;

public class WikipediaParser implements Parser {
    public Secret parse(String json, Secret secret)
    {
        JSONObject jsonObj = new JSONObject(json);
        JSONObject pagesElement = jsonObj.getJSONObject("query").getJSONObject("pages");

        Iterator<String> keys = pagesElement.keys();
        // get the first key of the json object
        JSONObject item = pagesElement.getJSONObject(keys.next());

        secret.setTitle(item.getString("title"));
        secret.setContentPlain(item.getString("extract"));
        secret.setUrl("https://en.wikipedia.org/wiki/" + secret.getTitle().replaceAll(" ", "_"));

        return secret;
    }
}
