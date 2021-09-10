package tomato_th.project_tomato.controller.backend;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import tomato_th.project_tomato.controller.backend.services.ServicesController;
import tomato_th.project_tomato.model.core.Order_detail;
import tomato_th.project_tomato.model.core.Orders;
import tomato_th.project_tomato.model.service.CustomerService;
import tomato_th.project_tomato.model.service.Services;
import tomato_th.project_tomato.service.OrderDetailService;
import tomato_th.project_tomato.service.OrderService;
import tomato_th.project_tomato.service.ServicesService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(path = "/admin/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private ServicesController servicesController;
    @Autowired
    private ServicesService servicesService;

    @InitBinder
    public void InitBinder(WebDataBinder data) {
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        data.registerCustomEditor(Date.class, new CustomDateEditor(s, true));
    }

    @RequestMapping(path = "")
    public String getAllOrder(Model model, HttpServletRequest request, HttpSession session)
    {

        return findPaginated(1,0,model,request,session);
    }

    @RequestMapping(path = "detailOrder")
    public String detailOrder(@RequestParam("id")Integer id, Model model)
    {
        Orders orders = orderService.getOrderById(id);
        List<Order_detail> listOrderDetail = orderDetailService.getAllByOrderId(id);
        String btnbtnStatus = btnStatus(orders.getStatus());
        String valueStatus = statusH(orders.getStatus());
        model.addAttribute("order",orders);
        model.addAttribute("listOrderDetail",listOrderDetail);
        model.addAttribute("btnbtn",btnbtnStatus);
        model.addAttribute("trangThai",valueStatus);
        return "admin/order/orderDetail";
    }

    @RequestMapping(path = "/searchOrderEmail")
    public String searchOrderEmail(@RequestParam("email")String email,Model model)
    {
        List<Orders> listOrder = orderService.listOrderByEmail(email);
        model.addAttribute("list", listOrder);
        countOrder(model);
        return "admin/order/orderList";
    }

    @RequestMapping(path = "updateStatusOrder",method = RequestMethod.POST)
    public String updateOrderStatus(@RequestParam("orderId")Integer orderId, @ModelAttribute("order")Orders orders) throws MessagingException, ParseException {
        int status = orders.getStatus();
        boolean bl = orderService.updateOrderStatus(orderId,orders.getStatus());
        if (bl) {
            if (orders.getStatus()==2)
            {
                Orders ordersMail = orderService.getOrderById(orderId);
                List<Order_detail> order_detailList = orderDetailService.getAllByOrderId(orderId);
                templateMail(ordersMail,order_detailList);
            }
            return "redirect:/admin/order/detailOrder?id="+orderId+"&&success=Update order status success";
        }
        return "redirect:/admin/order/detailOrder?id="+orderId+"&&error=Update order status failed";
    }


    @RequestMapping("/page/{pageNo}/{status}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, @PathVariable(value = "status") int status, Model model,HttpServletRequest request,HttpSession session) {
        int pageSize = 10;
        Page<Orders> page = null;
      switch (status)
      {
          case 1:
              page = orderService.findPagiWatting(pageNo, pageSize);
              break;
          case 2:
              page = orderService.findPagiConfirmed(pageNo, pageSize);
              break;
          case 3:
              page = orderService.findPagiShipping(pageNo, pageSize);
              break;
          case 4:
              page = orderService.findPagiComplete(pageNo, pageSize);
              break;
          case 5:
              page = orderService.findPagiCancelled(pageNo, pageSize);
              break;
          default:
              page = orderService.findPaginated(pageNo, pageSize);

      }
        List<Orders> listOrder = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("status",status);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("list", listOrder);
        countOrder(model);
        List<Services> servicesList = servicesService.listService();
        model.addAttribute("listService",servicesList);
        servicesController.saveHistoryUrl(request,session);

        return "admin/order/orderList";
    }

    @RequestMapping("/searchOSByServiceStatus")
    public String searchOSByServiceStatus(@RequestParam("service_id")Integer service_id,@RequestParam("status_order")Integer status,Model model)
    {

        return searchOSByServiceStatusPaginated(1,model,service_id,status);
    }

    @RequestMapping("/searchOSByServiceStatus/page/{pageNo}")
    public String searchOSByServiceStatusPaginated(@PathVariable(value = "pageNo")Integer pageNo,Model model,@RequestParam("service_id")Integer service_id,@RequestParam("status_order")Integer status)
    {
        int pageSize=15;
        if (service_id==0)
        {
            return "redirect:/admin/order?error=You have not selected the service !!";
        }
        if (status==0)
        {
            Page<Orders> page = orderService.findPaginatedOdAllStatusService(service_id,pageNo,pageSize);
            List<Orders> listOrder = page.getContent();
            model.addAttribute("totalPages", page.getTotalPages());
            model.addAttribute("totalItems", page.getTotalElements());
            model.addAttribute("list", listOrder);

        }else{
            Page<Orders> page = orderService.findPaginatedOdStatusService(status,service_id,pageNo,pageSize);
            List<Orders> listOrder = page.getContent();
            model.addAttribute("totalPages", page.getTotalPages());
            model.addAttribute("totalItems", page.getTotalElements());
            model.addAttribute("list", listOrder);

        }
        Services services = servicesService.getService(service_id);
        model.addAttribute("serviceName",services.getService_name());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("status",status);
        model.addAttribute("service_id",service_id);
        countOrder(model);
        List<Services> servicesList = servicesService.listService();
        model.addAttribute("listService",servicesList);
        return "admin/order/orderServiceList";
    }


    public String statusH(int status)
    {
        switch(status)
        {
            case 1:
                return "Waiting";
            case 2:
                return "Confirmed";
            case 3:
                return "Shipping";
            case 4:
                return "Complete";
            case 5:
                return "Cancelled";
            default:
                return "Null";
        }
    }

    public String btnStatus(int status)
    {
        switch(status)
        {
            case 1:
                return "btn-primary";
            case 2:
                return "btn-info";
            case 3:
                return "btn-warning";
            case 4:
                return "btn-success";
            case 5:
                return "btn-danger";
            default:
                return "btn-primary";
        }
    }

    public void countOrder(Model model)
    {

        int coutWatting = orderService.countOrderByStatus(1);
        int coutConfirmed = orderService.countOrderByStatus(2);
        int coutShipping = orderService.countOrderByStatus(3);
        int coutComplete = orderService.countOrderByStatus(4);
        int coutCancelled = orderService.countOrderByStatus(5);
        model.addAttribute("coutWatting",coutWatting);
        model.addAttribute("coutConfirmed",coutConfirmed);
        model.addAttribute("coutShipping",coutShipping);
        model.addAttribute("coutComplete",coutComplete);
        model.addAttribute("coutCancelled",coutCancelled);
    }
    public void templateMail(Orders ordersMail,List<Order_detail> order_detailList) throws MessagingException, ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS");
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
        String created_date = sdf.format(ordersMail.getCreated());
        Date date_created = sdf.parse(created_date);
        String received_date = sdf2.format(ordersMail.getReceived_date());
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,false,"charset=UTF-8");
        String htmlMsg ="";
        htmlMsg +="<!DOCTYPE html>";
        htmlMsg +="<html>";
        htmlMsg +="<head>";
        htmlMsg +="<style type='text/css'> body, table, td, a {-webkit-text-size-adjust: 100%;-ms-text-size-adjust: 100%;}";
        htmlMsg +="table,td {mso-table-lspace: 0pt;mso-table-rspace: 0pt;}";
        htmlMsg +="img {-ms-interpolation-mode: bicubic;}";
        htmlMsg +="img {border: 0;height: auto;line-height: 100%;outline: none;text-decoration: none;}";
        htmlMsg +="table {border-collapse: collapse !important;}";
        htmlMsg +="body { height: 100% !important;margin: 0 !important;padding: 0 !important;width: 100% !important; }";
        htmlMsg +="a[x-apple-data-detectors] { color: inherit !important;text-decoration: none !important;font-size: inherit !important;font-family: inherit !important;font-weight: inherit !important;line-height: inherit !important; }";
        htmlMsg +="@media screen and (max-width: 480px) {.mobile-hide { display: none !important; }.mobile-center { text-align: center !important; } }";
        htmlMsg +="div[style*='margin: 16px 0;'] { margin: 0 !important; }";
        htmlMsg +="</style>";

        htmlMsg +="<body style='margin: 0 !important; padding: 0 !important; background-color: #eeeeee;' bgcolor='#eeeeee'>";
        htmlMsg +="<div style='display: none; font-size: 1px; color: #fefefe; line-height: 1px; font-family: Open Sans, Helvetica, Arial, sans-serif; max-height: 0px; max-width: 0px; opacity: 0; overflow: hidden;'> For what reason would it be advisable for me to think about business content? That might be little bit risky to have crew member like them.</div>";
        htmlMsg +="<table border='0' cellpadding='0' cellspacing='0' width='100%'>";
        htmlMsg +="<tr>";
        htmlMsg +="<td align='center' style='background-color: #eeeeee;' bgcolor='#eeeeee'>";
        htmlMsg +="<table align='center' border='0' cellpadding='0' cellspacing='0' width='100%' style='max-width:800px;'>";
        htmlMsg +="<tr>";
        htmlMsg +="<td align='center' valign='top' style='font-size:0; padding: 35px;' bgcolor='#F44336'>";
        htmlMsg +="<div style='display:inline-block; max-width:50%; min-width:100px; vertical-align:top; width:100%;'>";
        htmlMsg +="<table align='left' border='0' cellpadding='0' cellspacing='0' width='100%' style='max-width:300px;'>";
        htmlMsg +="<tr>";
        htmlMsg +="<td align='left' valign='top' style='font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 36px; font-weight: 800; line-height: 48px;' class='mobile-center'> <h1 style='font-size: 36px; font-weight: 800; margin: 0; color: #ffffff;'>Tomato</h1>";
        htmlMsg +="</td></tr> </table> </div>";

        htmlMsg +="<div style='display:inline-block; max-width:50%; min-width:100px; vertical-align:top; width:100%;' class='mobile-hide'>";
        htmlMsg +="<table align='left' border='0' cellpadding='0' cellspacing='0' width='100%' style='max-width:300px;'>";
        htmlMsg +="<tr> <td align='right' valign='top' style='font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 48px; font-weight: 400; line-height: 48px;'>";
        htmlMsg +="<table cellspacing='0' cellpadding='0' border='0' align='right'>";
        htmlMsg +="<tr><td style='font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 18px; font-weight: 400;'>";
        htmlMsg +="<p style='font-size: 18px; font-weight: 400; margin: 0; color: #ffffff;'><a href='#' target='blank' style='color: #ffffff; text-decoration: none;'>Shop &nbsp;</a></p></td>";
        htmlMsg +="<td style='font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 18px; font-weight: 400; line-height: 24px;'> <a href='#' target='_blank' style='color: #ffffff; text-decoration: none;'><img src='https://img.icons8.com/color/48/000000/small-business.png' width='27' height='23' style='display: block; border: 0px;' /></a> </td>";
        htmlMsg +="</tr></table></td></tr></table></div></td></tr>";

        htmlMsg +="<tr><td align='center' style='padding: 35px 35px 20px 35px; background-color: #ffffff;' bgcolor='#ffffff'>";
        htmlMsg +="<table align='center' border='0' cellpadding='0' cellspacing='0' width='100%' style='max-width:600px;'>";
        htmlMsg +="<tr><td align='center' style='font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding-top: 25px;'> <img src='https://img.icons8.com/carbon-copy/100/000000/checked-checkbox.png' width='125' height='120' style='display: block; border: 0px;' /><br>";
        htmlMsg +="<h2 style='font-size: 30px; font-weight: 800; line-height: 36px; color: #333333; margin: 0;'> Thank You For Ordering From Us! </h2></td></tr>";

        htmlMsg +="<tr>";
        htmlMsg +="<td align='left' style='font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding-top: 10px;'>";
        htmlMsg +="<p style='font-size: 16px; font-weight: 400; line-height: 24px; color: #777777;'> Your order is confirmed! Review your order information below. We'll drop you another email when your order ships.</p>";
        htmlMsg +="</td></tr>";

        htmlMsg +="<tr>";
        htmlMsg +="<td align='left' style='padding-top: 20px;'>";
        htmlMsg +="<table cellspacing='0' cellpadding='0' border='0' width='100%'>";
        htmlMsg +="<tr>";
        htmlMsg +="<td width='55%' align='left' style='font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 800; line-height: 24px; padding: 15px 10px 5px 10px;'>Receiver:</td>";
        htmlMsg +="<td width='45%' align='left' style='font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 15px 10px 5px 10px;'>"+ordersMail.getFullname()+"</td>";
        htmlMsg +="</tr>";

        htmlMsg +="<tr>";
        htmlMsg +="<td width='55%' align='left' style='font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 800; line-height: 24px; padding: 15px 10px 5px 10px;'>Receiver Phone:</td>";
        htmlMsg +="<td width='45%' align='left' style='font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 15px 10px 5px 10px;'>"+ordersMail.getPhone_number()+"</td>";
        htmlMsg +="</tr>";

        htmlMsg +="<tr>";
        htmlMsg +="<td width='55%' align='left' style='font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 800; line-height: 24px; padding: 15px 10px 5px 10px;'>Created date:</td>";
        htmlMsg +="<td width='45%' align='left' style='font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 15px 10px 5px 10px;'>"+date_created+"</td>";
        htmlMsg +="</tr>";

        htmlMsg +="<tr>";
        htmlMsg +="<td width='55%' align='left' style='font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 800; line-height: 24px; padding: 15px 10px 5px 10px;'>Payment:</td>";
        htmlMsg +="<td width='45%' align='left' style='font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 15px 10px 5px 10px;'>"+ordersMail.getPayment()+"</td>";
        htmlMsg +="</tr></table></td></tr>";



        htmlMsg +="<tr>";
        htmlMsg +="<td align='left' style='padding-top: 20px;'>";
        htmlMsg +="<table cellspacing='0' cellpadding='0' border='0' width='100%'>";

        htmlMsg +="<tr>";
        htmlMsg +="<td width='60%' align='left' bgcolor='#eeeeee' style='font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 800; line-height: 24px; padding: 10px;'>Product  # </td>";
        htmlMsg +="<td width='15%' align='left' bgcolor='#eeeeee' style='font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 800; line-height: 24px; padding: 10px;'> Image</td>";
        htmlMsg +="<td width='25%' align='left' bgcolor='#eeeeee' style='font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 800; line-height: 24px; padding: 10px;'> price </td>";
        htmlMsg +="</tr>";
        for (Order_detail order_detail:order_detailList) {
            htmlMsg += "<tr>";
            htmlMsg += "<td width='60%' align='left' style='font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 15px 10px 5px 10px;'>"+order_detail.getProduct().getProduct_name()+"("+order_detail.getQuantity()+")</td>";
            htmlMsg += "<td width='15%' align='left' style=' padding: 15px 10px 5px 10px;'><img src='" + order_detail.getProduct().getImage() + "' width='100'></td>";
            htmlMsg += "<td width='25%' align='left' style='font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 15px 10px 5px 10px;'> $"+order_detail.getQuantity()*order_detail.getPrice()+"</td>";
            htmlMsg += "</tr>";
        }
        htmlMsg +="</table></td></tr>";

        htmlMsg +="<tr>";
        htmlMsg +="<td align='left' style='padding-top: 20px;'>";
        htmlMsg +="<table cellspacing='0' cellpadding='0' border='0' width='100%'>";
        htmlMsg +="<tr>";
        htmlMsg +="<td width='75%' align='left' style='font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 800; line-height: 24px; padding: 10px; border-top: 3px solid #eeeeee; border-bottom: 3px solid #eeeeee;'> TOTAL </td>";
        htmlMsg +="<td width='25%' align='left' style='font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 800; line-height: 24px; padding: 10px; border-top: 3px solid #eeeeee; border-bottom: 3px solid #eeeeee;'> $"+ ordersMail.getTotal_price()+"</td>";
        htmlMsg +="</tr></table></td></tr></table></td></tr>";


        htmlMsg +="<tr>";
        htmlMsg +="<td align='center' height='100%' valign='top' width='100%' style='padding: 0 35px 35px 35px; background-color: #ffffff;' bgcolor='#ffffff'>";
        htmlMsg +="<table align='center' border='0' cellpadding='0' cellspacing='0' width='100%' style='max-width:700px;'>";
        htmlMsg +="<tr>";

        htmlMsg +="<td align='center' valign='top' style='font-size:0;'>";
        htmlMsg +="<div style='display:inline-block; max-width:50%; min-width:240px; vertical-align:top; width:100%;'>";
        htmlMsg +="<table align='left' border='0' cellpadding='0' cellspacing='0' width='100%' style='max-width:300px;'>";

        htmlMsg +="<tr>";
        htmlMsg +="<td align='left' valign='top' style='font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px;'>";
        htmlMsg +="<p style='font-weight: 800;'>Delivery Address</p>";
        htmlMsg +="<p>"+ordersMail.getOrder_address()+"</p>";
        htmlMsg +="</td>";
        htmlMsg +="</tr>";
        htmlMsg +="</table>";
        htmlMsg +="</div>";
        htmlMsg +="<div style='display:inline-block; max-width:50%; min-width:240px; vertical-align:top; width:100%;'>";
        htmlMsg +="<table align='left' border='0' cellpadding='0' cellspacing='0' width='100%' style='max-width:300px;'>";
        htmlMsg +="<tr>";
        htmlMsg +="<td align='left' valign='top' style='font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px;'>";
        htmlMsg +="<p style='font-weight: 800;'>Estimated Delivery Date</p>";
        htmlMsg +="<p>"+received_date+"</p>";
        htmlMsg +="</td>";
        htmlMsg +="</tr>";
        htmlMsg +="</table></div></td></tr></table></td></tr></table></td></tr></table></td></tr></table></body></html>";

        mimeMessage.setContent(htmlMsg,"text/html; charset=UTF-8");
        helper.setTo(ordersMail.getOrder_email());
        helper.setSubject("Tomato@shop");
        mailSender.send(mimeMessage);
    }
    public void templatesMail(CustomerService customerService) throws MessagingException, ParseException {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,false,"charset=UTF-8");
        String htmlMsg ="";
        htmlMsg +="<!DOCTYPE html>";
        htmlMsg +="<html>";
        htmlMsg +="<head>";
        htmlMsg +="<style type='text/css'> body, table, td, a {-webkit-text-size-adjust: 100%;-ms-text-size-adjust: 100%;}";
        htmlMsg +="table,td {mso-table-lspace: 0pt;mso-table-rspace: 0pt;}";
        htmlMsg +="img {-ms-interpolation-mode: bicubic;}";
        htmlMsg +="img {border: 0;height: auto;line-height: 100%;outline: none;text-decoration: none;}";
        htmlMsg +="table {border-collapse: collapse !important;}";
        htmlMsg +="body { height: 100% !important;margin: 0 !important;padding: 0 !important;width: 100% !important; }";
        htmlMsg +="a[x-apple-data-detectors] { color: inherit !important;text-decoration: none !important;font-size: inherit !important;font-family: inherit !important;font-weight: inherit !important;line-height: inherit !important; }";
        htmlMsg +="@media screen and (max-width: 480px) {.mobile-hide { display: none !important; }.mobile-center { text-align: center !important; } }";
        htmlMsg +="div[style*='margin: 16px 0;'] { margin: 0 !important; }";
        htmlMsg +="</style>";

        htmlMsg +="<body style='margin: 0 !important; padding: 0 !important; background-color: #eeeeee;' bgcolor='#eeeeee'>";
        htmlMsg +="<div style='display: none; font-size: 1px; color: #fefefe; line-height: 1px; font-family: Open Sans, Helvetica, Arial, sans-serif; max-height: 0px; max-width: 0px; opacity: 0; overflow: hidden;'> For what reason would it be advisable for me to think about business content? That might be little bit risky to have crew member like them.</div>";
        htmlMsg +="<table border='0' cellpadding='0' cellspacing='0' width='100%'>";
        htmlMsg +="<tr>";
        htmlMsg +="<td align='center' style='background-color: #eeeeee;' bgcolor='#eeeeee'>";
        htmlMsg +="<table align='center' border='0' cellpadding='0' cellspacing='0' width='100%' style='max-width:800px;'>";
        htmlMsg +="<tr>";
        htmlMsg +="<td align='center' valign='top' style='font-size:0; padding: 35px;' bgcolor='#F44336'>";
        htmlMsg +="<div style='display:inline-block; max-width:50%; min-width:100px; vertical-align:top; width:100%;'>";
        htmlMsg +="<table align='left' border='0' cellpadding='0' cellspacing='0' width='100%' style='max-width:300px;'>";
        htmlMsg +="<tr>";
        htmlMsg +="<td align='left' valign='top' style='font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 36px; font-weight: 800; line-height: 48px;' class='mobile-center'> <h1 style='font-size: 36px; font-weight: 800; margin: 0; color: #ffffff;'>Tomato</h1>";
        htmlMsg +="</td></tr> </table> </div>";

        htmlMsg +="<div style='display:inline-block; max-width:50%; min-width:100px; vertical-align:top; width:100%;' class='mobile-hide'>";
        htmlMsg +="<table align='left' border='0' cellpadding='0' cellspacing='0' width='100%' style='max-width:300px;'>";
        htmlMsg +="<tr> <td align='right' valign='top' style='font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 48px; font-weight: 400; line-height: 48px;'>";
        htmlMsg +="<table cellspacing='0' cellpadding='0' border='0' align='right'>";
        htmlMsg +="<tr><td style='font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 18px; font-weight: 400;'>";
        htmlMsg +="<p style='font-size: 18px; font-weight: 400; margin: 0; color: #ffffff;'><a href='#' target='blank' style='color: #ffffff; text-decoration: none;'>Shop &nbsp;</a></p></td>";
        htmlMsg +="<td style='font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 18px; font-weight: 400; line-height: 24px;'> <a href='#' target='_blank' style='color: #ffffff; text-decoration: none;'><img src='https://img.icons8.com/color/48/000000/small-business.png' width='27' height='23' style='display: block; border: 0px;' /></a> </td>";
        htmlMsg +="</tr></table></td></tr></table></div></td></tr>";

        htmlMsg +="<tr><td align='center' style='padding: 35px 35px 20px 35px; background-color: #ffffff;' bgcolor='#ffffff'>";
        htmlMsg +="<table align='center' border='0' cellpadding='0' cellspacing='0' width='100%' style='max-width:600px;'>";
        htmlMsg +="<tr><td align='center' style='font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding-top: 25px;'> <img src='https://img.icons8.com/carbon-copy/100/000000/checked-checkbox.png' width='125' height='120' style='display: block; border: 0px;' /><br>";
        htmlMsg +="<h2 style='font-size: 30px; font-weight: 800; line-height: 36px; color: #333333; margin: 0;'> Thank you for using the service!! </h2></td></tr>";

        htmlMsg +="<tr>";
        htmlMsg +="<td align='left' style='font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding-top: 10px;'>";
        htmlMsg +="<p style='font-size: 16px; font-weight: 400; line-height: 24px; color: #777777;'> Dịch vụ của bạn(ID: "+customerService.getCustomer_service_id()+"-"+customerService.getServices().getService_name()+") đã được kích hoạt. Bạn đã có thể sử dụng dịch vụ của chúng tối. Mọi chi tiết tai Website..</p>";
        htmlMsg +="</td></tr>";
        htmlMsg +="</table></div></td></tr></table></td></tr></table></td></tr></table></td></tr></table></body></html>";

        mimeMessage.setContent(htmlMsg,"text/html; charset=UTF-8");
        helper.setTo(customerService.getCustomer().getEmail());
        helper.setSubject("Tomato@shop");
        mailSender.send(mimeMessage);
    }
}
