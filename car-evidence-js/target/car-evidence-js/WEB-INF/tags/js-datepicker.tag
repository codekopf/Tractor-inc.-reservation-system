<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
          xmlns:c="http://java.sun.com/jsp/jstl/core" version="2.1"
          xmlns:fn="http://java.sun.com/jsp/jstl/functions">
    
    <jsp:directive.tag body-content="tagdependent" />
    <jsp:directive.attribute name="query" required="true" rtexprvalue="true" />
    <jsp:directive.attribute name="jsParams" required="false" rtexprvalue="true" />
        
    jQuery("<c:out value="${query}" />").datepicker(<c:if test="${not empty jsParams}"><c:out value="${jsParams}" /></c:if>);    
    
</jsp:root>