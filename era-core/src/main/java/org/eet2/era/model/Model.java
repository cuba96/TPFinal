package org.eet2.era.model;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Model {

	private Map<KPI, BigDecimal> kpis = new HashMap<KPI, BigDecimal>();

	public void add(KPI kpi, BigDecimal value) {
		kpis.put(kpi, value);
	}

	public BigDecimal getValue(KPI kpi) {
		return this.kpis.get(kpi);
	}

	public void loadFromFile(String fileName) {
		try {
			this.kpis.clear();
			
			ClassLoader loader = this.getClass().getClassLoader();
			InputStream stream = loader.getResourceAsStream(fileName);
			Properties props = new Properties();
			props.load(stream);

			for (KPI kpi : KPI.values()) {
				if (props.containsKey(kpi.name())) {
					kpis.put(kpi, new BigDecimal(props.getProperty(kpi.name())));
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
