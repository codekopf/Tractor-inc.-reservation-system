<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
          xmlns:c="http://java.sun.com/jsp/jstl/core" version="2.1">
    
   <jsp:directive.tag body-content="tagdependent" />
   
   <jsp:directive.attribute name="reloadParent" required="true" rtexprvalue="true" type="java.lang.Boolean" />
    
    <c:if test="${reloadParent}">
	     CarEvidence.parentWindowReload();    
    	 new ParentWindowReloader();
    </c:if>   
   
    
</jsp:root>    