package io.github.mylk.secret.spawn.service.transformer;

import io.github.mylk.secret.spawn.model.Secret;
import io.github.mylk.secret.spawn.model.Settings;

public abstract class Transformer {
    private Settings settings;

    public abstract Secret transform(Secret secret);

    public Transformer(Settings settings) {
        this.settings = settings;
    }

    public String before(String phrase) {
        String[] phrases = phrase.split("\\.");
        if (settings.isTooRandom()) {
            phrase = phrases[(int) (Math.random() * (phrases.length - 1))];
        } else {
            phrase = phrases[0];
        }

        return phrase
                .trim()
                .replaceAll("\\(.*\\)", "")
                .replaceAll("[^A-Za-z0-9 .]", "")
                .replaceAll(" +", " ");
    }

    public String after(String phrase) {
        return phrase.substring(0, settings.getLength());
    }
}
