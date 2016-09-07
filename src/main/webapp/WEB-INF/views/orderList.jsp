<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>



<!DOCTYPE html>
<html lang="en">


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




<h1>All Orders</h1>

    <spring:url value="/orders/add" var="urlAddOrder" />


    <button class="btn btn-info" onclick="location.href='${urlAddOrder}'">Add Order</button>
<table class="table table-striped">
    <thead>
        <tr>
            <th>#ID</th>
            <th>Name</th>
            <th>Order Number</th>

        </tr>
        </thead>

        <c:forEach var="order" items="${orders}">
            <tr>
                <td>
                    ${order.id}
                </td>
                <td>${order.name}</td>
                <td>${order.orderNumber}</td>

                <td>
                    <spring:url value="/orders/${order.id}/delete" var="deleteUrl" />
                    <spring:url value="/orders/${order.id}/update" var="updateUrl" />
                    <spring:url value="/orders/${order.id}/detail" var="detailUrl" />

                    <button class="btn btn-info" onclick="location.href='${detailUrl}'">Detail</button>
                    <button class="btn btn-primary" onclick="location.href='${updateUrl}'">Update</button>
                    <button class="btn btn-danger" onclick="this.disabled=true;location.href='${deleteUrl}'">Delete</button></td>
            </tr>
        </c:forEach>
    </table>

</div>

<jsp:include page="../layout/footer.jsp" />

</body>
</html>