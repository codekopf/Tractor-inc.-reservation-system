<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
          xmlns:c="http://java.sun.com/jsp/jstl/core" version="2.1"
          xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
          xmlns:spring="http://www.springframework.org/tags"
          xmlns:form="http://www.springframework.org/tags/form"
          xmlns:custom="cz/ucl/hatchery/carevidence/Widgets"
		  xmlns:customSupport="cz/ucl/hatchery/carevidence/Support"
		 >
       
    <jsp:directive.tag body-content="scriptless" />
    
    <jsp:directive.attribute name="editable" required="true" rtexprvalue="true" type="java.lang.Boolean"/>
    <jsp:directive.attribute name="path" required="true" rtexprvalue="true" />
    <jsp:directive.attribute name="modelAttribute" required="true" rtexprvalue="true" type="java.lang.Object" />
    <jsp:directive.attribute name="format" />
    <jsp:directive.attribute name="displayValue" />    
            
    <c:choose>
        
        <c:when test="${editable}">
            
            <jsp:doBody />
        
        </c:when>
        <c:otherwise>
            
            <form:hidden path="${path}" />

            <c:choose>
                <c:when test="${not empty displayValue}">
                    <c:out value="${displayValue}" />
                </c:when>
                <c:otherwise>
                    
                    <c:choose>
                        <c:when test="${format eq 'date'}">
                            <fmt:formatDate value="${modelAttribute[path]}" />
                        </c:when>
                        <c:when test="${format eq 'boolean'}">
                                <c:choose>
							        <c:when test="${modelAttribute[path]}">
							            <spring:message code="layout.common.boolean.true" />
							        </c:when>
							        <c:otherwise>
							            <spring:message code="layout.common.boolean.false" />
							        </c:otherwise>
							    </c:choose>
                        </c:when>
                        <c:when test="${format eq 'number'}">
                            <custom:print-price value="${modelAttribute[path]}" pattern="#,##0.00" />
                        </c:when>
                        <c:when test="${format eq 'linebreak'}">
                            <customSupport:nl2br text="${modelAttribute[path]}" />
                        </c:when>
                        <c:otherwise>
                            <c:out value="${modelAttribute[path]}" />    
                        </c:otherwise>
                    </c:choose>
                    
                </c:otherwise>
            
            </c:choose>                                    
            
        </c:otherwise>
    
    </c:choose>
    
</jsp:root>    