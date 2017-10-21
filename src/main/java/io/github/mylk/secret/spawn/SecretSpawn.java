package io.github.mylk.secret.spawn;

import org.apache.commons.cli.*;
import io.github.mylk.secret.spawn.service.common.OptionsReader;
import io.github.mylk.secret.spawn.service.transformer.TransformerFactory;
import io.github.mylk.secret.spawn.service.transformer.Transformer;
import io.github.mylk.secret.spawn.service.parser.ParserFactory;
import io.github.mylk.secret.spawn.service.parser.Parser;
import io.github.mylk.secret.spawn.service.client.Rest;
import io.github.mylk.secret.spawn.model.Secret;

public class SecretSpawn {
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

        // getting config options and instantiate objects
        Integer secretLength = cmd.hasOption("length")
            ? Integer.parseInt(cmd.getOptionValue("length"))
            : Integer.parseInt(options.getOptionValue("secret.length"));

        String source = cmd.hasOption("source")
            ? cmd.getOptionValue("source")
            : options.getOptionValue("secret.source");

        String format = cmd.hasOption("format")
            ? cmd.getOptionValue("format")
            : options.getOptionValue("secret.format");

        String url = options.getOptionValue("source.url." + source.toLowerCase());
        if (url.isEmpty()) {
            System.out.println("The URL of the phrase source could not be found.");
            System.exit(1);
        }

        Boolean extraRandom = cmd.hasOption("extraRandom");

        // instantiate stuff
        ParserFactory parserFactory = new ParserFactory();
        Parser responseParser = parserFactory.getParser(source);

        TransformerFactory transformerFactory = new TransformerFactory();
        Transformer transformer = transformerFactory.getTransformer(format);

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
        Secret secret = new Secret();
        secret = responseParser.parse(response, secret);

        // create the secret string
        secret = transformer
            .setExtraRandom(extraRandom)
            .setSecretLength(secretLength)
            .transform(secret);

        System.out.printf("Secret:%n%s%n%n", secret.getContentTransformed());
        System.out.printf("Title:%n%s%n%n", secret.getTitle());
        System.out.printf("Original content:%n%s%n%n", secret.getContentPlain());
        System.out.printf("URL:%n%s%n%n", secret.getUrl());
    }
}
