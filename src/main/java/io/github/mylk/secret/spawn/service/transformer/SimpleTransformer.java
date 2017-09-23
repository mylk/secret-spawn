package io.github.mylk.secret.spawn.service.transformer;

public class SimpleTransformer extends Transformer {
    public String transform(String phrase)
    {
        phrase = before(phrase);

        phrase = phrase
            .replaceAll(" ", "_")
            .toLowerCase();

        phrase = after(phrase);
        return phrase;
    }
}
