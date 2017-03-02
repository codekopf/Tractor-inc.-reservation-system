<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
          xmlns:c="http://java.sun.com/jsp/jstl/core" version="2.1"
          xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
          xmlns:custom="cz/ucl/hatchery/carevidence/Widgets">
	
	<jsp:directive.tag body-content="tagdependent" />
	<jsp:directive.attribute name="value" required="true" rtexprvalue="true" />
	<jsp:directive.attribute name="pattern"  rtexprvalue="true" />
    
    <custom:print-price value="${value}" pattern="${pattern}" minFractionDigits="0" maxFractionDigits="0" />

</jsp:root>