<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:c="http://java.sun.com/jsp/jstl/core" version="2.1"
	xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:tiles="http://tiles.apache.org/tags-tiles"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:spring="http://www.springframework.org/tags">

	<form:form method="post" id="addCarForm" modelAttribute="carNewForm">

		<div class="action-edit_table-wrapper">
			<table class="form detail-table">
				<colgroup>
					<col class="label-column" />
					<col class="value-column" />
				</colgroup>
				<tbody>
					<tr>
						<th class="left"><label for="carType"><spring:message
									code="car.detail.car-type" /></label></th>
						<td><form:select path="type" id="carType">
								<c:forEach items="${carTypes}" var="item">
									<form:option value="${item.key}" label="${item.value}"
										title="${item.value}"></form:option>
								</c:forEach>
							</form:select></td>
					</tr>
					<tr>
						<td class="required"><label for="vin"><spring:message
									code="car.detail.vin" /></label></td>
						<td><form:input path="vin" id="vin" maxlength="17" minlength="17"
								cssClass="validate[required]"
								cssErrorClass=" fielderror " />
						</td>
					</tr>
					<tr>
						<td class="required"><label for="price"><spring:message
									code="car.detail.price" /></label></td>
						<td><form:input path="price" id="price" maxlength="14"
								cssClass="right date validate[required,custom[number],max[99999999999.99],min[0]]"
								cssErrorClass="  fielderror right date validate[required,custom[number],max[99999999999.99],min[0]]" />
						</td>
					</tr>
					<td class="required">
	                		<label for="datum">Ukazka DatePicker</label>
	                	</td>
		                <td>
		                      <form:input path="testDatum" id="testPicker" cssClass="date datepicker validate[required,custom[date]]" cssErrorClass="date datepicker validate[required,custom[date]] fielderror"/>
		                </td>
				</tbody>
			</table>
		</div>


		<div class="buttons-wrapper spaced">
			<button type="submit" class="hero">
				<spring:message code="layout.form.button.continue" />
			</button>
			<spring:url var="cancelUri" value="/cars" />
			<a href="${cancelUri}" class="button"><spring:message
					code="layout.form.button.cancel" /></a>
		</div>

	</form:form>
</jsp:root>
