package io.github.mylk.secret.spawn.service.transformer;

import io.github.mylk.secret.spawn.model.Secret;

public class SimpleTransformer extends Transformer {
    public Secret transform(Secret secret)
    {
        String phrase = before(secret.getContentPlain());

        phrase = phrase
            .replaceAll(" ", "_")
            .toLowerCase();

        secret.setContentTransformed(after(phrase));
        return secret;
    }
}
