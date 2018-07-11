package io.github.mylk.secretspawn.manager;

import io.github.mylk.secretspawn.exception.SourceCallException;
import io.github.mylk.secretspawn.model.Secret;
import io.github.mylk.secretspawn.model.Settings;
import io.github.mylk.secretspawn.service.client.Rest;
import io.github.mylk.secretspawn.service.parser.Parser;
import io.github.mylk.secretspawn.service.parser.ParserFactory;
import io.github.mylk.secretspawn.service.transformer.Transformer;
import io.github.mylk.secretspawn.service.transformer.TransformerFactory;

public class SecretManager {
    Settings settings;

    public Secret spawn() throws SourceCallException {
        // call the source
        String response;
        Rest client = new Rest();
        try {
            response = client.get(settings.getUrl());
        } catch (Exception ex) {
            throw new SourceCallException("The call to the phrase source failed: " + ex.getMessage());
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

    public void setSettings(Settings settings) {
        this.settings = settings;
    }
}
