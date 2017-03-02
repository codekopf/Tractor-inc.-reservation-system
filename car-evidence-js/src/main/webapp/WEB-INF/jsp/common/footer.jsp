<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
          xmlns:c="http://java.sun.com/jsp/jstl/core" version="2.1"
          xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"          
          xmlns:spring="http://www.springframework.org/tags"
          xmlns:customSupport="cz/ucl/hatchery/carevidence/Support">

    <jsp:directive.page buffer="none" session="true" language="java" contentType="text/html" pageEncoding="UTF-8" />          

    
	<div id="footer_wrapper">
	
        <div id="footer">
	        
	        <hr class="clear hidden" />
	        <jsp:useBean id="now" class="java.util.Date"/>
	        <p class="copyright">&amp;copy; Unicorn a.s. <fmt:formatDate value="${now}" pattern="yyyy" />, <customSupport:system-config key="version" /> - UserHelpDesk: <a href="mailto:info@plus4u.cz">info@plus4u.cz</a> (+420) 213 203 450</p>
	    
	    </div>
	
	</div>

	<div class="env_info local">&amp;nbsp;</div>

</jsp:root>