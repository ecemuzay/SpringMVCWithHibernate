<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../layout/header.jsp" />

<div class="container">

	<c:choose>
		<c:when test="${orderDetail['new']}">
			<h1>Add Order Detail</h1>
		</c:when>
		<c:otherwise>
			<h1>Update Order Detail</h1>
		</c:otherwise>
	</c:choose>
	<br />

	<spring:url value="/orderDetailAction" var="orderDetailActionUrl" />

	<form:form class="form-horizontal" method="post" modelAttribute="orderDetail" action="${orderDetailActionUrl}">

		<form:hidden path="id" />
		<form:hidden path="order.id" />

		<spring:bind path="name">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Name</label>
				<div class="col-sm-10">
					<form:input path="name" type="text" class="form-control " id="name" placeholder="Name" />
					<form:errors path="name" class="control-label" />
				</div>
			</div>
		</spring:bind>


		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">

				<c:choose>
					<c:when test="${orderDetail['new']}">
						<button type="submit" class="btn-lg btn-primary pull-right">Add</button>
					</c:when>
					<c:otherwise>
						<button type="submit" class="btn-lg btn-primary pull-right">Update</button>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</form:form>

</div>

<jsp:include page="../layout/footer.jsp" />

</body>
</html>