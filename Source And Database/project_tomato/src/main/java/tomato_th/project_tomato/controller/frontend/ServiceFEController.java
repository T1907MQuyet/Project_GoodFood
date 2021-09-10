package tomato_th.project_tomato.controller.frontend;


import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import tomato_th.project_tomato.model.Cart;
import tomato_th.project_tomato.model.Customer;
import tomato_th.project_tomato.model.core.Orders;
import tomato_th.project_tomato.model.core.Product;
import tomato_th.project_tomato.model.service.*;
import tomato_th.project_tomato.model.service.CustomerService;
import tomato_th.project_tomato.service.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(path = "/food/service")
public class ServiceFEController {
    @Autowired
    private ServicesService servicesService;
    @Autowired
    private FrontendController frontendController;
    @Autowired
    private CustomerServiceService customerServiceService;
    @Autowired
    private CustomerSPService customerSPService;
    @Autowired
    private CalenderService calenderService;
    @Autowired
    private tomato_th.project_tomato.service.CustomerService customerService;
    @Autowired
    private CustomerSOService customerSOService;
    @Autowired
    private OrderService orderService;

    @InitBinder
    public void InitBinder(WebDataBinder data) {
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        data.registerCustomEditor(Date.class, new CustomDateEditor(s, true));
    }

    @RequestMapping(path = "")
    public String checkoutService(@RequestParam("service")Integer service_id, Model model, HttpServletRequest request,HttpSession session)
    {
        try {
            if (service_id==0)
            {
                return "redirect:/food/checkout";
            }
           boolean checkLg = frontendController.checkLogin(request,session);
            if (checkLg==false)
            {
                return "redirect:/food/login";
            }
            frontendController.menu(model);
            CustomerService customerService = new CustomerService();
            Services services = servicesService.getService(service_id);
            if (services==null)
            {
                return "redirect:/?error=Service failed";
            }
            List<Calender> listCalender = calenderService.listCalenByServiceStatus(service_id,1);
            model.addAttribute("service",services);
            model.addAttribute("customerService",customerService);
            model.addAttribute("listCalender",listCalender);
            return "frontend/service/checkoutService";
        }catch (Exception e)
        {
            e.printStackTrace();
        }
       return "redirect:/?error=Service failed";
    }

    @RequestMapping(path = "/serviceInfo")
    public String serviceInformation(@RequestParam("service_id")Integer service_id,Model model)
    {
        Services services = servicesService.getService(service_id);
        List<Calender> listCalender = calenderService.listCalenByServiceStatus(service_id,1);
        model.addAttribute("service",services);
        model.addAttribute("listCalender",listCalender);
        return "frontend/ajax/serviceInfo";
    }

    @RequestMapping(path = "/buttonRegisterService")
    public String buttonRegister(@RequestParam("service_id")Integer service_id,Model model)
    {
        model.addAttribute("service_id",service_id);
        return "frontend/ajax/buttonService";
    }

    @RequestMapping(path = "",method = RequestMethod.POST)
    public String saveCheckoutService(@ModelAttribute("customerService")@Valid CustomerService customerService, BindingResult result, Model model, HttpSession session)
    {
        if (result.hasErrors())
        {
            Services services = servicesService.getService(customerService.getServices().getService_id());
            model.addAttribute("service",services);
            model.addAttribute("customerService",customerService);
            return "frontend/service/checkoutService";
        }
        try{
            List<Cart> listCart = (List<Cart>) session.getAttribute("listCart");
            CustomerService customerS = customerServiceService.saveCs2(customerService);

            if (listCart!= null)
            {
                for (int i=0; i<listCart.size();i++)
                {
                    CustomerServiceProduct customerServiceProduct = new CustomerServiceProduct();
                    customerServiceProduct.setCustomerService(customerS);
                    customerServiceProduct.setProduct(new Product(listCart.get(i).getProduct().getProduct_id()));
                    customerServiceProduct.setQuantity(listCart.get(i).getQuantity());
                    customerServiceProduct.setStatus(1);
                    customerSPService.saveCustomerSPro(customerServiceProduct);
                }
                session.removeAttribute("listCart");

            }
            return "redirect:/food/service/accountService?customer_id="+customerService.getCustomer().getCustomer_id()+"&success=Service booking successful";
        }catch (Exception e)
        {
            e.printStackTrace();
        }
       return "redirect:/?error=Service booking ";
    }

