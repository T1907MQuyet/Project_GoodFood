<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="pageTitle" scope="request" value="Category Manager"/>
<%@include file="/WEB-INF/views/layout/admin/header.jsp" %>
<div class="content-wrapper">
    <section class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <h1>Product management</h1>
                </div>
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-right">
                        <li class="breadcrumb-item"><a >Home</a></li>
                        <li class="breadcrumb-item active">Product management</li>
                    </ol>
                </div>
            </div>
        </div><!-- /.container-fluid -->
    </section>
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">
                <div class="card">
                    <div class="card-header">
                        <h3 class="card-title">Detail Product</h3>

                        <div class="card-tools">
                            <button type="button" class="btn btn-tool" data-card-widget="collapse" data-toggle="tooltip" title="Collapse">
                                <i class="fas fa-minus"></i></button>
                            <button type="button" class="btn btn-tool" data-card-widget="remove" data-toggle="tooltip" title="Remove">
                                <i class="fas fa-times"></i></button>
                        </div>
                    </div>
                    <div class="card-body" style="display: block;">
                        <div class="row">
                            <div class="col-12 col-md-12 col-lg-8 order-2 order-md-1">

                                <div class="row">
                                    <div class="col-12">
                                        <h4>Product information:</h4>
                                        <div class="post">
                                            <div class="user-block">
                                                <table class="table">
                                                    <tr>
                                                        <td>ID Product</td>
                                                        <td>${proDetail.product_id}</td>
                                                    </tr>
                                                    <tr>
                                                        <td>Product name:</td>
                                                        <td>${proDetail.product_name}</td>
                                                    </tr>
                                                    <tr>
                                                        <td>Price:</td>
                                                        <td><fmt:formatNumber>${proDetail.price}</fmt:formatNumber> $</td>
                                                    </tr>
                                                    <tr>
                                                        <td>Discount:</td>
                                                        <td>${proDetail.discount}</td>
                                                    </tr>
<%--                                                    <tr>--%>
<%--                                                        <td>Link Image:</td>--%>
<%--                                                        <td>${proDetail.image}</td>--%>
<%--                                                    </tr>--%>
                                                    <tr>
                                                        <td>Status:</td>
                                                        <td> <c:if test="${proDetail.status ==1}">
                                                                <span class="badge badge-success">Show</span>
                                                            </c:if>
                                                            <c:if test="${proDetail.status !=1}">
                                                                <span class="badge badge-danger">Hidden</span>
                                                            </c:if></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Created:</td>
                                                        <td><fmt:formatDate value="${proDetail.created}" pattern="dd-MM-yyyy" /></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Updated:</td>
                                                        <td><fmt:formatDate value="${proDetail.updated}" pattern="dd-MM-yyyy" /></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Category Detail:</td>
                                                        <td>${proDetail.category_detail.cate_detail_name}</td>
                                                    </tr>
                                                </table>
                                                <a href="${pageContext.request.contextPath}/admin/product/editPro?id=${proDetail.product_id}" class="btn btn-sm btn-info">Update/Edit</a>
                                                <a class="btn btn-primary btn-sm" href="${pageContext.request.contextPath}/admin/product"><i class="fas fa-arrow-left"></i> Back</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-12 col-md-8 col-lg-4 order-1 order-md-2">
                                <div class="card card-warning collapsed-card" style="transition: all 0.15s ease 0s; height: inherit; width: inherit;">
                                    <div class="card-header">
                                        <h3 class="card-title "><i class="fas fa-paint-brush"></i>Product Image</h3>

                                        <div class="card-tools">
                                            <!--<button type="button" class="btn btn-tool" data-card-widget="card-refresh" data-source="widgets.html" data-source-selector="#card-refresh-content" data-load-on-init="false"><i class="fas fa-sync-alt"></i></button>-->
                                            <button type="button" class="btn btn-tool" data-card-widget="maximize"><i class="fas fa-expand"></i></button>
                                            <button type="button" class="btn btn-tool" data-card-widget="collapse"><i class="fas fa-minus"></i></button>
                                            <button type="button" class="btn btn-tool" data-card-widget="remove"><i class="fas fa-times"></i></button>
                                        </div>
                                    </div>
                                    <div class="card-body" style="display: block;">
                                       Image
                                    </div>
                                    <div class="card-body table-responsive p-0" style="height: 450px; display: block;">
                                        <div class="col-md-12">
                                            <div class="row">
                                                <div class="col-sm-12">
                                                    <a href="${proDetail.image}" data-toggle="lightbox" data-title="sample 1 - white" data-gallery="gallery">
                                                        <img src="${proDetail.image}" class="img-fluid mb-2" alt="white sample">
                                                    </a>
                                                </div>

                                            </div>
                                        </div>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>



</div>

<%@include file="/WEB-INF/views/layout/admin/footer.jsp" %>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@10"></script>

<%@include file="/WEB-INF/views/layout/admin/infoActionc.jsp" %>

</body>
</html>



