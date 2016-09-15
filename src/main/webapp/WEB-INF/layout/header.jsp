<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<title>Spring Template Project With Hibernate</title>


	<spring:url value="/resources/css/bootstrap/bootstrap.min.css" var="bootstrapCss" />
	<spring:url value="/resources/css/bootstrap/bootstrap-theme.min.css" var="bootstrapThemeCss" />

	<link href="${bootstrapCss}" rel="stylesheet" />
	<link href="${bootstrapThemeCss}" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css" />
</head>

<spring:url value="/" var="urlHome" />
<spring:url value="/orders/list" var="urlOrders" />
<spring:url value="/users/list" var="urlUsers" />
<spring:url value="/logout" var="urlLogout" />
<spring:url value="/login" var="urlLogin" />

<nav class="navbar navbar-inverse ">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="${urlHome}"><spring:message code="label.umut"/> </a>

			<span class="navbar-brand">Dear <strong>${loggedinuser}</strong></span>

		</div>
		<div id="navbar">
			<ul class="nav navbar-nav navbar-right">

				<li class="active"><a href="${urlOrders}">Orders</a></li>
				<li class="active"><a href="${urlUsers}">Users</a></li>

				<sec:authorize access="isAnonymous()">
					<li class="active"><a href="${urlLogin}">Login</a></li>
				</sec:authorize>

				<sec:authorize access="isAuthenticated()">
				<li class="active"><a href="${urlLogout}">Logout</a></li>
				</sec:authorize>
			</ul>
		</div>
	</div>
</nav>

