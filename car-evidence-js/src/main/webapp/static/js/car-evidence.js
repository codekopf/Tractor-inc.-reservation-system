var isIe = /MSIE (\d+\.\d+);/.test(navigator.userAgent);
if (!window.console) {
	window.console = {
		log : function() {

		}
	}
}

String.prototype.reverse = function() {
	var newCena = new String();
	for (var j = this.length - 1; j > -1; j--) {
		newCena += this.substr(j, 1);
	}
	return newCena.valueOf();
};
String.prototype.price = function() {
	return this.reverse().replace(/(\d{3})/gi, "$1 ").reverse().replace(/\./gi,
			",").replace(/,(\d{1})$/gi, ",$10").valueOf();
};
String.prototype.toFloat = function() {
	return parseFloat(this.replace(/,/, ".").replace(/(\s|\xA0)/g, ""))
			.valueOf();
};
String.prototype.trim = function() {
	return this.replace(/^\s+|\s+$/g, '');
};
/*
 * decimal_sep: character used as deciaml separtor, it defaults to '.' when
 * omitted thousands_sep: char used as thousands separator, it defaults to ','
 * when omitted
 */
Number.prototype.toMoney = function(decimals, decimal_sep, thousands_sep) {
	var n = this, c = isNaN(decimals) ? 2 : Math.abs(decimals), // if decimal is
	// zero we
	// must take it, it means
	// user does not want to
	// show any decimal
	d = decimal_sep || '.', // if no decimal separator is passed we use the dot
	// as default decimal separator (we MUST use a
	// decimal separator)

	/*
	 * according to
	 * [http://stackoverflow.com/questions/411352/how-best-to-determine-if-an-argument-is-not-sent-to-the-javascript-function]
	 * the fastest way to check for not defined parameter is to use typeof value
	 * === 'undefined' rather than doing value === undefined.
	 */
	t = (typeof thousands_sep === 'undefined') ? ',' : thousands_sep, // if
	// you
	// don't
	// want
	// to
	// use a
	// thousands
	// separator
	// you
	// can
	// pass
	// empty
	// string
	// as
	// thousands_sep
	// value

	sign = (n < 0) ? '-' : '',

	// extracting the absolute value of the integer part of the number and
	// converting to string
	i = parseInt(n = Math.abs(n).toFixed(c)) + '',

	j = ((j = i.length) > 3) ? j % 3 : 0;
	return sign + (j ? i.substr(0, j) + t : '')
			+ i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + t)
			+ (c ? d + Math.abs(n - i).toFixed(c).slice(2) : '');
};

var CarEvidence = {};

CarEvidence.validation = {};

CarEvidence.validation.isFormValidated = function(formEl) {
	if (formEl.hasClass("validated")) {
		formEl.validationEngine("hideAll");
		if (!formEl.validationEngine("validate")) {
			return false;
		}
	}
	return true;
};

/* Popup definition */
CarEvidence.PopUpElement = {
	elementId : "#popup",
	dialog : null,
	defaultParams : {
		modal : true,
		resizable : false,
		width : 450,
		height : 'auto'
	},
	init : function() {
		if ($(this.elementId)[0] == null) {
			$("body").append("<div id=\"popup\"></div>");
		}
	},
	getElement : function() {
		return $(this.elementId);
	},
	setContent : function(content) {
		this.resetDialog();
		this.setHTMLContent(content);
	},
	setHTMLContent : function(content) {
		this.getElement().html(content);
	},
	openDialog : function(params, script) {
		
		for (key in params) {
			if (params[key] == "false") {
				params[key] = false;
			}
			if (params[key] == "true") {
				params[key] = true;
			}
			if (!isNaN(parseInt(params[key]))) {
				params[key] = parseInt(params[key]);
			}
		}
		var usedParams = jQuery.extend({}, this.defaultParams, params);
		this.dialog = this.getElement().dialog(usedParams);
		// Override default 0 value for correct min layout
		this.getElement().css('min-height', '35px');

		if (script != null) {
			eval(script);
		}
	},
	setOption : function(key, value) {
		this.getElement().dialog("option", key, value);
	},
	closeDialog : function() {
		if (this.getElement().is(":visible")) {
			this.getElement().dialog("close");
		}
	},
	resetDialog : function() {
		if (this.dialog != null) {
			this.closeDialog();
			this.getElement().remove();
			this.init();
		}
	},
	showContent : function() {
		this.getElement().children().css("visibility", "visible");
	},
	hideContent : function() {
		this.getElement().children().css("visibility", "hidden");
	},

	resize : function(requestedWidth, requestedHeight) {
		this.hideContent();
		var popupElem = this.getElement();
		popupElem.css('width', requestedWidth);

		var popupChildren = popupElem.children();
		var newHeight = 0;
		var newWidth = popupElem.outerWidth();
	
		$.each(popupChildren, function(i, child) {
			newHeight += $(child).outerHeight(true) || 0;
		});
	
		newHeight = newHeight || requestedHeight;

		popupElem.css('height', newHeight);
		var heightWithHeader = newHeight + $('.ui-widget-header').outerHeight();

		$('.ui-dialog').animate({
			left : ($(window).width() / 2) - (newWidth / 2),
			top : ($(window).height() / 2) - (heightWithHeader / 2),
			width : newWidth
		}, {
			duration : 350,
			complete : function() {
				CarEvidence.PopUpElement.showContent();
			}
		});
	}
}

/*
 * Common PopUp - after click on the element, takes html content of pop window
 * from ajax response
 */
var PopUp = function(element, params) {
	CarEvidence.PopUpElement.init();
	var elem;
	if (element == undefined) {
		return;
	}
	if (jQuery.type(element) === "string") {
		elem = $("#" + element);
		if (elem[0] == null) {
			return;
		}
	} else {
		elem = $(element);
	}
	this.init(elem, params);
};
PopUp.prototype.init = function(elem, params) {
	this.type = jQuery.extend({}, params);
	this.type.url = elem[0].href;
	this.addRndToUrl();
	elem[0].href = "#";
	var PP = this;
	$(elem).click(function(e) {
		return PP.dispatch(e);
	});
};
PopUp.prototype.addRndToUrl = function() {
	// to avoid IE cache
	this.type.url += (this.type.url.indexOf("?") >= 0 ? "&" : "?") + "rnd="
			+ Math.random();
};
PopUp.prototype.dispatch = function(e) {
	var opt = this.getAjaxOptions(e, this.type);
	var PP = this;
	if (this.type.openBefore) {
		CarEvidence.PopUpElement.resetDialog();
		CarEvidence.PopUpElement.openDialog();
		$.ajax(opt).done(function(data) {
			PP.processAfterOpen(data);
		});
	} else {
		$.ajax(opt).done(function(data) {
			PP.open(data);
		});
	}

	return false;
};
PopUp.prototype.getAjaxOptions = function(target, params) {
	var opt = jQuery.extend({}, params);
	this.repairAjaxOptions(opt);
	return opt;
};
PopUp.prototype.repairAjaxOptions = function(opt) {
	if (opt.type == null) {
		opt.type = "json";
	}
	if (opt.method == null) {
		opt.method = "GET";
	}
};
PopUp.prototype.open = function(data) {
	CarEvidence.PopUpElement.setContent(data.content);
	this.repairParams(data.params);
	CarEvidence.PopUpElement.openDialog(data.params, data.script);
	util.wrapCarEvidenceCode('.notices-list');
};

PopUp.prototype.processAfterOpen = function(data) {
	CarEvidence.PopUpElement.setHTMLContent(data.content);
	this.repairParams(data.params);
	var height = null, width = null;
	for (key in data.params) {
		if (key == "height") {
			height = data.params[key];
		} else if (key == "width") {
			width = data.params[key];
		} else {
			CarEvidence.PopUpElement.setOption(key, data.params[key]);
		}
	}
	if (width != null && height != null) {
		parent.CarEvidence.PopUpElement.resize(width, height);
	}

};
PopUp.prototype.repairParams = function(params) {
	if (params == null) {
		return;
	}

	// Just hide loading layer, when popup has no additonal close listener
	if (params['close']) {
		var existingFun = params['close'];
		params['close'] = function() {
			existingFun.apply();
			parent.loadingWrapper.hide();
		}
	} else {
		params['close'] = function() {
			parent.loadingWrapper.hide();
		}
	}

	for (key in params) {
		if (key.indexOf("listener-") == 0) {
			var str = params[key];
			var listenerType = key.substr(9);
			delete params[key];
			if (listenerType == 'close') {
				params[listenerType] = function() {
					// If page will be reloaded, don't hide loading layer,
					// because IE 8 has problem with multiple hiding/showing
					str.indexOf('location.reload') > -1 ? parent.loadingWrapper
							.show() : parent.loadingWrapper.hide();
					eval(str);
				};
			} else {
				params[listenerType] = function() {
					eval(str);
				};
			}

		}
	}
};

