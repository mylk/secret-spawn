package io.github.mylk.secret.spawn.service.transformer;

import io.github.mylk.secret.spawn.model.Secret;
import io.github.mylk.secret.spawn.model.Settings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Transformer {
    private Settings settings;

    public abstract Secret transform(Secret secret);

    public Transformer(Settings settings) {
        this.settings = settings;
    }

    public Secret before(Secret secret) {
        String phrase = pickPhrase(secret.getContentPlain());

        phrase = phrase
                .trim()
                .replaceAll(" +", " ");

        secret.setContentPlain(phrase);
        return secret;
    }

    public String after(String phrase) {
        return phrase;
    }

    private String pickPhrase(String content) {
        String[] phrases = content.split("\\.");

        // pick the first or a random phrase
        int phrasePosition = 0;
        if (settings.isTooRandom()) {
            phrasePosition = (int) (Math.random() * (phrases.length - 1));
        }
        String phrase = phrases[phrasePosition];

        // cut the phrase as close to the requested length
        List<String> words = new ArrayList<>(Arrays.asList(phrase.split(" ")));
        List<String> wordsSelected = new ArrayList<>();
        Integer wordsSelectedLength = 0;
        for (String word : words) {
            if (wordsSelectedLength >= settings.getLength()) {
                break;
            }

            word = word
                    .replaceAll("\\(.*\\)", "")
                    .replaceAll("[^A-Za-z0-9 .]", "");
            wordsSelected.add(word);

            wordsSelectedLength += word.length() + 1;
        }

        phrase = String.join(" ", wordsSelected);
        return phrase;
    }
}
