package io.github.mylk.secret.spawn.Manager;

import io.github.mylk.secret.spawn.model.Secret;
import io.github.mylk.secret.spawn.model.Settings;
import io.github.mylk.secret.spawn.service.client.Rest;
import io.github.mylk.secret.spawn.service.parser.Parser;
import io.github.mylk.secret.spawn.service.parser.ParserFactory;
import io.github.mylk.secret.spawn.service.transformer.Transformer;
import io.github.mylk.secret.spawn.service.transformer.TransformerFactory;

public class SpawnManager {
    Settings settings;

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public Secret spawn() {
        // call the source
        String response;
        Rest client = new Rest();
        try {
            response = client.get(settings.getUrl());
        } catch (Exception ex) {
            System.out.println("The call to the phrase source failed: " + ex.getMessage());
            System.exit(1);
            return null;
        }

        // parse the response
        ParserFactory parserFactory = new ParserFactory(settings);
        Parser responseParser = parserFactory.getParser();
        Secret secret = new Secret();
        secret = responseParser.parse(response, secret);

        // create the secret string
        TransformerFactory transformerFactory = new TransformerFactory(settings);
        Transformer transformer = transformerFactory.getTransformer();
        secret = transformer.transform(secret);

        return secret;
    }
}