var FormPopUp = function(element, params) {
	CarEvidence.PopUpElement.init();
	var elem;
	if (jQuery.type(element) === "string") {
		elem = $("#" + element);
		if (elem[0] == null) {
			return;
		}
	} else {
		elem = $(element);

	}
	if (elem[0] == null) {
		return;
	}
	this.init(elem, params);
};
FormPopUp.prototype = new PopUp();
FormPopUp.prototype.constructor = FormPopUp;
FormPopUp.prototype.init = function(elem, params) {
	this.type = jQuery.extend({}, params);
	this.type.method = elem.attr("method");
	this.type.url = elem.attr("action");
	this.addRndToUrl();
	var FPP = this;
	elem.on("submit", function(e) {
		return FPP.dispatch(this);
	});
};
FormPopUp.prototype.getAjaxOptions = function(target, params) {
	var opt = jQuery.extend({}, params);
	this.repairAjaxOptions(opt);
	opt.data = $(target).serialize();
	return opt;
};

var FormButtonPopUp = function(buttons, params) {
	CarEvidence.PopUpElement.init();
	this.init(buttons, params);
};
FormButtonPopUp.prototype = new FormPopUp();
FormButtonPopUp.prototype.constructor = FormButtonPopUp;
FormButtonPopUp.prototype.init = function(btns, params) {
	var buttons = $(btns);
	this.form = $("#" + params.formId);
	if (this.form[0] == null) {
		return;
	}
	this.type = jQuery.extend({}, params);
	this.type.method = this.form.attr("method");
	this.type.url = this.form.attr("action");
	this.addRndToUrl();
	var FPP = this;
	buttons.on("click", function(e) {
		return FPP.dispatch(this);
	});
};
FormButtonPopUp.prototype.getAjaxOptions = function(button, params) {
	var opt = jQuery.extend({}, params);

	if ($(button).children(".actionUrl")[0] != undefined) {
		opt.url = $(button).children(".actionUrl").val();
	}

	if ($(button).children("div").children(".actionUrl")[0] != undefined) {
		opt.url = $(button).children("div").children(".actionUrl").val();
	}
	
	this.repairAjaxOptions(opt);
	button = $(button);
	opt.data = $(this.form).serialize() + "&" + button.attr("name") + "="
			+ button.attr("value");
	return opt;
};

/* Error PopUp */
var ErrorPopUp = function(params) {
	this.setup(params);
};
ErrorPopUp.prototype = new PopUp();
ErrorPopUp.prototype.constructor = ErrorPopUp;
ErrorPopUp.prototype.setup = function(params) {
	if (params == null) {
		return;
	}
	CarEvidence.PopUpElement.init();
	var data = {
		content : this.getContent(params),
		params : {
			title : params.title,
			modal : true,
			buttons : [ {
				text : 'Ok',
				'class' : 'button',
				click : function() {
					CarEvidence.PopUpElement.closeDialog();
				}
			} ]
		}
	};
	PopUp.prototype.open(data);
};
ErrorPopUp.prototype.getListClass = function() {
	return "errors";
};
ErrorPopUp.prototype.getCommonClass = function() {
	return "notices-list";
};
ErrorPopUp.prototype.getContent = function(params) {
	var content = "<ul class='" + this.getCommonClass() + " "
			+ this.getListClass() + "'>";
	content += this.getItemList(params.messages) + "</ul>";
	return content;
};
ErrorPopUp.prototype.getItemList = function(itemList) {
	var list = "";
	for (i in itemList)
		list += "<li>" + itemList[i] + "</li>";
	return list;
};

/* Wagning PopUp */
var WarningPopUp = function(params) {
	this.setup(params);
};
WarningPopUp.prototype = new ErrorPopUp();
WarningPopUp.prototype.constructor = WarningPopUp;
WarningPopUp.prototype.getListClass = function() {
	return "warnings";
};

/* Notice PopUp */
var NoticePopUp = function(params) {
	this.setup(params);
};
NoticePopUp.prototype = new ErrorPopUp();
NoticePopUp.prototype.constructor = NoticePopUp;
NoticePopUp.prototype.setup = function(params) {
	if (params == null) {
		return;
	}
	CarEvidence.PopUpElement.init();
	var NPU = this;
	var data = {
		content : this.getContent(params),
		params : {
			title : params.title,
			modal : false,
			position : {
				my : "center top+10",
				at : "center top"
			},
			close : function(e, ui) {
				if (NPU.timeout != null) {
					window.clearTimeout(NPU.timeout);
				}
			}
		}
	};
	PopUp.prototype.open(data);
	this.timeout = null;
	this.close();
};
NoticePopUp.prototype.close = function() {
	this.timeout = window.setTimeout(function() {
		CarEvidence.PopUpElement.closeDialog();
	}, 5000);
};
NoticePopUp.prototype.getListClass = function() {
	return "notices";
};

var notification = {

	wrapper : null,

	show : function(params) {
		if (!params || !params.messages) {
			return;
		}
		this.wrapper = $('#slide-notification_wrapper');
		var html = '';
		$.each(params.messages, function(i, m) {
			html += '<span>' + m + '</span><br/>';
		});
		this.wrapper.find('.messages').html(html);

		notification.relativeTo($(window).width(), this.wrapper);
		util.wrapCarEvidenceCode('#slide-notification_wrapper');
		this.wrapper.addClass('active');
		this.animateHandlerPositionIE();

		this.wrapper.find('.slide-notification_hide-button').click(this.hide);
		hideOnBlur.setup(this.wrapper, 5000, this.animateHandlerPositionIE,
				true)
	},

	hide : function() {
		notification.wrapper.removeClass('active');
		notification.animateHandlerPositionIE();
	},

	animateHandlerPositionIE : function() {
		if (isIe) {
			notification.wrapper.animate({
				top : notification.wrapper.hasClass('active') ? '0' : '-200px'
			}, 300);
		}
	},

	relativeTo : function(relativeToWidth, targetElem) {
		targetElem.css('left', (parseInt(relativeToWidth) / 2) - 250);
	}

}

/* Component for confirmation after click on recent link */
var LinkConfirm = function(element, params) {
	this.init(element, params);
};
LinkConfirm.prototype.init = function(element, params) {
	element = $(element);
	if (element[0] == null) {
		return;
	}
	CarEvidence.PopUpElement.init();
	this.params = params;
	this.bindListener(element);
};
LinkConfirm.prototype.bindListener = function(elem) {
	var CF = this;
	elem.click(function(e) {
		return CF.dispatch(e);
	});
};
LinkConfirm.prototype.dispatch = function(e) {
	this.openDialog();
	return false;
};
LinkConfirm.prototype.openDialog = function() {
	var CF = this;
	CarEvidence.PopUpElement.setContent(CF.getConfirmText());
	CarEvidence.PopUpElement.openDialog({
		modal : true,
		buttons : [ {
			text : CF.params.ok,
			click : function() {
				if(typeof CF.params.successCallback === "function"){
					CF.params.successCallback.call(CF);
				}
				CF.onConfirm();
			},
			"class" : "hero"
		}, {
			text : CF.params.cancel,
			click : function() {
				CarEvidence.PopUpElement.closeDialog();
				if(typeof CF.params.failCallback === "function"){
					CF.params.failCallback.call(CF);
				}
			},
			"class" : "button"
		} ]
	});
};
LinkConfirm.prototype.onConfirm = function() {
};
LinkConfirm.prototype.getConfirmText = function() {
	return this.params.confirmText;
};

/* Component for confirmation after click on recent link */
var LinkSubmitConfirm = function(element, params) {
	this.init(element, params);
};
LinkSubmitConfirm.prototype = new LinkConfirm();
LinkSubmitConfirm.prototype.constructor = LinkConfirm;
LinkSubmitConfirm.prototype.init = function(element, params) {
	this.element = $(element);
	if (this.element[0] == null) {
		return;
	}
	CarEvidence.PopUpElement.init();
	this.params = params;
	this.bindListener(this.element);
};
LinkSubmitConfirm.prototype.onConfirm = function() {
	var CF = this;
	CarEvidence.AjaxPoster.post($(CF.element).attr("href"));
	parent.location.reload();
};

