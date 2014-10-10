package org.eet2.era;

import org.eet2.era.algorithm.BasicAlgorithm;
import org.eet2.era.algorithm.Result;
import org.eet2.era.model.Model;
import org.eet2.era.processor.Processor;
import org.eet2.era.processor.ProcessorContext;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

	@Test
	public void testSerializeResult() {
		// given
		boolean expectedResultStatus = true;
		
		Model model = new Model();
		model.loadFromFile("model.properties");

		BasicAlgorithm algorithm = new BasicAlgorithm();

		ProcessorContext context = new ProcessorContext();
		context.setAlgorithm(algorithm);
		context.setModel(model);

		Processor processor = new Processor();
		processor.setContext(context);
		Result actualResult = processor.process();

		// when
		actualResult.serializeTo("result.html");
		
		// then
		Assert.assertEquals(expectedResultStatus, actualResult.isOk());
	}

}
