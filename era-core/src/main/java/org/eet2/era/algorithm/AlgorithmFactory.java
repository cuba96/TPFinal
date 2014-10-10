package org.eet2.era.algorithm;

public class AlgorithmFactory {

	private AlgorithmFactory() {
	}

	public static Algorithm getAlgorithm(String algorithmType) {
		if (algorithmType == null) {
			return null;
		}
		
		if (algorithmType.equalsIgnoreCase("basic")) {
			return new BasicAlgorithm();
		} else if (algorithmType.equalsIgnoreCase("conocimiento")) {
			return new Conocimiento();
		}
			else if (algorithmType.equalsIgnoreCase("performance")) {
			return new PerformanceAlgorithm();
		}

		throw new RuntimeException("non existing algorithm");
	}

}