/* Component for confirmation after submit recent form */
var FormConfirm = function(formElement, params) {
	this.confirmText = "";
	this.init(formElement, params);
};
FormConfirm.prototype = new LinkConfirm();
FormConfirm.prototype.constructor = LinkConfirm;
FormConfirm.prototype.bindListener = function(formElement) {
	var param = formElement.attr("title") != null ? formElement.attr("title")
			: "";
	var CF = this;
	CF.confirmText = CF.params.confirmText.replace("{0}", param);
	formElement.submit(function(e) {
		return CF.dispatch(e);
	});
	this.formElement = formElement;
};
FormConfirm.prototype.dispatch = function(e) {
	if (!CarEvidence.validation.isFormValidated(this.formElement)) {
		return false;
	}

	this.openDialog();
	return false;
};
FormConfirm.prototype.onConfirm = function() {
	CarEvidence.PopUpElement.getElement().css("visibility", "hidden");
	CarEvidence.PopUpElement.getElement().next().css("visibility", "hidden"); // buttons
	CarEvidence.PopUpElement.getElement().parent().addClass("ajaxloader");
	this.formElement[0].submit();
};
FormConfirm.prototype.getConfirmText = function() {
	return this.confirmText;
};
/*
 * Show confirmation form after loading page.
 * */
var FormConfirmWithSubmitForm = function(formElement, params) {
	this.confirmText = "";
	this.init(formElement, params);
	$(formElement).submit();
};
FormConfirmWithSubmitForm.prototype = new FormConfirm();
FormConfirmWithSubmitForm.prototype.constructor = FormConfirm;

/* Component for binding form confirmation on several forms */
MultiConfirmBinder = {
	form : function(elements, params) {
		$.each($(elements), function(i, node) {
			new FormConfirm(node, params);
		});
	}
};

MultiLinkConfirmBinder = {
		form : function(elements, params) {
			$.each($(elements), function(i, node) {
				new LinkSubmitConfirm(node, params);
			});
		}
	};

/*
 * Component that makes N first columns as an anchor, rest of the table is
 * posible to horizonal scrolled
 */
CarEvidence.TableAnchor = function(tableEl, params) {

	this.table = $(tableEl);
	this.table.css("border-bottom", "none");

	var maxOuterWidth = [], maxInnerWidth = [], height = [];

	if (params.firstColumnsWidth != null) {
		maxOuterWidth = params.firstColumnsWidth;
		$.each(maxOuterWidth, function(i, w) {
			maxInnerWidth[i] = 0;
		});
	}

	var trs = $(tableEl + " tr");
	$.each(trs, function(j, tr) {
		var cells = $(tr).children("td,th"), cell = null;
		var k = 0;
		for (var i = 0; i < (params.firstCells - k); i++) {
			cell = $(cells[i]);
			if (i == 0) {
				height.push(cell.height());
			}

			if (cell.attr("colspan") != null && cell.attr("colspan") > 1) {
				k += cell.attr("colspan") - 1;
				continue;
			}
			var w = cell.outerWidth();
			if (maxOuterWidth[i + k] == null) {
				maxOuterWidth[i + k] = w;
				maxInnerWidth[i + k] = cell.innerWidth()
						- parseInt(cell.css("paddingLeft"))
						- parseInt(cell.css("paddingRight"));
			} else {
				var x = maxOuterWidth[i + k]
						- parseInt(cell.css("paddingLeft"))
						- parseInt(cell.css("paddingRight"));
				if (0 < x && maxInnerWidth[i + k] < x) {
					maxInnerWidth[i + k] = x;
				}
			}

		}
		cell.addClass("lastscroll");
	});

	var width = 0;
	$.each(maxOuterWidth, function(i, w) {
		width += w;
	});

	this.table.addClass("fixed");
	var insc = this.table.parent(".innerscroll");
	var ousc = insc.parent(".outerscroll");
	insc.css({
		width : $(ousc).width() - width + "px",
		marginLeft : width + "px"
	});

	var trs = $(tableEl + " tr");
	$.each(trs, function(j, tr) {
		var cells = $(tr).children("td,th"), left = 0;

		var k = 0;
		var tempolaryLeft = 0;
		for (var i = 0; i < (params.firstCells - k); i++) {
			var cell = $(cells[i]);
			var tempolaryLeft = 0;
			cell.addClass("notscroll").css("height", height[j] + "px");

			if (cell.attr("colspan") != null && cell.attr("colspan") > 1) {
				var w = 0, colspan = cell.attr("colspan");
				for (var n = 0; n < colspan; n++) {
					if (n > 0 && n < colspan - 1) {
						w += maxOuterWidth[i + k + n];
					} else {
						w += maxInnerWidth[i + k + n];

					}

				}

				cell.width(w + (maxOuterWidth[i + k] - maxInnerWidth[i + k]))
						.removeAttr("colspan").css("zIndex", 2);

				var leftVar = left + maxOuterWidth[i + k];
				for (var n = 1; n < colspan; n++) {
					var cl = document.createElement(cell[0].nodeName);
					$(cl).html("&nbsp;").width(maxInnerWidth[i + k + n]).css({
						"left" : leftVar + "px",
						"zIndex" : -1
					}).addClass("notscroll");
					leftVar = left + maxOuterWidth[i + k + n];
					tempolaryLeft += maxOuterWidth[i + k + n];
					cell.after(cl);

				}

				k += colspan - 1;

			} else {
				cell.width(maxInnerWidth[i + k]);
			}

			cell.css("left", left + "px");
			left += tempolaryLeft + maxOuterWidth[i + k];
		}
		// nastaveni vysky na tabulku, aby se nezmenila vyska radku
		var cellFisrtNormal = $(cells[(params.firstCells)]);
		cellFisrtNormal.css("height", height[j] + "px");
	});

	var rest = insc.width() - $(tableEl).width();
	if (rest > 0) {
		var td = $(tableEl + " tr td:last");
		td.width(td.width() + rest);
	}
};

/* Component for session timeout countdown */
CarEvidence.SessionTimer = {
	t : null,
	el : null,
	UNIT : {
		d : 86400,
		h : 3600,
		min : 60
	}, // ,s:0},
	it : null,
	init : function(t, el) {
		this.el = $(el);
		if (this.el == null) {
			return;
		}
		this.t = t;
		this.it = window.setInterval("CarEvidence.SessionTimer.update();", 5000);
	},
	update : function() {
		this.t--;
		if (this.t <= 0) {
			window.clearInterval(this.it);
		}
		this.el.html(this.format());
	},
	format : function() {
		if (this.t <= 0) {
			return "0s";
		}
		var s = "", tm = this.t;
		for (un in this.UNIT) {
			if (tm > 0 && tm > this.UNIT[un]) {
				var n = tm != 0 && this.UNIT[un] != 0 ? parseInt(tm
						/ this.UNIT[un]) : tm;
				s += n + un + " ";
				tm -= n * this.UNIT[un];
			}
		}
		return s;
	}
};

/*
 * Component enabling / disabling target element(s) by checking checkbox
 * Parameters: elementQuery - checkbox(s) query targetElementsQuery - target
 * element(s) query reverse - reverse functionality ie elements will be disabled
 * when checkbox is checked
 */
function CheckBoxElementsDisabler(params) {
	this.init(params);
};
CheckBoxElementsDisabler.prototype.init = function(params) {
	this.params = params;
	if (params == null) {
		return;
	}
	var CBCC = this;
	this.targets = [];
	$.each($(this.params.targetElementsQuery), function(i, node) {
		CBCC.targets.push(node);
	});

	$.each($(this.params.elementQuery), function(i, node) {
		$(node).on("change", function(e) {
			CBCC.dispatch(this);
		});
		if (node.checked) {
			CBCC.dispatch(node);
		}
	});
};
CheckBoxElementsDisabler.prototype.dispatch = function(element) {
	var CBCC = this;
	$.each(this.targets, function(i, node) {
		node.disabled = (CBCC.params.reverse) ? element.checked
				: !element.checked;
		if (node.disabled) {
			$(node).prop('checked', false);
		} else {
			$(node).prop('checked', true);
		}
	});
};

/*
 * Component enabling / disabling target element(s) by select option from
 * comboBox Parameters: elementQuery - combobox(s) query optionToEnable - value
 * for enable element targetElementsQuery - target element(s) query reverse -
 * reverse functionality ie elements will be disabled when checkbox is checked
 */
