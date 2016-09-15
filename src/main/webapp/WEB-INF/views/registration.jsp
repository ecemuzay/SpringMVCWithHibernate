<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>


<jsp:include page="../layout/header.jsp"/>

<body>
	<div class="container">

		<c:choose>
			<c:when test="${user['new']}">
				<h1>Add User</h1>
			</c:when>
			<c:otherwise>
				<h1>Update User</h1>
			</c:otherwise>
		</c:choose>
		<br />

		<spring:url value="/users/usersAction" var="userActionUrl" />

		<form:form class="form-horizontal" method="post" modelAttribute="user" action="${userActionUrl}">
			<form:input type="hidden" path="id" id="id"/>
			<spring:bind path="firstName">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<label class="col-sm-2 control-label">First Name </label>
					<div class="col-sm-10">
						<form:input path="firstName" type="text" class="form-control" id="name" placeholder="First Name" />
						<form:errors path="firstName" class="control-label" />
					</div>
				</div>
			</spring:bind>

			<spring:bind path="lastName">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<label class="col-sm-2 control-label">Last Name </label>
					<div class="col-sm-10">
						<form:input path="lastName" type="text" class="form-control " id="name" placeholder="Last Name" />
						<form:errors path="lastName" class="control-label" />
					</div>
				</div>
			</spring:bind>

			<spring:bind path="ssoId">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<label class="col-sm-2 control-label">SSO ID </label>
					<div class="col-sm-10">
						<c:choose>
							<c:when test="${user['new']}">
								<form:input type="text" path="ssoId" id="ssoId" class="form-control" placeholder="SSO ID"/>
							</c:when>
							<c:otherwise>
								<form:input type="text" path="ssoId" id="ssoId" class="form-control" readonly="true" placeholder="SSO ID"/>
								<form:errors path="ssoId" class="control-label" />
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</spring:bind>

			<spring:bind path="password">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<label class="col-sm-2 control-label">Password </label>
					<div class="col-sm-10">
						<form:input path="password" type="password" class="form-control " id="name" placeholder="Password" />
						<form:errors path="password" class="control-label" />
					</div>
				</div>
			</spring:bind>

			<spring:bind path="email">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<label class="col-sm-2 control-label">Email </label>
					<div class="col-sm-10">
						<form:input path="email" type="password" class="form-control " id="name" placeholder="Email" />
						<form:errors path="email" class="control-label" />
					</div>
				</div>
			</spring:bind>

			<spring:bind path="userProfiles">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<label class="col-sm-2 control-label">Roles </label>
					<div class="col-sm-10">
						<form:select path="userProfiles" items="${roles}" multiple="true" itemValue="id" itemLabel="type" class="form-control input-sm" />
						<form:errors path="userProfiles" class="control-label"/>

					</div>
				</div>
			</spring:bind>

			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<spring:url value="/users/list" var="urlListUser" />
					<c:choose>
						<c:when test="${user['new']}">
							<button type="submit" class="btn-lg btn-primary pull-right"><spring:message code="button.add"/></button>
						</c:when>
						<c:otherwise>
							<button type="submit" class="btn-lg btn-primary pull-right"><spring:message code="button.update"/></button>
						</c:otherwise>
					</c:choose>
					<button type="button" class="btn-lg btn-danger pull-right" onclick="location.href='${urlListUser}'">Cancel</button>
				</div>
			</div>
		</form:form>
	</div>
</body>
</html>