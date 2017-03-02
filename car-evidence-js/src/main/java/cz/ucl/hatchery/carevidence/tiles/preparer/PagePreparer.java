package cz.ucl.hatchery.carevidence.tiles.preparer;

import javax.servlet.http.HttpServletRequest;

import org.apache.tiles.context.TilesRequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cz.ucl.hatchery.carevidence.access.UserSession;
import cz.ucl.hatchery.carevidence.util.UserUtil;
import cz.ucl.hatchery.carevidence.web.RequestParamsConstants;

/**
 * Slouzi pri pripravu atributu pro vykresleni sablony, ktera jsou mimo samotny
 * "content", ktery si pripravuje dany controller.
 */
@Component
public class PagePreparer extends BasicPagePreparer {

	private static final Logger LOG = LoggerFactory.getLogger(PagePreparer.class);

	@Autowired
	private UserSession userSession;

	@Override
	protected void putAttributesToRequest(final TilesRequestContext context, final HttpServletRequest request) {
		if (LOG.isTraceEnabled()) {
			LOG.trace("putAttributesToRequest(...) - start");
		}

		if (userSession.isAuthenticated()) {
			putToRequest(context, RequestParamsConstants.REQ_PARAM_USER,
					UserUtil.getFullName(userSession.getCurrentUser()));
		}

		// last visited uri
		putToRequest(context, RequestParamsConstants.REQ_PARAM_LAST_VISITED_URI, userSession.getLastVisitedURI());

	}

}
