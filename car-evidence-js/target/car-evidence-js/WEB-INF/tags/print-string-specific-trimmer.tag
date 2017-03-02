<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root 
  xmlns:jsp="http://java.sun.com/JSP/Page"
  xmlns:c="http://java.sun.com/jsp/jstl/core" version="2.1"
  xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
  xmlns:fn="http://java.sun.com/jsp/jstl/functions">

	<jsp:directive.tag body-content="tagdependent" />
	<jsp:directive.attribute name="stringToTrim" required="true" rtexprvalue="true" />

	<jsp:directive.attribute name="length" required="false" rtexprvalue="true" />
	<jsp:directive.attribute name="delimiter" required="false" rtexprvalue="true" />

	<c:if test="${empty delimiter}">
		<c:set var="delimiter" value=" " />
	</c:if>
	<c:if test="${empty length}">
		<c:set var="length" value="30" />
	</c:if>

 	<c:choose>

		<c:when test="${fn:length(stringToTrim) gt length}">

			<c:set var="itemSubstr" value="${fn:split(stringToTrim, delimiter)}" />
			<c:set var="itemLength" value="0" />
			<c:forEach items="${itemSubstr}" var="itemTrim">
				<c:set var="itemLength" value="${itemLength + fn:length(itemTrim)}" />
				<c:if test="${itemLength lt length}">
					<c:out value="${itemTrim}" /><jsp:text> </jsp:text>
				</c:if>
			</c:forEach>
			...
		</c:when>
		<c:otherwise>
			<c:out value="${stringToTrim}" />
		</c:otherwise>

	</c:choose>

</jsp:root>