function ComboBoxElementsDisabler(params) {
	this.init(params);
};
ComboBoxElementsDisabler.prototype.init = function(params) {
	this.params = params;
	if (params == null) {
		return;
	}

	var CBCC = this;
	this.targets = [];
	this.optionValue = this.params.optionToEnable;

	$.each($(this.params.targetElementsQuery), function(i, node) {
		CBCC.targets.push(node);
	});

	$.each($(this.params.elementQuery), function(i, node) {
		$(node).on("change", function(e) {
			CBCC.dispatch(this);
		});

		CBCC.dispatch(node);
	});
};
ComboBoxElementsDisabler.prototype.dispatch = function(element) {
	var CBCC = this;
	$.each(this.targets, function(i, node) {
		var selectedValue = element.value;
		var isSelectTargetValue = selectedValue == CBCC.optionValue;
		node.disabled = (CBCC.params.reverse) ? isSelectTargetValue
				: !isSelectTargetValue;
	});
};

/*
 * Component enabling / disabling target element(s) by choose option in
 * radioElemenets Parameters: elementQuery - radio(s) query optionToEnable -
 * value for enable element targetElementsQuery - target element(s) query
 * reverse - reverse functionality ie elements will be disabled when radio is
 * checked, uncheckedElems - elements to unchecked when is targetselements disabled
 */
function RadioElementsDisabler(params) {
	this.params = params;
	if (params == null) {
		return;
	}

	var RED = this;
	this.targets = [];
	this.targetsTwoOption = [];
	this.optionValue = this.params.optionToEnable;
	this.uncheckedElems = params.uncheckedElems == null ? false : params.uncheckedElems;
	

	$.each($(this.params.targetElementsQuery), function(i, node) {
		RED.targets.push(node);
	});
	$.each($(this.params.targetElementsTwoOptionQuery), function(i, node) {
		RED.targetsTwoOption.push(node);
	});

	$.each($(this.params.elementQuery), function(i, node) {
		$(node).on("change", function(e) {
			RED.dispatch(this);
		});
		if (node.checked) {
			RED.dispatch(node);
		}
	});
};
RadioElementsDisabler.prototype.dispatch = function(element) {
	var RED = this;
	$.each(this.targets, function(i, node) {
		var selectedValue = element.value;
		var isSelectTargetValue = selectedValue == RED.optionValue;
		node.disabled = (RED.params.reverse) ? isSelectTargetValue
				: !isSelectTargetValue;
		if(RED.uncheckedElems && node.disabled){
			node.checked = false;
		}
	});
	
	$.each(this.targetsTwoOption, function(i, node) {
		var selectedValue = element.value;
		var isSelectTargetValue = selectedValue == RED.optionValue;
		node.disabled = (RED.params.reverse) ? !isSelectTargetValue
				: isSelectTargetValue;
		if(RED.uncheckedElems && node.disabled){
			node.checked = false;
		}
	});
};
/*
 * Extends RadioElementsDisabler for lump actions with percentage support and variable support.
 * Required attr:
 * 
 * secondTargetElementsQuery - elements which wont be enable if VS is enabled
 * 
 */
var LumpActionEditRadioElementsDisabler = function(params){
	RadioElementsDisabler.call(this,params);
	this.init(params);
}
LumpActionEditRadioElementsDisabler.prototype = new RadioElementsDisabler();
LumpActionEditRadioElementsDisabler.prototype.constructor = LumpActionEditRadioElementsDisabler;
LumpActionEditRadioElementsDisabler.prototype.init = function(params) {
	var RED = this;
	this.params = params;
	$.each($(this.params.elementQuery), function(i, node) {
		$(node).on("change", function(e) {
			RED.dispatch(this);
			RED.dispatchVS(this);
		});
		if (node.checked) {
			RED.dispatch(node);
			RED.dispatchVS(node);
		}
	});
};
LumpActionEditRadioElementsDisabler.prototype.dispatchVS = function(element){
	var RED = this;
	$.each($(this.params.secondTargetElementsQuery), function(i, node) {
		var selectedValue = element.value;
		if(selectedValue){
			node.disabled = true;
			$.each(RED.targets, function(i, node) {
				if(node.id == 'isVariableDefValue'){
					node.checked = true;
				}else{
					node.checked = false;
				}
			});
			
		}else{
			node.disabled = false;
		};
	});
};
/*
 * Component adding / removing class to / from target element(s) by checking
 * checkbox Parameters: elementQuery - checkbox(s) query targetElementsQuery -
 * target element(s) query defaultClass - title of css class, which will be add
 * to target elements(s) when checkbox is unchecked newClass - title of css
 * class, which will be add to target elements(s) when checkbox is checked
 * labelClass - if set, will be added to label element of target element, when
 * checkbox is checked
 */
function CheckBoxClassChanger(params) {
	CheckBoxElementsDisabler.call(this, params);
};
CheckBoxClassChanger.prototype = new CheckBoxElementsDisabler();
CheckBoxClassChanger.prototype.constructor = CheckBoxElementsDisabler;
CheckBoxClassChanger.prototype.dispatch = function(element) {
	var CBCC = this;
	var addClass = element.checked ? this.params.newClass
			: this.params.defaultClass;
	var remClass = element.checked ? this.params.defaultClass
			: this.params.newClass;
	$.each(this.targets, function(i, node) {
		$(node).removeClass(remClass).addClass(addClass);
		CBCC.toggleLabel(node, element.checked);
	});

};
CheckBoxClassChanger.prototype.toggleLabel = function(element, change) {
	if (this.params.labelClass == null) {
		return;
	}
	var query = "label[for=\"" + element.id + "\"]";
	if (change) {
		$(query).addClass(this.params.labelClass);
	} else {
		$(query).removeClass(this.params.labelClass);
	}
};

/*
 * Component adding / removing class to / from target element(s) by checking one
 * of radio button from group Parameters: elementQuery - radio button(s) query
 * targetElementsQuery - target element(s) query defaultClass - title of css
 * class, which will be add to target elements(s) when radio is unchecked
 * newClass - title of css class, which will be add to target elements(s) when
 * radio is checked labelClass - if set, will be added to label element of
 * target element, when radio is checked required - contains an array of IDs of
 * radios; if array contains ID of radio element, which triggers the event,
 * newClass will be user for target elemens(s) instead of defaultClass
 */
function RadioClassChanger(params) {
	CheckBoxClassChanger.call(this, params);
};
RadioClassChanger.prototype = new CheckBoxClassChanger();
RadioClassChanger.prototype.constructor = CheckBoxClassChanger;
RadioClassChanger.prototype.dispatch = function(element) {
	var change = element.checked
			&& $.inArray(element.id, this.params.required) >= 0;
	var addClass = change ? this.params.newClass : this.params.defaultClass;
	// hides all validation messages
	var form = $($(element).closest("form"));
	if (form[0] != null && form.hasClass("validated")) {
		form.validationEngine("hideAll");
	}

	var RCC = this;
	$.each(this.targets, function(i, node) {
		$(node).removeClass(RCC.params.newClass).removeClass(
				RCC.params.defaultClass).addClass(addClass);
		RCC.toggleLabel(node, change);
	});
};

/* Component for checking / unchecking group of checkboxes */
function CheckBoxToggler(params) {
	this.init(params);
};
CheckBoxToggler.prototype.init = function(params) {
	if (params == null) {
		return;
	}
	this.params = params;
	this.mainCheck = $(params.mainCheckbox);
	if (this.mainCheck[0] == null) {
		return;
	}
	this.checkboxes = $(params.checkboxes);
	var CBT = this;
	this.mainCheck.on("change", function(e) {
		CBT.dispatch();
	});
	$.each(this.checkboxes, function(i, node) {
		$(node).on("change", function(e) {
			var checked = $(CBT.params.checkboxes + ":checked");
			if (CBT.checkboxes.size() == checked.size()) {
				CBT.mainCheck.prop('checked', true);
			}else{
				CBT.mainCheck.prop('checked', false);
			}
		});
		
		
	});
};
CheckBoxToggler.prototype.dispatch = function() {
	var checked = this.mainCheck[0].checked;
	this.checkboxes.prop("checked", checked);
};

