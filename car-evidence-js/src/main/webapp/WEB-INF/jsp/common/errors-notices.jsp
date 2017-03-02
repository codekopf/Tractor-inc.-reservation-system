<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
          xmlns:c="http://java.sun.com/jsp/jstl/core" version="2.1"
          xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
          xmlns:fn="http://java.sun.com/jsp/jstl/functions"
          xmlns:spring="http://www.springframework.org/tags"
          xmlns:customTiles="cz/ucl/hatchery/carevidence/tags/TilesSupport">
       
    <jsp:directive.page buffer="none" session="true" language="java" contentType="text/html" pageEncoding="UTF-8" />          

    <customTiles:print-errors />   
    <customTiles:print-notices />
    <customTiles:print-warnings />
                          
</jsp:root>          