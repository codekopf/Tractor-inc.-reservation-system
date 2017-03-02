"use strict";

// HTML elements are defined in "layout-...jsp"
var loadingWrapper = {

	showRequestsCount : 0,

	hideRequestsCount : 0,

	isLoadingDialogVisible : false,

	skipNextShow : false,

	show : function() {
		var wrapper = $('#loading_ico_wrapper', parent.document);

		if (this.skipNextShow) {
			this.skipNextShow = false;
			return;
		}
		this.showRequestsCount++;
		if (this.isLoadingDialogVisible) {
			return;
		}

		this.isLoadingDialogVisible = true;

		wrapper.css('top', Math.max(1, $(parent).height() / 2 - 21) + 'px');
		wrapper.css('left', Math.max(1, $(parent).width() / 2 - 21) + 'px');

		wrapper.show();
		if (isIe) {
			wrapper.html(wrapper.html());
		}
		$('#loading_layer', parent.document).show();
	},

	hide : function() {
		this.hideRequestsCount++;

		if (!this.isLoadingDialogVisible
				|| this.hideRequestsCount < this.showRequestsCount) {
			return;
		}
		this.isLoadingDialogVisible = false;
		$('#loading_ico_wrapper', parent.document).hide();
		$('#loading_layer', parent.document).hide();
	},

	/**
	 * If called, wrapper wont be showed on next request. This might be useful
	 * for example for file downloads with server business validation. Initially
	 * is used at audit filter page.
	 */
	skipNextShowRequest : function() {
		this.skipNextShow = true;
	}

}

$(window).bind('beforeunload', function() {
	parent.loadingWrapper.show();
});
$(document).ajaxSend(function() {
	parent.loadingWrapper.show();
});
$(document).ajaxComplete(function() {
	parent.loadingWrapper.hide();
});
$(document).ajaxError(function() {
	parent.loadingWrapper.hide();
});
