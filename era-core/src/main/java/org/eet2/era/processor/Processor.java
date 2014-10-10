package org.eet2.era.processor;

import org.eet2.era.algorithm.Result;

public class Processor {

	private ProcessorContext context;

	public void setContext(ProcessorContext context) {
		this.context = context;
	}

	public Result process() {
		return context.getAlgorithm().run(context.getModel());
	}

}
