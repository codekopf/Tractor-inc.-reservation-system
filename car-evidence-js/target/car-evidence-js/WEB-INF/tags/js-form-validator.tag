<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
          xmlns:c="http://java.sun.com/jsp/jstl/core" version="2.1">
    
    <jsp:directive.tag body-content="tagdependent" />
    <jsp:directive.attribute name="form" required="true" rtexprvalue="true" />
    <jsp:directive.attribute name="params" required="false" rtexprvalue="true" />
    
    jQuery("<c:out value="${form}" />").validationEngine({
        showArrow:true,
        autoHidePrompt:true,
        autoHideDelay:5000,
        promptPosition:"topLeft"
        <c:if test="${not empty params}">,<c:out value="${params}" escapeXml="false" /></c:if>
    });    
    
</jsp:root>    