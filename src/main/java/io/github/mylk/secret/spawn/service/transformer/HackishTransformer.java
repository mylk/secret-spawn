package io.github.mylk.secret.spawn.service.transformer;

import io.github.mylk.secret.spawn.model.Secret;
import io.github.mylk.secret.spawn.model.Settings;
import org.apache.commons.lang3.text.WordUtils;

public class HackishTransformer extends Transformer {
    public HackishTransformer(Settings settings) {
        super(settings);
    }

    public Secret transform(Secret secret) {
        secret = before(secret);
        String phrase = WordUtils.capitalizeFully(secret.getContentPlain());

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
