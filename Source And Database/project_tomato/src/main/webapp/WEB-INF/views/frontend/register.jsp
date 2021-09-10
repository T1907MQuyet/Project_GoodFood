<%--
  Created by IntelliJ IDEA.
  User: asuspc
  Date: 4/29/2021
  Time: 10:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: asuspc
  Date: 4/29/2021
  Time: 10:15 PM
  To change this template use File | Settings | File Templates.
--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<c:set var="pageTitle" scope="request" value="Home"/>
<%@include file="/WEB-INF/views/layout/frontend/header.jsp" %>
<%@include file="/WEB-INF/views/layout/frontend/navbar.jsp" %>
<section class="page_header" style="background: url('/assets/frontend/img/bg4-1.png')">
    <div class="container">
        <div class="row">
            <div class="col-md-12 text-center">
                <h2 class="text-uppercase">Register</h2>
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
                                <h3 class="text-center">Register An Account</h3>
                                <br>
                                <f:form class="logregform" method="post" modelAttribute="cusNew" action="">
                                    <div class="row">
                                        <spring:bind path="email">
                                            <div class="form-group">
                                                <div class="col-md-12">
                                                    <label>E-mail Address</label>
                                                    <f:input type="email" path="email" value="" class="form-control ${status.error ?'border border-danger':''}"/>
                                                    <f:errors path="email" class="text-danger"  ></f:errors>
                                                    <p class="text-danger">${param.errorEmail}</p>
                                                </div>
                                            </div>
                                        </spring:bind>
                                    </div>
                                    <div class="clearfix space20"></div>
                                    <div class="row">
                                        <spring:bind path="username">
                                        <div class="form-group">
                                            <div class="col-md-12">
                                                <label>Full Name</label>
                                                <f:input type="text" path="username" value="" class="form-control ${status.error ?'border border-danger':''}"/>
                                                <f:errors path="username" class="text-danger"  ></f:errors>
                                            </div>
                                        </div>
                                        </spring:bind>
                                    </div>
                                    <div class="clearfix space20"></div>
                                    <div class="row">
                                        <div class="form-group">
                                            <spring:bind path="password">
                                            <div class="col-md-6">
                                                <label>Password</label>
                                                <f:input type="password" path="password" value="" class="form-control ${status.error ?'border border-danger':''}"/>
                                                <f:errors path="password" class="text-danger"  ></f:errors>
                                                <p class="text-danger">${param.errorPass}</p>
                                            </div>
                                            </spring:bind>
                                            <div class="col-md-6">
                                                <label>Re-enter Password</label>
                                                <input type="password" name="password_vf" value="" class="form-control"/>
                                                <p class="text-danger">${param.errorPass}</p>
                                            </div>
                                        </div>
                                    </div>


                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="space20"></div>
                                            <button type="submit" class="btn btn-default pull-right">Register</button>
                                        </div>
                                    </div>
                                </f:form>
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

