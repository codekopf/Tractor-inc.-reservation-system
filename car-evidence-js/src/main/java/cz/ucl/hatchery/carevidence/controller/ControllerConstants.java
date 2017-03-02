package cz.ucl.hatchery.carevidence.controller;

import cz.ucl.hatchery.carevidence.web.CommonConstants;

public class ControllerConstants {

	public static final String SUB_URI_NEW = "/new";
	public static final String SUB_URI_DETAIL_HISTORY = "history";
	public static final String SUB_URI_EDIT = "/edit";
	public static final String SUB_URI_AUDIT = "/audit";
	public static final String SUB_URI_LIST = "/list";
	public static final String SUB_URI_SUCCESS = "/success";
	public static final String SUB_URI_RESULT = "/result";
	public static final String SUB_URI_POPUP = "/popup";
	public static final String SUB_URI_UNLOCK = "/unlock";

	public static final String SUB_URI_PREPARE_EDIT = "/prepare-edit";
	public static final String SUB_URI_PREPARE_OTHER_ACTION = "/prepare-other-action";

	public static final String SUB_URI_PATH_VARIABLE_PREFIX = "/" + CommonConstants.LEFT_CURLY_BRACKET;
	public static final String SUB_URI_PATH_VARIABLE_SUFFIX = CommonConstants.RIGHT_CURLY_BRACKET;

	public static final String PATH_VARIABLE_DETAIL_TYPE = "detailType";

	public static final String VIEW_NAME_PAGE401 = "page401";
	public static final String VIEW_NAME_PAGE403 = "page403";
	public static final String VIEW_NAME_PAGE404 = "page404";
	public static final String VIEW_NAME_PAGE500 = "page500";

	public static final String VIEW_NAME_ERROR = "error";
	// TABLE
	public static final Integer ITEMS_PER_PAGE = 20;

	public static final String URI_ERROR = "/error";
	public static final String URI_ERROR_401 = URI_ERROR + CommonConstants.SLASH + "401";
	public static final String URI_ERROR_403 = URI_ERROR + CommonConstants.SLASH + "403";
	public static final String URI_ERROR_404 = URI_ERROR + CommonConstants.SLASH + "404";
	public static final String URI_ERROR_500 = URI_ERROR + CommonConstants.SLASH + "500";

	// commons message key
	public static final String MSG_KEY_ERROR_SAVE = "error.save";
	public static final String MSG_KEY_NOTICE_SAVE = "notice.save";
	public static final String MSG_KEY_WITHOUT_WF = "businessaction.detail.history.withoutwf";

	public static final String PAGE_NR_1 = "1";

}
