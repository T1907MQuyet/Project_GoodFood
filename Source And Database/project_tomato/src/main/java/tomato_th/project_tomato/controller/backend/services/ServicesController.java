package tomato_th.project_tomato.controller.backend.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import tomato_th.project_tomato.controller.backend.OrderController;
import tomato_th.project_tomato.controller.frontend.ServiceFEController;
import tomato_th.project_tomato.model.Customer;
import tomato_th.project_tomato.model.News;
import tomato_th.project_tomato.model.core.Order_detail;
import tomato_th.project_tomato.model.core.Orders;
import tomato_th.project_tomato.model.core.Product;
import tomato_th.project_tomato.model.service.*;
import tomato_th.project_tomato.model.service.CustomerService;
import tomato_th.project_tomato.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(path = "/admin/service")
public class ServicesController {
    @Autowired
    private ServicesService servicesService;
    @Autowired
    private CalenderService calenderService;
    @Autowired
    private CustomerServiceService customerServiceService;
    @Autowired
    private CustomerSPService customerSPService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private CustomerSOService customerSOService;
    @Autowired
    private OrderController orderController;
    @Autowired
    private ServiceFEController serviceFEController;
    @Autowired
    private Service_News_Service service_news_service;
    @Autowired
    private NewsService newsService;

    @InitBinder
    public void InitBinder(WebDataBinder data) {
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        data.registerCustomEditor(Date.class, new CustomDateEditor(s, true));
    }

    @RequestMapping(path = "")
    public String showService(Model model)
    {
        Services services = new Services();
        return findPaginated(1,model,services);
    }

    @RequestMapping(path = "/saveService",method = RequestMethod.POST)
    public String saveService(@ModelAttribute("serviceNew")@Valid Services services, BindingResult result,Model model)
    {
        if (result.hasErrors())
        {
            return findPaginated(1,model,services);
        }
        boolean checkServiceName = servicesService.checkServiceName(services.getService_name());
        if (checkServiceName==false)
        {
            return "redirect:/admin/service?errorservicename=Service name is existed";
        }
        boolean bl = servicesService.saveService(services);
        if (bl)
        {
            return "redirect:/admin/service?success=Add new service success";
        }
        return "redirect:/admin/service?error=Add new service failed";
    }

    @RequestMapping(path = "/editService")
    public String editService(@RequestParam("id")Integer id,Model model)
    {
        Services services = servicesService.getService(id);
        model.addAttribute("serviceEdit",services);
        return "admin/services/serviceEdit";
    }

    @RequestMapping(path = "/updateService",method = RequestMethod.POST)
    public String updateService(@ModelAttribute("serviceEdit")Services services)
    {
        boolean checkName = checkServiceName(services.getService_name(),services.getService_id());
        if (checkName==false)
        {
            return "redirect:/admin/service/editService?id="+services.getService_id()+"&&errorservicename=Service name is existed";
        }
        boolean bl = servicesService.updateService(services);
        if (bl)
        {
            return "redirect:/admin/service?success=Update service success";
        }
        return "redirect:/admin/service?error=Update service failed";
    }

    @RequestMapping(path = "/deleteService")
    public String deleteService(@RequestParam("id")Integer id)
    {
        boolean bl = servicesService.deleteService(id);
        if (bl)
        {
            return "redirect:/admin/service?success=Delete service success";
        }
        return "redirect:/admin/service?error=Delete service failed";
    }

    @RequestMapping(path = "/detailService")
    public String detailService(@RequestParam("id")Integer id,Model model)
    {
        Services services= servicesService.getService(id);
        List<Calender> listCalender = calenderService.listCalenderByService(id);
        Calender calender = new Calender();
        model.addAttribute("services",services);
        model.addAttribute("listCalender",listCalender);
        model.addAttribute("calenderNew",calender);

        List<Service_News> service_newsList = service_news_service.getListServiceNewByServiceId(id);
//        List<News> newsList = new ArrayList<>();
//        for (Service_News service_news: service_newsList) {
//            News news = newsService.getNewById(service_news.getNews().getNews_id());
//            newsList.add(news);
//        }
        model.addAttribute("service_newsList",service_newsList);
        return "admin/services/detailService";
    }

