<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="modal-header">
    <h5 class="modal-title" id="exampleModalLabel">Category Trash</h5>
    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
        <span aria-hidden="true">&times;</span>
    </button>
</div>
<div class="modal-body">

    <table class="table table-striped projects">
        <thead>
        <tr>
            <th style="width: 10px">STT</th>
            <th>Service name</th>
            <th>Created</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${list}" var="service" varStatus="itr">
            <tr>
                <td>${offset+itr.index+1}</td>
                <td>${service.service_name }
                </td>
                <td>${service.created}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/admin/trash/restoreServices?id=${service.service_id}" class="btn  btn-info btn-sm"><i class="fas fa-pencil-alt"> </i> Restore</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div class="modal-footer">
    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
</div>

