<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>

<jsp:include page="../layout/header.jsp" />

<div class="container">

	<c:choose>
		<c:when test="${order['new']}">
			<h1><spring:message code="label.addOrder"/></h1>
		</c:when>
		<c:otherwise>
			<h1><spring:message code="label.updateOrder"/></h1>
		</c:otherwise>
	</c:choose>
	<br />

	<spring:url value="/ordersAction" var="orderActionUrl" />

	<form:form class="form-horizontal" method="post" modelAttribute="order" action="${orderActionUrl}">

		<form:hidden path="id" />

		<spring:bind path="name">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label"><spring:message code="label.name"/> </label>
				<div class="col-sm-10">
					<form:input path="name" type="text" class="form-control " id="name" placeholder="Name" />
					<form:errors path="name" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<spring:bind path="orderNumber">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label"><spring:message code="label.orderNumber"/> </label>
				<div class="col-sm-10">
					<form:input path="orderNumber" class="form-control" id="orderNumber" placeholder="Order Number" />
					<form:errors path="orderNumber" class="control-label" />
				</div>
			</div>
		</spring:bind>


		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<c:choose>
					<c:when test="${order['new']}">
						<button type="submit" class="btn-lg btn-primary pull-right"><spring:message code="button.add"/></button>
					</c:when>
					<c:otherwise>
						<button type="submit" class="btn-lg btn-primary pull-right"><spring:message code="button.update"/></button>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</form:form>

</div>

<jsp:include page="../layout/footer.jsp" />

</body>
</html>