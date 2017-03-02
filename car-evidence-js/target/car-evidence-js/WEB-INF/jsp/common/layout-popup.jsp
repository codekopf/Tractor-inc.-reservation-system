<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
          xmlns:c="http://java.sun.com/jsp/jstl/core" version="2.1"
          xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
          xmlns:tiles="http://tiles.apache.org/tags-tiles"
		  xmlns:customTiles="cz/ucl/hatchery/carevidence/TilesSupport"
		  >
          
	<jsp:output doctype-root-element="html" 
	            doctype-public="-//W3C//DTD XHTML 1.0 Strict//EN" 
	            doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd" /> 

    <jsp:directive.page buffer="none" session="true" language="java" contentType="text/html" pageEncoding="UTF-8" />          

    <fmt:setLocale value="${requestScope.locale}" />

	<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="cs" lang="cs" class="popup">
	    
	    <head>
	       
            <tiles:insertAttribute name="metahead" />
		   
			<title><tiles:getAsString name="title" /></title>
            
            <customTiles:css-files-list />
            
            <customTiles:js-files-list />
						      
        </head>
        <tiles:useAttribute name="body-class" classname="java.lang.String" id="bodyClass" ignore="true" />
        <body class="popup ${bodyClass}">
	
	        <div id="container">
				<div id="content">
					<tiles:insertAttribute name="errors-notices" />
					<tiles:insertAttribute name="body" />
				</div>
            </div>	           	        
            
            <tiles:useAttribute name="js-startup" classname="java.lang.String" id="jsStartup" ignore="true" />
			<script type="text/javascript">
	            $(window).bind('load', function() {
	            	parent.loadingWrapper.hide();
	            });
              
               jQuery(document).ready(
                       function() {
                       		<c:if test="${not empty jsStartup}">
                       			<c:out value="${jsStartup}" escapeXml="false"/>
                      		</c:if>
                      		overflowToDot.init('td');
                       }
               );

			</script>

	    </body>                

	</html>
          
</jsp:root>         
