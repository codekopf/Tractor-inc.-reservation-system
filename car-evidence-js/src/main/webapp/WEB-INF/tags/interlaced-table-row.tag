<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
          xmlns:c="http://java.sun.com/jsp/jstl/core" version="2.1">
       
    <jsp:directive.tag body-content="scriptless" />
    
    <jsp:directive.attribute name="rowcount" required="true" rtexprvalue="true" type="java.lang.Integer"/>
    <jsp:directive.attribute name="indexcell" rtexprvalue="true" type="java.lang.Boolean"/>
    
    <c:choose>
    
        <c:when test="${rowcount % 2 == 0}">
        
            <tr class="suda">           
                <c:if test="${indexcell}">
                    <td class="right index">
                        <c:out value="${rowcount}" />.
                    </td>
                </c:if>
                <jsp:doBody />            
            </tr>
        
        </c:when>
    
        <c:otherwise>

            <tr>           
                <c:if test="${indexcell}">
                    <td class="right index">
                        <c:out value="${rowcount}" />.
                    </td>
                </c:if>
                <jsp:doBody />            
            </tr>
        
        </c:otherwise>
    
    </c:choose>
    
</jsp:root>    