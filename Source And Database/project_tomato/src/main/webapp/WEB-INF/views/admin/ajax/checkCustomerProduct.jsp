<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<table class="table table-striped projects">
    <thead>
    <tr>
        <th style="width: 10px">STT</th>
        <th>Product</th>
        <th>Image</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${list}" var="customerSProduct" varStatus="itr">
        <tr>
            <td>${offset+itr.index+1}</td>
            <td>
                <span class="text-bold">name:</span> ${customerSProduct.product.product_name}
                <br>
                <span class="text-bold">Price:</span> <fmt:formatNumber>${customerSProduct.product.price}</fmt:formatNumber> $
                <br>
                <span class="text-bold">Category:</span> ${customerSProduct.product.category_detail.cate_detail_name}
                <br>
                <span class="text-bold">Quantity:</span> ${customerSProduct.quantity}
                <br>
                <span class="text-bold">Status:</span>
                <c:if test="${customerSProduct.status==1}"><span class="badge badge-success">show</span></c:if>
                <c:if test="${customerSProduct.status==2}"><span class="badge badge-danger">hidden</span></c:if>
            </td>
            <td><img src="${customerSProduct.product.image}" width="50"></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