function RadioElementsActivator(params) {
	this.radios = $(params.radios);
	this.elementsContSuffix = params.elementsContSuffix;
	var REA = this;
	var checked = null;
	$.each(this.radios, function(i, node) {
		if (i == 0 || node.checked) {
			checked = node;
		}
		$(node).click(function(e) {
			REA.dispatch(this);
		});
	});
	checked.checked = true;
	this.dispatch(checked);
};
RadioElementsActivator.prototype.dispatch = function(el) {
	var REA = this;
	// hides all validation messages
	var form = $($(el).closest("form"));
	if (form[0] != null && form.hasClass("validated")) {
		form.validationEngine("hideAll");
	}

	$.each(this.radios, function(i, node) {
		var parentEl = $("#" + node.id + REA.elementsContSuffix);
		if (parentEl[0] != null) {
			REA.change(parentEl, el != node);
		}
	});
};
RadioElementsActivator.prototype.change = function(parentEl, disable) {
	parentEl.find("input,select,textarea").prop("disabled", disable);
};


/* Component for mass change values of radio buttons */
function RadioSelectionChanger(params) {
	this.init(params);
};
RadioSelectionChanger.prototype.init = function(params) {
	
	this.params = params;
	if (params == null) {
		return;
	}

	var RSC = this;
	this.targets = [];
	this.mainRadio = this.params.mainRadio;
	this.targetElementsQuery = this.params.targetElementsQuery;
	
	$(RSC.mainRadio).on("change", function(e) {
		RSC.dispatch(this);
	});
	
};
RadioSelectionChanger.prototype.dispatch = function() {
	var RSC = this;
	var value =	$(RSC.mainRadio + ":checked").val();
	
	$.each($(RSC.targetElementsQuery + "[value='" + value + "']"), function(e, node) {
		  $(node).change().prop("checked", true);
		});
	
};


/* Komponenta vyresutuje nastaveni poli ve formulari */
function ResetForm(params) {
	this.form = $(params.form);
	this.button = $(params.resetbutton);
	if (this.form[0] == null || this.button[0] == null) {
		return;
	}
	var RF = this;
	this.button.on("click", function(e) {
		RF.reset();
	});
};
ResetForm.prototype.reset = function() {
	$.each(this.form.find("input[type!='hidden'],select,textarea"), function(i,
			node) {
		if (node.nodeName == "SELECT") {
			node.selectedIndex = node.multiple ? -1 : 0;
			$(node).change();
		} else {
			node.value = "";
		}
	});

};

/* Komponenta pro zavreni popup okna */
function PopUpCloseButton(button) {
	var self = this;
	$(button).click(function(e) {
		self.dispatch();
	});
};
PopUpCloseButton.prototype.dispatch = function() {
	parent.CarEvidence.PopUpElement.closeDialog();
};

/* Komponenta pro reload cele stranky z popup okna */
function PopUpRefreshPageButton(button) {
	PopUpCloseButton.call(this, button);
};
PopUpRefreshPageButton.prototype = new PopUpCloseButton();
PopUpRefreshPageButton.prototype.constructor = PopUpCloseButton;
PopUpRefreshPageButton.prototype.dispatch = function() {
	PopUpCloseButton.prototype.dispatch.call();
	parent.location.reload();
};

/* Komponenta pro skryvani / zobrazeni radku v tabulce */
function TableRowsExpander(params) {
	this.init(params);
};
TableRowsExpander.prototype.init = function(params) {
	this.msg = params.msg;
	this.setupRows(params);
	this.expanded = true;
	this.expand();
	var TRE = this;
	$(params.buttons).click(function() {
		TRE.switchButtonLabel(this);
		TRE.expand();
	});
};
TableRowsExpander.prototype.setupRows = function(params) {
	this.rows = params.rows != null ? $(params.rows) : $([]);
};
TableRowsExpander.prototype.expand = function() {
	var display = this.expanded ? "none" : "table-row";
	$(this.rows).css("display", display);
	this.expanded = !this.expanded;
};
TableRowsExpander.prototype.switchButtonLabel = function(button) {
	$(button).html(this.expanded ? this.msg.expand : this.msg.collapse);
};

/* Komponenta pro odesilani POST pozadavku na server */
CarEvidence.AjaxPoster = {
	post : function(postUrl, additionalParams) {
		var params = jQuery.extend({
			method : "POST",
			url : postUrl,
			async : false
		}, additionalParams || {});
		$.ajax(params);
	},
	postOnUnload : function(postUrl, onParent) {
		var fn = function() {
			CarEvidence.AjaxPoster.post(postUrl);
		};
		var w = $(onParent ? window.parent : window);
		$("form").bind("submit", function() {
			w.unbind("unload", fn);
		});
		w.bind("unload", fn);
	}
};

/*
 * Component for hidding body element in popup when form is successfully
 * submitted
 */
CarEvidence.hidePopupContentOnSubmit = function(formEl) {
	var form = $(formEl);
	if (form[0] == null) {
		return;
	}
	
	form.submit(function(e) {
		if (!CarEvidence.validation.isFormValidated(form)) {
			return false;
		}

		CarEvidence.PopUpElement.hideContent();

	});
};

/* Component for reload parent opener (window that opened actual window) window */
function ParentWindowReloader() {
	$(window).bind("unload", function() {
		CarEvidence.parentWindowReload();
	});
}

CarEvidence.parentWindowReload = function() {
	if (window.opener) {
		window.opener.location.reload();
	}
};

CarEvidence.parentWindowReloadAfterDelay = function(milliseconds) {
	
	setTimeout(function(){window.parent.location.reload(); },milliseconds)
	
};


/* progress indicator on submitted form */
CarEvidence.FormSubmitProgress = function(formEl, progressEl) {
	formEl.submit(function(e) {
		progressEl.addClass('progressIndicatorShown');
		return true;
	});

};

/* Component for multiple bind */
var CollectionsBinder = function CollectionsBinder(params) {
	this.init(params);
};
CollectionsBinder.prototype.init = function(params) {
	if (params == null) {
		return;
	}
	this.params = params;
	if (params.items == undefined) {
		return;
	}

	var CB = this;
	CB.process();
};
CollectionsBinder.prototype.processItem = function(i, node) {
};
CollectionsBinder.prototype.process = function() {
	var CB = this;
	$.each($(CB.params.items), function(i, node) {
		CB.processItem(i, node);
	});
};

/* Component for multiple bind tooltips 
 * PARAMS:
 * 
 * items - elements which trigger event for tooltip
 * replaceSemicolon - if true replaces ';' by '<br/>', default true
 * replaceHtmlEntities - if true replaces '>' by '&gt;' and '<' by '&lt;' and '&amp;lt;' by '&lt;', default false
 * 
 * */
var TooltipBinder = function(params) {
	if (params == null) {
		return;
	}
	this.params = params;
	if (params.items == undefined) {
		return;
	}
	if(params.replaceSemicolon == undefined){
		params.replaceSemicolon = true;
	}
	if(params.replaceHtmlEntities == undefined){
		params.replaceHtmlEntities = false;
	}

	var TB = this;
	TB.process();
};
TooltipBinder.prototype = new CollectionsBinder();
TooltipBinder.prototype.constructor = TooltipBinder;

TooltipBinder.prototype.processItem = function(i, node) {
	node = $(node);
	var TB = this;
	var text = node.attr("title");
	if(TB.params.replaceHtmlEntities){
		text = text.replace(/&amp;gt;/g, "&gt").replace(/&amp;lt;/g, "&lt;");
		text = text.replace(/</g, "&gt").replace(/</g, "&lt;");
	}

	if(TB.params.replaceSemicolon){
		text = text.replace(/;/g, "<br />");
	}
	
	if (node.attr("title") != null) {
		node.tooltip({
			track : true,
			escape : true,
			content : text,
			tooltipClass : "tooltip"
		});
	}
};

/* Component for multiple bind PopUp */
var MultiSourcePopUp = function(params) {
	if (params == null) {
		return;
	}
	this.params = params;
	if (params.items == undefined) {
		return;
	}

	var TB = this;
	TB.process();
};
MultiSourcePopUp.prototype = new CollectionsBinder();
MultiSourcePopUp.prototype.constructor = MultiSourcePopUp;

MultiSourcePopUp.prototype.processItem = function(i, node) {
	new PopUp(node, this.params);
};

var VinImportsListPopUpBinder = function(params) {
	if (params == null) {
		return;
	}
	this.params = params;
	if (params.items == null) {
		return;
	}

	var TB = this;
	TB.process();
};
VinImportsListPopUpBinder.prototype = new CollectionsBinder();
VinImportsListPopUpBinder.prototype.constructor = VinImportsListPopUpBinder;
VinImportsListPopUpBinder.prototype.processItem = function(i, node) {
	var params = jQuery.extend(true, {}, this.params);
	node.href = params.actionUri.replace('__id__', node.id);
	new PopUp(node, params);
};

