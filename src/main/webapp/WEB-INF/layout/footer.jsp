<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>



<div class="container">
	<hr>
	<footer>
		<p>&copy; 2016</p>
	</footer>
</div>


<spring:url value="/resources/javascripts/bootstrap/bootstrap.min.js" var="bootstrapJs" />
<spring:url value="/resources/javascripts/jquery/jquery-1.11.1.min.js" var="jqueryJs" />
<script src="${jqueryJs}"></script>
<script src="${bootstrapJs}"></script>
