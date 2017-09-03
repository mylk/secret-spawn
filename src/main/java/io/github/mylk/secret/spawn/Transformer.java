package io.github.mylk.secret.spawn;

import io.github.mylk.secret.spawn.type.Format;
import org.apache.commons.lang3.text.WordUtils;

public class Transformer
{
    private String secretFormat = "";

    public Transformer(String type)
    {
        secretFormat = type;
    }

    public String transform(String phrase)
    {
        if (secretFormat.equals(Format.Type.SIMPLE.name())) {
            return transformSimple(phrase);
        } else if (secretFormat.equals(Format.Type.HACKISH.name())) {
            return transformHackish(phrase);
        }

        return "";
    }

    private String transformCommon(String phrase)
    {
        return phrase
            .split("\\.")[0]
            .trim()
            .replaceAll("\\(.*\\)", "")
            .replaceAll("[^A-Za-z0-9 .]", "")
            .replaceAll(" +", " ");
    }

    private String transformSimple(String phrase)
    {
        return
            transformCommon(phrase)
            .replaceAll(" ", "_")
            .toLowerCase();
    }

    private String transformHackish(String phrase)
    {
        phrase = transformCommon(phrase);
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
