<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="pageTitle" scope="request" value="Menu Detail management"/>
<%@include file="/WEB-INF/views/layout/admin/header.jsp" %>

<div class="content-wrapper">
    <section class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <h1>Menu Detail management</h1>
                </div>
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-right">
                        <li class="breadcrumb-item"><a >Home</a></li>
                        <li class="breadcrumb-item active">Menu Detail management</li>
                    </ol>
                </div>
            </div>
            <div class="row">
                <div class="col-12">
                    <div class="row">
                        <div class="col-2">
<%--                            <a class="btn btn-block btn-warning btn-sm" data-toggle="modal" data-target="#exampleModal1" onclick="addProduct()">Add Product</a>--%>
                        </div>
                    </div>
                </div>
            </div>
        </div><!-- /.container-fluid -->
    </section>
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-4">
                <div class="card">
                    <div class="card-header">
                        <h3 class="card-title">Menu Detail information</h3>
                    </div>
                    <!-- /.card-header -->
                    <div class="card-body">
                        <div class="row">
                            <div class="col-12 col-md-12 col-lg-12 order-2 order-md-1">
                                    <div class="col-12">
                                        <div class="post">
                                            <div class="user-block">
                                                <table class="table">
                                                    <tr>
                                                        <td>ID Menu Detail</td>
                                                        <td>${menu_detail.menu_detail_id}</td>
                                                    </tr>
                                                    <tr>
                                                        <td>Menu name:</td>
                                                        <td>${menu_detail.menu_detail_name}</td>
                                                    </tr>
                                                    <tr>
                                                        <td>Status:</td>
                                                        <td><c:if test="${menu_detail.status ==1}">
                                                                <span class="badge badge-success">Show</span>
                                                            </c:if>
                                                            <c:if test="${menu_detail.status !=1}">
                                                                <span class="badge badge-danger">Hidden</span>
                                                            </c:if>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>Created:</td>
                                                        <td><fmt:formatDate value="${menu_detail.created}" pattern="dd-MM-yyyy" /></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Updated:</td>
                                                        <td><fmt:formatDate value="${menu_detail.updated}" pattern="dd-MM-yyyy" /></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Type Menu:</td>
                                                        <td>${menu_detail.menu.menu_name}</td>
                                                    </tr>
                                                </table>
                                                <a href="${pageContext.request.contextPath}/admin/menuDetail/editMenuDetail?id=${menu_detail.menu_detail_id}" class="btn btn-sm btn-info">Update/Edit</a>
                                                <a href="${pageContext.request.contextPath}/admin/menuDetail" class="btn btn-sm btn-primary">Back</a>
                                            </div>
                                        </div>
                                    </div>
                            </div>
                        </div>
                    </div>

                </div>
                <!-- /.card -->
            </div>
            <div class="col-md-8">
                <div class="card card-info direct-chat direct-chat-primary">
                    <div class="card-header ui-sortable-handle" style="cursor: move;">
                        <h3 class="card-title">Product Menu Detail list</h3>

                        <div class="card-tools">
                            <button type="button" class="btn btn-tool" data-card-widget="collapse">
                                <i class="fas fa-minus"></i>
                            </button>
                            <button type="button" class="btn btn-tool" data-card-widget="remove"><i class="fas fa-times"></i>
                            </button>
                        </div>
                    </div>
                    <!-- /.card-header -->
                    <div class="card-body">
                        <!-- Conversations are loaded here -->
                        <div class="direct-chat-messages" style="height: 400px;">

                            <table class="table table-striped projects">
                                <thead>
                                <tr>
                                    <th style="width: 10px">STT</th>
                                    <th>Product Name</th>
                                    <th>Category Detail</th>
                                    <th>Price</th>
                                    <th>Image</th>
                                    <th>Actions</th>
                                </tr>
                                </thead>
                                <tbody id="product_menu_detail_list">
                                <c:forEach items="${listProMD}" var="proMD" varStatus="itr">
                                    <tr>
                                        <td>${offset+itr.index+1}
                                        </td>
                                        <td>${proMD.product.product_name}</td>
                                        <td>${proMD.product.category_detail.cate_detail_name}</td>
                                        <td><fmt:formatNumber>${proMD.product.price}</fmt:formatNumber> $</td>
                                        <td><img src="${proMD.product.image}" width="50"></td>
                                        <td class="project-actions ">
                                            <a href="${pageContext.request.contextPath}/admin/menuDetail/removeProMD?id=${proMD.product_menu_detail_id}&&menuD_id=${menu_detail.menu_detail_id}" class="btn btn-danger btn-sm"><i class="fas fa-trash"></i> Remove</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="card  direct-chat direct-chat-primary">
                    <div class="card-header ui-sortable-handle" style="cursor: move;">
                        <h3 class="card-title">Product list</h3>

                        <div class="card-tools">
                            <button type="button" class="btn btn-tool" data-card-widget="collapse">
                                <i class="fas fa-minus"></i>
                            </button>
                            <button type="button" class="btn btn-tool" data-card-widget="remove"><i class="fas fa-times"></i>
                            </button>
                        </div>
                    </div>
                    <!-- /.card-header -->
                    <div class="card-body">
                        <!-- Conversations are loaded here -->
                        <div class="direct-chat-messages" style="height: 400px;">

                            <label>Category:</label>
                            <select class="custom-select" id="selectCateDetail">
                                <c:forEach items="${listCateDetail}"  var="cateDetail">
                                    <option value="${cateDetail.cate_detail_id}">${cateDetail.cate_detail_name}</option>
                                </c:forEach>
                            </select>
                            <table class="table table-striped projects">
                                <thead>
                                <tr>
                                    <th style="width: 10px">STT</th>
                                    <th>Product Name</th>
                                    <th>Image</th>
                                    <th>Actions</th>
                                </tr>
                                </thead>
                                <tbody id="allProduct">
