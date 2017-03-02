<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
          xmlns:c="http://java.sun.com/jsp/jstl/core" version="2.1"
          xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
          xmlns:fn="http://java.sun.com/jsp/jstl/functions"
          xmlns:spring="http://www.springframework.org/tags"
          xmlns:customSupport="cz/ucl/hatchery/carevidence/Support">
          
	<div id="home_infobox">
	    <h2><spring:message code="layout.homepage.title" /></h2>  
	</div>
    <div id="content-wrapper">
	    <table class="form">
	    
	     <colgroup>
	         <col style="width: 202px" />
	         <col />
	     </colgroup>        
	
	        <tr>
	            <th class="left"><spring:message code="layout.homepage.info.user" /></th>
	            <td><c:out value="${requestScope.user}" /></td>
	        </tr>
	        <tr>
	            <th class="left"><spring:message code="layout.homepage.info.version" /></th>
	            <td>
	                <customSupport:system-config key="version" /><br />(<fmt:formatDate value="${applicationStart}" />)
	            </td>
	        </tr>
	        <tr>
	            <th class="left"><spring:message code="layout.homepage.info.server" /></th>
	            <td><c:out value="${server}" /></td>
	        </tr>
	    
	    </table>    

	</div>
</jsp:root>          