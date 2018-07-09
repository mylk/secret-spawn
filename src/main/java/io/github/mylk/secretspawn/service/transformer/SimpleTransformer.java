package io.github.mylk.secretspawn.service.transformer;

import io.github.mylk.secretspawn.model.Secret;
import io.github.mylk.secretspawn.model.Settings;

public class SimpleTransformer extends Transformer {
    public SimpleTransformer(Settings settings) {
        super(settings);
    }

    public Secret transform(Secret secret) {
        secret = preparePhrase(secret);

        String phrase = secret.getContentPlain()
                .replaceAll("(^ )|( $)", "")
                .replaceAll(" ", "_")
                .toLowerCase();

        secret.setContentTransformed(phrase);
        return secret;
    }
}
