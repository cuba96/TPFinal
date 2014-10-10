package org.eet2.era.processor;

import org.eet2.era.algorithm.Algorithm;
import org.eet2.era.model.Model;

public class ProcessorContext {

	private Algorithm algorithm;
	private Model model;

	public void setAlgorithm(Algorithm algorithm) {
		this.algorithm = algorithm;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public Algorithm getAlgorithm() {
		return this.algorithm;
	}

	public Model getModel() {
		return this.model;
	}

}