var GenericListFormPopUpBinder = function(params) {
	if (params == null) {
		return;
	}
	this.params = params;
	if (params.items == null) {
		return;
	}

	var TB = this;
	TB.process();
};
GenericListFormPopUpBinder.prototype = new CollectionsBinder();
GenericListFormPopUpBinder.prototype.constructor = GenericListFormPopUpBinder;
GenericListFormPopUpBinder.prototype.processItem = function(i, node) {
	var data = jQuery.extend(true, {}, this.params.data);
	new FormPopUp(node, data);
};

/*
 * Component for multiple bind PopUp Parameters: * items - items for action *
 * redirectUrl - URL to redirect * suffixRedirectUrl - URL to redirect suffix
 */
var PakectPopupsBinder = function(params) {
	this.init(params);
};
PakectPopupsBinder.prototype = new CollectionsBinder();
PakectPopupsBinder.prototype.constructor = PakectPopupsBinder;
PakectPopupsBinder.prototype.init = function(params) {
	if (params == null) {
		return;
	}
	this.params = params;
	if (params.items == undefined) {
		return;
	}
	var PPB = this;

	PPB.redirectUrl = params.redirectUrl;

	PPB.suffixRedirectUrl = params.suffixRedirectUrl;
	PPB.process();
};

PakectPopupsBinder.prototype.processItem = function(i, node) {
	var PPB = this;
	node = $(node);
	var hash = node.parents("tr").find("td input[type='hidden'].identifier").attr("value");

	new PopUp(node, {
		type : "json",
		method : "POST",
		data : {
			redirectUrl : PPB.redirectUrl + hash,
			suffixRedirectUrl : PPB.suffixRedirectUrl
		}
	});
};

/*
 * Komponenta pro scrollTo element Parameters: * elementId - ID elementu *
 * linkId - ID linku
 */
CarEvidence.Scroller = {
	bindScrollToToLink : function(elementId, linkId) {
		$("#" + linkId).on("click", function(e) {
			CarEvidence.Scroller.scrollTo(elementId, 1000);
		});
	},
	scrollTo : function(elementId, time) {
		$("body").scrollTo($("#" + elementId), time);
	},
	scrollToPosition : function(postion, time, scrollFixerTop) {

		var scrollFixerTopHeight = 0;

		if (scrollFixerTop == undefined) {
			scrollFixerTopHeight = 0;
		} else {
			scrollFixerTopHeight = scrollFixerTop.scrollDiv.height() + 9;
		}

		$("body").scrollTo(postion - scrollFixerTopHeight, time);

	}

};

