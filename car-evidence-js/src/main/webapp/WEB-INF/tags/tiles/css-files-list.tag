<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:c="http://java.sun.com/jsp/jstl/core" version="2.1"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:tiles="http://tiles.apache.org/tags-tiles"
	>

	<jsp:directive.tag body-content="tagdependent" />
	
	<c:set var="baseUrl"><spring:url value="/static/" /></c:set>

	<tiles:importAttribute name="cssfiles" />
	<c:forEach items="${cssfiles}" var="item">
		<link href="${baseUrl}${item}" rel="stylesheet" type="text/css" media="screen" />
	</c:forEach>
	
	<jsp:text><![CDATA[<!--[if IE 8]>]]></jsp:text>
		<link rel="stylesheet" type="text/css" href="${baseUrl}style/ie8.css" />
	<jsp:text><![CDATA[<![endif]-->]]></jsp:text>    

</jsp:root>