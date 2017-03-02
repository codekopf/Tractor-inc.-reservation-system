<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
          xmlns:c="http://java.sun.com/jsp/jstl/core" version="2.1"
          xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
          xmlns:spring="http://www.springframework.org/tags"
          xmlns:customTiles="cz/ucl/hatchery/carevidence/tags/TilesSupport">
                
	<div id="header">
	    
	    <h1>
            <jsp:element name="a">
                <jsp:attribute name="href">${baseHref}</jsp:attribute>
                <jsp:attribute name="title"><spring:message code="layout.main-navigation.homepage.title" /></jsp:attribute>
                <jsp:body>
                    <span class="none">
                        <spring:message code="layout.header.title" />
                    </span>
                </jsp:body>
            </jsp:element>
	    </h1>
	    
        <ul class="lang">
            <c:forEach items="${langMenu}" var="item" varStatus="status">
                <c:set var="title">
                    <spring:message code="layout.header.switchlanguage" arguments="${item.title}" />
                </c:set>
                <c:choose>
                    <c:when test="${status.last}">
                        <li class="last">
		                    <jsp:element name="a">
                                <jsp:attribute name="href"><spring:url value="${actualUri}${item.uri}" /></jsp:attribute>
                                <jsp:attribute name="title"><c:out value="${title}" /></jsp:attribute>
                                <jsp:body>
                                    <c:out value="${item.title}" />
                                </jsp:body>
		                    </jsp:element>
                        </li>
                    </c:when>
                    <c:otherwise>
		                <li>
                            <jsp:element name="a">
                                <jsp:attribute name="href"><spring:url value="${actualUri}${item.uri}" /></jsp:attribute>
                                <jsp:attribute name="title"><c:out value="${title}" /></jsp:attribute>
                                <jsp:body>
                                    <c:out value="${item.title}" />
                                </jsp:body>
                            </jsp:element>
		                </li>                    
                    </c:otherwise>                                                
                </c:choose>
            </c:forEach>
        </ul>    
<!--  	
	    <form id="searchform" action="#" method="get">
	        
	        <div>
	            <spring:message code="layout.header.search.placeholder" var="placeholder"/>
	            <label for="searchtext" class="none"><c:out value="${placeholder}" /></label>	            
	            <input type="text" id="searchtext" name="search" value="${placeholder}" />
	            <button type="submit" class="square">
	                <span class="none"><spring:message code="layout.header.search.button" /></span>
	            </button>                           
	        </div>
	    
	    </form>
 -->	
	
	</div>
         
          
</jsp:root>          