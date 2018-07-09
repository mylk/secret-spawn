package io.github.mylk.secretspawn.service.transformer;

import io.github.mylk.secretspawn.model.Secret;
import io.github.mylk.secretspawn.model.Settings;
import org.apache.commons.lang3.text.WordUtils;

public class HackishTransformer extends Transformer {
    public HackishTransformer(Settings settings) {
        super(settings);
    }

    public Secret transform(Secret secret) {
        secret = preparePhrase(secret);
        String phrase = WordUtils.capitalizeFully(secret.getContentPlain());

        phrase = phrase
                .replaceAll(" ", "")
                .replaceAll("a", "\\@")
                .replaceAll("e", "3")
                .replaceAll("i", "1")
                .replaceAll("s", "\\$")
                .replaceAll("o", "0");

        secret.setContentTransformed(phrase);
        return secret;
    }
}
