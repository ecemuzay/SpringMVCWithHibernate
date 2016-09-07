<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<head>
<title>Spring Template Project With Hibernate</title>


	<spring:url value="/resources/css/bootstrap/bootstrap.min.css" var="bootstrapCss" />
	<spring:url value="/resources/css/bootstrap/bootstrap-theme.min.css" var="bootstrapThemeCss" />

	<link href="${bootstrapCss}" rel="stylesheet" />
	<link href="${bootstrapThemeCss}" rel="stylesheet" />

</head>

<spring:url value="/" var="urlHome" />


<nav class="navbar navbar-inverse ">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="${urlHome}">Umut Büyükdurmuş</a>
		</div>
		<div id="navbar">
			<ul class="nav navbar-nav navbar-right">

			</ul>
		</div>
	</div>
</nav>

