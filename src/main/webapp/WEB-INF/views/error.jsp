<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<jsp:include page="../layout/header.jsp" />

<body>

	<div class="container">

		<h1>Error Page</h1>

		<p>${exception}</p>

	</div>

	<jsp:include page="../layout/footer.jsp" />

</body>
</html>