    @RequestMapping(path = "/deleteCalender")
    public String deleteCalender(@RequestParam("id")Integer idCalender,@RequestParam("service_id")Integer service_id)
    {
        boolean bl = calenderService.deleteCalender(idCalender);
        if (bl)
        {
            return "redirect:/admin/service/detailService?id="+service_id+"&&success=Delete Calender success";
        }
        return "redirect:/admin/service/detailService?id="+service_id+"&&success=Delete Calender failed";
    }

    @RequestMapping(path = "/saveCalender",method = RequestMethod.POST)
    public String saveCalender(@ModelAttribute("calenderNew")Calender calender)
    {
        boolean bl = calenderService.saveCalender(calender);
        if (bl)
        {
            return "redirect:/admin/service/detailService?id="+calender.getServices().getService_id()+"&&success=Add Calender success";
        }
        return "redirect:/admin/service/detailService?id="+calender.getServices().getService_id()+"&&error=Add Calender failed";
    }
    @RequestMapping(path = "/hiddenCalender")
    public String hiddenCalender(@RequestParam("id")Integer id,@RequestParam("service_id")Integer service_id)
    {
        boolean bl = calenderService.hiddenCalender(id);
        if (bl)
        {
            return "redirect:/admin/service/detailService?id="+service_id+"&&success=Update Calender success";
        }
        return "redirect:/admin/service/detailService?id="+service_id+"&&success=Update Calender failed";
    }
    @RequestMapping(path = "/updateDateCalender")
    public String updateCheckDateCalender(@RequestParam("id")Integer id) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH)-1;
        calendar.set(Calendar.DAY_OF_MONTH,day);
        Date dateNow = calendar.getTime();

        List<Calender> listCalender = calenderService.listCalenByServiceStatus(id,1);
        if (listCalender==null)
        {
            Services services = servicesService.getService(id);
            services.setStatus(2);
            servicesService.updateService(services);
            return "redirect:/admin/service/detailService?id="+id+"&&success=Service has ended";
        }
        for (Calender calender: listCalender) {
            if (dateNow.after(calender.getSolar_calendar()))
            {
                calenderService.hiddenCalender(calender.getCalender_id());
            }
        }
        return "redirect:/admin/service/detailService?id="+id+"&&success=Update Calender success";
    }

    @RequestMapping(path = "/showCalender")
    public String showCalender(@RequestParam("id")Integer id,@RequestParam("service_id")Integer service_id)
    {
        boolean bl = calenderService.showCalender(id);
        if (bl)
        {
            return "redirect:/admin/service/detailService?id="+service_id+"&&success=Update Calender success";
        }
        return "redirect:/admin/service/detailService?id="+service_id+"&&success=Update Calender success";
    }

    @RequestMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo")int pageNo, Model model, Services services)
    {
        int pageSize = 10;
        Page<Services> page = servicesService.findPaginated(pageNo,pageSize);
        List<Services> listServices = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("list", listServices);
        model.addAttribute("serviceNew", services);
        countStatusService(model);
        return "admin/services/serviceList";
    }

    @RequestMapping("/customerService")
    public String showCustomerService(Model model)
    {
        return custStatusPaginated(1,model);
    }
    @RequestMapping("/customerService/page/{pageNo}")
    public String custStatusPaginated(@PathVariable(value = "pageNo")int pageNo,Model model)
    {
        int pageSize = 10;
        Page<CustomerService> page = customerServiceService.findByCustStatusPaginated(pageNo,pageSize);
        List<CustomerService> listCustService = page.getContent();
        getListService(model);
        model.addAttribute("currentPage",pageNo);
        model.addAttribute("totalPages",page.getTotalPages());
        model.addAttribute("totalItems",page.getTotalElements());
        model.addAttribute("list",listCustService);
        countStatusService(model);
        return "admin/services/customerService";
    }
    @RequestMapping("/searchServiceCust")
    public String searchServiceCust(@RequestParam("email")String email,@RequestParam("service_id")Integer service_id,@RequestParam("status")Integer status,Model model)
    {
        if (service_id==0)
        {
            return "redirect:/admin/service/customerService?error=You have not selected the service";
        }
        List<CustomerService> list = customerServiceService.searhByEmailStatus(email,service_id,status);
        model.addAttribute("list",list);
        getListService(model);
        return "admin/services/customerService";
    }

    @RequestMapping("/customerSstatus")
    public String searchServiceCustActive(@RequestParam("status")Integer status,Model model)
    {
        return searchServiceCustActivePaginated(1,model,status);
    }
    @RequestMapping("/customerSstatus/page/{pageNo}")
    public String searchServiceCustActivePaginated(@PathVariable(value = "pageNo")int pageNo,Model model,@RequestParam("status")Integer status)
    {
        int pageSize = 10;
        Page<CustomerService> page = customerServiceService.findByCustPaginatedStatus(pageNo,pageSize,status);
        List<CustomerService> listCustService = page.getContent();
        getListService(model);
        model.addAttribute("currentPage",pageNo);
        model.addAttribute("totalPages",page.getTotalPages());
        model.addAttribute("totalItems",page.getTotalElements());
        model.addAttribute("list",listCustService);
        model.addAttribute("status",status);
        countStatusService(model);
        return "admin/services/customerServiceByStatus";
    }
    @RequestMapping("/checkCalenderService")
    public String checkCalenderService(@RequestParam("id")Integer id, Model model)
    {
       List<Calender> list = calenderService.listCalenByServiceStatus(id,1);
       model.addAttribute("list",list);
        return "admin/ajax/checkCalender";
    }

    @RequestMapping("/checkCustPro")
    public String checkCustomerServiceProduct(@RequestParam("custService_id")Integer customer_service_id,Model model)
    {
        List<CustomerServiceProduct> list = customerSPService.getBtCustomerServiceByCSPId(customer_service_id);
        model.addAttribute("list",list);
        return "admin/ajax/checkCustomerProduct";
    }

    @RequestMapping("/createOrder")
    public String createOrderCSP(@RequestParam("id")Integer id,Model model)
    {
        try{
        CustomerService customerService = customerServiceService.getById(id);
        //get date time now
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        //get calender by service
        List<Calender> listCalen = calenderService.listCalenByServiceStatus(customerService.getServices().getService_id(),1);
        if (listCalen.size()<=0)
        {
            return "redirect:/admin/service/customerService?error=Order creation failed";
        }
        Calender calender = listCalen.get(0);
        //calculate date received_date
        Calendar calenderCustSer = Calendar.getInstance();
        calenderCustSer.setTime(calender.getSolar_calendar());
        int dayReceived = calenderCustSer.get(Calendar.DAY_OF_MONTH)-customerService.getReceived_date();
        calenderCustSer.set(Calendar.DAY_OF_MONTH,dayReceived);
        Date dateCustSer = calenderCustSer.getTime();
        Date dateSolarCalen = calender.getSolar_calendar();
        List<CustomerServiceProduct> customerServiceProductList = customerSPService.getByCustomerServiceStatus(customerService.getCustomer_service_id(),1);
        if (calendar.get(Calendar.DAY_OF_MONTH)==calenderCustSer.get(Calendar.DAY_OF_MONTH))
        {
            try{
                createOrderByCustomerService(customerService,customerServiceProductList,dateCustSer,dateSolarCalen);
                return "redirect:/admin/service/customerService?success=Order created successfully";
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        if(calendar.get(Calendar.DAY_OF_MONTH)!=calenderCustSer.get(Calendar.DAY_OF_MONTH))
        {
            //createOrderByCustomerService(customerService,customerServiceProductList,dateCustSer,dateSolarCalen);
           return "redirect:/admin/service/customerService?error=Unable to create order due to incorrect delivery date";
            //return "redirect:/admin/service/detailCustomerService?id="+id+"&&success=Order created successfully";
        }
        }catch (Exception e)
        {

        }
        return "redirect:/admin/service/customerService?error=Order creation failed";
    }
    @RequestMapping("/createOrder2")
    public String createOrderCSP2(@RequestParam("id")Integer id,Model model)
    {
        try{
            CustomerService customerService = customerServiceService.getById(id);
            //get date time now
            Calendar calendar = Calendar.getInstance();
            Date date = calendar.getTime();
            //get calender by service
            List<Calender> listCalen = calenderService.listCalenByServiceStatus(customerService.getServices().getService_id(),1);
            if (listCalen.size()<=0)
            {
                return "redirect:/admin/service/customerService?error=Order creation failed";
            }
            Calender calender = listCalen.get(0);
            //calculate date received_date
            Calendar calenderCustSer = Calendar.getInstance();
            calenderCustSer.setTime(calender.getSolar_calendar());
            int dayReceived = calenderCustSer.get(Calendar.DAY_OF_MONTH)-customerService.getReceived_date();
            calenderCustSer.set(Calendar.DAY_OF_MONTH,dayReceived);
            Date dateCustSer = calenderCustSer.getTime();
            Date dateSolarCalen = calender.getSolar_calendar();
            List<CustomerServiceProduct> customerServiceProductList = customerSPService.getByCustomerServiceStatus(customerService.getCustomer_service_id(),1);
            if (calendar.get(Calendar.DAY_OF_MONTH)==calenderCustSer.get(Calendar.DAY_OF_MONTH))
            {
                try{
                    createOrderByCustomerService(customerService,customerServiceProductList,dateCustSer,dateSolarCalen);
                    return "redirect:/admin/service/customerService?success=Order created successfully";
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            if(calendar.get(Calendar.DAY_OF_MONTH)!=calenderCustSer.get(Calendar.DAY_OF_MONTH))
            {
                createOrderByCustomerService(customerService,customerServiceProductList,dateCustSer,dateSolarCalen);
//            return "redirect:/admin/service/customerService?error=Unable to create order due to incorrect delivery date";
                return "redirect:/admin/service/detailCustomerService?id="+id+"&&success=Order created successfully";
            }
        }catch (Exception e)
        {

        }
        return "redirect:/admin/service/customerService?error=Order creation failed";
    }

    @RequestMapping("/createOrderByEvenDate")
    public String createOrderByEvenDate(@RequestParam("id")Integer id,@RequestParam("service_id")Integer service_id,@RequestParam("date_received")Integer date_received)
    {
        try{
            CustomerService customerService = customerServiceService.getById(id);
            //get date time now
            Calendar calendar = Calendar.getInstance();
            Date date = calendar.getTime();
            //get calender by service
            List<Calender> listCalen = calenderService.listCalenByServiceStatus(customerService.getServices().getService_id(),1);
            if (listCalen.size()<=0)
            {
                return "redirect:/admin/service/customerService?error=Order creation failed";
            }
            Calender calender = listCalen.get(0);
            //calculate date received_date
            Calendar calenderCustSer = Calendar.getInstance();
            calenderCustSer.setTime(calender.getSolar_calendar());
            int dayReceived = calenderCustSer.get(Calendar.DAY_OF_MONTH)-customerService.getReceived_date();
            calenderCustSer.set(Calendar.DAY_OF_MONTH,dayReceived);
            Date dateCustSer = calenderCustSer.getTime();
            Date dateSolarCalen = calender.getSolar_calendar();
            List<CustomerServiceProduct> customerServiceProductList = customerSPService.getByCustomerServiceStatus(customerService.getCustomer_service_id(),1);
            if (calendar.get(Calendar.DAY_OF_MONTH)==calenderCustSer.get(Calendar.DAY_OF_MONTH))
            {
                try{
                    createOrderByCustomerService(customerService,customerServiceProductList,dateCustSer,dateSolarCalen);
                    return "redirect:/admin/service/searchServiceReceived?service_id="+service_id+"&date_received="+date_received+"&success=Order created successfully";
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            if(calendar.get(Calendar.DAY_OF_MONTH)!=calenderCustSer.get(Calendar.DAY_OF_MONTH))
            {
                createOrderByCustomerService(customerService,customerServiceProductList,dateCustSer,dateSolarCalen);
//            return "redirect:/admin/service/customerService?error=Unable to create order due to incorrect delivery date";
                return "redirect:/admin/service/searchServiceReceived?service_id="+service_id+"&date_received="+date_received+"&success=Order created successfully";
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return "redirect:/admin/service/searchServiceReceived?service_id="+service_id+"&date_received="+date_received+"&error=Order creation failed";
    }
    @RequestMapping(value = "/checkOrderToday")
    public String checkOrderToday(Model model)
    {
        updateCalenderAuto();
        List<CustomerService> listCs = customerServiceService.getAllCSByShow();
        List<CustomerService> listNew = new ArrayList<>();
        //get date time now
        Calendar calendar = Calendar.getInstance();
        try {
        for (CustomerService customerService: listCs) {
            List<Calender> listCalender = calenderService.listCalenByServiceStatus(customerService.getServices().getService_id(),1);
            if (listCalender.size()>0){
                Calender calender = listCalender.get(0);
                Calendar calenderCustSer = Calendar.getInstance();
                calenderCustSer.setTime(calender.getSolar_calendar());

                int dayReceived = calenderCustSer.get(Calendar.DAY_OF_MONTH)-customerService.getReceived_date();
                calenderCustSer.set(Calendar.DAY_OF_MONTH,dayReceived);
                Date dateCustSer = calenderCustSer.getTime();
                if (calendar.get(Calendar.DAY_OF_MONTH)==calenderCustSer.get(Calendar.DAY_OF_MONTH))
                {
                    if (customerSOService.checkCustomerSO(customerService.getCustomer_service_id(),dateCustSer)==false)
                    {
                        listNew.add(customerService);
                    }
                }
            }
        }
        model.addAttribute("list",listNew);
        }catch (Exception e)
        {

        }
        return "admin/ajax/createdOrder";
    }

    @RequestMapping("/checkOrderCS")
    public String showOrderByCS(@RequestParam("id")Integer id,Model model)
    {
        List<CustomerServiceOrder> list = customerSOService.getAllByCustomerService(id);
        model.addAttribute("list",list);
        return "admin/ajax/showOrderCS";
    }

    @RequestMapping("/updateStatusCS")
    public String updateStatusCustomerService()
    {
        try{
            List<CustomerService> list = customerServiceService.getAllCSByShow();
            for (CustomerService customerService: list) {
                if (customerService.getServices().getStatus()!=1)
                {
                    customerServiceService.canceledService(customerService);
                }
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return "redirect:/admin/service/customerService?success=Update successfully";
    }

    @RequestMapping("/activeService")
    public String activeStatusService(@RequestParam("id")Integer id)
    {
        try {
            CustomerService customerService = customerServiceService.getById(id);
            boolean bl = customerServiceService.saveCs(customerService);
            if (bl)
            {
                orderController.templatesMail(customerService);
                return "redirect:/admin/service/customerService?success=Active service successfully";
            }
            return "redirect:/admin/service/customerService?error=Active service failed";
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return "redirect:/admin/service/customerService?error=Active service failed";
    }

    @RequestMapping("/detailCustomerService")
    public String detailCustomerService(@RequestParam("id")Integer id,Model model,HttpServletRequest request,HttpSession session)
    {
        updateCalenderAuto();
        CustomerService customerService = customerServiceService.getById(id);
        List<CustomerServiceProduct> customerServiceProductList = customerSPService.getByCustomerService(id);
        List<CustomerServiceOrder> customerServiceOrderList = customerSOService.getAllByCustomerService(id);
        List<Calender> calenderList = calenderService.listCalenderByService(customerService.getServices().getService_id());
        List<Calender> listCalen = calenderService.listCalenByServiceStatus(customerService.getServices().getService_id(),1);
        List<CalenderOrder> calenderOrderList = new ArrayList<>();
        Calender calenderActive = null;
        try{
            calenderActive = listCalen.get(0);
        }catch (Exception e)
        {
           // e.printStackTrace();
        }
            for (Calender calender: calenderList) {
                CalenderOrder calenderOrder = new CalenderOrder();
                calenderOrder.setCalender(calender);
                calenderOrder.setStatus(2);
                calenderOrder.setActive(2);
                calenderOrder.setCustomerServiceOrder(null);
                for (CustomerServiceOrder customerServiceOrder:customerServiceOrderList) {
                    if (customerServiceOrder.getReceived_date().equals(calender.getSolar_calendar()))
                    {
                        calenderOrder.setStatus(1);
                        calenderOrder.setCustomerServiceOrder(customerServiceOrder);
                    }
                }
                if (calenderActive!=null)
                {
                    if (calender.getSolar_calendar().equals(calenderActive.getSolar_calendar()))
                    {
                        calenderOrder.setActive(1);
                    }
                }
                calenderOrderList.add(calenderOrder);
            }

        double total=0;
        for (CustomerServiceProduct customerServiceProduct: customerServiceProductList) {
            total +=customerServiceProduct.getProduct().getPrice() * customerServiceProduct.getQuantity();
        }

        model.addAttribute("totalMoney",total);
        model.addAttribute("calenderOrderList",calenderOrderList);
        model.addAttribute("customerServiceOrderList",customerServiceOrderList);
        model.addAttribute("customerServiceProductList",customerServiceProductList);
        model.addAttribute("customerService",customerService);
        serviceFEController.checkListCalen(listCalen,model);

        return "admin/services/customerServiceDetail";
    }

    @RequestMapping("/searchServiceReceived")
    public String searchServiceReceived(@RequestParam("service_id")Integer service_id,@RequestParam("date_received")Integer date_received,Model model)
    {
        return searchServiceReceivedPaginated(1,model,service_id,date_received);
    }

    @RequestMapping("/searchServiceReceived/page/{pageNo}")
    public String searchServiceReceivedPaginated(@PathVariable(value = "pageNo")int pageNo,Model model,@RequestParam("service_id")Integer service_id,@RequestParam("date_received")Integer date_received)
    {
        int pageSize = 10;
        updateCalenderAuto();
        //Calender calender = null;
        //List<CustomerServiceOrder> customerServiceOrderList = null;
        if (service_id==0)
        {
            return "redirect:/admin/service/customerService";
        }
        if (date_received==4)
        {
            Page<CustomerService> page = customerServiceService.findByCSAll(service_id,pageNo,pageSize);
            List<CustomerService> listCustService = page.getContent();
            model.addAttribute("totalPages",page.getTotalPages());
            model.addAttribute("totalItems",page.getTotalElements());
            model.addAttribute("list",listCustService);
        }else{
            Page<CustomerService> page = customerServiceService.findByCSReceivedDate(service_id,date_received,pageNo,pageSize);
            List<CustomerService> listCustService = page.getContent();
            model.addAttribute("totalPages",page.getTotalPages());
            model.addAttribute("totalItems",page.getTotalElements());
            model.addAttribute("list",listCustService);
        }
        Calender calender = new Calender();
        List<Calender> listCalender = calenderService.listCalenByServiceStatus(service_id,1);
        if (listCalender!=null)
        {
            calender = listCalender.get(0);
        }
        List<CustomerServiceOrder> customerServiceOrderList = customerSOService.getCSODByReceivedDate(calender.getSolar_calendar());
        getListService(model);
        model.addAttribute("currentPage",pageNo);
        model.addAttribute("service_id",service_id);
        model.addAttribute("date_received",date_received);
        model.addAttribute("calender",calender);
        model.addAttribute("customerServiceOrderList",customerServiceOrderList);
        countStatusService(model);

        List<CustomerService> customerServiceList = customerServiceService.getListByCSReceivedDate(service_id);
        List<CustomerService> customerServiceListNoOrder = new ArrayList<>();
        for (CustomerService customerService:customerServiceList) {
            if (customerSOService.checkCustomerSOReceivedDate(customerService.getCustomer_service_id(),calender.getSolar_calendar())==false)
            {
                customerServiceListNoOrder.add(customerService);
            }
        }
        model.addAttribute("customerServiceListNoOrder",customerServiceListNoOrder);
        return "admin/services/customerServiceReceived";
    }

    @RequestMapping("/createOrderByService")
    public String createOrderByService(@RequestParam("service_id")Integer service_id,@RequestParam("upcoming_date")String upcoming_date,Model model) throws ParseException {
        try{
            List<CustomerService> customerServiceList = customerServiceService.getListByCSReceivedDate(service_id);
            Date date_upcoming = new SimpleDateFormat("yyyy-MM-dd").parse(upcoming_date);
            for (CustomerService customerService:customerServiceList) {
                if (customerSOService.checkCustomerSOReceivedDate(customerService.getCustomer_service_id(),date_upcoming)==false)
                {
                    List<CustomerServiceProduct> customerServiceProductList = customerSPService.getByCustomerServiceStatus(customerService.getCustomer_service_id(),1);
                    Calendar calenderCustSer = Calendar.getInstance();
                    calenderCustSer.setTime(date_upcoming);
                    int dayReceived = calenderCustSer.get(Calendar.DAY_OF_MONTH)-customerService.getReceived_date();
                    calenderCustSer.set(Calendar.DAY_OF_MONTH,dayReceived);
                    Date dateCustSer = calenderCustSer.getTime();
                    createOrderByCustomerService(customerService,customerServiceProductList,dateCustSer,date_upcoming);
                }
            }
            return "redirect:/admin/service/searchServiceReceived?service_id="+service_id+"&date_received=4&success=Order created successfully";
        }catch (Exception e) { e.printStackTrace(); }
        return "redirect:/admin/service/searchServiceReceived?service_id="+service_id+"&date_received=4&error=Order creation failed";
    }
    
    public boolean checkServiceName(String service_name,int service_id)
    {
        Services services = servicesService.getService(service_id);
        boolean checkServiceName = servicesService.checkServiceName(service_name);
        if (checkServiceName==false)
        {
            if (service_name.equals(services.getService_name()))
            {
                return true;
            }else{
                return false;
            }
        }
        return true;
    }

    public double sumAmountService(List<CustomerServiceProduct> list)
    {
        double sum = 0;
        for (CustomerServiceProduct customerServiceProduct:list)
        {
            sum += customerServiceProduct.getQuantity()*customerServiceProduct.getProduct().getPrice();
        }
        return sum;
    }

    public void createOrderByCustomerService(CustomerService customerService,List<CustomerServiceProduct> customerServiceProductList,Date dateCustSer,Date dateSolarCalen)
    {
        Orders orders = new Orders();
        orders.setReceived_date(dateCustSer);
        orders.setStatus(1);
        orders.setPayment_status(0);
        orders.setCustomer(new Customer(customerService.getCustomer().getCustomer_id()));
        orders.setFullname(customerService.getCustomer().getUsername());
        orders.setOrder_email(customerService.getCustomer().getEmail());
        orders.setOrder_address(customerService.getReceived_address());
        orders.setPhone_number(customerService.getReceived_phone_number());
        orders.setTotal_price(sumAmountService(customerServiceProductList));
        orders.setOrder_note(customerService.getNote());
        orders.setPayment("payment delivery");
        Orders ordersSave = orderService.saveOrder(orders);
        for (CustomerServiceProduct customerServiceProduct: customerServiceProductList) {
            Order_detail order_detail = new Order_detail();
            order_detail.setOrder(ordersSave);
            order_detail.setProduct(new Product(customerServiceProduct.getProduct().getProduct_id()));
            order_detail.setQuantity(customerServiceProduct.getQuantity());
            order_detail.setPrice(customerServiceProduct.getProduct().getPrice());
            orderDetailService.saveOrderDetail(order_detail);
        }
        CustomerServiceOrder customerServiceOrder = new CustomerServiceOrder();
        customerServiceOrder.setCustomerService(customerService);
        customerServiceOrder.setOrders(ordersSave);
        customerServiceOrder.setReceived_date(dateSolarCalen);
        customerServiceOrder.setCreated(dateCustSer);
        customerSOService.saveCustomerSO(customerServiceOrder);
    }

    public void updateCalenderAuto()
    {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH)-1;
        calendar.set(Calendar.DAY_OF_MONTH,day);
        Date dateNow = calendar.getTime();
        List<Calender> listCalender = calenderService.getAllCalenBystatus(1);
        for (Calender calender: listCalender) {
            if (dateNow.after(calender.getSolar_calendar()))
            {
                calenderService.hiddenCalender(calender.getCalender_id());
            }
        }
    }

    public void getListService(Model model)
    {
        List<Services> listService = servicesService.listService();
        model.addAttribute("listService",listService);
    }
    public String endedService(int id)
    {
        Services services = servicesService.getService(id);
        services.setStatus(2);
        servicesService.updateService(services);
        return "redirect:/admin/service/detailService?id="+id+"&&success=Service has ended";
    }

    public void saveHistoryUrl(HttpServletRequest request, HttpSession session)
    {
        String referer = request.getHeader("Referer");
        session.setAttribute("historyAdmin_url",referer);
    }

    private void countStatusService(Model model)
    {
        List<CustomerService> listAll = customerServiceService.getAllCustomerService();
        List<CustomerService> listActive = customerServiceService.getListByStatus(1);
        List<CustomerService> listNoneActive = customerServiceService.getListByStatus(2);
        List<CustomerService> listWaiting = customerServiceService.getListByStatus(0);
        model.addAttribute("countAll",listAll.size());
        model.addAttribute("countActive",listActive.size());
        model.addAttribute("countNoneActive",listNoneActive.size());
        model.addAttribute("countWaiting",listWaiting.size());
    }


}
