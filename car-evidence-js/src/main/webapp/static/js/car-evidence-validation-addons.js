CarEvidence.validation.checkDateWithoutYear = function(field, rules, i, options){
	
	var pattern=new RegExp(CarEvidence.validation.format.dateWithoutYear.regex);

	if (!pattern.test(field.val()))
		return CarEvidence.validation.format.dateWithoutYear.alertText;
	
    var a = pattern.exec(field.val());
    var d = parseInt(a[1]),
    	m = parseInt(a[2]);

    var dt = new Date();
    dt.setFullYear(2013);
	dt.setMonth(m-1,d);

	if (!(d==dt.getDate() && m==(dt.getMonth()+1)))
		return CarEvidence.validation.format.dateWithoutYear.alertText;
		
};
CarEvidence.validation.checkPercentRange = function(field, rules, i, options){
	
	var pattern=new RegExp(CarEvidence.validation.format.percentValue.regex);

	if (!pattern.test(field.val()))
		return CarEvidence.validation.format.percentValue.alertText;
	
};
CarEvidence.validation.checkIntegerWithTrailingZeros = function(field, rules, i, options){
	
	var pattern=new RegExp(CarEvidence.validation.format.intWithTrailingZeros.regex);

	if (!pattern.test(field.val()))
		return CarEvidence.validation.format.intWithTrailingZeros.alertText;
	
};
CarEvidence.validation.checkIntegerWithNoPlusSign = function(field, rules, i, options){
	
	var pattern=new RegExp(CarEvidence.validation.format.intWithNoPlusSign.regex);

	if (!pattern.test(field.val()))
		return CarEvidence.validation.format.intWithNoPlusSign.alertText;
	
};