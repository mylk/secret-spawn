package io.github.mylk.secret.spawn;

import io.github.mylk.secret.spawn.parser.Wikipedia;
import io.github.mylk.secret.spawn.client.Rest;
import org.apache.commons.cli.*;

public class SecretSpawn
{
    public static void main(String[] args)
    {
        // defining the options
        Options options = new Options();
        CommandLine cmd = null;
        options.addOption("length", true, "The secret's length");

        // parse command options, show help when failing
        CommandLineParser optionParser = new DefaultParser();
        try {
            cmd = optionParser.parse(options, args);
        } catch (ParseException ex) {
            HelpFormatter helpFormatter = new HelpFormatter();
            helpFormatter.printHelp("secret-spawn", options);
            System.out.println(ex.getMessage());
            System.exit(1);
        }

        // getting config options (command options or configuration)
        String url = "";
        Integer secretLength = null;
        ConfigParser config = new ConfigParser();
        try {
            url = config.get("source.url.wikipedia");
            secretLength = Integer.parseInt(config.get("secret.length"));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.exit(1);
        }
        if (cmd.hasOption("length")) {
            secretLength = Integer.parseInt(cmd.getOptionValue("length"));
        }

        // calling the source
        String response;
        Rest client = new Rest();
        try {
            response = client.get(url);
        } catch (Exception ex) {
            System.out.println("Something went wrong with the call to the phrase source: " + ex.getMessage());
            System.exit(1);
            return;
        }

        // parse the response
        String content;
        Wikipedia responseParser = new Wikipedia();
        content = responseParser.parse(response);

        // create the secret
        String phrase = content
            .split("\\.")[0]
            .trim()
            .replaceAll("[^A-Za-z0-9 .]", "")
            .replaceAll(" +", " ")
            .replaceAll(" ", "_")
            .toLowerCase()
            .substring(0, secretLength);
        System.out.println(phrase);
    }
}
