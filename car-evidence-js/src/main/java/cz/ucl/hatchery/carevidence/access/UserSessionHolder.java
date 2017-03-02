package cz.ucl.hatchery.carevidence.access;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.beanutils.ConvertUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope(value = "session")
public class UserSessionHolder implements Serializable {

	private static final long serialVersionUID = -4512568868181713558L;

	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(UserSessionHolder.class);

	private Locale userLocale;
	private CustomUser currectUser = new CustomUser();

	private Map<String, Object> properties = new HashMap<String, Object>();

	private String lastVisitedUri = "";
	private String actualUri = "";

	private CustomUser impersonator;

	public Locale getUserLocale() {
		return userLocale;
	}

	public void setUserLocale(final Locale userLocale) {
		this.userLocale = userLocale;
	}

	public CustomUser getCurrectUser() {
		return currectUser;
	}

	public void setCurrectUser(final CustomUser currectUser) {
		this.currectUser = currectUser;
	}

	protected CustomUser getImpersonator() {
		return impersonator;
	}

	protected void setImpersonator(final CustomUser impersonator) {
		this.impersonator = impersonator;
	}

	public String getLastVisitedUri() {
		return lastVisitedUri;
	}

	public void setActualURI(final String uri) {
		lastVisitedUri = actualUri;
		actualUri = uri;
	}

	/*
	 * public void setLastVisitedUri(String lastVisitedUri) {
	 * this.lastVisitedUri = lastVisitedUri; }
	 */
	public void setProperty(final String key, final Object object) {
		this.properties.put(key, object);
	}

	public Object getProperty(final String key) {
		return this.properties.get(key);
	}

	@SuppressWarnings("unchecked")
	public <T> T getProperty(final String key, final Class<T> clazz) {
		return (T) (clazz.isInstance(this.properties.get(key)) ? this.properties.get(key)
				: ConvertUtils.convert(this.properties.get(key), clazz));
	}

}
