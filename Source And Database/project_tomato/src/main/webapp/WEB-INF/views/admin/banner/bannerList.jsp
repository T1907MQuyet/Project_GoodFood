<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="pageTitle" scope="request" value="Banner management"/>
<%@include file="/WEB-INF/views/layout/admin/header.jsp" %>

<div class="content-wrapper">
    <section class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <h1>Banner management</h1>
                </div>
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-right">
                        <li class="breadcrumb-item"><a >Home</a></li>
                        <li class="breadcrumb-item active">Banner management</li>
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
                        <h3 class="card-title">Banner List</h3>
                    </div>
                    <!-- /.card-header -->
                    <div class="card-body ">
                        <table class="table table-striped projects">
                            <thead>
                            <tr>
                                <th style="width: 10px">STT</th>
                                <th>Banner name</th>
                                <th>Image</th>
                                <th>Actions</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${list}" var="banner" varStatus="itr">
                                <tr>
                                    <td>${offset+itr.index+1}</td>
                                    <td>${banner.banner_name }
                                        <br>
                                        <c:if test="${banner.status ==1}">
                                            <span class="badge badge-success">Show</span>
                                        </c:if>
                                        <c:if test="${banner.status ==2}">
                                            <span class="badge badge-danger">Hidden</span>
                                        </c:if>
                                    </td>
                                    <td><img src="${banner.image_links}" width="100"></td>
                                    <td>
                                        <c:if test="${banner.status ==1}">
                                            <a href="${pageContext.request.contextPath}/admin/banner/editBanner?id=${banner.banner_id}&&status=2" class="btn  btn-info btn-sm"><i class="fas fa-pencil-alt"> </i></a>
                                        </c:if>
                                        <c:if test="${banner.status !=1}">
                                            <a href="${pageContext.request.contextPath}/admin/banner/editBanner?id=${banner.banner_id}&&status=1" class="btn  btn-info btn-sm"><i class="fas fa-pencil-alt"> </i></a>
                                        </c:if>
                                        <a href="${pageContext.request.contextPath}/admin/banner/deleteBanner?id=${banner.banner_id}" class="btn  btn-danger btn-sm"><i class="fas fa-trash"> </i> </a>
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
                                    <li class="page-item"><a class="page-link" href="/admin/banner/page/${currentPage-1}">«</a></li>
                                </c:if>
                                <c:forEach  end="${totalPages}" begin="1" varStatus="loop">
                                    <c:if test="${currentPage != loop.index}">
                                        <li class="page-item "><a class="page-link" href="/admin/banner/page/${loop.index}">${loop.index}</a></li>
                                    </c:if>
                                    <c:if test="${currentPage == loop.index}">
                                        <li class="page-item active"><a class="page-link" href="/admin/banner/page/${loop.index}">${loop.index}</a></li>
                                    </c:if>
                                </c:forEach>
                                <c:if test="${currentPage<totalPages}">
                                    <li class="page-item"><a class="page-link" href="/admin/banner/page/${currentPage+1}">»</a></li>
                                </c:if>
                            </c:if>
                        </ul>
                    </div>
                </div>
                <!-- /.card -->
            </div>
            <div class="col-md-5">
                <!-- general form elements -->
                <div class="card card-info">
                    <div class="card-header">
                        <h3 class="card-title">Add Banner New!</h3>
                        <div class="card-tools">
                            <button type="button" class="btn btn-tool" data-card-widget="collapse" data-toggle="tooltip" title="Collapse">
                                <i class="fas fa-minus"></i></button>
                        </div>
                    </div>
                    <div class="card-body" style="display: block;">
                        <!-- /.card-header -->
                        <!-- form start -->
                        <%--@elvariable id="bannerNew" type=""--%>
                        <f:form action="${pageContext.request.contextPath}/admin/banner/saveBanner" method="POST" enctype="multipart/form-data" modelAttribute="bannerNew">
                            <spring:bind path="banner_name">
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Banner name:</label>
                                    <f:input path="banner_name" type="text"  class="form-control  ${status.error ?'border border-danger':''} " id="exampleInputEmail1"  placeholder="Enter banner name..."/>
                                    <f:errors path="banner_name" class="text-danger"  ></f:errors>
                                </div>
                            </spring:bind>
                            <div class="form-group">
                                <label for="exampleInputEmail1">Type_Banner</label>
                                <f:select class="custom-select" path="type_banner.type_banner_id">
                                    <f:options items="${typeBannerList}" itemLabel="type_name" itemValue="type_banner_id"/>
                                </f:select>
                            </div>
                            <div class="form-group">
                                <label for="exampleInputEmail1">Link Image:</label>
                                <input  type="file" name="image" required="required" class="form-control " id="exampleInputEmail1" />
                            </div>
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
                                    <f:errors path="status" class="text-danger"  ></f:errors>
                                </div>
                            </spring:bind>
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

</body>
</html>