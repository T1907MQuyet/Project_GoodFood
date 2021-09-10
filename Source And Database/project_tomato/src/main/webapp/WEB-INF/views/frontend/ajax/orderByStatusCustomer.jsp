
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="row">
    <div class="col-md-12 " style="margin-bottom: 10px">
        <a href="${pageContext.request.contextPath}/food/service/accountService?customer_id=${customer.customer_id}" class="btn btn-primary">Service List</a>
    </div>
    <div class="col-md-12">
        <div class="shop-grid">
            <select id="searchStatusOrder">
                <option value="0">All</option>
                <option value="1">Waiting</option>
                <option value="2">Confirmed</option>
                <option value="3">Shipping</option>
                <option value="4">Complete</option>
                <option value="5">Cancelled</option>
            </select>
            <span>Showing 1-10 of ${listOrder.size()} Results</span>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-md-12">
        <h3>Recent Orders</h3>
        <br>
        <table class="cart-table account-table table table-bordered">
            <thead>
            <tr>
                <th>ID Order</th>
                <th>date created</th>
                <th>Status</th>
                <th>Total</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="order" items="${listOrder}">
                <tr>
                    <td>
                            ${order.orderId}
                    </td>
                    <td>
                        <fmt:formatDate value="${order.created}" pattern="dd-MM-yyyy" /><br>
                        <p  class="badge badge-success">${order.customerServiceOrders.customerService.services.service_name}</p>
                    </td>
                    <td>
                        <c:if test="${order.status ==1}">
                            <p  class="badge badge-primary">Waiting</p>
                        </c:if>
                        <c:if test="${order.status ==2}">
                            <p  class="badge badge-info">Confirmed</p>
                        </c:if>
                        <c:if test="${order.status ==3}">
                            <p  class="badge badge-success">Shipping</p>
                        </c:if>
                        <c:if test="${order.status ==4}">
                            <p  class="badge badge-warning">Complete</p>
                        </c:if>
                        <c:if test="${order.status ==5}">
                            <p  class="badge badge-danger">Cancelled</p>
                        </c:if>
                    </td>
                    <td>
                        $${order.total_price}
                    </td>
                    <td>
                        <a data-toggle="modal" data-target="#exampleModal1" onclick="getOrderDetail(${order.orderId})" href="javascript:void(0);">View</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <br>
        <div class="card-footer clearfix">
            <ul class="pagination pagination-sm m-0 float-right">
                <c:if test="${totalPages > 1}">
                    <c:if test="${currentPage>1}">
                        <li class="page-item"><a class="page-link" href="/food/account/page/${currentPage-1}?customer_id=${customer_id}&&status=${status}">«</a></li>
                    </c:if>
                    <c:forEach  end="${totalPages}" begin="1" varStatus="loop">
                        <c:if test="${currentPage != loop.index}">
                            <li class="page-item "><a class="page-link" href="/food/account/page/${loop.index}?customer_id=${customer_id}&&status=${status}">${loop.index}</a></li>
                        </c:if>
                        <c:if test="${currentPage == loop.index}">
                            <li class="page-item active"><a class="page-link" href="/food/account/page/${loop.index}?customer_id=${customer_id}&&status=${status}">${loop.index}</a></li>
                        </c:if>
                    </c:forEach>
                    <c:if test="${currentPage<totalPages}">
                        <li class="page-item"><a class="page-link" href="/food/account/page/${currentPage+1}?customer_id=${customer_id}&&status=${status}">»</a></li>
                    </c:if>
                </c:if>
            </ul>
        </div>
    </div>
</div>