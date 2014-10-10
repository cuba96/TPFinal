package org.eet2.era;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.eet2.era.algorithm.Algorithm;
import org.eet2.era.algorithm.AlgorithmFactory;
import org.eet2.era.algorithm.Result;
import org.eet2.era.model.Model;
import org.eet2.era.processor.Processor;
import org.eet2.era.processor.ProcessorContext;

/**
 * entry point
 */
public class App {

	public static void main(String[] args) {
		Options options = createOptions();
		CommandLineParser parser = new BasicParser();
		try {
			CommandLine cmd = parser.parse(options, args);

			String algorithm = cmd.getOptionValue("algorithm");
			String model = cmd.getOptionValue("model");
			String result = cmd.getOptionValue("result");

			new App().execute(model, algorithm, result);
			
		} catch (ParseException e) {
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp("java -jar era-1.0.jar ", options);
			System.exit(-1);
		}
	}

	private static Options createOptions() {
		Options options = new Options();
		// @formatter:off
		Option resultFile = new Option("result", true, "use given file for results");
		resultFile.isRequired();
		
		Option modelFile = new Option("model", true, "use given file for model");
		modelFile.isRequired();
		
		Option algorithm = new Option("algorithm", true, "use given algorithm");
		algorithm.isRequired();
		// @formatter:on

		options.addOption(resultFile);
		options.addOption(modelFile);
		options.addOption(algorithm);
		return options;
	}

	private void execute(String modelFile, String algorithmType,
			String resultFile) {

		System.out.println("starting...");
		Model model = new Model();
		model.loadFromFile(modelFile);

		Algorithm algorithm = AlgorithmFactory.getAlgorithm(algorithmType);

		System.out.println("setting up context...");
		ProcessorContext context = new ProcessorContext();
		context.setAlgorithm(algorithm);
		context.setModel(model);

		System.out.println("creating processor...");
		Processor processor = new Processor();
		processor.setContext(context);

		System.out.println("processing data...");
		Result actualResult = processor.process();

		actualResult.serializeTo(resultFile);
		System.out.println("done.");
	}

}
