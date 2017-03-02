<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
          xmlns:c="http://java.sun.com/jsp/jstl/core" version="2.1"
          xmlns:form="http://www.springframework.org/tags/form"
          xmlns:fmt="http://java.sun.com/jsp/jstl/fmt">
    
    <jsp:directive.tag body-content="tagdependent" />

    <jsp:directive.attribute name="editable" required="true" rtexprvalue="true" type="java.lang.Boolean" />
    <jsp:directive.attribute name="path" required="true" rtexprvalue="true" />
    <jsp:directive.attribute name="required" required="true" rtexprvalue="true" type="java.lang.Boolean" />
    <jsp:directive.attribute name="validation" required="true" rtexprvalue="true" />
    <jsp:directive.attribute name="size" required="true" rtexprvalue="true" type="java.lang.Integer"/>
    <jsp:directive.attribute name="value" required="true" rtexprvalue="true" />

     <c:choose>
        <c:when test="${editable}">
            <c:set var="requiredStr">
                <c:choose>
                    <c:when test="${required}"><c:out value="required," /></c:when>
                    <c:otherwise></c:otherwise>
                </c:choose>
            </c:set>
            <form:input path="${path}"
                    cssErrorClass="fielderror right validate[${requiredStr}${validation}]"
                    cssClass="right validate[${requiredStr}${validation}]"
                    size="${size}"/>
        </c:when>
        <c:otherwise>
            <c:out value="${value}"/>
        </c:otherwise>
    </c:choose>
</jsp:root>