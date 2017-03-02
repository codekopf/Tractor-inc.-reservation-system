<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
          xmlns:c="http://java.sun.com/jsp/jstl/core" version="2.1"
          xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
          xmlns:spring="http://www.springframework.org/tags"
		  xmlns:customSupport="cz/ucl/hatchery/carevidence/Support">
       
    <jsp:directive.tag body-content="tagdependent" />
           
    <jsp:directive.attribute name="itemsCount" required="true" rtexprvalue="true" type="java.lang.Long"/>
    <jsp:directive.attribute name="pagesCount" required="true" rtexprvalue="true" type="java.lang.Integer" />
    <jsp:directive.attribute name="pagerItems" required="true" rtexprvalue="true" type="java.util.List" />
    <jsp:directive.attribute name="withoutForm" rtexprvalue="true" type="java.lang.Boolean" />
    <jsp:directive.attribute name="urlParameters" required="false" rtexprvalue="true" type="java.lang.String" />
          
	<div class="pager">
	    
	    <spring:url value="${actualUri}" var="actualUri"/>
	    
	    <p class="pagesinfo">
	        <spring:message code="layout.pager.info.total" arguments="${itemsCount},${pagesCount}" />
	    </p>            
		
	    <p class="pages">
	    
	        <c:forEach items="${pagerItems}" var="pagerItem" varStatus="status">
	        
	            <c:set var="uri">
	               <fmt:formatNumber value="${pagerItem.pageNumber}" var="pageNumber" />
	               <customSupport:link-from-url url="${actualUri}" page="${pageNumber}" />
	            </c:set>
	            <c:if test="${not empty urlParameters}">
	            	<c:set var="uri" value="${uri}&amp;${urlParameters}"/>
	            </c:if>
	        
	            <c:choose>
	            
	                <!-- Arrows -->
	                <c:when test="${pagerItem.isArrow}">
	                
	                    <c:set var="icoClass">
	                        <jsp:text>ico </jsp:text>
	                        <c:choose>
	                            <c:when test="${status.first}">firstpage</c:when>
	                            <c:when test="${status.index eq 1}">prevpage</c:when>
	                            <c:when test="${status.last}">lastpage</c:when>
	                            <c:otherwise>nextpage</c:otherwise>
	                        </c:choose>                        
	                    </c:set>
	                    
	                    <c:choose>
	                        <c:when test="${pagerItem.active}">
	                            
	                            <jsp:element name="a">
	                                <jsp:attribute name="href"><c:out value="${uri}" /></jsp:attribute>
	                                <jsp:attribute name="title"><spring:message code="${pagerItem.messageKey}" arguments="${pagerItem.pageNumber}" /></jsp:attribute>
	                                <jsp:attribute name="class"><c:out value="${icoClass}" /></jsp:attribute>
	                                <jsp:body>
	                                    <span>
	                                        <spring:message code="${pagerItem.messageKey}" arguments="${pagerItem.pageNumber}" />
	                                    </span>&amp;nbsp;
	                                </jsp:body>
	                            </jsp:element>                    
	                                           
	                        </c:when>
	                        <c:otherwise>
	
	                            <jsp:element name="a">
	                                <jsp:attribute name="title"><spring:message code="${pagerItem.messageKey}" arguments="${pagerItem.pageNumber}" /></jsp:attribute>
	                                <jsp:attribute name="class"><c:out value="${icoClass}" /></jsp:attribute>
	                                <jsp:body>
	                                    <span>
	                                        <spring:message code="${pagerItem.messageKey}" arguments="${pagerItem.pageNumber}" />
	                                    </span>&amp;nbsp;
	                                </jsp:body>
	                            </jsp:element>                    
	                        
	                        </c:otherwise>
	                    
	                    </c:choose>
	                                   
	                </c:when>
	                <!-- Pages Numbers -->
	                <c:otherwise>
	                
	                    <c:set var="aClass">page<c:if test="${pagerItem.active}"><jsp:text> </jsp:text>active</c:if></c:set>
	                
	                    <jsp:element name="a">
	                        <jsp:attribute name="href"><c:out value="${uri}" /></jsp:attribute>
	                        <jsp:attribute name="title"><spring:message code="${pagerItem.messageKey}" arguments="${pagerItem.pageNumber}" /></jsp:attribute>
	                        <jsp:attribute name="class"><c:out value="${aClass}" /></jsp:attribute>
	                        <jsp:body>
	                            
	                            <c:choose>
	                                <c:when test="${pagerItem.active}">
	                                    (<c:out value="${pagerItem.pageNumber}" />)
	                                </c:when>
	                                <c:otherwise>
	                                    <c:out value="${pagerItem.pageNumber}" />
	                                </c:otherwise>
	                            </c:choose>
	                            
	                        </jsp:body>
	                    </jsp:element>
	                
	                </c:otherwise>
	            
	            </c:choose>
	                    
	        </c:forEach>

	    </p>
            
		<c:if test="${empty withoutForm or not withoutForm}">
		
			<form class="pagesform" method="get" action="${actualUri}">
				<div>
    				<label class="inline" for="pagenr"><spring:message code="layout.pager.form.label"/>:</label>&amp;nbsp;
	       			<input type="text" class="right validate[custom[integer]]" name="page" id="pagenr" maxlength="3" />&amp;nbsp;
					<button class="square" type="submit">
    					<span class="none"><spring:message code="layout.form.button.do" /></span>
					</button>
				</div>                          
			</form>         
			
			<script type="text/javascript">
				jQuery(document).ready(
				        function() {
				            $("#pagenr").numericInput();
				        }
				);
			</script>
        
        </c:if>
	
	</div>          
          
</jsp:root>          