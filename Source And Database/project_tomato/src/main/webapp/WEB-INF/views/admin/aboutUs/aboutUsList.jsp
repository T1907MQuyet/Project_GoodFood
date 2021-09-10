<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="pageTitle" scope="request" value="About Us Manager"/>
<%@include file="/WEB-INF/views/layout/admin/header.jsp" %>

<div class="content-wrapper">
    <section class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <h1>AboutUs Manager</h1>
                </div>
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-right">
                        <li class="breadcrumb-item"><a >Home</a></li>
                        <li class="breadcrumb-item active">AboutUs Manager</li>
                    </ol>
                </div>
            </div>
        </div><!-- /.container-fluid -->
    </section>
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-5">
                <div class="card">
                    <div class="card-header">
                        <h3 class="card-title">AboutUs List</h3>
                    </div>
                    <!-- /.card-header -->
                    <div class="card-body">
                        <table class="table table-striped projects">
                            <thead>
                            <tr>
                                <th style="width: 10px">STT</th>
                                <th>AboutUs name</th>
                                <th>Created</th>
                                <th>Actions</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${list}" var="about" varStatus="itr">
                                <tr>
                                    <td>${offset+itr.index+1}</td>
                                    <td>${about.title }
                                        <br>
                                        <c:if test="${about.status ==1}">
                                            <span class="badge badge-success">Show</span>
                                        </c:if>
                                        <c:if test="${about.status ==2}">
                                            <span class="badge badge-danger">Hidden</span>
                                        </c:if>
                                    </td>
                                    <td><img src="${about.image}" width="60"></td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/admin/category/editCate?id=${about.news_id}" class="btn  btn-info btn-sm"><i class="fas fa-pencil-alt"> </i> Edit</a>
                                        <a href="${pageContext.request.contextPath}/admin/category/deleteCate?id=${about.news_id}" class="btn  btn-danger btn-sm"><i class="fas fa-trash"> </i> Delete</a>
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
                                    <li class="page-item"><a class="page-link" href="/admin/aboutUs/page/${currentPage-1}">«</a></li>
                                </c:if>
                                <c:forEach  end="${totalPages}" begin="1" varStatus="loop">
                                    <c:if test="${currentPage != loop.index}">
                                        <li class="page-item "><a class="page-link" href="/admin/aboutUs/page/${loop.index}">${loop.index}</a></li>
                                    </c:if>
                                    <c:if test="${currentPage == loop.index}">
                                        <li class="page-item active"><a class="page-link" href="/admin/aboutUs/page/${loop.index}">${loop.index}</a></li>
                                    </c:if>
                                </c:forEach>
                                <c:if test="${currentPage<totalPages}">
                                    <li class="page-item"><a class="page-link" href="/admin/aboutUs/page/${currentPage+1}">»</a></li>
                                </c:if>
                            </c:if>
                        </ul>
                    </div>
                </div>
                <!-- /.card -->
            </div>
            <div class="col-md-7">
                <!-- general form elements -->
                <div class="card card-info">
                    <div class="card-header">
                        <h3 class="card-title">Add About Us New!</h3>
                        <div class="card-tools">
                            <button type="button" class="btn btn-tool" data-card-widget="collapse" data-toggle="tooltip" title="Collapse">
                                <i class="fas fa-minus"></i></button>
                        </div>
                    </div>
                    <div class="card-body" style="display: block;">
                        <!-- /.card-header -->
                        <!-- form start -->
                        <%--@elvariable id="aboutUsNew" type=""--%>
                        <f:form action="${pageContext.request.contextPath}/admin/aboutUs/aboutUs" enctype="multipart/form-data" method="POST" modelAttribute="aboutUsNew">
                            <spring:bind path="title">
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Title:</label>
                                    <f:input path="title" type="text"  class="form-control  ${status.error ?'border border-danger':''}" id="exampleInputEmail1"  placeholder="Enter category name..."/>
                                    <f:errors path="title" class="text-danger"  ></f:errors>
                                </div>
                            </spring:bind>
                            <spring:bind path="description">
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Title:</label>
                                    <f:textarea path="description" type="text"  class="form-control  ${status.error ?'border border-danger':''}" id="descriptionId"  placeholder="Enter category name..."/>
                                    <f:errors path="description" class="text-danger"  ></f:errors>
                                </div>
                            </spring:bind>
                            <spring:bind path="content">
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Title:</label>
                                    <f:textarea path="content" type="text"  class="form-control  ${status.error ?'border border-danger':''}" id="titleId"  placeholder="Enter category name..."/>
                                    <f:errors path="content" class="text-danger"  ></f:errors>
                                </div>
                            </spring:bind>
                            <spring:bind path="status">
                                <div class="form-group">
                                    <label>Status:</label>
                                    <div class="custom-control custom-radio">
                                        <f:radiobutton class="custom-control-input" path="status" value="1" checked="true"  id="customRadio1" />
                                        <label for="customRadio1" class="custom-control-label">Show</label>
                                    </div>
                                    <div class="custom-control custom-radio">
                                        <f:radiobutton class="custom-control-input" path="status" value="2" id="customRadio2" />
                                        <label for="customRadio2" class="custom-control-label">Hidden</label>
                                    </div>
                                </div>
                            </spring:bind>
                            <div class="col-md-12">
                                <div class="row">
                                    <div class="form-group col-md-12">
                                        <label >File Image:</label>
                                        <input name="file_avatar" id="fileImage" type="file" class="form-control" />
                                    </div>
                                </div>
                            </div>
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


<%@include file="/WEB-INF/views/layout/admin/footer.jsp" %>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@10"></script>

<%@include file="/WEB-INF/views/layout/admin/infoActionc.jsp" %>
<script type="text/javascript">
    $(function () {
        // Summernote
        $('#descriptionId').summernote();
        // Summernote
        $('#titleId').summernote();
        click();
    })
</script>

</body>
</html>