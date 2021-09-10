<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Registration Page</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/plugins/fontawesome-free/css/all.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
    <!-- icheck bootstrap -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/dist/css/adminlte.min.css">
    <!-- Google Font: Source Sans Pro -->
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">
</head>
<body class="hold-transition register-page">
<div class="register-box">
    <div class="register-logo">
        <a href=""><b>Admin</b></a>
    </div>
    <div class="card">
        <div class="card-body register-card-body">
            <p class="login-box-msg">Register a new Admin</p>
            <%--@elvariable id="userNew" type=""--%>
            <f:form action="${pageContext.request.contextPath}/register/registerAdmin" method="post" modelAttribute="userNew">
                <spring:bind path="username">
                    <div class="input-group mt-3">
                        <f:input type="text" class="form-control ${status.error ?'border border-danger':''}" path="username" placeholder="Full name"/>
                        <div class="input-group-append">
                            <div class="input-group-text">
                                <span class="fas fa-user"></span>
                            </div>
                        </div>
                    </div>
                    <f:errors path="username" class="text-danger"  ></f:errors>
                </spring:bind>
                <spring:bind path="email">
                    <div class="input-group mt-3">
                        <f:input type="email" path="email" class="form-control ${status.error ?'border border-danger':''} ${param.error.equals('Email already exists')?'border border-danger':''}" placeholder="Email"/>
                        <div class="input-group-append">
                            <div class="input-group-text">
                                <span class="fas fa-envelope"></span>
                            </div>
                        </div>

                    </div>
                    <c:if test="${param.error.equals('Email already exists')}">
                        <p class="text-danger">${param.error}</p>
                    </c:if>
                    <f:errors path="email" class="text-danger"  ></f:errors>
                </spring:bind>
                <spring:bind path="password">
                    <div class="input-group mt-3">
                        <f:input type="password" path="password" class="form-control ${status.error ?'border border-danger':''}" placeholder="Password"/>
                        <div class="input-group-append">
                            <div class="input-group-text">
                                <span class="fas fa-lock"></span>
                            </div>
                        </div>
                    </div>
                    <f:errors path="password" class="text-danger"  ></f:errors>
                </spring:bind>
                <div class="row">
                    <div class="col-4 mt-3">
                        <button type="submit" class="btn btn-primary btn-block">Register</button>
                    </div>
                    <!-- /.col -->
                </div>
            </f:form>
            <a href="${pageContext.request.contextPath}/login" class="text-center">I already have a membership</a>
        </div>
        <!-- /.form-box -->
    </div><!-- /.card -->
</div>
<script src="${pageContext.request.contextPath}/assets/plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="${pageContext.request.contextPath}/assets/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="${pageContext.request.contextPath}/assets/dist/js/adminlte.min.js"></script>
<%@include file="/WEB-INF/views/layout/admin/infoActionc.jsp" %>
</body>
</html>