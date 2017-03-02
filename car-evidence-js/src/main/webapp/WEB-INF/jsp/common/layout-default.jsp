<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
          xmlns:c="http://java.sun.com/jsp/jstl/core" version="2.1"
          xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
          xmlns:fn="http://java.sun.com/jsp/jstl/functions"
          xmlns:tiles="http://tiles.apache.org/tags-tiles"
          xmlns:spring="http://www.springframework.org/tags"
		  xmlns:customTiles="cz/ucl/hatchery/carevidence/TilesSupport"
		  >
		  
	<jsp:output doctype-root-element="html" 
	            doctype-public="-//W3C//DTD XHTML 1.0 Strict//EN" 
	            doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd" />

	<jsp:directive.page buffer="none" session="true" language="java" contentType="text/html" pageEncoding="UTF-8" />
	<c:set var="baseUrl"><spring:url value="/static/" /></c:set>
	<fmt:setLocale value="${requestScope.locale}" />

	<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="cs" lang="cs">

<head>

		<tiles:insertAttribute name="metahead" />

		<title><tiles:getAsString name="title" /></title> 

		<customTiles:css-files-list />
		
		<customTiles:js-files-list />
</head>
<body>
		<div id="slide-notification_wrapper">
			<div class="slide-notification_hide-button">
				<jsp:text />
			</div>
			<div class="messages">
				<jsp:text />
			</div>
		</div>
		<div id="loading_layer">
			<jsp:text />
		</div>
		<div id="loading_ico_wrapper">
			<img src="${baseUrl}images/loading_window_bg.gif">
			<jsp:text />
		</img>
	</div>
	<div id="container">

		<tiles:insertAttribute name="header" />

		<tiles:insertAttribute name="main-navigation" />

		<div id="pagebody">
			
			<tiles:useAttribute name="back-button" classname="java.lang.String" id="backButton" ignore="true" />
			<c:if test="${not empty backButton}">

				<c:set var="backButtonProperties" value="${fn:split(backButton, '|')}" />
				<div class="backbutton">

					<jsp:element name="a">
						<jsp:attribute name="class">button button-like</jsp:attribute>
						<jsp:attribute name="href">
							<c:out value="${backButtonProperties[0]}" />
						</jsp:attribute>
						<jsp:attribute name="title">
							<c:out value="${backButtonProperties[1]}" />
						</jsp:attribute>
						<jsp:body>
							<c:out value="${backButtonProperties[1]}" />
						</jsp:body>
					</jsp:element>

				</div>

			</c:if>
			<tiles:insertAttribute name="active-version-button"/>
			<hr class="hidden clear" />
			
			<h2>
				<tiles:getAsString name="h2-title" />
			</h2>
			<div id="content">

				<tiles:insertAttribute name="errors-notices" />
				
				<tiles:insertAttribute name="body" />

			</div>

			<hr class="hidden clear" />

		</div>

		<tiles:insertAttribute name="footer" />

	</div>

	<tiles:insertAttribute name="js-common-startup" />

	<tiles:useAttribute name="js-startup" classname="java.lang.String" id="jsStartup" ignore="true" />
	<script type="text/javascript">
              
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