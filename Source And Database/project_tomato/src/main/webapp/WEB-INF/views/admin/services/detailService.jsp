<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="pageTitle" scope="request" value="Service management"/>
<%@include file="/WEB-INF/views/layout/admin/header.jsp" %>
<div class="content-wrapper">
    <section class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <h1>Service Detail management</h1>
                </div>
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-right">
                        <li class="breadcrumb-item"><a >Home</a></li>
                        <li class="breadcrumb-item active">Service Detail management</li>
                    </ol>
                </div>
            </div>
            <div class="row">
                <div class="col-12">
                    <div class="row">
                        <div class="col-2">
                        <c:if test="${services.status ==1}">
                            <a class="btn btn-block btn-warning btn-sm" data-toggle="modal" data-target="#exampleModal1" >Add Calender</a>
                            </c:if>
                        </div>
                        <div class="col-2">
                        <c:if test="${services.status ==1}">
                            <a href="${pageContext.request.contextPath}/admin/service/updateDateCalender?id=${services.service_id}" class="btn btn-block btn-info btn-sm" >update calender</a>
                        </c:if>
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
                        <h3 class="card-title">Service Detail information</h3>
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
                                                    <td>ID Service</td>
                                                    <td>${services.service_id}</td>
                                                </tr>
                                                <tr>
                                                    <td>Service name:</td>
                                                    <td>${services.service_name}</td>
                                                </tr>
                                                <tr>
                                                    <td>Status:</td>
                                                    <td>
                                                        <c:if test="${services.status ==1}">
                                                            <span class="badge badge-success">Show</span>
                                                        </c:if>
                                                        <c:if test="${services.status !=1}">
                                                            <span class="badge badge-danger">Hidden</span>
                                                        </c:if>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>Created:</td>
                                                    <td><fmt:formatDate value="${services.created}" pattern="dd-MM-yyyy" /></td>
                                                </tr>
                                                <tr>
                                                    <td>Updated:</td>
                                                    <td><fmt:formatDate value="${services.updated}" pattern="dd-MM-yyyy" /></td>
                                                </tr>
                                            </table>
                                            <a href="${pageContext.request.contextPath}/admin/service/editService?id=${services.service_id}" class="btn btn-sm btn-info">Update/Edit</a>
                                            <a href="${pageContext.request.contextPath}/admin/service" class="btn btn-sm btn-primary"><i class="fas fa-arrow-left"></i> Back</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="card">
                    <div class="card-header">
                        <h3 class="card-title">Service Detail News</h3>
                    </div>
                    <!-- /.card-header -->
                    <div class="card-body">
                        <div class="row">
                            <div class="col-12 col-md-12 col-lg-12 order-2 order-md-1">
                                <div class="col-12">
                                    <div class="post">
                                        <div class="user-block">
                                            <table class="table">
                                                <tbody>
                                                    <c:forEach items="${service_newsList}" var="newnew" varStatus="itr">
                                                        <tr>
                                                            <td>${offset+itr.index+1}</td>
                                                            <td>
                                                                <a href="${pageContext.request.contextPath}/admin/news/editNews?id=${newnew.news.news_id}">${newnew.news.title_name}</a>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
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
                <!-- general form elements -->
                <div class="card card-info">
                    <div class="card-header">
                        <h3 class="card-title">Calender list</h3>
                        <div class="card-tools">
                            <button type="button" class="btn btn-tool" data-card-widget="collapse" data-toggle="tooltip" title="Collapse">
                                <i class="fas fa-minus"></i></button>
                        </div>
                    </div>
                    <div class="card-body" style="display: block;">
                        <table class="table table-striped projects">
                            <thead>
                            <tr>
                                <th style="width: 10px">STT</th>
                                <th>Solar Calendar</th>
                                <th>lunar_calendar</th>
                                <th>Status</th>
                                <th>Actions</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${listCalender}" var="calender" varStatus="itr">
                                <tr>
                                    <td>${offset+itr.index+1}</td>
                                    <td><fmt:formatDate value="${calender.solar_calendar}" pattern="dd-MM-yyyy" /></td>
                                    <td><fmt:formatDate value="${calender.lunar_calendar}" pattern="dd-MM-yyyy" /></td>
                                    <c:if test="${calender.status ==1}">
                                        <td><span class="badge badge-success">Show</span></td>
                                    </c:if>
                                    <c:if test="${calender.status ==2}">
                                        <td><span class="badge badge-danger">Hidden</span></td>
                                    </c:if>
                                    <td class="project-actions ">
                                        <c:if test="${calender.status ==2}">
                                        <a href="${pageContext.request.contextPath}/admin/service/showCalender?id=${calender.calender_id}&&service_id=${services.service_id}" class="btn btn-success btn-sm"><i class="far fa-eye"></i></a>
                                        </c:if>
                                        <c:if test="${calender.status ==1}">
                                        <a href="${pageContext.request.contextPath}/admin/service/hiddenCalender?id=${calender.calender_id}&&service_id=${services.service_id}" class="btn btn-primary btn-sm"><i class="far fa-window-close"></i></a>
                                        </c:if>
                                        <a href="${pageContext.request.contextPath}/admin/service/deleteCalender?id=${calender.calender_id}&&service_id=${services.service_id}" class="btn btn-danger btn-sm"><i class="fas fa-trash"></i></a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <!-- /.card -->
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
                <%--@elvariable id="calenderNew" type=""--%>
                <f:form action="${pageContext.request.contextPath}/admin/service/saveCalender" method="POST" modelAttribute="calenderNew">
                    <f:input path="services.service_id" type="hidden" value="${services.service_id}"/>
                    <spring:bind path="solar_calendar">
                        <div class="form-group">
                            <label for="exampleInputEmail1">Solar calendar:</label>
                            <f:input path="solar_calendar" type="date"  class="form-control  ${status.error ?'border border-danger':''} " required="required" id="exampleInputEmail1"  />
                            <f:errors path="solar_calendar" class="text-danger"  ></f:errors>
                        </div>
                    </spring:bind>
                    <spring:bind path="lunar_calendar">
                        <div class="form-group">
                            <label for="exampleInputEmail1">Lunar calendar:</label>
                            <f:input path="lunar_calendar" type="date"  class="form-control  ${status.error ?'border border-danger':''} " required="required" id="exampleInputEmail1"  />
                            <f:errors path="lunar_calendar" class="text-danger"  ></f:errors>
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
                            <f:errors path="status" class="text-danger"  ></f:errors>
                        </div>
                    </spring:bind>
                    <div class="card-footer">
                        <button type="submit" class="btn btn-info">Add New</button>
                    </div>
                </f:form>
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

</script>
</body>
</html>