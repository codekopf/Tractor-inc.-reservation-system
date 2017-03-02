CarEvidence.validation.format = {
		dateWithoutYear : {
            "regex": /^([0-9]{2})\.([0-9]{2})\.$/,
            "alertText": "* Neplatné datum, datum musí být ve formátu den.měsíc. (dd.mm.)",
		},
		percentValue: {
			"regex": /^[\+]?([0-9]{1,3})?([,]([0-9]{0,2}))?$/,
			"alertText": "* Neplatný procentuální formát, procentuální formát musí být ve formátu ##,00.",
			"alertTextRange": "* Neplatná procentuální hodnota, procentuální hodnota musí mít rozsah 0%  - 100%.",
		},
		intWithTrailingZeros: {
			"regex": /^([0-9\s\u00A0]{1,11})(([,.](0{2}))|())$/,
			"alertText": "* Nelze ukládat desetinná čísla.",
		},
		intWithNoPlusSign: {
			"regex": /^[0-9\s]{1,11}$/,
			"alertText": "* Neplatné číslo",
		}
};