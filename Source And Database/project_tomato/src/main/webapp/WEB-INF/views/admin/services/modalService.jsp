<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="modal fade" id="exampleModal1" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="max-width:700px " role="document">
        <div class="modal-content" style="width: 700px">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Calender</h5>
            </div>
            <div class="modal-body">
                <table class="table table-striped projects">
                    <thead>
                        <tr>
                            <th style="width: 10px">STT</th>
                            <th>solar calendar</th>
                            <th>lunar calendar</th>
                        </tr>
                    </thead>
                    <tbody id="calender"></tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="exampleModal2" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="max-width:700px " role="document">
        <div class="modal-content" style="width: 700px">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel1">Product information</h5>
            </div>
            <div class="modal-body" id="customerServiceProduct"></div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="exampleModal3" tabindex="2" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="max-width:700px " role="document">
        <div class="modal-content" style="width: 700px">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel2">Product information</h5>
            </div>
            <div class="modal-body" id="customerServiceOrder"></div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="exampleModal4" tabindex="2" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="max-width:700px " role="document">
        <div class="modal-content" style="width: 700px">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel3">Orders information</h5>
            </div>
            <div class="modal-body" id="customerSO">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="exampleModal5" tabindex="2" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="max-width:700px " role="document">
        <div class="modal-content" style="width: 700px">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel5">Search Service</h5>
            </div>
            <div class="modal-body" >
                <form class="row" method="GET" action="${pageContext.request.contextPath}/admin/service/searchServiceReceived" role="form">
                    <div class="form-group col-5">
                        <select class="form-control" name="service_id">
                            <option value="0">--Service Selection--</option>
                            <c:forEach items="${listService}" var="service">
                                <c:choose>
                                    <c:when test="${service.service_id==service_id}">
                                        <option value="${service.service_id}" selected>${service.service_name}
                                            <c:if test="${service.status ==1}">
                                                <span>(active)</span>
                                            </c:if>
                                            <c:if test="${service.status ==2}">
                                                <span>(none active)</span>
                                            </c:if>
                                        </option>
                                    </c:when>
                                    <c:when test="${service.service_id!=service_id}">
                                        <option value="${service.service_id}">${service.service_name}
                                            <c:if test="${service.status ==1}">
                                                <span>(active)</span>
                                            </c:if>
                                            <c:if test="${service.status ==2}">
                                                <span>(none active)</span>
                                            </c:if>
                                        </option>
                                    </c:when>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group col-4">
                        <select class="form-control" name="date_received">
                            <c:choose>
                                <c:when test="${date_received==0}">
                                    <option value="4" >All</option>
                                    <option value="0" selected>Right Day</option>
                                    <option value="1">Before 1 Day</option>
                                    <option value="2">Before 2 Day</option>
                                    <option value="3">Before 3 Day</option>
                                </c:when>
                                <c:when test="${date_received==1}">
                                    <option value="4" >All</option>
                                    <option value="0" >Right Day</option>
                                    <option value="1" selected>Before 1 Day</option>
                                    <option value="2">Before 2 Day</option>
                                    <option value="3">Before 3 Day</option>
                                </c:when>
                                <c:when test="${date_received==2}">
                                    <option value="4" >All</option>
                                    <option value="0" >Right Day</option>
                                    <option value="1">Before 1 Day</option>
                                    <option value="2" selected>Before 2 Day</option>
                                    <option value="3">Before 3 Day</option>
                                </c:when>
                                <c:when test="${date_received==3}">
                                    <option value="4" >All</option>
                                    <option value="0" >Right Day</option>
                                    <option value="1">Before 1 Day</option>
                                    <option value="2">Before 2 Day</option>
                                    <option value="3" selected>Before 3 Day</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="4" >All</option>
                                    <option value="0" >Right Day</option>
                                    <option value="1" >Before 1 Day</option>
                                    <option value="2" >Before 2 Day</option>
                                    <option value="3" >Before 3 Day</option>
                                </c:otherwise>
                            </c:choose>
                        </select>
                    </div>
                    <div class="col-3">
                        <button type="submit" class="btn btn-outline-primary">Search</button>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="exampleModal6" tabindex="2" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="max-width:700px " role="document">
        <div class="modal-content" style="width: 700px">
            <div class="modal-header">
                <h5 class="modal-title" >Service information:</h5>
            </div>
            <div class="modal-body">
                <table class="table table-striped projects">
                    <tbody>
                    <tr>
                        <td><span class="text-bold">Service has not created an order:</span><span class="badge badge-info infoTextSize"> ${customerServiceListNoOrder.size()}</span></td>
                    </tr>
                    <tr>
                        <td><span class="text-bold">Upcoming Date:</span> <span class="badge badge-success infoTextSize"><fmt:formatDate value="${calender.solar_calendar}" pattern="dd-MM-yyyy" /></span></td>
                    </tr>
                    </tbody>
                </table>

                <a class="btn btn-info " href="${pageContext.request.contextPath}/admin/service/createOrderByService?service_id=${param.service_id}&upcoming_date=${calender.solar_calendar}"><i class="fas fa-plus"> </i> Create orders for all services</a>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>