/*
* Komponenta pro reseni vicekrokovych popup formularu, jez bude mit
* povinne parametry: formId
* nepovinne parametry: serializedContentSelector, methodSend, popupBackButton
* 
* Prvni krok musi byt obalen DIVem s classami: steps, actual-step.
* Kazdy krok muze v sobe nest jeden z nasledujicich skrytych inputu s class: nextStepUrl, checkNextStepUrl
* Jejich smysl je popsan v nasledujici tabulce
* 
*   nextStepUrl    |   checkNextStepUrl  | CHOVANI
* _________________|_____________________|__________________________
*     URL          |       x             |  AJAX_GET_NEXT_CONTENT
*     x            |       URL           |  AJAX_CHECK_AND_GET_NEXT_CONTENT (if (next content empty) SUBMIT_FORM )
*     x            |       x             |  SUBMIT_FORM
* 
* * formId
* * serializedContent - data ktere se pouziji pro overeni
*
* Rozhodovani co komponenta ucinni:
* 
*	if ( nextStepUrl not empty ) {
*		call AJAX_GET_NEXT_CONTENT
*	} else if ( checkNextStepUrl not empty ) {
*		call AJAX_CHECK_AND_GET_NEXT_CONTENT
*	} else {
*		call SUBMIT_FORM;
*	}
*
* Komponenta ziskavani dat pro nasledujici krok zpusobi nasledujici:
*		
*		AJAX_GET_NEXT_CONTENT
*			- odesle ajaxem na server serializedContent.
*			call SET_NEXT_CONTENT
*
*		AJAX_CHECK_AND_GET_NEXT_CONTENT
*
*			- odesle ajaxem na server serializedContent.
*			if (next content empty) {
*				SUBMIT_FORM;
*			} else {
*				call SET_NEXT_CONTENT
*			}
*
*		SET_NEXT_CONTENT
*			- skryje DIVy predchozich kroku
*			- prida DIV pro nasledujici krok a vlozi kontent z AJAXu
*
*
*/
var AbstractPageHandler = function(params) {
	this.init(params);
};
AbstractPageHandler.prototype.init = function(params) {
	if (params == null) {
		return;
	}
	this.params = params;
	if (params.formId == undefined) {
		return;
	}
	
	var PPH = this;
	
	PPH.form = $(params.formId);
	if (PPH.form == null) {
		return;
	}
	
	if (params.methodSend == undefined) {
		PPH.methodSend = "POST";
	} else {
		PPH.methodSend = params.methodSend;
	}
	
	PPH.initBackButton(params);
	if (params.serializedContentSelector != undefined) {
		PPH.serializedContent = $(params.serializedContentSelector);
	} else	if (params.serializedContent != undefined) {
		PPH.serializedContent = $(serializedContent);
	} else {
		PPH.serializedContent = PPH.form;
	}

	PPH.submittable = false;
	
	PPH.form.submit( function(e) {
		
		if (!PPH.form.validationEngine('validate') ) {
			return false;
		}
		
		if (!PPH.submittable) {
			PPH.dispatch(this);
		}
		
		return PPH.submittable;
		
		
	});
	
	
};
AbstractPageHandler.prototype.dispatch = function() {
	var PPH = this;
	
	if ( $("div.actual-step .nextStepUrl")[0] != undefined) {
		PPH.ajaxGetNextContent($("div.actual-step .nextStepUrl").val());
		return;
	} else if ( $("div.actual-step .checkNextStepUrl")[0] != undefined) {
		PPH.ajaxGetNextContent($("div.actual-step .checkNextStepUrl").val());
		return;
	} else {
		PPH.submitForm();
	}	
};
AbstractPageHandler.prototype.ajaxGetNextContent = function(urlStep) {
	
	var PPH = this;
	
	$.ajax({
		url : urlStep,
		type : PPH.methodSend,
		data : PPH.serializedContent.serialize(),
		success : function(data) {
			if ( $(data.content).hasClass("emptyResult") ) {
				PPH.submitForm();
			} else if ( $(data.content).hasClass("emptyError") ) {
				PPH.setErrorContent(data.content);
			} else {
				
				PPH.setNextContent(data.content);
				
				if (data.script != null) {
					eval(data.script);
				}
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			PPH.setErrorContent(textStatus);
		}
	});

};
AbstractPageHandler.prototype.setErrorContent = function(content) {
	var PPH = this;
	
	var currentStep = $("div.actual-step");
	
	PPH.removeErrorNotice();
	
	var div = $('<div/>', {
		"class": 'errorStep',
		display: 'block',
		html: content
	});

	
	currentStep.before(div);
	
	CarEvidence.PopUpElement.resize(currentStep.children(".widthResize").val());
		
};
AbstractPageHandler.prototype.submitForm = function() {
	var PPH = this;
	
	PPH.submittable = true;
	PPH.form.submit();

};

AbstractPageHandler.prototype.removeErrorNotice = function() {
	
	if ( $(".errorStep")[0] != undefined) {
		 $(".errorStep").remove();
	}
};

/*
 * Handler pro popupy. Overriduje pouze nektere tridy z AbstractPageHandler.
 */
var PopUpPageHandler = function(params){
	this.init(params);
}
PopUpPageHandler.prototype = new AbstractPageHandler();
PopUpPageHandler.prototype.constructor = AbstractPageHandler;
PopUpPageHandler.prototype.callBack = function(e) {
	
	var PPH = this;
	
	PPH.removeErrorNotice();
	
	var actualStep = $(".actual-step.steps");
	var previousStep = actualStep.prev(".steps");
	
	previousStep.addClass("actual-step").show();
	actualStep.remove();

	if (previousStep.children(".hidePreviousStep")[0] != undefined) {
		previousStep.prev(".steps").show();
	}	
	
	if (previousStep.prev(".steps")[0] == null) {
		PPH.popupBackButton.hide();
	};
	
	PPH.removeErrorNotice();
	
	CarEvidence.PopUpElement.resize(previousStep.children(".widthResize").val());
};
PopUpPageHandler.prototype.setNextContent = function(content) {
	var PPH = this;
	
	var currentStep = $("div.actual-step");
	
	currentStep.removeClass("actual-step");
	
	if (PPH.params.popupBackButton != undefined) {
		PPH.popupBackButton.show();
	}
	
	var div = $('<div/>', {
		"class": 'actual-step steps',
		display: 'block',
		html: content
	});
	
	if ( $(div).children(".hidePreviousStep")[0] == undefined) {
		$("div.steps").hide();
	}
	
	currentStep.after(div);
	
	PPH.removeErrorNotice();
	
};
PopUpPageHandler.prototype.initBackButton = function(params) {
	var PPH = this;
	if (params.popupBackButton != undefined) {
		PPH.popupBackButton = $(params.popupBackButton);
		PPH.popupBackButton.hide();
		
		PPH.popupBackButton.on("click", function(e) {
			PPH.callBack(e);
		});
		
	}
};

/*
 * Handler pro normalni page. Overriduje pouze nektere tridy z AbstractPageHandler.
 */
var PageHandler = function(params){
	AbstractPageHandler.call(this, params);
	var finishUrl = $("#finalStepUrl").val();
	if(finishUrl != null){
		$(params.formId).attr('action', finishUrl);
	}
}
PageHandler.prototype = new AbstractPageHandler();
PageHandler.prototype.constructor = AbstractPageHandler;
PageHandler.prototype.init = function(params) {
	AbstractPageHandler.prototype.init.call(this,params);
	var PPH = this;
	if($(".nav-breadcrumb li.actual-step").prevAll(".nav-breadcrumb_item")[0] == undefined){
		return;
	}
	
	$.each($(".nav-breadcrumb li.actual-step").prevAll(".nav-breadcrumb_item"),function(index,elem){
		// nastaveni predchoziho na enabled a doplneni odkazu
		var stepId = $(elem).find(".step-key").val();
		PPH.addLinkToStep(elem, stepId);
	});
	var prevStepId = $(".nav-breadcrumb li.actual-step").prev().find(".step-key").val();
	$(PPH.params.oneStepBackButton).attr("step", prevStepId);
	$(PPH.params.oneStepBackButton).show();
	
};
PageHandler.prototype.initBackButton = function(params) {
	var PPH = this;
	//difinovani kroku zpet pro drobenkove menu
	if (params.popupBackButton != undefined) {
		PPH.popupBackButton = $(params.popupBackButton);
		PPH.popupBackButton.on("click", ".nav-breadcrumb_item.enabled a",function() {
			PPH.callBack($(this));
		});
	}
	// pokud je definovano tlacitko zpet ve wizzardu(o 1 krok zpet)
	if(params.oneStepBackButton != undefined){
		PPH.oneStepBackButton = $(params.oneStepBackButton);
		PPH.oneStepBackButton.on("click", function() {
			PPH.oneStepCallBack($(this));
		});
	}
};
PageHandler.prototype.oneStepCallBack = function(elem){
	var PPH = this;
	var previousStepId = $(PPH.params.formId + " .actual-step").prev().find(".stepId").val();
	PPH.callBack($(".nav-breadcrumb_item.enabled a."+previousStepId));
};
PageHandler.prototype.initFinishUrl = function(params){

}
PageHandler.prototype.callBack = function(elem) {
	
	var PPH = this;
	
	PPH.removeErrorNotice();
	var previousStepClass = $(elem).find(".step-key").val();
	
	var previousStep = $(PPH.params.formId + " .steps."+previousStepClass);
	previousStep.addClass("actual-step").show();
	previousStep.nextAll(".steps").remove();
	$("#wizardCurrentStep").val(previousStepClass);
	
	//nastaveni viditelnosti drobenkove navigace pro uzivatelem kliknutou polozku
	var parentNavigation = $(elem).parent(".nav-breadcrumb_item");
	parentNavigation.removeClass("enabled");
	parentNavigation.addClass("actual-step");
	parentNavigation.addClass("active");
	parentNavigation.nextAll().removeClass("actual-step");
	parentNavigation.nextAll().removeClass("active");
	this.setMenuItemContent(parentNavigation);
	parentNavigation.nextAll().addClass("disabled");
	
	$.each(parentNavigation.nextAll("enabled"), function(element){
		PPH.setMenuItemContent(element);
	});
	
	if (previousStep.children(".hidePreviousStep")[0] != undefined) {
		previousStep.prev(".steps").show();
	}
	
	if(previousStep.prev(".steps").length == 0){
		$(PPH.params.oneStepBackButton).hide();
	}else{
		//nastaveni kroku o jeden zpet z nove zobrazeneho
		var previousStepId = $(PPH.params.formId + " .actual-step").prev().find(".stepId").val();
		$(PPH.params.oneStepBackButton).attr("step", previousStepId);
	}
	//nastaveni titulku odesilaciho tlacitka, pokud bylo zmeneno
	if(PPH.params.submitBtnTitle != null){
		$("#submit-button").text(PPH.params.submitBtnTitle);
	}
};

PageHandler.prototype.setMenuItemContent = function(element) {
	var text = element.find("a").text();
	element.empty();
	element.append(text);
}
PageHandler.prototype.setNextContent = function(content) {
	var PPH = this;
	
	var currentStep = $("div.actual-step");
	
	currentStep.removeClass("actual-step");
	
	var div = $('<div/>', {
		"class": 'actual-step steps active',
		display: 'block',
		html: content
	});
	
	if ( $(div).children(".hidePreviousStep")[0] == undefined) {
		$("div.steps").hide();
	}
	
	currentStep.after(div);
	
	PPH.removeErrorNotice();
	// nastaveni predchoziho na enabled a doplneni odkazu
	var prevStepId = $("#wizardCurrentStep").val();
	var prevStepNav = $(".nav-breadcrumb_item.active")
	PPH.addLinkToStep(prevStepNav, prevStepId);
	
	//nastaveni viditelnosti drobenkove navigace, predchozi kroky
	var stepId = $(".actual-step .stepId").val();
	$("#wizardCurrentStep").val(stepId);
	currNav = $(".nav-breadcrumb_item."+stepId);
	PPH.enableAllPrevious(currNav);	
	
	//nastevni finish url do formulare pro odeslani na server
	var finishUrl = $("#finalStepUrl").val();
	if(finishUrl != null){
		$(PPH.params.formId).attr('action', finishUrl);
		if(PPH.params.finishBtnTitle != null){
			// zmena titulku tlacitka pro submit
			PPH.params.submitBtnTitle = $("#submit-button").text();
			$("#submit-button").text(PPH.params.finishBtnTitle);
		}
	}
	// zobrazeni tlacitka "zpet"
	$(PPH.params.oneStepBackButton).show();
	$(PPH.params.oneStepBackButton).attr("step", prevStepId);
};

PageHandler.prototype.enableAllPrevious = function(currNav){
	$(currNav).removeClass("disabled");
	$(currNav).prevAll().removeClass("active");
	$(currNav).prevAll().removeClass("actual-step");
	$(currNav).prevAll().addClass("enabled");
	$(currNav).addClass("actual-step");
	$(currNav).addClass("active");
}


PageHandler.prototype.addLinkToStep = function(step, stepId){
	var stepText = $(step).text();
	$(step).empty();
	$(step).addClass("enabled");
	$(step).append('<a href="#" class="steps ' + stepId + '"><input type="hidden" value="' + stepId + '" class="step-key"/>' + stepText + '</a>');
}
/*
 * Params
 * text - text to display in popup
 * title - title of dialog
 * okBtnTxt - text of confim button
 * cancelBtnTxt - text of cancel button
 * 
 */
var PopUpCreateFaWarning = function(params) {
	this.setup(params);
};
PopUpCreateFaWarning.prototype = new PopUp();
PopUpCreateFaWarning.prototype.constructor = PopUpCreateFaWarning;
PopUpCreateFaWarning.prototype.setup = function(params) {
	CarEvidence.PopUpElement.init();
	var data = {
			content : params.text,
			params : {
				title : params.title,
				modal : true,
				buttons : [ {
					text : params.okBtnTxt,
					'class' : 'button',
					click : function() {
						CarEvidence.PopUpElement.closeDialog();
					}
				},{
					text : params.cancelBtnTxt,
					'class' : 'button',
					click : function() {
						window.location.href = params.backUrl;
					}
				} ]
			}
		};
		PopUp.prototype.open(data);
}


/*
 * Component for add/remove by template
 * 
 * Parameters:
 * 
 * templateId - id template elementu
 * whereAdd - selector of wrappers element for adding item
 * addLink - id of adder link (must be wrapped by div)
 * itemClass - specific class on item
 * deleteableClass - class on delete links
 * limitItems - max count of added item
 */
var AppenderByTemplate = function(params) {
	this.init(params);
};
AppenderByTemplate.prototype.init = function(params) {
	this.params = params;
	if (params == null) {
		return;
	}
	var ABT = this;
	if (params.templateId == null) {
		return;
	}
	if (params.whereAdd == null) {
		return;
	}
	if (params.addLink == null) {
		return;
	}
	if (params.itemClass == null) {
		return;
	}
	if (params.deleteableClass == null) {
		return;
	}
	
	// bind dispatchAdding
	$(ABT.params.addLink).on("click", function(e) {
		ABT.dispatchAdding(e);
		return false;
	});
	
	ABT.hide($(ABT.params.addLink));
	
	// bind dispatchRemove
	$.each($(ABT.params.whereAdd + " ." + ABT.params.deleteableClass), function(i, node) {
		node_v = $(node);
		node_v.on("click", function(e) {
			ABT.dispatchRemove(this);
			return false;
		});
	});

};
AppenderByTemplate.prototype.show = function(element) {
	var ABT = this;
	
	if (ABT.params.customShow != null) {
		ABT.params.customShow.call(element);
		return false;
	}
	var vLength = $(ABT.params.whereAdd+" ."+ABT.params.itemClass).length;
	if (ABT.params.limitItems != null && vLength < ABT.params.limitItems) {
		$(ABT.params.addLink).closest("div").removeClass("none");
	}
}
AppenderByTemplate.prototype.hide = function(element) {
	var ABT = this;
	
	if (ABT.params.customHide != null) {
		ABT.params.customHide.call(element);
		return false;
	}
	var vLength = $(ABT.params.whereAdd+" ."+ABT.params.itemClass).length;
	if (ABT.params.limitItems != null &&  vLength >= ABT.params.limitItems) {
		$(ABT.params.addLink).closest("div").addClass("none");
	}
}
AppenderByTemplate.prototype.dispatchAdding = function(element) {
	var ABT = this;
	
	var newItem = $(ABT.params.templateId).clone();
  
	newItem
		.attr("id","")
		.find("."+ABT.params.deleteableClass)
			.on("click", function(e) {
				ABT.dispatchRemove(this);
				return false;
			});
	
	if (ABT.params.defaultValue != null) {
		ABT.params.defaultValue.call(this, newItem, ABT.params.whereAdd+" ."+ABT.params.itemClass);
	}
	 $(ABT.params.whereAdd).append(newItem);
	
	 ABT.hide(this);
};

AppenderByTemplate.prototype.dispatchRemove = function(element) {
	var ABT = this;
	$(element).closest(" ."+ABT.params.itemClass).remove();
	ABT.show(this);
	
};


/*
 * 
 */

var overflowToDot = {
	init : function(selector) {
		$(selector).each(
				function(i, e) {
					if (e.scrollWidth > $(e).innerWidth() + 1) {
						$.each($(e).find('*').toArray().concat(e),
								function(i, child) {
									if (overflowToDot.hasText(child)) {
										var original = child.innerHTML;
										overflowToDot.cutAndDot(child, 0);
										var count = 0;
										while (child.innerHTML
												&& e.scrollWidth > $(e)
														.innerWidth() + 1
												&& count++ < 500) {
											overflowToDot.cutAndDot(child, 2);
										}
										// Ignore unicode symbols (like &nbsp;),
										// common case for jsp empty tags
										if (!original.match(/^&.*;$/)) {
											child.setAttribute('title',
													original);
											e.setAttribute('title', original);
										}
										return false;
									}
								});
					}
				});
	},

	dots : '...',

	cutAndDot : function(e, count) {
		count = (count || 0) + this.dots.length;
		var html = e.innerHTML;
		// Regexp for unicode symbols like &nbsp;
		var p = new RegExp('.*&[a-zA-Z]{1,5};[.]{0,' + count + '}');
		var substrTo = p.test(html) ? html.lastIndexOf('&') : html.length
				- count;
		e.innerHTML = html.substr(0, substrTo) + this.dots;
		if (e.innerHTML == this.dots) {
			e.innerHTML = '';
		}
	},

	hasText : function(e) {
		var h = e.innerHTML;
		return h && h != this.dots && !h.match(/<\/|\/>|^[ ]+$|^<.*>$/);
	}

}

var hideOnBlur = {

	timers : [],

	// Get or set
	timer : function(element, newValue) {
		return newValue ? this.timers[element.id + element.className] = newValue
				: this.timers[element.id + element.className];
	},

	setup : function(element, delay, ieAnimation, triggerHideOnStart) {
		element = element instanceof jQuery ? element : $(element);
		element.on('mouseenter', function() {
			var t = hideOnBlur.timer(element);
			if (t) {
				clearTimeout(t);
			}
		});
		var hideFunction = function() {
			hideOnBlur.timer(element, setTimeout(function() {
				element.removeClass('active');
				if (isIe && ieAnimation) {
					ieAnimation.apply();
				}
			}, delay));
		};
		element.on('mouseleave', hideFunction);
		if (triggerHideOnStart) {
			hideFunction.apply();
		}
	}

}

var util = {

	wrapCarEvidenceCode : function() {
		$.each(arguments, function(i, selector) {
			$(selector).each(
					function(i, element) {
						var e = $(element);
						e.html(e.html().replace(/(MK[A-Z]{2,6}[0-9]{4,8}:)/g,
								'<font class="message-code">$1</font>'));
					});
		});
	},

	/**
	 * On <code>source</code> change event toggle <code>target</code>
	 * 'disabled' attribute
	 */
	disableToggleOnChange : function(source, target) {
		$(source).on('change', function() {
			$(target).prop('disabled', !$(target).prop('disabled'))
		});
	},
	
	/**
	 * On <code>source</code> change event toggle <code>target</code>
	 * 'hide' attribute
	 */
	hiddenToggleOnChange : function(source, target) {
		$(source).on('change', function() {
			if ($(target).is(":visible")) {
				$(target).hide();
			} else {
				$(target).show();
			}
		});
	}

}
$(document).ready(function() {
	util.wrapCarEvidenceCode('.notices-list', 'div.errors');

	/*
	 * Fix IE 3D button effect if there is no <div> as inner wrapping element in
	 * <button> element
	 */
	if (isIe) {
		$("button:not(:has(div))").wrapInner(function() {
			return "<div></div>";
		});
	}
});

// Develop only, dnd support for JSP name labels
(function() {
	$(document).ready(
			function() {
				var all = $('.support_me_jsp_name');
				if (!all.size()) {
					return;
				}
				
				all.hide();
				var switcher = document.createElement('button');
				switcher.innerHTML = 'T';
				switcher.onclick = function() {
					all.toggle();
					return false;
				}
				switcher.style.position = 'absolute';
				switcher.style.top = '5px';
				switcher.style.right = '15px';
				switcher.style.zIndex = 500;
				switcher.style.width = '20px';
				switcher.style.minWidth = '20px';
				document.body.appendChild(switcher);

				if (isIe) {
					return;
				}

				var dragStartListener = function(event) {
					elem = $(event.target);
					event.dataTransfer.setData("text/plain", (parseInt(elem
							.css('left'), 10) - event.clientX)
							+ ','
							+ (parseInt(elem.css('top'), 10) - event.clientY)
							+ ',' + elem.attr('id'));
				};
				all.each(function(i, e) {
					e.addEventListener('dragstart', dragStartListener, false);
				});

				document.body.addEventListener('dragover', function(event) {
					event.preventDefault();
					return false;
				}, false);

				document.body.addEventListener('drop',
						function(event, elem) {
							var params = event.dataTransfer.getData(
									"text/plain").split(',');
							var source = document.getElementById(params[2])
									|| window.frames[0].document
											.getElementById(params[2]);
							if (!source) {
								return;
							}
							source.style.left = (event.clientX + parseInt(
									params[0], 10))
									+ 'px';
							source.style.top = (event.clientY + parseInt(
									params[1], 10))
									+ 'px';
							event.preventDefault();
							return false;
						}, false);
			});
})();
