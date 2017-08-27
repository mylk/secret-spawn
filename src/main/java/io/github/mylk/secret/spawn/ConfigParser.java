package io.github.mylk.secret.spawn;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigParser
{
    private final String PROPERTY_FILENAME = "config.properties";

    public String get(String property) throws Exception, IOException
    {
        String value = "";
        InputStream inputStream;
        Properties properties = new Properties();

        inputStream = getClass().getClassLoader().getResourceAsStream(PROPERTY_FILENAME);
        if (inputStream != null) {
            properties.load(inputStream);
        } else {
            throw new IOException("File '" + PROPERTY_FILENAME + "' not found.");
        }

        try {
            value = properties.getProperty(property);
        } catch (Exception ex) {
            throw new Exception("Configuration property '" + property + "'not found.");
        } finally {
            inputStream.close();
        }

        return value;
    }
}
