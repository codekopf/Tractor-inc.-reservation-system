<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
          xmlns:c="http://java.sun.com/jsp/jstl/core" version="2.1"
          xmlns:spring="http://www.springframework.org/tags">
    
    <jsp:directive.tag body-content="tagdependent" />
    <jsp:directive.attribute name="items" required="true" rtexprvalue="true" type="java.util.List"/>
    <jsp:directive.attribute name="commonNumber" required="true" rtexprvalue="true" type="java.lang.String"/>
        
    <ul class="bookmark">
    
        <c:forEach items="${items}" var="menuItem">
                    
            <c:choose>
                <c:when test="${menuItem.active}">
                    <c:set var="varClass" value="active" />
                </c:when>
                <c:otherwise>
                    <c:set var="varClass" value="" />
                </c:otherwise>
            
            </c:choose>
            <li class="${ varClass }" >
                <jsp:element name="a">
                    <jsp:attribute name="href"><spring:url value="${menuItem.uri}" /></jsp:attribute>
                    <jsp:attribute name="title"><spring:message code="${menuItem.messageTitlePrefix}" /><c:out value=" ${commonNumber} - " /><spring:message code="${menuItem.messageKey}" /></jsp:attribute>
                    <jsp:body>
                        <spring:message code="${menuItem.messageKey}" />
                    </jsp:body>
                </jsp:element>
            </li>
            
        </c:forEach>
        
    </ul> 
    
</jsp:root>    