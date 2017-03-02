package cz.ucl.hatchery.carevidence.web;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

public class UrlStringProvider {

	private Map<String, String[]> parameterMap;
	private String baseUrl;

	public UrlStringProvider(final String baseUrl) {
		this.baseUrl = baseUrl;
		this.parameterMap = new LinkedHashMap<String, String[]>();
	}

	public UrlStringProvider(final String baseUrl, final Map<String, String[]> parameterMap) {
		this.baseUrl = baseUrl;
		this.parameterMap = new LinkedHashMap<String, String[]>(parameterMap);
	}

	public void setBaseUrl(final String baseUrl) {
		this.baseUrl = baseUrl;
	}

	@Override
	public String toString() {
		final List<String> parameters = new ArrayList<String>();

		synchronized (parameterMap) {

			for (final String parameterName : parameterMap.keySet()) {
				final String[] values = parameterMap.get(parameterName);
				for (final String value : values) {
					try {
						parameters.add(parameterName + "=" + URLEncoder.encode(value, CommonConstants.ENCODING_UTF8));
					} catch (final UnsupportedEncodingException e) {
						parameters.add(parameterName + "=" + value);
					}
				}
			}
		}

		final String paramString = StringUtils.join(parameters, CommonConstants.AMPERSAND);

		if (StringUtils.isEmpty(paramString)) {
			return StringUtils.defaultIfEmpty(baseUrl, CommonConstants.EMPTY_STRING);
		}

		return StringUtils.defaultIfEmpty(baseUrl, CommonConstants.EMPTY_STRING) + CommonConstants.QUESTION_MARK
				+ paramString;
	}

	public void setParameter(final String parameterName, final String[] parameterValues) {
		synchronized (parameterMap) {
			parameterMap.put(parameterName, parameterValues);
		}
	}

	public String[] getParameter(final String parameterName) {
		String[] ret = null;
		synchronized (parameterMap) {
			ret = parameterMap.get(parameterName);
		}
		return ret;
	}

	public void addParameter(final String parameterName, final String parameterValue) {
		synchronized (parameterMap) {
			final String[] values = parameterMap.get(parameterName);
			if (values == null) {
				parameterMap.put(parameterName, new String[] { parameterValue });
			} else {
				parameterMap.put(parameterName, ArrayUtils.add(values, parameterValue));
			}
		}
	}

	public void clearParameters() {
		synchronized (parameterMap) {
			parameterMap.clear();
		}
	}

	public Map<String, String[]> getParametersMap() {
		return new LinkedHashMap<String, String[]>(parameterMap);
	}

}
