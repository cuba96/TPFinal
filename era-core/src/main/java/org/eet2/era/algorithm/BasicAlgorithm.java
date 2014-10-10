package org.eet2.era.algorithm;

import java.math.BigDecimal;

import org.eet2.era.model.KPI;
import org.eet2.era.model.Model;

public class BasicAlgorithm implements Algorithm {

	private static final BigDecimal MAXIMUM = new BigDecimal(200);

	public Result run(Model model) {
		BigDecimal aec = model.getValue(KPI.ATENCION_EN_CLASE);
		BigDecimal dec = model.getValue(KPI.DISTRACCION_EN_CLASE);
		BigDecimal tdm = model.getValue(KPI.TIEMPO_DEMANDADO);
		BigDecimal tem = model.getValue(KPI.TIEMPO_EMPLEADO);
		BigDecimal pg = model.getValue(KPI.PARTICIPACION);

		//TODO aplicar formula sobre los KPIs
		
		Result result = new Result();
		
		result.addValue("ATENCION_EN_CLASE", aec.multiply(MAXIMUM));
		result.addValue("DISTRACCION_EN_CLASE", dec.multiply(MAXIMUM));
		result.addValue("TIEMPO_DEMANDADO", tdm.multiply(MAXIMUM));
		result.addValue("TIEMPO_EMPLEADO", tem.multiply(MAXIMUM));
		result.addValue("PARTICIPACION", pg.multiply(MAXIMUM));
		
		result.setOk(true);
		
		return result;
	}

}
