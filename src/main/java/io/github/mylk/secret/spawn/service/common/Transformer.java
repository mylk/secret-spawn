package io.github.mylk.secret.spawn.service.common;

import io.github.mylk.secret.spawn.enums.Format;
import org.apache.commons.lang3.text.WordUtils;

public class Transformer
{
    private Format format;

    public Transformer(Format format)
    {
        this.format = format;
    }

    public String transform(String phrase)
    {
        String[] phrases = phrase.split("\\.");
        phrase = transformCommon(phrases[0]);

        if (format.equals(Format.SIMPLE)) {
            return transformSimple(phrase);
        } else if (format.equals(Format.HACKISH)) {
            return transformHackish(phrase);
        }

        return "";
    }

    private String transformCommon(String phrase)
    {
        return phrase
            .trim()
            .replaceAll("\\(.*\\)", "")
            .replaceAll("[^A-Za-z0-9 .]", "")
            .replaceAll(" +", " ");
    }

    private String transformSimple(String phrase)
    {
        return phrase
            .replaceAll(" ", "_")
            .toLowerCase();
    }

    private String transformHackish(String phrase)
    {
        phrase = WordUtils.capitalizeFully(phrase);

        phrase = phrase
            .replaceAll(" ", "")
            .replaceAll("a", "\\@")
            .replaceAll("e", "3")
            .replaceAll("i", "1")
            .replaceAll("s", "\\$")
            .replaceAll("o", "0");

        return phrase;
    }
}
