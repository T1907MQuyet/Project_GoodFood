<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="modal fade" id="exampleModal5" tabindex="2" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="max-width:700px " role="document">
        <div class="modal-content" style="width: 700px">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel5">Search Service</h5>
            </div>
            <div class="modal-body" >
                <form class="row" method="GET" action="${pageContext.request.contextPath}/admin/order/searchOSByServiceStatus" role="form">
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
                        <select class="form-control" name="status_order">
                            <c:choose>
                                <c:when test="${status==1}">
                                    <option value="0">All</option>
                                    <option value="1" selected>Waiting</option>
                                    <option value="2">Confirmed</option>
                                    <option value="3">Shipping</option>
                                    <option value="4">Complete</option>
                                    <option value="5">Cancelled</option>
                                </c:when>
                                <c:when test="${status==2}">
                                    <option value="0">All</option>
                                    <option value="1" >Waiting</option>
                                    <option value="2" selected>Confirmed</option>
                                    <option value="3">Shipping</option>
                                    <option value="4">Complete</option>
                                    <option value="5">Cancelled</option>
                                </c:when>
                                <c:when test="${status==3}">
                                    <option value="0">All</option>
                                    <option value="1" >Waiting</option>
                                    <option value="2" >Confirmed</option>
                                    <option value="3" selected>Shipping</option>
                                    <option value="4">Complete</option>
                                    <option value="5">Cancelled</option>
                                </c:when>
                                <c:when test="${status==4}">
                                    <option value="0">All</option>
                                    <option value="1" >Waiting</option>
                                    <option value="2" >Confirmed</option>
                                    <option value="3" >Shipping</option>
                                    <option value="4" selected>Complete</option>
                                    <option value="5">Cancelled</option>
                                </c:when>
                                <c:when test="${status==5}">
                                    <option value="0">All</option>
                                    <option value="1" >Waiting</option>
                                    <option value="2" >Confirmed</option>
                                    <option value="3" >Shipping</option>
                                    <option value="4" >Complete</option>
                                    <option value="5" selected>Cancelled</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="0" >All</option>
                                    <option value="1" >Waiting</option>
                                    <option value="2" >Confirmed</option>
                                    <option value="3" >Shipping</option>
                                    <option value="4" >Complete</option>
                                    <option value="5" >Cancelled</option>
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