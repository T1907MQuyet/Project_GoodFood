package tomato_th.project_tomato.controller.frontend;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import tomato_th.project_tomato.config.paypal.PaypalPaymentIntent;
import tomato_th.project_tomato.config.paypal.PaypalPaymentMethod;
import tomato_th.project_tomato.model.*;
import tomato_th.project_tomato.model.core.*;
import tomato_th.project_tomato.model.service.Services;
import tomato_th.project_tomato.service.*;
import tomato_th.project_tomato.service.NewsService;
import tomato_th.project_tomato.utils.Utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping(path = "/")
public class FrontendController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryDetailService categoryDetailService;
    @Autowired
    private ProductService productService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private MenuDetailService menuDetailService;
    @Autowired
    private ProductMenuDetailService productMenuDetailService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private CustomerServiceService customerServiceService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private NewsService newsService;
    @Autowired
    private PaypalService paypalService;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private BannersService bannersService;
    @Autowired
    private ServicesService servicesService;

    public static final String URL_PAYPAL_SUCCESS = "food/pay/success";
    public static final String URL_PAYPAL_CANCEL = "food/pay/cancel";
    private Logger log = LoggerFactory.getLogger(getClass());

    @InitBinder
    public void InitBinder(WebDataBinder data) {
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        data.registerCustomEditor(Date.class, new CustomDateEditor(s, true));
    }

    @RequestMapping(path = "")
    public String index(Model model)
    {
        List<News> listNewsPromotions = newsService.listNewsByTypeActiveHome(1);
        List<News> listNews = newsService.listNewsByActiveHome(3);
        Banners bannersTop = bannersService.getBannersByBannerName("Banner Top Trang Chủ");
        model.addAttribute("listNewsPromotions",listNewsPromotions);
        model.addAttribute("listNews",listNews);
        model.addAttribute("BannerTop",bannersTop);
        menu(model);
        List<Services> servicesList = servicesService.listServiceByStatus(1);
        model.addAttribute("customerServiceList",servicesList);
        return "frontend/home";
    }

    @RequestMapping(path = "food/getProByCate")
    public String getProByCate(@RequestParam("cateDetail_id")Integer cateDetail_id, Model model)
    {
        List<Product> listPro = productService.lisProByCateDetail(cateDetail_id);
        model.addAttribute("listPro",listPro);
        Category_detail category_detail = categoryDetailService.getCateDetailById(cateDetail_id);
        model.addAttribute("category_detail",category_detail);
        menu(model);
        return "frontend/productByCate";
    }

    @RequestMapping(path = "food/productDetail")
    public String getProductByDetail(@RequestParam("proId")Integer product_id, Model model)
    {
        menu(model);
        Product product = productService.getProById(product_id);
        model.addAttribute("product",product);

        List<Product> lisProPriority = productService.listProByPriority();
        model.addAttribute("listProPriority",lisProPriority);
        return "frontend/productDetail";
    }

    @RequestMapping(path = "food/menu")
    public String menuPro(Model model)
    {
        return findAllPaginated(1,model);
    }

    @RequestMapping(path = "food/menuProInMenu")
    public String getProByMenu(@RequestParam("menuDetailId")Integer menuDetaiId, Model model)
    {
        return findPaginated(1,model,menuDetaiId);
    }

    @RequestMapping("food/menuProInMenu/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model, @RequestParam("menuDetailId")Integer menuDetaiId) {
        int pageSize = 9;
        Page<Product_menu_detail> page = productMenuDetailService.findPaginated(pageNo,pageSize,menuDetaiId);
        List<Product_menu_detail> listProMenuDetail = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listProMenuDetail", listProMenuDetail);
        model.addAttribute("menuDetailId",menuDetaiId);
        menu(model);
        return "frontend/productByMenuId";
    }

    @RequestMapping(value = "food/menu/page/{pageNo}")
    public String findAllPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
        int pageSize = 9;
        Page<Product_menu_detail> page = productMenuDetailService.findAllPaginated(pageNo,pageSize);
        List<Product_menu_detail> listProMenuDetail = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listProMenuDetail", listProMenuDetail);
        menu(model);
        return "frontend/productMenu";
    }

    @RequestMapping(value = "food/login")
    public String loginFood(Model model)
    {
        menu(model);
        return "frontend/login";
    }

    @RequestMapping(value = "food/login",method = RequestMethod.POST)
    public String loginFoodPost(@RequestParam("email")String email, @RequestParam("password")String password, HttpSession session, Model model,HttpServletRequest request)
    {
        String referer;
        if (session.getAttribute("histrory_url")!=null)
        {
              referer = (String) session.getAttribute("histrory_url");
              session.removeAttribute("histrory_url");
        }
        else{
             referer = "/";
        }
        if (email!="") {
            try {
                Customer customer = customerService.findByEmail(email);
                boolean bl = passwordEncoder.matches(password,customer.getPassword());
                if (bl)
                {
                    session.setAttribute("loginCustomer",customer);

                    return "redirect:"+referer+"?success=Login Success";
                }
            } catch (Exception e) { }
        }
        menu(model);
        return "redirect:/food/login?error=Login Failed";
    }

    @RequestMapping(value = "food/register")
    public String registerFrom(Model model)
    {
        menu(model);
        Customer customer = new Customer();
        model.addAttribute("cusNew",customer);
        return "frontend/register";
    }
    @RequestMapping(value = "food/register",method = RequestMethod.POST)
    public String saveRegister(@ModelAttribute("cusNew")@Valid Customer customer, BindingResult result, Model model, @RequestParam("password_vf")String pwvf)
    {
        if (result.hasErrors())
        {
            model.addAttribute("cusNew",customer);
            return "frontend/register";
        }
        if (pwvf.equals(customer.getPassword()))
        {

        }else{
            return "redirect:/food/register?errorPass=passwords are not the same";
        }
        boolean checkMail = checkEmail(customer.getEmail());
        if (checkMail==false)
        {
            return "redirect:/food/register?errorEmail=Email already exists";
        }
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customerService.saveCustomer(customer);
        return "redirect:/food/login?success=register Success";
    }

    @RequestMapping(path = "food/logout")
    public String logout(HttpSession session)
    {
        session.removeAttribute("loginCustomer");
        return "redirect:/?success=Logout Success";
    }

    @RequestMapping(path = "food/checkout")
    public String checkout(Model model,HttpSession session,HttpServletRequest request)
    {
        menu(model);
       boolean checkLg = checkLogin(request,session);
       if (checkLg==false)
       {
           return "redirect:/food/login";
       }
       getListCartInCheckout(session,model);
        Orders orders = new Orders();
        model.addAttribute("orderNew",orders);
        return  "frontend/checkout";
    }

    @RequestMapping(path = "food/checkout",method = RequestMethod.POST)
    public String checkoutPost(@ModelAttribute("orderNew")@Valid Orders orders, BindingResult result, Model model, HttpSession session, HttpServletRequest request,@RequestParam("date_received")String date_received) throws ParseException {

        LocalDate l = new LocalDate();
        LocalDate ld_received = LocalDate.parse(date_received);
        if (l.isAfter(ld_received))
        {
            return "redirect:/?error=Received date time is correct";
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(date_received);
        orders.setReceived_date(date);
        if (result.hasErrors())
        {
            menu(model);
            model.addAttribute("orderNew",orders);
            return  "frontend/checkout";
        }
        List<Cart> listCart = (List<Cart>) session.getAttribute("listCart");
        Orders ordersNew = orderService.saveOrder(orders);
        if (listCart != null)
        {
            for (int i=0;i<listCart.size();i++)
            {
                Order_detail order_detail = new Order_detail();
                order_detail.setOrder(ordersNew);
                order_detail.setProduct(new Product(listCart.get(i).getProduct().getProduct_id()));
                order_detail.setQuantity(listCart.get(i).getQuantity());
                order_detail.setPrice(listCart.get(i).getProduct().getPrice()-(listCart.get(i).getProduct().getPrice()*listCart.get(i).getProduct().getDiscount())/100);
                orderDetailService.saveOrderDetail(order_detail);
            }
            session.removeAttribute("listCart");
        }
        if (ordersNew.getPayment().equals("online payment"))
        {
            String cancelUrl = Utils.getBaseURL(request) + "/" + URL_PAYPAL_CANCEL;
            String successUrl = Utils.getBaseURL(request) + "/" + URL_PAYPAL_SUCCESS+"?orderId="+ordersNew.getOrderId();

            orderService.updateOrderStatus(ordersNew.getOrderId(),5);
            try{
                Payment payment = paypalService.createPayment(orders.getTotal_price(),"USD",
                        PaypalPaymentMethod.paypal, PaypalPaymentIntent.sale,"Payment order id: "+ordersNew.getOrderId(),cancelUrl,successUrl);
                for(Links links : payment.getLinks()){
                    if(links.getRel().equals("approval_url")){
                        return "redirect:" + links.getHref();
                    }
                }
            }catch (PayPalRESTException e)
            {
                log.error(e.getMessage());
            }

            return "redirect:/?error=payment fail";
        }

        return "redirect:/?success=successful purchase";
    }

    @GetMapping(URL_PAYPAL_CANCEL)
    public String cancelPay(){
        return "redirect:/?error=payment fail";
    }

    @GetMapping(URL_PAYPAL_SUCCESS)
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId,@RequestParam("orderId")Integer order_id){
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            if(payment.getState().equals("approved")){
                Orders orderUpdate = orderService.getOrderById(order_id);
                orderUpdate.setPayment_status(1);
                orderService.saveOrder(orderUpdate);
                return "redirect:/?success=payment successful purchase";
            }
        } catch (PayPalRESTException e) {
            log.error(e.getMessage());
        }
        return "redirect:/";
    }

    @RequestMapping(path = "food/account")
    public String infoAccount(@RequestParam("customer_id")Integer cust_id, Model model,HttpSession session,HttpServletRequest request)
    {
        boolean checkLg = checkLogin(request,session);
        if (checkLg==false)
        {
            return "redirect:/food/login";
        }
        boolean checkCustomerId = checkCustomer(session,cust_id);
        if (checkCustomerId==false)
        {
            return "frontend/404";
        }

        return findPagiOrder(1,model,cust_id,session,request);
    }

    @RequestMapping("food/account/page/{pageNo}")
    public String findPagiOrder(@PathVariable(value = "pageNo") int pageNo, Model model, @RequestParam("customer_id")Integer customer_id,HttpSession session,HttpServletRequest request) {
        int pageSize = 15;
        boolean checkLg = checkLogin(request,session);
        if (checkLg==false)
        {
            return "redirect:/food/login";
        }
        boolean checkCustomerId = checkCustomer(session,customer_id);
        if (checkCustomerId==false)
        {
            return "frontend/404";
        }
        Customer customer = customerService.getCustomerById(customer_id);
        Page<Orders> page = orderService.findPagiCustomer(pageNo,pageSize,customer_id);
        List<Orders> listOrder = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listOrder", listOrder);
        model.addAttribute("customer_id",customer_id);
        model.addAttribute("customer",customer);
        menu(model);
        List<Services> servicesList = servicesService.listServiceShow();
        model.addAttribute("servicesList",servicesList);
        return "frontend/account";
    }
    @RequestMapping(path = "/food/account/orderStatus")
    public String accountOrderByStatus(@RequestParam("customer_id")Integer cust_id,@RequestParam("status")Integer status,Model model,HttpSession session,HttpServletRequest request)
    {

        return accountOrderByStatusPaginated(1,model,cust_id,status,session,request);
    }

    @RequestMapping("/food/account/orderStatus/page/{pageNo}")
    public String accountOrderByStatusPaginated(@PathVariable(value = "pageNo")int pageNo,Model model,@RequestParam("customer_id")Integer cust_id,@RequestParam("status")Integer status,HttpSession session,HttpServletRequest request)
    {
        int pageSize = 15;
        boolean checkLg = checkLogin(request,session);
        if (checkLg==false)
        {
            return "redirect:/food/login";
        }
        boolean checkCustomerId = checkCustomer(session,cust_id);
        if (checkCustomerId==false)
        {
            return "frontend/404";
        }
        Customer customer = customerService.getCustomerById(cust_id);
        Page<Orders> page = orderService.findPaginatedOdByStatusCustomer(cust_id,status,pageNo,pageSize);
        List<Orders> listOrder = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listOrder", listOrder);
        model.addAttribute("customer",customer);
        model.addAttribute("customer_id",cust_id);
        model.addAttribute("status",status);
        menu(model);
        List<Services> servicesList = servicesService.listServiceShow();
        model.addAttribute("servicesList",servicesList);
        return "frontend/accountOrderStatus";
    }

    @RequestMapping("food/account/orderDetail")
    public String getOrderDetailByAc(@RequestParam("order_id")Integer order_id, Model model)
    {
        Orders orders = orderService.getOrderById(order_id);
        List<Order_detail> list = orderDetailService.getAllByOrderId(order_id);
        model.addAttribute("listOrderDetail",list);
        model.addAttribute("order_id",order_id);
        model.addAttribute("status",orders.getStatus());
        model.addAttribute("payment",orders.getPayment());
        model.addAttribute("payment_status",orders.getPayment_status());
        model.addAttribute("orders",orders);
        return "frontend/ajax/orderDetail";
    }

    //huy don
    @RequestMapping("food/canceledOrder")
    public String canceldOrder(@RequestParam("order_id")Integer order_id, @RequestParam("status")Integer status)
    {
        Orders orders = orderService.getOrderById(order_id);
        boolean bl = orderService.canceledOrder(order_id,status);
        if (bl)
        {
            return "redirect:/food/account?customer_id="+orders.getCustomer().getCustomer_id()+"&&success=The order was successfully canceled";
        }

        return "redirect:/food/account?customer_id="+orders.getCustomer().getCustomer_id()+"&&error=Unsuccessfully canceled orders";
    }

    public void menu(Model model)
    {
        List<Category> listCate = categoryService.listCateByStatus(1);
        List<Category_detail> listCateDetail = categoryDetailService.listCateDetailStatus(1);
        model.addAttribute("listCate",listCate);
        model.addAttribute("listCateDetail",listCateDetail);
        List<Menu> listMenu = menuService.listMenuByStatus(1);
        List<Menu_detail> listMenuDetail = menuDetailService.listMenuDerailByStatus(1);
        model.addAttribute("listMenu",listMenu);
        model.addAttribute("listMenuDetail",listMenuDetail);
    }

    public boolean checkEmail(String email)
    {
        Customer customer = customerService.findByEmail(email);
        if (customer==null)
        {
            return true;
        }
        return false;
    }

    //login admin
    @RequestMapping(path = "/login")
    public String loginAdmin()
    {
        boolean bl = checkUser();
        if (bl==false)
        {
            createUserAdmin();
        }

        return "admin/user/login";
    }

    public boolean checkUser()
    {
        String userEmail = "admin@localhost.com";
        List<User> list = userService.getAllUser();
        for (User us: list) {
            if (us.getEmail().equals(userEmail))
            {
                return true;
            }
        }
        return false;
    }

    public void createUserAdmin()
    {
        Role role = new Role();
        role.setId(1);
        role.setName("USER");
        Role roleComplete =   roleService.insertRole(role);
        Role role2 = new Role();
        role2.setId(2);
        role2.setName("ADMIN");
        Role role2Complete = roleService.insertRole(role2);

        User userAdmin = new User();
        userAdmin.setUsername("adminadmin");
        userAdmin.setEmail("admin@localhost.com");
        userAdmin.setPassword("123456789");
        User userAdminComplete = userService.saveUser(userAdmin);

        User_roles ur = new User_roles();
        ur.setRoleId(roleComplete);
        ur.setUserId(userAdminComplete);
        userRoleService.saveRole(ur);
        User_roles ur2 = new User_roles();
        ur2.setRoleId(role2Complete);
        ur2.setUserId(userAdminComplete);
        userRoleService.saveRole(ur2);
    }

    //feedback
    @RequestMapping(path = "food/feedback")
    public String showFeedback(Model model)
    {
        menu(model);
        Feedbacks feedback = new Feedbacks();
        model.addAttribute("feedback",feedback);
        return "frontend/feedback";
    }

    @RequestMapping(path = "food/saveFeedback",method = RequestMethod.POST)
    public String saveFeedback(@ModelAttribute("feedback")@Valid Feedbacks feedbacks,BindingResult result,Model model)
    {
        if (result.hasErrors())
        {
            return "frontend/feedback";
        }
        boolean check = feedbackService.save(feedbacks);
        if (check)
        {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("Tomanto@shop");
            message.setTo("trungpvth1807021@fpt.edu.vn");

            String mailSubject = feedbacks.getFullname()+"has sent a massage";
            String mailContent = "Sender Name: "+feedbacks.getFullname()+"\n";
            mailContent += "Sender Email: "+feedbacks.getEmail()+"\n";
            mailContent += "Phone: "+feedbacks.getPhonenumber()+"\n";
            mailContent += "Content: "+feedbacks.getContent()+"\n";

            message.setSubject(mailSubject);
            message.setText(mailContent);
            mailSender.send(message);
            return "redirect:/?success=successful feedback";
        }
        return "redirect:/?error=feedback fail";
    }
    @RequestMapping(path = "/food/canceledService")
    public String canceledCustomerService(@RequestParam("customer_service_id")Integer customer_service_id)
    {
        tomato_th.project_tomato.model.service.CustomerService customerService = customerServiceService.getById(customer_service_id);

        try{
            boolean bl = customerServiceService.canceledService(customerService);
            if (bl)
            {
                return "redirect:/food/service/accountService?customer_id="+customerService.getCustomer().getCustomer_id()+"&&success=Successful canceled service";
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return "redirect:/food/service/accountService?customer_id="+customerService.getCustomer().getCustomer_id()+"&&error=Failed canceled service";
    }

    public boolean checkLogin(HttpServletRequest request,HttpSession session)
    {
        if (session.getAttribute("loginCustomer")==null)
        {
            String referer = request.getHeader("Referer");
            session.setAttribute("histrory_url",referer);
            return false;
        }else{
            return true;
        }
    }

    public boolean checkCustomer(HttpSession session,int custId)
    {
        try{
            if(session.getAttribute("loginCustomer")==null)
            {
                return false;
            }
            Customer customer = (Customer) session.getAttribute("loginCustomer");
            if (customer.getCustomer_id()==custId)
            {
                return true;
            }
        }catch (Exception e){}
        return false;
    }

    //get cart
    public void getListCartInCheckout(HttpSession session,Model model)
    {
        List<Cart> listCart = (List<Cart>) session.getAttribute("listCart");
        model.addAttribute("listCart",listCart);
    }
}
