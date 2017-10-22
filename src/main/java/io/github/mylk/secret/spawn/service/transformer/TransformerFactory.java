package io.github.mylk.secret.spawn.service.transformer;

import io.github.mylk.secret.spawn.enums.Format;
import io.github.mylk.secret.spawn.model.Settings;
import org.apache.commons.lang3.NotImplementedException;

public class TransformerFactory {
    private Settings settings;

    public TransformerFactory(Settings settings) {
        this.settings = settings;
    }

    public Transformer getTransformer() {
        Transformer transformer;

        if (settings.getFormat().toUpperCase().equals(Format.SIMPLE.name())) {
            transformer = new SimpleTransformer(settings);
        } else if (settings.getFormat().toUpperCase().equals(Format.HACKISH.name())) {
            transformer = new HackishTransformer(settings);
        } else {
            throw new NotImplementedException("Format not implemented yet.");
        }

        return transformer;
    }
}
