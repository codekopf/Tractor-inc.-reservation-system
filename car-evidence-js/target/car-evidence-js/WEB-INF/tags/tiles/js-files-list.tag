<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
          xmlns:c="http://java.sun.com/jsp/jstl/core" version="2.1"
          xmlns:spring="http://www.springframework.org/tags"
          xmlns:tiles="http://tiles.apache.org/tags-tiles">
    
    <jsp:directive.tag body-content="tagdependent" />
    
    <c:set var="baseUrl"><spring:url value="/static/" /></c:set>
    
    <tiles:importAttribute name="jsfiles" />
    <spring:message code="layout.form.button.close" var="closeTitle"/>
    <script>
    	var CLOSE_TITLE = "${closeTitle}";
    </script>
    <c:forEach items="${jsfiles}" var="item">
        <script type="text/javascript" src="${baseUrl}${item}"><jsp:text /></script>
    </c:forEach>   
    
</jsp:root>