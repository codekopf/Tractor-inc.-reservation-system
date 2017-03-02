<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
          xmlns:c="http://java.sun.com/jsp/jstl/core" version="2.1"
          xmlns:spring="http://www.springframework.org/tags">
	
	<jsp:directive.tag body-content="tagdependent" />
	<jsp:directive.attribute name="test" required="true" rtexprvalue="true" />

	<c:choose>
	    <c:when test="${test}">
	        <spring:message code="layout.common.boolean.true" />
	    </c:when>
	    <c:otherwise>
	        <spring:message code="layout.common.boolean.false" />
	    </c:otherwise>
	</c:choose>

</jsp:root>