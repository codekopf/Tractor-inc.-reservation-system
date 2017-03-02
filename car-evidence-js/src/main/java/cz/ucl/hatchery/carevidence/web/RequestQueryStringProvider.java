package cz.ucl.hatchery.carevidence.web;

import java.util.LinkedHashMap;
import java.util.Map;

public class RequestQueryStringProvider {

	private Map<String, String[]> parameterMap = new LinkedHashMap<String, String[]>();
	private String baseUrl = "";

	public void setParameterMap(final Map<String, String[]> parameterMap) {
		this.parameterMap = new LinkedHashMap<String, String[]>(parameterMap);
	}

	public void addParameter(final String key, final String[] value) {
		this.parameterMap.put(key, value);
	}

	public void setBaseUrl(final String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public UrlStringProvider getQueryString() {
		return new UrlStringProvider(this.baseUrl, this.parameterMap);

	}

}
