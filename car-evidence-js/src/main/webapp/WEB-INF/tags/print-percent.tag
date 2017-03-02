<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
          xmlns:c="http://java.sun.com/jsp/jstl/core" version="2.1"
          xmlns:fmt="http://java.sun.com/jsp/jstl/fmt">
	
	<jsp:directive.tag body-content="tagdependent" />
	<jsp:directive.attribute name="value" required="true" rtexprvalue="true" />
	<jsp:directive.attribute name="pattern"  rtexprvalue="true" />
	
	<c:if test="${empty pattern}">
      <c:set var="pattern" value="##0.00" />
    </c:if>
	

    <c:if test="${not empty value}">
        <fmt:formatNumber value="${value/100}" pattern="${pattern} %" />
    </c:if>

</jsp:root>