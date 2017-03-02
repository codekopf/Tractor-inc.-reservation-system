<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
          xmlns:c="http://java.sun.com/jsp/jstl/core"
          xmlns:spring="http://www.springframework.org/tags"
          xmlns:tiles="http://tiles.apache.org/tags-tiles">

    <jsp:directive.page buffer="none" session="true" language="java" contentType="text/html" pageEncoding="UTF-8" />

    <tiles:insertDefinition name="carevidence.default">
        
        <tiles:putAttribute name="title">Car Evidence | <spring:message code="layout.500.title" /></tiles:putAttribute>
        <tiles:putAttribute name="h2-title"><spring:message code="layout.500.title" /></tiles:putAttribute>
    
        <tiles:putAttribute name="body" value="/WEB-INF/jsp/base/content/500-body.jsp" />
    
        <tiles:putAttribute name="js-startup">
        
            // if is in PopUp -> hide all except content
            if (window.location != window.parent.location) {
                $("#header").hide();   
                $("#mainnav").hide();   
                $("#footer_wrapper").hide();   
                $("#alertmessage").hide();
                $("div.env_info").hide();
                $("body").addClass("popup");
                //$("#pagebody").removeAttr("id");           
            }
        
        </tiles:putAttribute>
    
    </tiles:insertDefinition>
    
</jsp:root>