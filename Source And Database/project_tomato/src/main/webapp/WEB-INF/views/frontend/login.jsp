<%--
  Created by IntelliJ IDEA.
  User: asuspc
  Date: 4/29/2021
  Time: 10:15 PM
  To change this template use File | Settings | File Templates.
--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="pageTitle" scope="request" value="Home"/>
<%@include file="/WEB-INF/views/layout/frontend/header.jsp" %>
<%@include file="/WEB-INF/views/layout/frontend/navbar.jsp" %>
<section class="page_header" style="background: url('/assets/frontend/img/bg4-1.png')">
    <div class="container">
        <div class="row">
            <div class="col-md-12 text-center">
                <h2 class="text-uppercase">Login</h2>
            </div>
        </div>
    </div>
</section>
<section class="shop-content">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="row shop-login ">
                    <div class="col-md-3">

                    </div>
                    <c:if test="${loginCustomer==null}">
                    <div class="col-md-6">
                        <div class="box-content">
                            <h3 class="text-center">Existing Customer</h3>
                            <br>
                            <form class="logregform" method="post" action="">
                                <div class="row">
                                    <div class="form-group">
                                        <div class="col-md-12">
                                            <label>E-mail Address</label>
                                            <input type="text" name="email" value="" class="form-control">
                                        </div>
                                    </div>
                                </div>
                                <div class="clearfix"></div>
                                <div class="row">
                                    <div class="form-group">
                                        <div class="col-md-12">
                                            <a class="pull-right" href="index.html">(Lost Password?)</a>
                                            <label>Password</label>
                                            <input type="password" name="password" value="" class="form-control">
                                        </div>
                                    </div>
                                </div>
                                <div class="clearfix"></div>
                                <div class="row">
                                    <div class="col-md-6">

                                    </div>
                                    <div class="col-md-6">
                                        <button type="submit" class="btn btn-default pull-right">Login</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    </c:if>
                    <c:if test="${loginCustomer!=null}">
                        <div class="col-md-6">
                            <div class="box-content">
                                <h3 class="text-center">Logged in successfully !!!</h3>
                                <br>

                            </div>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</section>


<%@include file="/WEB-INF/views/layout/frontend/footer.jsp" %>
<%@include file="/WEB-INF/views/layout/frontend/ajaxScript.jsp" %>
