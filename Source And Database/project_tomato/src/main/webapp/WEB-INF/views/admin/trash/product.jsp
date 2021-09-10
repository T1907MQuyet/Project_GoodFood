<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="modal-header">
    <h5 class="modal-title" id="exampleModalLabel">Product Trash</h5>
    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
        <span aria-hidden="true">&times;</span>
    </button>
</div>
<div class="modal-body">

    <table class="table table-striped projects">
        <thead>
        <tr>
            <th style="width: 10px">STT</th>
            <th>Product name</th>
            <th>Updated</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${list}" var="pro" varStatus="itr">
            <tr>
                <td>${offset+itr.index+1}</td>
                <td>${pro.product_name }</td>
                <td>${pro.updated}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/admin/trash/restoreProduct?id=${pro.product_id}" class="btn  btn-info btn-sm"><i class="fas fa-pencil-alt"> </i> Restore</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div class="modal-footer">
    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
</div>

