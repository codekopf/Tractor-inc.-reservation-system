<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:c="http://java.sun.com/jsp/jstl/core" version="2.1"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:customSupport="cz/ucl/hatchery/carevidence/tags/Support">

	<ul id="mainnav">

		<li class="menu_header home">
			<jsp:element name="a">
				<jsp:attribute name="href"><c:out value="${baseHref}" /></jsp:attribute>
				<jsp:attribute name="class">home</jsp:attribute>
				<jsp:attribute name="title"><spring:message code="layout.main-navigation.homepage.title" /></jsp:attribute>
				<jsp:body>
					<span class="none">
						<spring:message code="layout.main-navigation.homepage.title" />
					</span>
				</jsp:body>
			</jsp:element>
		</li>
		<li class="menu_header">
				<jsp:element name="a">
					<jsp:attribute name="href"><c:out value="${baseHref}/cars" /></jsp:attribute>
					<jsp:attribute name="class"></jsp:attribute>
					<jsp:attribute name="title"><spring:message code="navigation.cars" /></jsp:attribute>
					<jsp:body>
						<spring:message code="navigation.cars" />
					</jsp:body>
				</jsp:element>
				
				<ul class="menu">
						<li class="menu_item">
							<jsp:element name="a">
								<jsp:attribute name="href"><c:out value="${baseHref}/cars" /></jsp:attribute>
								<jsp:attribute name="title"><spring:message code="navigation.cars.list" /></jsp:attribute>
								<jsp:body>
									<spring:message code="navigation.cars.list" />
								</jsp:body>
							</jsp:element>
						</li>
						<li class="menu_item">
							<jsp:element name="a">
								<jsp:attribute name="href"><c:out value="${baseHref}/cars/new" /></jsp:attribute>
								<jsp:attribute name="title"><spring:message code="navigation.cars.add" /></jsp:attribute>
								<jsp:body>
									<spring:message code="navigation.cars.add" />
								</jsp:body>
							</jsp:element>
						</li>
				</ul>
		</li>
	</ul>

</jsp:root>

