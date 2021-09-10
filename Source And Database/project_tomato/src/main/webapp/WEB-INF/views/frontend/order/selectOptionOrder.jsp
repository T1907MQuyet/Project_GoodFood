<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<select onchange="location = this.value;" style="margin-right: 20px">
    <option value="${pageContext.request.contextPath}/food/account?customer_id=${customer.customer_id}">All</option>
    <option value="${pageContext.request.contextPath}/food/account/orderStatus?customer_id=${customer.customer_id}&&status=1" <c:if test="${status==1}">selected</c:if> >Waiting</option>
    <option value="${pageContext.request.contextPath}/food/account/orderStatus?customer_id=${customer.customer_id}&&status=2" <c:if test="${status==2}">selected</c:if>>Confirmed</option>
    <option value="${pageContext.request.contextPath}/food/account/orderStatus?customer_id=${customer.customer_id}&&status=3" <c:if test="${status==3}">selected</c:if>>Shipping</option>
    <option value="${pageContext.request.contextPath}/food/account/orderStatus?customer_id=${customer.customer_id}&&status=4" <c:if test="${status==4}">selected</c:if>>Complete</option>
    <option value="${pageContext.request.contextPath}/food/account/orderStatus?customer_id=${customer.customer_id}&&status=5" <c:if test="${status==5}">selected</c:if>>Cancelled</option>
</select>

<select onchange="location = this.value;">
    <option value="${pageContext.request.contextPath}/food/account?customer_id=${customer.customer_id}">All Service</option>
    <c:forEach items="${servicesList}" var="service">
    <option value="${pageContext.request.contextPath}/food/service/orderByService?service_id=${service.service_id}&&customer_id=${customer.customer_id}" <c:if test="${service_id==service.service_id}">selected</c:if> >${service.service_name}</option>
    </c:forEach>
</select>