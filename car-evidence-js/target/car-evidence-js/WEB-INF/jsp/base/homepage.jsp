<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
          xmlns:c="http://java.sun.com/jsp/jstl/core"
          xmlns:spring="http://www.springframework.org/tags"
          xmlns:tiles="http://tiles.apache.org/tags-tiles">

    <jsp:directive.page buffer="none" session="true" language="java" contentType="text/html" pageEncoding="UTF-8" />

    <tiles:insertDefinition name="carevidence.default">

        <tiles:putAttribute name="title">CarEvidence | <spring:message code="layout.homepage.title" /></tiles:putAttribute>
        <tiles:putAttribute name="body" value="/WEB-INF/jsp/base/content/homepage-body.jsp" />
        
    </tiles:insertDefinition>
    
</jsp:root>