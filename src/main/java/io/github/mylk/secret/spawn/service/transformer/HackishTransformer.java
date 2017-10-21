package io.github.mylk.secret.spawn.service.transformer;

import io.github.mylk.secret.spawn.model.Secret;
import org.apache.commons.lang3.text.WordUtils;

public class HackishTransformer extends Transformer {
    public Secret transform(Secret secret)
    {
        String phrase = before(secret.getContentPlain());
        phrase = WordUtils.capitalizeFully(phrase);

        phrase = phrase
            .replaceAll(" ", "")
            .replaceAll("a", "\\@")
            .replaceAll("e", "3")
            .replaceAll("i", "1")
            .replaceAll("s", "\\$")
            .replaceAll("o", "0");

        secret.setContentTransformed(after(phrase));
        return secret;
    }
}
