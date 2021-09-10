<c:if test="${list.size()>0}">
    <div class="card-body p-0">
        <table class="table table-striped projects">
            <thead>
            <tr>
                <th style="width: 10px">#</th>
                <th>Order</th>
                <th>Received date</th>
                <th>Status</th>
                <th>Address</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="order" varStatus="itr">
                <tr>
                    <td>${offset+itr.index+1}</td>
                    <td><span class="text-bold">ID: </span> ${order.orderId}<br>
                        <span class="text-bold">Email: </span> ${order.order_email}<br>
                        <span class="text-bold">FullName: </span> ${order.fullname}<br>
                        <span class="text-bold">Created: </span><fmt:formatDate value="${order.created}" type="both" /> <br>
                        <span class="text-bold">Total Price: </span>  <fmt:formatNumber>${order.total_price}</fmt:formatNumber>$<br>
                        <c:if test="${order.customerServiceOrders.customerService.services.service_name != null}">
                            <span class="text-bold">Service: </span> <span class="badge badge-success infoTextSize">${order.customerServiceOrders.customerService.services.service_name}</span> <br>
                        </c:if>
                        <span class="text-bold">Payment: </span><p  class="badge badge-info infoTextSize">${order.payment}</p>
                    </td>
                    <td><fmt:formatDate value="${order.received_date}" type="both" pattern="dd-MM-yyyy" /></td>
                    <td><c:if test="${order.status ==1}">
                        <p  class="btn btn-primary btn-sm text-bold">Waiting</p>
                    </c:if>
                        <c:if test="${order.status ==2}">
                            <p  class="btn btn-info btn-sm text-bold"  >Confirmed</p>
                        </c:if>
                        <c:if test="${order.status ==3}">
                            <p  class="btn btn-warning btn-sm text-bold">Shipping</p>
                        </c:if>
                        <c:if test="${order.status ==4}">
                            <p  class="btn btn-success btn-sm text-bold">Complete</p>
                        </c:if>
                        <c:if test="${order.status ==5}">
                            <p  class="btn btn-danger btn-sm text-bold">Cancelled</p>
                        </c:if>
                    </td>
                    <td>${order.order_address}</td>
                    <td class="project-actions ">
                        <a href="${pageContext.request.contextPath}/admin/order/detailOrder?id=${order.orderId}" class="btn btn-primary btn-sm"><i class="fas fa-folder"> </i> Detail</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>
<c:if test="${list.size()==0}">
    <div class="card-body p-0">
        <div class="col-12">
            <p class="text-center">No orders yet</p>
        </div>
    </div>
</c:if>