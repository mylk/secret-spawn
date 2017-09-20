package io.github.mylk.secret.spawn;

import io.github.mylk.secret.spawn.service.common.OptionsReader;
import io.github.mylk.secret.spawn.service.common.Transformer;
import org.apache.commons.cli.*;
import io.github.mylk.secret.spawn.service.parser.Wikipedia;
import io.github.mylk.secret.spawn.service.client.Rest;
import io.github.mylk.secret.spawn.enums.Source;
import io.github.mylk.secret.spawn.enums.Format;

public class SecretSpawn
{
    public static void main(String[] args)
    {
        // defining the options
        Options cliOptions = new Options();
        CommandLine cmd = null;
        cliOptions.addOption("length", true, "The secret's length");
        cliOptions.addOption("source", true, "The secret's source [wikipedia]");
        cliOptions.addOption("format", true, "The secret's format [simple, hackish]");
        cliOptions.addOption("extraRandom", false, "Pick a random phrase out of a paragraph");

        // parse command options, show help when failing
        CommandLineParser optionParser = new DefaultParser();
        try {
            cmd = optionParser.parse(cliOptions, args);
        } catch (ParseException ex) {
            HelpFormatter helpFormatter = new HelpFormatter();
            helpFormatter.printHelp("secret-spawn", cliOptions);
            System.out.println(ex.getMessage());
            System.exit(1);
        }

        OptionsReader options = new OptionsReader();

        // getting config options (cli or configuration file options)
        Integer secretLength = cmd.hasOption("length") ?
                Integer.parseInt(cmd.getOptionValue("length"))
                : Integer.parseInt(options.getOptionValue("secret.length"));

        String source = cmd.hasOption("source")
                ? cmd.getOptionValue("source")
                : options.getOptionValue("secret.source");
        try {
            Source.valueOf(source.toUpperCase());
        } catch (Exception ex) {
            System.out.println("Phrase source not supported.");
            System.exit(1);
        }

        String secretFormat = cmd.hasOption("format")
                ? cmd.getOptionValue("format")
                : options.getOptionValue("secret.format");
        Format format = null;
        try {
            format = Format.valueOf(secretFormat.toUpperCase());
        } catch (Exception ex) {
            System.out.println("Secret type not supported.");
            System.exit(1);
        }

        String url = options.getOptionValue("source.url." + source.toLowerCase());
        if (url.isEmpty()) {
            System.out.println("The URL of the phrase source could not be found.");
            System.exit(1);
        }

        // calling the source
        String response;
        Rest client = new Rest();
        try {
            response = client.get(url);
        } catch (Exception ex) {
            System.out.println("The call to the phrase source failed: " + ex.getMessage());
            System.exit(1);
            return;
        }

        // parse the response
        String content;
        Wikipedia responseParser = new Wikipedia();
        content = responseParser.parse(response);

        Boolean extraRandom = cmd.hasOption("extraRandom");
        Transformer transformer = new Transformer(format, extraRandom);

        // create the secret
        String phrase = transformer.transform(content)
            .substring(0, secretLength);
        System.out.println(phrase);
    }
}
