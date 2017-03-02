<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
          xmlns:c="http://java.sun.com/jsp/jstl/core"
          xmlns:spring="http://www.springframework.org/tags"
          xmlns:tiles="http://tiles.apache.org/tags-tiles"
          xmlns:custom="cz/ucl/hatchery/carevidence/Widgets"
          xmlns:customTiles="cz/ucl/hatchery/carevidence/TilesSupport">

    <jsp:directive.page buffer="none" session="true" language="java" contentType="text/html" pageEncoding="UTF-8" />

    <tiles:insertDefinition name="carevidence.default">

        <tiles:putAttribute name="title">CarEvidence | <spring:message code="car.title.add-new" /></tiles:putAttribute>
        <tiles:putAttribute name="h2-title"><spring:message code="car.title.add-new" /></tiles:putAttribute>
        
        <tiles:putAttribute name="body" value="/WEB-INF/jsp/car/content/add-new-car-body.jsp" />
        
        <tiles:putAttribute name="js-startup">
        	<custom:js-setup-datepicker />
        	<custom:js-datepicker query=".datepicker"  />

            <custom:js-form-validator form="#addCarForm" params="validationEventTrigger:'submit'" />
        </tiles:putAttribute>
        
        <!-- additional CSS files to add to head of page -->
        <tiles:putListAttribute name="cssfiles" inherit="true">
        
            <customTiles:put-validation-css />
        
        </tiles:putListAttribute>

        <!-- additional JS files to add to head of page -->
        <tiles:putListAttribute name="jsfiles" inherit="true">
            
            <customTiles:put-validation-js />
            <customTiles:put-datepicker-js />
            
            
        </tiles:putListAttribute>
        
    </tiles:insertDefinition>
    
</jsp:root>