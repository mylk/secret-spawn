package io.github.mylk.secret.spawn.service.transformer;

public abstract class Transformer {
    private Boolean extraRandom;
    private Integer secretLength;

    public abstract String transform(String phrase);

    public Transformer setExtraRandom(Boolean extraRandom)
    {
        this.extraRandom = extraRandom;
        return this;
    }

    public Transformer setSecretLength(Integer secretLength)
    {
        this.secretLength = secretLength;
        return this;
    }

    public String before(String phrase)
    {
        String[] phrases = phrase.split("\\.");
        if (extraRandom) {
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

    public String after(String phrase)
    {
        return phrase.substring(0, secretLength);
    }
}
