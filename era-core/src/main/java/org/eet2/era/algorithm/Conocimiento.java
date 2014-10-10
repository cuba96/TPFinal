package org.eet2.era.algorithm;

import java.math.BigDecimal;

import org.eet2.era.model.KPI;
import org.eet2.era.model.Model;

public class Conocimiento implements Algorithm {

	private static final BigDecimal MAXIMUM = new BigDecimal(200);

	public Result run(Model model) {
		BigDecimal gplm = model.getValue(KPI.GUSTO_POR_LA_MATERIA);
		BigDecimal material = model.getValue(KPI.MATERIAL);
		
		BigDecimal tg = model.getValue(KPI.TP_GRUPO);
		BigDecimal ts = model.getValue(KPI.TP_SOLO);
	
		BigDecimal didactica = tg.add(ts).divide(new BigDecimal(2));
		
		BigDecimal aec = model.getValue(KPI.ATENCION_EN_CLASE);
		BigDecimal ae = model.getValue(KPI.AYUDA_EXTRA);
		BigDecimal dificultad = model.getValue(KPI.DIFICULTAD);
	
		BigDecimal conocimiento = aec.add(ae).subtract(dificultad).add(new BigDecimal(10)).multiply(new BigDecimal(3.3)) ;
		
		BigDecimal tiempoDedicado = model.getValue(KPI.TIEMPO_DEDICADO);
		BigDecimal tiempoDemandado = model.getValue(KPI.TIEMPO_DEMANDADO);
		
		BigDecimal tiempoTotal = tiempoDedicado.multiply(new BigDecimal(100)).divide(tiempoDemandado);
		
		Result result = new Result();

		result.addValue("CONOCIMIENTO", conocimiento.multiply(MAXIMUM));
		result.addValue("GUSTO_POR_LA_MATERIA", gplm.multiply(MAXIMUM));
		result.addValue("MATERIAL", material.multiply(MAXIMUM));
		result.addValue("TIEMPO_TOTAL", tiempoTotal.multiply(MAXIMUM));
		result.addValue("DIDACTICA", didactica.multiply(MAXIMUM));
		result.setOk(true);
		
		return result;
	}

}

