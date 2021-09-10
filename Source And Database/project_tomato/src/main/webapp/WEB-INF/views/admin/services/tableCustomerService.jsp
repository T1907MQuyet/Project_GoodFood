<div class="card-body ">
    <c:if test="${list.size()>0}">
    <table class="table table-striped projects">
        <thead>
        <tr>
            <th style="width: 10px">STT</th>
            <th>Customer</th>
            <th>Service</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach items="${list}" var="customerService" varStatus="itr">
            <tr>
                <td>${offset+itr.index+1}</td>
                <td><span class="text-bold">ID:</span> ${customerService.customer_service_id }
                    <br>
                    <span class="text-bold">Email:</span> ${customerService.customer.email }
                    <br>
                    <c:if test="${customerService.status ==1}">
                        <span class="text-bold">Status:</span> <span class="badge badge-success infoTextSize" >active</span>
                    </c:if>
                    <c:if test="${customerService.status ==2}">
                        <span class="text-bold">Status:</span> <span class="badge badge-danger infoTextSize">none active</span>
                    </c:if>
                    <c:if test="${customerService.status ==0}">
                        <span class="text-bold">Status:</span> <span class="badge badge-primary infoTextSize">waiting</span>
                    </c:if>
                    <br>
                    <span class="text-bold">Registration Date:</span> <fmt:formatDate value="${customerService.created}" pattern="dd-MM-yyyy" />
                    <br>
                    <span class="text-bold">Order:</span> <a href="javascript:void(0);" data-toggle="modal" data-target="#exampleModal4" onclick="getAllCustServiceOrder(${customerService.customer_service_id})" class="badge badge-primary infoTextSize">Check</a>
                </td>
                <td><span class="text-bold">Name:</span> ${customerService.services.service_name }
                    <br>
                    <c:if test="${customerService.received_date !=0}">
                    <span class="text-bold">Received Date:</span> Before ${customerService.received_date} Day
                    </c:if>
                    <c:if test="${customerService.received_date ==0}">
                        <span class="text-bold">Received Date:</span> Right Day
                    </c:if>
                    <br>
                    <a  data-toggle="modal" data-target="#exampleModal1" class="badge badge-primary infoTextSize" href="javascript:void(0);" onclick="getCalender(${customerService.services.service_id})">calendar</a>
                    <a  data-toggle="modal" data-target="#exampleModal2" class="badge badge-primary infoTextSize" href="javascript:void(0);" onclick="getCustomerServiceProduct(${customerService.customer_service_id})">check product</a>
                    <c:if test="${customerService.services.status ==1}">
                        <span class="badge badge-success infoTextSize" >active</span>
                    </c:if>
                    <c:if test="${customerService.services.status ==2}">
                        <span class="badge badge-danger infoTextSize">non active</span>
                    </c:if>
                </td>
                <td>
                    <c:if test="${customerService.status ==1}">
<%--                        <a href="${pageContext.request.contextPath}/admin/service/createOrder?id=${customerService.customer_service_id}"  class="btn  btn-success btn-sm"><i class="fas fa-plus"> </i> Create order</a>--%>
                    </c:if>
                    <c:if test="${customerService.status !=0}">
                        <a href="${pageContext.request.contextPath}/admin/service/detailCustomerService?id=${customerService.customer_service_id}" class="btn  btn-primary btn-sm"><i class="fas fa-folder"> </i> </a>
                    </c:if>
                    <c:if test="${customerService.status ==0}">
                        <a href="${pageContext.request.contextPath}/admin/service/activeService?id=${customerService.customer_service_id}" onclick="return confirm('Are you sure?')"  class="btn  btn-success btn-sm"><i class="fas fa-plus"> </i> Active</a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
    </c:if>
    <c:if test="${list.size()==0}">
        <p class="text-center">No service yet</p>
    </c:if>
</div>