<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="pageTitle" scope="request" value="Product Manager"/>
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
            <div class="row">
                <div class="col-md-12">
                    <div class="card card-warning collapsed-card">
                        <div class="card-header">
                            <h3 class="card-title">Look Up Products</h3>

                            <div class="card-tools">

                                <button type="button" class="btn btn-tool" data-card-widget="collapse"><i class="fas fa-plus"></i>
                                </button>

                                <button type="button" class="btn btn-tool" data-card-widget="remove"><i class="fas fa-times"></i>
                                </button>
                            </div>
                        </div>
                        <!-- /.card-header -->
                        <div class="card-body" style="display: none; color: #FFF">
                            <div class="col-md-12" style="margin-top: 10px;margin-bottom: 10px;">
                                <div class="row">
                                    <div class="col-12">
                                        <form class="row" method="GET" action="${pageContext.request.contextPath}/admin/product/searchProName" role="form">
                                                <div class="form-group col-3">
                                                    <input type="text" name="key_pro_name" class="form-control" id="exampleInputEmail1" placeholder="Enter product name">
                                                </div>
                                            <div class="col-3">
                                                <button type="submit" class="btn btn-outline-primary">Search</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-3 col-sm-6 col-12">
                                        <a href="${pageContext.request.contextPath}/admin/product" class="btn btn-block btn-primary">All</a>
                                    </div>
                                    <div class="col-md-3 col-sm-6 col-12">
                                        <a href="${pageContext.request.contextPath}/admin/product/show" class="btn btn-block btn-success">Show</a>
                                    </div>
                                    <div class="col-md-3 col-sm-6 col-12">
                                        <a  href="${pageContext.request.contextPath}/admin/product/hidden" class="btn btn-block btn-danger">Hidden</a>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- /.card-footer-->
                    </div>
                </div>
                <div class="col-12">
                    <div class="row">
                        <div class="col-2">
                            <a class="btn btn-block btn-info btn-sm" href="${pageContext.request.contextPath}/admin/product/insertProduct">Insert Product</a>
                        </div>
                    </div>
                </div>
            </div>
        </div><!-- /.container-fluid -->
    </section>
    <div class="container-fluid">
        <div class="row">

            <div class="col-md-12">
                <div class="card">
                    <div class="card-header">
                        <h3 class="card-title">Product List</h3>
                    </div>
                    <!-- /.card-header -->
                    <div class="card-body p-0">
                        <table class="table table-striped projects">
                            <thead>
                            <tr>
                                <th style="width: 10px">STT</th>
                                <th>Product</th>
                                <th>Category Detail</th>
                                <th>Price</th>
                                <th>Image</th>
                                <th>Actions</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${list}" var="pro" varStatus="itr">
                                <tr>
                                    <td>${offset+itr.index+1}
                                    </td>
                                    <td><span class="text-bold">name:</span> ${pro.product_name}<br>
                                        <c:if test="${pro.status ==1}">
                                            <span class="text-bold">status:</span><span class="badge badge-success">Show</span>
                                        </c:if>
                                        <c:if test="${pro.status !=1}">
                                            <span class="text-bold">status:</span><span class="badge badge-danger">Hidden</span>
                                        </c:if>
                                        <br><span class="text-bold">created:</span>
                                        <fmt:formatDate value="${pro.created}" pattern="dd-MM-yyyy" />
                                    </td>
                                    <td>${pro.category_detail.cate_detail_name}</td>
                                    <td><fmt:formatNumber>${pro.price}</fmt:formatNumber> $</td>
                                    <td><img src="${pro.image}" width="100"></td>
                                    <td class="project-actions ">
                                        <a href="${pageContext.request.contextPath}/admin/product/detailPro?id=${pro.product_id}" class="btn btn-primary btn-sm"><i class="fas fa-folder"> </i></a>
                                        <a href="${pageContext.request.contextPath}/admin/product/deleteProducts?id=${pro.product_id}" class="btn btn-danger btn-sm"><i class="fas fa-trash"></i></a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <!-- /.card-body -->
                    <div class="card-footer clearfix">
                        <ul class="pagination pagination-sm m-0 float-right">
                            <c:if test="${totalPages > 1}">
                                <c:if test="${currentPage>1}">
                                    <li class="page-item"><a class="page-link" href="/admin/product/page/${currentPage-1}">«</a></li>
                                </c:if>
                                <c:forEach  end="${totalPages}" begin="1" varStatus="loop">
                                    <c:if test="${currentPage != loop.index}">
                                        <li class="page-item "><a class="page-link" href="/admin/product/page/${loop.index}">${loop.index}</a></li>
                                    </c:if>
                                    <c:if test="${currentPage == loop.index}">
                                        <li class="page-item active"><a class="page-link" href="/admin/product/page/${loop.index}">${loop.index}</a></li>
                                    </c:if>
                                </c:forEach>
                                <c:if test="${currentPage<totalPages}">
                                    <li class="page-item"><a class="page-link" href="/admin/product/page/${currentPage+1}">»</a></li>
                                </c:if>
                            </c:if>
                        </ul>
                    </div>
                </div>
                <!-- /.card -->
            </div>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/views/layout/admin/footer.jsp" %>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<%@include file="/WEB-INF/views/layout/admin/infoActionc.jsp" %>

</body>
</html>



