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
                <h2 class="text-uppercase">Feedback</h2>
            </div>
        </div>
    </div>
</section>
<section class="main-content contact-content">
    <div class="container">
        <div class="col-md-10 col-md-offset-1">
            <div class="row">
                <div class="col-md-6">
                    <h3 class="text-left no-margin-top">Address</h3>
                    <div class="footer-address contact-info">
                        <p><i class="fa fa-map-marker"></i>No.08 Tôn Thất Thuyết</p>
                        <p><i class="fa fa-phone"></i>Phone: (415) 124-5678</p>
                        <p><i class="fa fa-envelope-o"></i>support@vietnam.com</p>
                    </div>
                    <br>
                    <h3 class="text-left no-margin-top">Working hours</h3>
                    <div class="contact-info text-muted">
                        <p>10:00 am to 11:00 pm on Weekdays</p>
                        <p>11:00 am to 11:30 pm on Weekends</p>
                    </div>
                    <br>
                    <h3 class="text-left no-margin-top">Follow Us</h3>
                    <div class="contact-social">
                        <a href="https://www.facebook.com/"><i class="fa fa-facebook"></i></a>
                        <a href="https://www.twitter.com/"><i class="fa fa-twitter"></i></a>
                        <a href="https://www.dribbble.com/"><i class="fa fa-dribbble"></i></a>
                        <a href="https://www.instagram.com/"><i class="fa fa-instagram"></i></a>
                    </div>
                </div>
                <div class="col-md-6">
                    <%--@elvariable id="feedback" type=""--%>
                    <f:form class="contact-form" id="contactForm" action="saveFeedback" method="post" modelAttribute="feedback">
                        <spring:bind path="fullname">
                            <div class="form-group">
                                <f:input class="form-control ${status.error ?'border border-danger':''}" path="fullname" id="name" placeholder="Full Name" type="text" />
                                <f:errors path="fullname" class="text-danger"></f:errors>
                            </div>
                        </spring:bind>
                        <spring:bind path="email">
                            <div class="form-group">
                                <f:input class="form-control ${status.error ?'border border-danger':''}" type="email" path="email" id="email" placeholder="Email Address" />
                                <f:errors path="email" class="text-danger"></f:errors>
                            </div>
                        </spring:bind>
                        <spring:bind path="phonenumber">
                            <div class="form-group">
                                <f:input class="form-control ${status.error ?'border border-danger':''}" path="phonenumber" placeholder="Phone number" type="text" id="subject" name="subject"/>
                                <f:errors path="phonenumber" class="text-danger"></f:errors>
                            </div>
                        </spring:bind>
                        <spring:bind path="content">
                            <div class="form-group">
                                <f:textarea class="form-control ${status.error ?'border border-danger':''}" path="content" id="message" placeholder="Message" rows="5"></f:textarea>
                                <f:errors path="content" class="text-danger"></f:errors>
                            </div>
                        </spring:bind>
                        <button type="submit" class="btn btn-default btn-lg btn-block" id="js-contact-btn">Send message</button>
                    </f:form>
                    <div id="js-contact-result" data-success-msg="Form submitted successfully." data-error-msg="Oops. Something went wrong."></div>
                </div>
            </div>
        </div>
    </div>
</section>


<%@include file="/WEB-INF/views/layout/frontend/footer.jsp" %>
<%@include file="/WEB-INF/views/layout/frontend/ajaxScript.jsp" %>

