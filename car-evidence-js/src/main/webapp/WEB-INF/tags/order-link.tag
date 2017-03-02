<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
          xmlns:c="http://java.sun.com/jsp/jstl/core" version="2.1"
          xmlns:spring="http://www.springframework.org/tags"
          xmlns:customSupport="cz/ucl/hatchery/carevidence/Support"
		  >
                
    <jsp:directive.tag body-content="tagdependent" />
    
    <jsp:directive.attribute name="messageCode" required="true" rtexprvalue="true"/>
    <jsp:directive.attribute name="actualTableOrdering" required="true" rtexprvalue="true" type="cz.ucl.hatchery.carevidence.model.TableOrder"/>    
    <jsp:directive.attribute name="orderColumn" required="true" rtexprvalue="true"/>
    <jsp:directive.attribute name="arguments" required="false" rtexprvalue="true"/>
    <jsp:directive.attribute name="argumentSeparator" required="false" rtexprvalue="true" />
    
    <customSupport:order-dir-type tableOrdering="${actualTableOrdering}" orderColumn="${orderColumn}" var="orderDir" />

    <jsp:element name="a">
        
        <jsp:attribute name="href">            
            <spring:url value="${actualUri}" var="actualUri"/>
            <customSupport:link-from-url url="${actualUri}" ordercol="${orderColumn}" orderdir="${orderDir}" />
        </jsp:attribute>
        
        <jsp:attribute name="class">
            
            <jsp:text>orderlink</jsp:text>
            <c:if test="${actualTableOrdering.orderColumn eq orderColumn}">
                <c:choose>
	                <c:when test="${not actualTableOrdering.ascending}"><jsp:text> desc</jsp:text></c:when>            
                    <c:otherwise><jsp:text> asc</jsp:text></c:otherwise>
                </c:choose>
            </c:if>
        
        </jsp:attribute>
        
        <jsp:body>
        	<c:choose>
        		<c:when test="${empty arguments}">
        		    <spring:message code="${messageCode}"/>
        		</c:when>
        		<c:otherwise>
        			<c:if test="${empty argumentSeparator}">
        				<c:set var="argumentSeparator" value=","></c:set>
        			</c:if>
        			<spring:message code="${messageCode}" arguments="${arguments}" argumentSeparator="${argumentSeparator}"/>
        		</c:otherwise>
        	</c:choose>
        </jsp:body>
    
    </jsp:element>    
    
</jsp:root>    