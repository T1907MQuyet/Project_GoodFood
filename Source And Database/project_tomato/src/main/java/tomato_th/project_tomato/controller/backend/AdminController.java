package tomato_th.project_tomato.controller.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import tomato_th.project_tomato.model.Customer;
import tomato_th.project_tomato.model.Role;
import tomato_th.project_tomato.model.User;
import tomato_th.project_tomato.model.User_roles;
import tomato_th.project_tomato.model.core.Orders;
import tomato_th.project_tomato.model.core.Product;
import tomato_th.project_tomato.model.service.Services;
import tomato_th.project_tomato.service.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CustomerServiceService customerServiceService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ServicesService servicesService;

    @RequestMapping("")
    public String showIndex(Model model)
    {
        chartRevenue(model);
        countInfo(model);
        List<Services> servicesList = servicesService.listServiceByStatus(1);
        model.addAttribute("servicesList",servicesList);
        return "admin/home";
    }

    @RequestMapping("/roleUser")
    public String showRoleUser(Model model)
    {
        List<User_roles> list = userRoleService.getAllRole();
        User_roles user_roles = new User_roles();
        List<User> listUser = userService.getAllUser();
        List<Role> listRole = roleService.getAllRole();
        model.addAttribute("userRoleNew",user_roles);
        model.addAttribute("listUser",listUser);
        model.addAttribute("listRole",listRole);
        model.addAttribute("list",list);
        return "admin/userRole/listUserRole";
    }

    @RequestMapping("/revenueToDay")
    public String revenueToDay(@RequestParam("date_revenue")String date_revenue,Model model)
    {
        double totalRevenue = orderService.totalByDay(date_revenue);
        model.addAttribute("totalRevenue",totalRevenue);
        return "/admin/ajax/revenueToDay";
    }

    @RequestMapping(value = "/saveRoleUser",method = RequestMethod.POST)
    public String saveRoleUse(@ModelAttribute("userRoleNew")User_roles user_roles)
    {
        boolean bl = userRoleService.saveRole(user_roles);
        if (bl)
        {
            return "redirect:/admin/roleUser?success= Add authenticator success";
        }
        return "redirect:/admin/roleUser?error= Add authenticator failed";
    }

    @RequestMapping(value = "/deleteRoleUser")
    public String deleteUserRole(@RequestParam("id")Integer id)
    {
        User_roles user_roles = userRoleService.getRoleById(id);
        if (user_roles.getUserId().getEmail().equals("admin@localhost.com"))
        {
            return "redirect:/admin/roleUser?error";
        }
        boolean bl = userRoleService.deleteRole(id);
        if (bl)
        {
            return "redirect:/admin/roleUser?success= Delete authenticator success";
        }
        return "redirect:/admin/roleUser?error= Delete authenticator failed";
    }
    @RequestMapping("editRoleUser")
    public String editRoleUser(@RequestParam("id")Integer roleUserId, Model model)
    {
        User_roles userRoles = userRoleService.getRoleById(roleUserId);
        List<User> listUser = userService.getAllUser();
        List<Role> listRole = roleService.getAllRole();
        model.addAttribute("userRolesEdit",userRoles);
        model.addAttribute("listUser",listUser);
        model.addAttribute("listRole",listRole);
        return "admin/userRole/editUserRole";
    }
    @RequestMapping(path = "/updateRoleUser",method = RequestMethod.POST)
    public String updateRoleUser(@ModelAttribute("userRolesEdit")User_roles user_roles)
    {
        User_roles userRoles = userRoleService.getRoleById(user_roles.getId());
        if (userRoles.getUserId().getEmail().equals("admin@localhost.com"))
        {
            return "redirect:/admin/roleUser?error";
        }
        boolean bl = userRoleService.editRole(user_roles);
        if (bl)
        {
            return "redirect:/admin/roleUser?success";
        }else{
            return "redirect:/admin/roleUser?error";
        }
    }


    public void chartRevenue(Model model)
    {
        double january = orderService.totalByMonth("2021-01-01" ,"2021-01-31");
        model.addAttribute("january",january);
        double february = orderService.totalByMonth("2021-02-01" ,"2021-02-31");
        model.addAttribute("february",february);
        double march = orderService.totalByMonth("2021-03-01" ,"2021-03-31");
        model.addAttribute("march",march);
        double april = orderService.totalByMonth("2021-04-01" ,"2021-04-31");
        model.addAttribute("april",april);
        double may = orderService.totalByMonth("2021-05-01" ,"2021-05-31");
        model.addAttribute("may",may);
        double june = orderService.totalByMonth("2021-06-01" ,"2021-06-31");
        model.addAttribute("june",june);
        double july = orderService.totalByMonth("2021-07-01" ,"2021-07-31");
        model.addAttribute("july",july);
        double august = orderService.totalByMonth("2021-08-01" ,"2021-08-31");
        model.addAttribute("august",august);
        double september = orderService.totalByMonth("2021-09-01" ,"2021-09-31");
        model.addAttribute("september",september);
        double october = orderService.totalByMonth("2021-10-01" ,"2021-10-31");
        model.addAttribute("october",october);
        double november = orderService.totalByMonth("2021-11-01" ,"2021-11-31");
        model.addAttribute("november",november);
        double december = orderService.totalByMonth("2021-12-01" ,"2021-12-31");
        model.addAttribute("december",december);
    }

    public void countInfo(Model model)
    {
        List<Product> productList = productService.listProByStatus(1);
        int countProductActive = productList.size();
        List<tomato_th.project_tomato.model.service.CustomerService> customerServiceList = customerServiceService.getListByStatus(0);
        int countServiceWaiting = customerServiceList.size();

        List<Orders> ordersList = orderService.listOrderByStatus(1);
        int countOrderWaiting = ordersList.size();

        List<Customer> customerList = customerService.getAllCustomer();
        int countCustomerList = customerList.size();

        model.addAttribute("countProductActive",countProductActive);
        model.addAttribute("countServiceWaiting",countServiceWaiting);
        model.addAttribute("countOrderWaiting",countOrderWaiting);
        model.addAttribute("countCustomerList",countCustomerList);
    }
}
