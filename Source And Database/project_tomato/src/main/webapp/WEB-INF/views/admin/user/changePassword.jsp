<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="pageTitle" scope="request" value="Category Manager"/>
<%@include file="/WEB-INF/views/layout/admin/header.jsp" %>

<div class="content-wrapper">
    <section class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <h1>Account Admin Manager</h1>
                </div>
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-right">
                        <li class="breadcrumb-item"><a >Home</a></li>
                        <li class="breadcrumb-item active">Account Admin Manager</li>
                    </ol>
                </div>
            </div>
        </div><!-- /.container-fluid -->
    </section>
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-5">
                <!-- general form elements -->
                <div class="card card-info">
                    <div class="card-header">
                        <h3 class="card-title">Change Password!</h3>
                        <div class="card-tools">
                            <button type="button" class="btn btn-tool" data-card-widget="collapse" data-toggle="tooltip" title="Collapse">
                                <i class="fas fa-minus"></i></button>
                        </div>
                    </div>
                    <div class="card-body" style="display: block;">
                        <!-- /.card-header -->
                        <!-- form start -->
                        <%--@elvariable id="user" type=""--%>
                        <f:form action="${pageContext.request.contextPath}/admin/user/updatePassword" method="POST" modelAttribute="user">
                                <f:input path="id" type="hidden"/>
                                <div class="form-group">
                                    <label >Password old:</label>
                                    <input name="passOld" type="password" class="form-control" placeholder="Enter password old"/>
                                    <p class="text-danger">${param.passOld}</p>
                                </div>
                                <div class="form-group">
                                    <label >Password New:</label>
                                    <input name="passNew" type="password" class="form-control" placeholder="Enter password New"/>
                                    <p class="text-danger">${param.passNew}</p>
                                </div>
                                <div class="form-group">
                                    <label >Password old:</label>
                                    <input name="passNewCf" type="password" class="form-control" placeholder="Enter password Confirmation"/>
                                    <p class="text-danger">${param.passNew}</p>
                                </div>



                            <div class="card-footer">
                                <button type="submit" class="btn btn-info">Save</button>
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