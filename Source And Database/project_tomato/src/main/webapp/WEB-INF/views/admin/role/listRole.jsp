<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<c:set var="pageTitle" scope="request" value="Role management "/>
<%@include file="../../layout/admin/header.jsp" %>
<div class="content-wrapper">
    <section class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <h1>Role management</h1>
                    <c:if test="${param.success != null}">
                        <div class="alert alert-success" role="alert">
                            thanh cong
                        </div>
                    </c:if>
                    <c:if test="${param.error!=null}">
                        <div class="alert alert-danger" role="alert">
                            that bai
                        </div>
                    </c:if>
                </div>
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-right">
                        <li class="breadcrumb-item"><a >Home</a></li>
                        <li class="breadcrumb-item active">Role management</li>
                    </ol>
                </div>
            </div>
        </div><!-- /.container-fluid -->
    </section>
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-7">
                <div class="card">
                    <div class="card-header">
                        <h3 class="card-title">Role List</h3>

                    </div>
                    <!-- /.card-header -->
                    <div class="card-body">
                        <table class="table table-striped projects">
                            <thead>
                            <tr>
                                <th style="width: 10px">No.</th>
                                <th>Role</th>
                                <th>Actions</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${list}" var="role" varStatus="itr">
                                <tr>
                                    <td>${offset+itr.index+1}</td>
                                    <td>${role.name }</td>
                                    <td>
                                        <a href="" class="btn  btn-info btn-sm"><i class="fas fa-pencil-alt"> </i> Edit</a>
                                        <a href="" class="btn  btn-danger btn-sm"><i class="fas fa-trash"> </i> Delete</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <%-- <mytag:PaginatiomnTaglib steps="10" offset="${offset}" count="${count}" uri="/cateController/getAll.htm" next="&raquo;" previous="&laquo;"/> --%>
                    </div>
                    <!-- /.card-body -->
<%--                    <div class="card-footer clearfix">--%>
<%--                        <ul class="pagination pagination-sm m-0 float-right">--%>
<%--                            <c:if test="${totalPages > 1}">--%>
<%--                                <c:if test="${currentPage>1}">--%>
<%--                                    <li class="page-item"><a class="page-link" href="/admin/category/page/${currentPage-1}">«</a></li>--%>
<%--                                </c:if>--%>
<%--                                <c:forEach  end="${totalPages}" begin="1" varStatus="loop">--%>
<%--                                    <c:if test="${currentPage != loop.index}">--%>
<%--                                        <li class="page-item "><a class="page-link" href="/admin/category/page/${loop.index}">${loop.index}</a></li>--%>
<%--                                    </c:if>--%>
<%--                                    <c:if test="${currentPage == loop.index}">--%>
<%--                                        <li class="page-item active"><a class="page-link" href="/admin/category/page/${loop.index}">${loop.index}</a></li>--%>
<%--                                    </c:if>--%>
<%--                                </c:forEach>--%>
<%--                                <c:if test="${currentPage<totalPages}">--%>
<%--                                    <li class="page-item"><a class="page-link" href="/admin/category/page/${currentPage+1}">»</a></li>--%>
<%--                                </c:if>--%>
<%--                            </c:if>--%>
<%--                        </ul>--%>
<%--                    </div>--%>
                </div>
                <!-- /.card -->
            </div>
            <div class="col-md-5">
                <!-- general form elements -->
                <div class="card card-info">
                    <div class="card-header">
                        <h3 class="card-title">Add New Role!</h3>
                        <div class="card-tools">
                            <button type="button" class="btn btn-tool" data-card-widget="collapse" data-toggle="tooltip" title="Collapse">
                                <i class="fas fa-minus"></i></button>
                        </div>
                    </div>
                    <div class="card-body" style="display: block;">
                        <!-- /.card-header -->
                        <!-- form start -->
                        <f:form action="${pageContext.request.contextPath}/admin/role/saveRole" method="POST" modelAttribute="roleNew">
                        <div class="form-group">
                            <label for="exampleInputEmail1">Role Name:</label>
                            <f:input path="name" type="text"  class="form-control" id="exampleInputEmail1" required="true" placeholder="Nhập Tên Danh mục..."/>
                        </div>


                            <!-- /.card-body -->
                            <div class="card-footer">
                                <button type="submit" class="btn btn-info">Add New</button>
                            </div>
                            </f:form>
                    </div>
                    <!-- /.card -->
                </div>
            </div>
        </div>
    </div>
</div>


<jsp:include page="../../layout/admin/footer.jsp"/>