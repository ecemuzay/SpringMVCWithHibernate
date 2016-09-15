<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>



<!DOCTYPE html>
<html>


<jsp:include page="../layout/header.jsp"/>

<body>

<div class="container">

    <c:if test="${not empty msg}">
        <div class="alert alert-${css} alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            <strong>${msg}</strong>
        </div>
    </c:if>

    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">All Orders </span>
            <sec:authorize access="hasRole('ADMIN')">
            <spring:url value="/orders/add" var="urlAddOrder" />
            <button class="btn btn-danger pull-right" onclick="location.href='${urlAddOrder}'">Add Order</button>
            </sec:authorize>
        </div>

        <table class="table table-striped table-hover">
            <thead>
                <tr>
                    <th>#ID</th>
                    <th>Name</th>
                    <th>Order Number</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="order" items="${orders}">
                    <tr>
                        <td>${order.id}</td>
                        <td>${order.name}</td>
                        <td>${order.orderNumber}</td>

                        <td>
                            <spring:url value="/orders/${order.id}/delete" var="deleteUrl" />
                            <spring:url value="/orders/${order.id}/update" var="updateUrl" />
                            <spring:url value="/orders/${order.id}/detail" var="detailUrl" />

                            <button class="btn btn-info" onclick="location.href='${detailUrl}'">Detail</button>
                            <sec:authorize access="hasRole('ADMIN') or hasRole('USER')">
                                <button class="btn btn-primary" onclick="location.href='${updateUrl}'">Update</button>
                            </sec:authorize>
                            <sec:authorize access="hasRole('ADMIN')">
                                <button class="btn btn-danger" onclick="this.disabled=true;location.href='${deleteUrl}'">Delete</button>
                            </sec:authorize>
                        </td>

                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<jsp:include page="../layout/footer.jsp" />

</body>
</html>