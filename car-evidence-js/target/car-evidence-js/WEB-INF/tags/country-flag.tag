<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
          xmlns:c="http://java.sun.com/jsp/jstl/core" version="2.1"
          xmlns:fmt="http://java.sun.com/jsp/jstl/fmt">
    
    <jsp:directive.tag body-content="tagdependent" />
    
    <jsp:directive.attribute name="countryCode" required="true" rtexprvalue="true" />
    
    <div id="country_flag">
    	<div class="posrelative">
	    	<span class="overflowhidden">
	        	 <img src="../images/flags/${countryCode}.png" alt="Flag ${countryCode}"/>    
	         </span>
    	</div>
    </div>        
    
</jsp:root>    