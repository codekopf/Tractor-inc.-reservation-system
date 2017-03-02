<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" 
          xmlns:c="http://java.sun.com/jsp/jstl/core" version="2.1"
          xmlns:spring="http://www.springframework.org/tags">
                
    <jsp:directive.tag body-content="tagdependent" />
    
    <jsp:directive.attribute name="colspan" required="true" rtexprvalue="true" type="java.lang.Integer"/>
    <jsp:directive.attribute name="cellsbefore" required="false" rtexprvalue="true" type="java.lang.Integer"/>
    <jsp:directive.attribute name="cellsafter" required="false" rtexprvalue="true" type="java.lang.Integer"/>
    
    <tr>

        <c:if test="${not empty cellsbefore and cellsbefore gt 0}">
            
            <c:forEach begin="1" end="${cellsbefore}" step="1">
                <td />
            </c:forEach>
        
        </c:if>
    
        <td colspan="${colspan}"><spring:message code="layout.table.noitem" /></td>

        <c:if test="${not empty cellsafter and cellsafter gt 0}">
            
            <c:forEach begin="1" end="${cellsafter}" step="1">
                <td />
            </c:forEach>
        
        </c:if>
        
    </tr>
    
</jsp:root>        