    @RequestMapping(path = "/serviceDetail")
    public String detailCustomerService(@RequestParam("id")Integer id,Model model)
    {
        try{
            CustomerService customerService = customerServiceService.getById(id);
            List<CustomerServiceProduct> listCSP = customerSPService.getByCustomerService(customerService.getCustomer_service_id());
            List<Calender> listCalen = calenderService.listCalenByServiceStatus(customerService.getServices().getService_id(),1);
            checkListCalen(listCalen,model);

            model.addAttribute("custService",customerService);
            model.addAttribute("listCSP",listCSP);
        }catch (Exception e)
        {

        }

        return "frontend/ajax/serviceDetail";
    }

    @RequestMapping(path = "/getCalender")
    public String getCalenderByServiceId(@RequestParam("service_id")Integer service_id,Model model)
    {
        List<Calender> list = calenderService.listCalenByServiceStatus(service_id,1);
        model.addAttribute("listCalender",list);
        return "frontend/ajax/getCalenderByService";
    }

    @RequestMapping(path = "/accountService")
    public String getAllAccountServiceCustomer(@RequestParam("customer_id")Integer customer_id,Model model)
    {
        return getAllAccountServiceCustomerPaginated(1,customer_id,model);
    }

    @RequestMapping(path = "/accountService/page/{pageNo}")
    public String getAllAccountServiceCustomerPaginated(@PathVariable("pageNo")Integer pageNo,@RequestParam("customer_id")Integer customer_id,Model model)
    {
        int pageSize = 15;
        Customer customer = customerService.getCustomerById(customer_id);
        Page<CustomerService> pageCust = customerServiceService.findByCustPaginated(pageNo,pageSize,customer_id);
        List<tomato_th.project_tomato.model.service.CustomerService> listCustService = pageCust.getContent();
        model.addAttribute("listCustService",listCustService);
        model.addAttribute("totalPages",pageCust.getTotalPages());
        model.addAttribute("customer_id",customer_id);
        model.addAttribute("customer",customer);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalItems", pageCust.getTotalElements());
        frontendController.menu(model);
        List<Services> servicesList = servicesService.listServiceShow();
        model.addAttribute("servicesList",servicesList);
        return "frontend/accountService";
    }

    @RequestMapping(path = "/orderByService")
    public String getListOrderByService(@RequestParam("service_id")Integer service_id,@RequestParam("customer_id")Integer customer_id,Model model,HttpSession session)
    {
        return getListOrderByServicePaginated(1,model,service_id,customer_id,session);
    }

    @RequestMapping(path = "/orderByService/page/{pageNo}")
    public String getListOrderByServicePaginated(@PathVariable("pageNo")Integer pageNo,Model model,@RequestParam("service_id")Integer service_id,@RequestParam("customer_id")Integer customer_id,HttpSession session)
    {
        int pageSize = 15;
        boolean checkCustomerId = frontendController.checkCustomer(session,customer_id);
        if (checkCustomerId==false)
        {
            return "frontend/404";
        }
        Customer customer = customerService.getCustomerById(customer_id);
        Page<Orders> page = orderService.findPaginatedOdByCustomerAndService(service_id,customer_id,pageNo,pageSize);
        List<Orders> listOrder = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listOrder", listOrder);
        model.addAttribute("customer",customer);
        model.addAttribute("customer_id",customer_id);
        frontendController.menu(model);
        List<Services> servicesList = servicesService.listServiceShow();
        model.addAttribute("servicesList",servicesList);
        model.addAttribute("service_id",service_id);
        return "frontend/accountOrderService";
    }

    @RequestMapping(path = "/showOrderCS")
    public String getListOrderByCS(@RequestParam("customer_service_id")Integer customer_service_id,Model model)
    {
        List<CustomerServiceOrder> list = customerSOService.getAllByCustomerService(customer_service_id);
        model.addAttribute("list",list);
        return "frontend/ajax/showOrderCustomerService";
    }

    public void checkListCalen(List<Calender> listCalen,Model model)
    {
        if (listCalen != null)
        {
            Calender calender = listCalen.get(0);
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String dateClen = dateFormat.format(calender.getSolar_calendar());
            model.addAttribute("calender",dateClen);
        }else
        {
            String calender = "Sự kiện đã kết thúc";
            model.addAttribute("calender",calender);
        }
    }


}
