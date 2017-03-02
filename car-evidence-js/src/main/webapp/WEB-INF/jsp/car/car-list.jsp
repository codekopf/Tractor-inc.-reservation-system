<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
          xmlns:c="http://java.sun.com/jsp/jstl/core"
          xmlns:spring="http://www.springframework.org/tags"
          xmlns:tiles="http://tiles.apache.org/tags-tiles"
		  >

    <jsp:directive.page buffer="none" session="true" language="java" contentType="text/html" pageEncoding="UTF-8" />

    <tiles:insertDefinition name="carevidence.default">

        <tiles:putAttribute name="title">CarEvidence | <spring:message code="car.title.list" /></tiles:putAttribute>
        <tiles:putAttribute name="h2-title"><spring:message code="car.title.list" /></tiles:putAttribute>
        
        <tiles:putAttribute name="body" value="/WEB-INF/jsp/car/content/car-list-body.jsp" />
        
    </tiles:insertDefinition>
    
</jsp:root>