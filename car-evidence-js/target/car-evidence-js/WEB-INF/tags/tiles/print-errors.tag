<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
          xmlns:c="http://java.sun.com/jsp/jstl/core" version="2.1"
          xmlns:fn="http://java.sun.com/jsp/jstl/functions"
          xmlns:spring="http://www.springframework.org/tags">
    
    <jsp:directive.tag body-content="tagdependent" />

    <c:if test="${not empty controllerError}">            
    
	    <ul class="notices-list errors">
	        
             <c:set var="isCollection" value="${false}" />
             <c:catch>
                 <c:set var="s" value="${controllerError.iterator()}" />
	             <c:set var="isCollection" value="${true}" />
             </c:catch>
             
             <c:choose>
                 <c:when test="${isCollection}">
                     <c:forEach items="${controllerError}" var="errorKey">
                         <li><c:out value="${errorKey}" /></li>
                     </c:forEach>
                 </c:when>
                 <c:otherwise>
                     <li><c:out value="${controllerError}" /></li>
                 </c:otherwise>
             </c:choose>
	        
	    </ul>                        
    

    </c:if>    
    
</jsp:root>