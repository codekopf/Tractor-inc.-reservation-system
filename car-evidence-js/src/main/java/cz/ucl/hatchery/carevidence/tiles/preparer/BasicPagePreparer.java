package cz.ucl.hatchery.carevidence.tiles.preparer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import org.apache.tiles.AttributeContext;
import org.apache.tiles.context.TilesRequestContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cz.ucl.hatchery.carevidence.access.UserSession;
import cz.ucl.hatchery.carevidence.tiles.LanguageMenuProvider;
import cz.ucl.hatchery.carevidence.web.RequestParamsConstants;

/**
 * Prepare basic context that is common for all costum views. E.g. property
 * 'baseHref'.
 *
 * @author unknown
 */
public class BasicPagePreparer implements ViewPreparer {

	static final Logger LOGGER = LoggerFactory.getLogger(BasicPagePreparer.class);

	@Autowired
	protected UserSession userSession;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(final TilesRequestContext context, final AttributeContext attributeContext) {
		final PageContext pageContext = getPageContext(context);
		HttpServletRequest request;
		try {
			request = getServletRequest(pageContext);
		} catch (final NullPointerException e) {
			LOGGER.error("pageContext is null, cannot prepare stuff for tiles", e);
			return;
		}

		putBasicInfoToRequest(context, request);
		putAttributesToRequest(context, request);
	}

	protected void putAttributesToRequest(final TilesRequestContext context, final HttpServletRequest request) {
		// nothing to do
	}

	protected void putBasicInfoToRequest(final TilesRequestContext context, final HttpServletRequest request) {
		putToRequest(context, RequestParamsConstants.REQ_PARAM_BASE_HREF, request.getContextPath());
		putToRequest(context, RequestParamsConstants.REQ_PARAM_LANG_MENU,
				(new LanguageMenuProvider()).getMenuItems(request));
	}

	protected void putToRequest(final TilesRequestContext context, final String key, final Object o) {
		context.getRequestScope().put(key, o);
	}

	private HttpServletRequest getServletRequest(final PageContext pageContext) {
		return (HttpServletRequest) pageContext.getRequest();
	}

	private PageContext getPageContext(final TilesRequestContext context) {
		PageContext pageContext = null;

		for (final Object o : context.getRequestObjects()) {

			if (o instanceof PageContext) {
				pageContext = (PageContext) o;
				break;
			}

		}

		return pageContext;
	}

}