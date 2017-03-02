<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
          xmlns:c="http://java.sun.com/jsp/jstl/core" version="2.1"
          xmlns:fmt="http://java.sun.com/jsp/jstl/fmt">
	
	<jsp:directive.tag body-content="tagdependent" />
	<jsp:directive.attribute name="value" required="true" rtexprvalue="true" />
	<jsp:directive.attribute name="pattern"  rtexprvalue="true" />
	<jsp:directive.attribute name="minFractionDigits"  rtexprvalue="true" />
	<jsp:directive.attribute name="maxFractionDigits"  rtexprvalue="true" />
    
   	<jsp:directive.include file="common/print-price.jspf" />

</jsp:root>