package io.github.mylk.secret.spawn.service.transformer;

import org.apache.commons.lang3.text.WordUtils;

public class HackishTransformer extends Transformer {
    public String transform(String phrase)
    {
        phrase = before(phrase);
        phrase = WordUtils.capitalizeFully(phrase);

        phrase = phrase
            .replaceAll(" ", "")
            .replaceAll("a", "\\@")
            .replaceAll("e", "3")
            .replaceAll("i", "1")
            .replaceAll("s", "\\$")
            .replaceAll("o", "0");

        phrase = after(phrase);
        return phrase;
    }
}
