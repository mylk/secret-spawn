package io.github.mylk.secret.spawn.service.transformer;

import io.github.mylk.secret.spawn.model.Secret;
import io.github.mylk.secret.spawn.model.Settings;

public class SimpleTransformer extends Transformer {
    public SimpleTransformer(Settings settings) {
        super(settings);
    }

    public Secret transform(Secret secret) {
        String phrase = before(secret.getContentPlain());

        phrase = phrase
            .replaceAll(" ", "_")
            .toLowerCase();

        secret.setContentTransformed(after(phrase));
        return secret;
    }
}