<%--                                <c:forEach items="${listProduct}" var="product" varStatus="itr">--%>
<%--                                    <tr>--%>
<%--                                        <td>${offset+itr.index+1}--%>
<%--                                        </td>--%>
<%--                                        <td>${product.product_name}--%>
<%--                                            <br>--%>
<%--                                            <fmt:formatNumber>${product.price}</fmt:formatNumber> $--%>
<%--                                            <br>--%>
<%--                                                ${product.category_detail.cate_detail_name}--%>
<%--                                        </td>--%>
<%--                                        <td><img src="${product.image}" width="50"></td>--%>
<%--                                        <td class="project-actions ">--%>
<%--                                            <a href="${pageContext.request.contextPath}/admin/product/deleteProducts?id=${product.product_id}" class="btn btn-success btn-sm"><i class="fas fa-trash"></i> ADD</a>--%>
<%--                                        </td>--%>
<%--                                    </tr>--%>
<%--                                </c:forEach>--%>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="exampleModal1" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="max-width:700px " role="document">
        <div class="modal-content" style="width: 700px">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Add Product for menu</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
<%--                <label>Category:</label>--%>
<%--                <select class="custom-select" id="selectCateDetail">--%>
<%--                    <c:forEach items="${listCateDetail}"  var="cateDetail">--%>
<%--                        <option value="${cateDetail.cate_detail_id}">${cateDetail.cate_detail_name}</option>--%>
<%--                    </c:forEach>--%>
<%--                </select>--%>
<%--                <table class="table table-striped projects">--%>
<%--                    <thead>--%>
<%--                    <tr>--%>
<%--                        <th style="width: 10px">STT</th>--%>
<%--                        <th>Product Name</th>--%>
<%--                        <th>Image</th>--%>
<%--                        <th>Actions</th>--%>
<%--                    </tr>--%>
<%--                    </thead>--%>
<%--                    <tbody id="allProduct">--%>
<%--                    <c:forEach items="${listProduct}" var="product" varStatus="itr">--%>
<%--                        <tr>--%>
<%--                            <td>${offset+itr.index+1}--%>
<%--                            </td>--%>
<%--                            <td>${product.product_name}--%>
<%--                                <br>--%>
<%--                                <fmt:formatNumber>${product.price}</fmt:formatNumber> đ--%>
<%--                                <br>--%>
<%--                                ${product.category_detail.cate_detail_name}--%>
<%--                            </td>--%>
<%--                            <td><img src="${product.image}" width="50"></td>--%>
<%--                            <td class="project-actions ">--%>
<%--                                <a href="${pageContext.request.contextPath}/admin/product/deleteProducts?id=${product.product_id}" class="btn btn-success btn-sm"><i class="fas fa-trash"></i> ADD</a>--%>
<%--                            </td>--%>
<%--                        </tr>--%>
<%--                    </c:forEach>--%>
<%--                    </tbody>--%>
<%--                </table>--%>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>

        </div>
    </div>
</div>

<%@include file="/WEB-INF/views/layout/admin/footer.jsp" %>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@10"></script>

<%@include file="/WEB-INF/views/layout/admin/infoActionc.jsp" %>
<script>
    $(function () {
        $("select").change(getCateDetail);
        getCateDetail();
    });

    function getCateDetail() {
        var _cateId = $("#selectCateDetail").val();
        var _menuDetailId = ${menu_detail.menu_detail_id};
        $.ajax({
            method:'GET',
            url:'../menuDetail/proByCate?cate_id='+_cateId+'&&proMNId='+_menuDetailId,
            success:function (res) {
                $("#allProduct").html(res);
            }
        })
    };
    function addProductMenuDetail(_product_id,_menuD_id)
    {
        $.ajax({
            method: 'GET',
            url: '/admin/menuDetail/insertProMenuDetailAjax?product_id='+_product_id+'&&menuD_id='+_menuD_id,
            success:function (res)
            {
                $("#product_menu_detail_list").html(res);
            }
        })
    }
</script>
</body>
</html>