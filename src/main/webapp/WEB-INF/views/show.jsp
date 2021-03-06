<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../layout/header.jsp" />

<div class="container">

	<c:if test="${not empty msg}">
		<div class="alert alert-${css} alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			<strong>${msg}</strong>
		</div>
	</c:if>

	<h1><spring:message code="label.orderDetail"/></h1>
	<br />

	<div class="row">
		<label class="col-sm-2"><spring:message code="label.id"/></label>
		<div class="col-sm-10">${order.id}</div>
	</div>

	<div class="row">
		<label class="col-sm-2"><spring:message code="label.name"/></label>
		<div class="col-sm-10">${order.name}</div>
	</div>

	<div class="row">
		<label class="col-sm-2"><spring:message code="label.orderNumber"/></label>
		<div class="col-sm-10">${order.orderNumber}</div>
	</div>


	<div class="row">

		<spring:url value="/orderDetail/${order.id}/add" var="urlAddOrderDetail" />
		<button class="btn btn-info" onclick="location.href='${urlAddOrderDetail}'">Add Order Detail</button>

		<table class="table table-striped">
			<thead>
			<tr>
				<th><spring:message code="label.id"/></th>
				<th><spring:message code="label.name"/> </th>
				<th><spring:message code="label.action"/></th>
			</tr>
			</thead>

			<c:forEach var="orderDetail" items="${orderDetails}">
				<tr>
					<td>
							${orderDetail.id}
					</td>
					<td>${orderDetail.name}</td>

					<td>
						<spring:url value="/orderDetail/${order.id}/${orderDetail.id}/delete" var="deleteUrl" />
						<spring:url value="/orderDetail/${orderDetail.id}/update" var="updateUrl" />


						<button class="btn btn-primary" onclick="location.href='${updateUrl}'">Update</button>
						<button class="btn btn-danger" onclick="this.disabled=true;location.href='${deleteUrl}'">Delete</button></td>


				</tr>
			</c:forEach>
		</table>

	</div>

</div>

<jsp:include page="../layout/footer.jsp" />

</body>
</html>