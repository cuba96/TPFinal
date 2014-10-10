package org.eet2.era.algorithm;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;

public class Result {

	private Map<String, BigDecimal> values = new HashMap<String, BigDecimal>();
	private boolean isOk;

	public Map<String, BigDecimal> getValues() {
		return values;
	}

	public void setValues(Map<String, BigDecimal> values) {
		this.values = values;
	}

	public boolean isOk() {
		return isOk;
	}

	public void addValue(String name, String value) {
		this.values.put(name, new BigDecimal(value));
	}

	public void addValue(String name, BigDecimal value) {
		this.values.put(name, value);
	}

	public void setOk(boolean isOk) {
		this.isOk = isOk;
	}

	public void serializeTo(String fileName) {
		String template;
		try {
			template = this.readTemplate();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		StringBuilder builder = new StringBuilder();
		builder.append("[");
		for (Map.Entry<String, BigDecimal> entry : values.entrySet()) {
			builder.append("{");
			builder.append("\"label\":\"" + entry.getKey() + "\",");
			builder.append("\"value\":" + entry.getValue().toString());
			builder.append("}");
			builder.append(",");
		}
		builder.append("]");

		template = template.replaceAll("##VALUES##", builder.toString());
		
		File file = new File(fileName);
		try {
			FileUtils.writeStringToFile(file, template);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public String readTemplate() throws IOException {
		StringBuilder builder = new StringBuilder();
		
		BufferedReader br = null;
		InputStreamReader isr = null;
		InputStream is = null;
		
		try {
			is = getClass().getClassLoader().getResourceAsStream("template.html");
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);

			String line;
			while ((line = br.readLine()) != null) {
				builder.append(line+"\r\n");
			}

		} finally {
			if (br != null) {
				br.close();
			}
			
			if (isr != null) {
				isr.close();
			}
			
			if (is != null) {
				is.close();
			}
		}

		return builder.toString();
	}

}
