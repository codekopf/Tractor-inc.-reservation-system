CarEvidence.validation.format = {
		dateWithoutYear : {
            "regex": /^([0-9]{2})\.([0-9]{2})\.$/,
            "alertText": "* Invalid date format, valid format is day.month (dd.mm)",            
		},
		percentValue: {
			"regex": /^[-\+]?([0-9]{1,3})?([\.]([0-9]{0,2}))?$/,
			"alertText": "* Invalid percentage value, percentage value format must be ###,00.",
			"alertTextRange": "* Invalid percentage value, percentage value be between 0% and 100%.",
			
		},
		intWithTrailingZeros: {
			"regex": /^[0-9]{1,3}(,?([0-9]{3}))*((.(0{2}))|())$/,
			"alertText": "* Invalid number format, valid format is ###,###,###.00",
		},
		intWithNoPlusSign: {
			"regex": /^[0-9]{1,3}(,?([0-9]{3}))*$/,
			"alertText": "* Invalid number format, valid format is ##,###",
		}
};