<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
          xmlns:c="http://java.sun.com/jsp/jstl/core" version="2.1"
          xmlns:spring="http://www.springframework.org/tags"
          xmlns:tiles="http://tiles.apache.org/tags-tiles">
    
    <jsp:directive.tag body-content="tagdependent" />
    
    <tiles:addAttribute>js/js-languages/custom-<c:out value="${locale.language}" />.js</tiles:addAttribute>
    
</jsp:root>