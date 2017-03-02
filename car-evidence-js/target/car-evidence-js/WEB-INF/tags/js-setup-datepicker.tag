<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
          xmlns:c="http://java.sun.com/jsp/jstl/core" version="2.1"
          xmlns:spring="http://www.springframework.org/tags"
          >
    
    <jsp:directive.tag body-content="tagdependent" />
    <jsp:directive.attribute name="dateformat" rtexprvalue="true" />
    
    <c:if test="${empty dateformat}">
        <c:set var="dateformat">dd.mm.yy</c:set>
    </c:if>
    
    $.datepicker.setDefaults({
         dateFormat: "<c:out value="${dateformat}" />",
         showOn: "both",
         buttonImageOnly:true,
         buttonImage: "../static/images/calendar.png",
         buttonText: "<spring:message code="layout.form.button.choose-date" />"
    });
    
</jsp:root>