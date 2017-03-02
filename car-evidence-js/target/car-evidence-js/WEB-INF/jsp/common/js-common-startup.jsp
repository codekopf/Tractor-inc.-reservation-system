<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
		xmlns:c="http://java.sun.com/jsp/jstl/core"
		xmlns:spring="http://www.springframework.org/tags"
		xmlns:custom="cz/ucl/hatchery/carevidence/tags/Widgets"
		xmlns:customSupport="cz/ucl/hatchery/carevidence/tags/Support"
		xmlns:json="http://www.atg.com/taglibs/json">

	<c:set var="popupJsScript" scope="request">

		jQuery(document).ready(

			function() {
				
				<!-- look for links to open in new window -->
				jQuery("body").delegate(".open_new_window, .target_blank", "click", function() { this.target = "_blank";  return true;});
				

		<c:choose>
			<c:when test="${not empty controllerErrorPopup or not empty controllerErrorPopupKey}">
				new ErrorPopUp(
					<json:object escapeXml="false">

						<c:choose>
							<c:when test="${not empty controllerErrorPopupKey}">

								<json:array name="messages">
									<c:forEach items="${controllerErrorPopupKey}" var="errorKey">
										<json:property><spring:message code="${errorKey}" /></json:property>
									</c:forEach>	                            
								</json:array>

							</c:when>
							<c:otherwise>
								<json:array name="messages">
									 <c:set var="isCollection" value="${false}" />
									 <c:catch>
										 <c:set var="s" value="${controllerErrorPopup.iterator()}" />
										 <c:set var="isCollection" value="${true}" />
									 </c:catch>
									 
									 <c:choose>
										 <c:when test="${isCollection}">
											 <c:forEach items="${controllerErrorPopup}" var="errorKey">
												 <json:property><c:out value="${errorKey}" /></json:property>
											 </c:forEach>
										 </c:when>
										 <c:otherwise>
											 <json:property><c:out value="${controllerErrorPopup}" /></json:property>
										 </c:otherwise>
									 </c:choose>
								</json:array>
							</c:otherwise>
						</c:choose>
						
						<json:property name="title"><spring:message code="error.title" /></json:property>
						
					</json:object>    
				);


			</c:when>
			<c:when test="${not empty controllerWarningPopup or not empty controllerWarningPopupKey}">            

				<!-- Warning popup -->
				new WarningPopUp(
					<json:object escapeXml="false">

						<c:choose>
							<c:when test="${not empty controllerWarningPopupKey}">

								<json:array name="messages">
									<c:forEach items="${controllerWarningPopupKey}" var="errorKey">
										<json:property><spring:message code="${errorKey}" /></json:property>
									</c:forEach>
								</json:array>
								
							</c:when>
							<c:otherwise>
								<json:array name="messages">
									 <c:set var="isCollection" value="${false}" />
									 <c:catch>
										 <c:set var="s" value="${controllerWarningPopup.iterator()}" />
										 <c:set var="isCollection" value="${true}" />
									 </c:catch>
									 
									 <c:choose>
										 <c:when test="${isCollection}">
											 <c:forEach items="${controllerWarningPopup}" var="errorKey">
												 <json:property><c:out value="${errorKey}" /></json:property>
											 </c:forEach>
										 </c:when>
										 <c:otherwise>
											 <json:property><c:out value="${controllerWarningPopup}" /></json:property>
										 </c:otherwise>
									 </c:choose>
								</json:array>
							</c:otherwise>
						</c:choose>

						<json:property name="title"><spring:message code="warning.title" /></json:property>

					</json:object>
				);
				
			</c:when>       
			<c:when test="${not empty controllerNoticePopupKey or not empty controllerNoticePopup}">    
				<!-- Notification -->
				setTimeout(function() { 
					notification.show(
						<json:object escapeXml="false">
							<c:choose>                          
								<c:when test="${not empty controllerNoticePopupKey}">

									<json:array name="messages">
										<c:forEach items="${controllerNoticePopupKey}" var="errorKey">
											<json:property><spring:message code="${errorKey}" /></json:property>
										</c:forEach>
									</json:array>

								</c:when>
								<c:otherwise>
									<json:array name="messages" items="${controllerNoticePopup}" />
										<json:array name="messages">
										 <c:set var="isCollection" value="${false}" />
										 <c:catch>
											 <c:set var="s" value="${controllerNoticePopup.iterator()}" />
											 <c:set var="isCollection" value="${true}" />
										 </c:catch>
										 
										 <c:choose>
											 <c:when test="${isCollection}">
												 <c:forEach items="${controllerNoticePopup}" var="errorKey">
													 <json:property><c:out value="${errorKey}" /></json:property>
												 </c:forEach>
											 </c:when>
											 <c:otherwise>
												 <json:property><c:out value="${controllerNoticePopup}" /></json:property>
											 </c:otherwise>
										 </c:choose>
									</json:array>
								</c:otherwise>
							</c:choose>
							<json:property name="title"><spring:message code="notice.title" /></json:property>
						</json:object>
					); }, 150);
			</c:when>
		</c:choose>

		
			}
		);

	</c:set>

	<script type="text/javascript"><c:out value="${popupJsScript}" escapeXml="false" /></script>

</jsp:root>