<c:if test="${listOrder.size()>0}">
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
</c:if>
<c:if test="${listOrder.size()<=0}">
    <div class="text-center" style="color: #b1bbc3;margin-top: 30px;">
        <i class="fas fa-file-invoice" style="font-size: 100px;"></i>
        <br>
        <br>
        <p>No orders</p>

    </div>
</c:if>