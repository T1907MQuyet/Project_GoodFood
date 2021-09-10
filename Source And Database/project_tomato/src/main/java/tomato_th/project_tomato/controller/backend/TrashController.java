package tomato_th.project_tomato.controller.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tomato_th.project_tomato.model.core.*;
import tomato_th.project_tomato.model.service.Calender;
import tomato_th.project_tomato.model.service.Services;
import tomato_th.project_tomato.service.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/admin/trash")
public class TrashController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryDetailService categoryDetailService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private MenuDetailService menuDetailService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ServicesService servicesService;
    @Autowired
    private CalenderService calenderService;

    @RequestMapping(path = "")
    public String index(Model model)
    {
        int category = categoryService.countCategoryByStatus(3);
        int categoryDetail = categoryDetailService.countCateDetailByStatus(3);
        int menu = menuService.countMenuByStatus(3);
        int menuDetail = menuDetailService.countMenuDetailByStatus(3);
        int product = productService.countProductByStatus(3);
        int services = servicesService.countServiceTrash();
        List<Calender> calenderList = calenderService.getAllCalenBystatus(3);
        int calender = calenderList.size();
        model.addAttribute("cateTrash",category);
        model.addAttribute("cateDetailTrash",categoryDetail);
        model.addAttribute("menuTrash",menu);
        model.addAttribute("menuDetailTrash",menuDetail);
        model.addAttribute("productTrash",product);
        model.addAttribute("serviceTrash",services);
        model.addAttribute("calenderTrash",calender);

        return "admin/trash/trash";
    }

    @RequestMapping(path = "category")
    public String category(Model model)
    {
        List<Category> list = categoryService.listCateByStatus(3);
        model.addAttribute("list",list);
        return "admin/trash/category";
    }
    @RequestMapping(path = "restoreCategory")
    public String restoreCategory(@RequestParam("id")Integer id)
    {
        Category category = categoryService.getCateById(id);
        category.setStatus(2);
        boolean bl = categoryService.updateCate(category);
        if (bl)
        {
            return "redirect:/admin/trash?success=Restore category ";
        }
        return "redirect:/admin/trash?error=Restore category failed";
    }


    @RequestMapping(path = "categoryDetail")
    public String categoryDetail(Model model)
    {
        List<Category_detail> list = categoryDetailService.listCateDetailStatus(3);
        model.addAttribute("list",list);
        return "admin/trash/categoryDetail";
    }

    @RequestMapping(path = "restoreCategoryDetail")
    public String restoreCategoryDetail(@RequestParam("id")Integer id)
    {
        Category_detail category_detail = categoryDetailService.getCateDetailById(id);
        category_detail.setUpdated(new Date());
        category_detail.setStatus(2);
        boolean bl = categoryDetailService.updateCate(category_detail);
        if (bl)
        {
            return "redirect:/admin/trash?success=Restore category detail ";
        }
        return "redirect:/admin/trash?error=Restore category detail failed";
    }

    @RequestMapping(path = "menu")
    public String menu(Model model)
    {
        List<Menu> list = menuService.listMenuByStatus(3);
        model.addAttribute("list",list);
        return "admin/trash/menu";
    }

    @RequestMapping(path = "restoreMenu")
    public String restoreMenu(@RequestParam("id")Integer id)
    {
        Menu menu = menuService.getMenyById(id);
        menu.setUpdated(new Date());
        menu.setStatus(2);
        boolean bl = menuService.updateMenu(menu);
        if (bl)
        {
            return "redirect:/admin/trash?success=Restore menu ";
        }
        return "redirect:/admin/trash?error=Restore menu  failed";
    }

    @RequestMapping(path = "menuDetail")
    public String menuDetail(Model model)
    {
        List<Menu_detail> list = menuDetailService.listMenuDerailByStatus(3);
        model.addAttribute("list",list);
        return "admin/trash/menuDetail";
    }
    @RequestMapping(path = "restoreMenuDetail")
    public String restoreMenuDetail(@RequestParam("id")Integer id)
    {
        Menu_detail menu_detail = menuDetailService.getMenuDetailById(id);
        menu_detail.setUpdated(new Date());
        menu_detail.setStatus(2);
        boolean bl = menuDetailService.updateMenu(menu_detail);
        if (bl)
        {
            return "redirect:/admin/trash?success=Restore menu detail";
        }
        return "redirect:/admin/trash?error=Restore menu detail failed";
    }

    @RequestMapping(path = "product")
    public String product(Model model)
    {
        List<Product> list = productService.listProByStatus(3);
        model.addAttribute("list",list);
        return "admin/trash/product";
    }

    @RequestMapping(path = "restoreProduct")
    public String restoreProduct(@RequestParam("id")Integer id)
    {
        Product product = productService.getProById(id);
        product.setUpdated(new Date());
        product.setStatus(2);
        boolean bl = productService.updateProduct(product);
        if (bl)
        {
            return "redirect:/admin/trash?success=Restore product";
        }
        return "redirect:/admin/trash?error=Restore product failed";
    }

    @RequestMapping(path = "/services")
    public String services(Model model)
    {
        List<Services> list = servicesService.listServiceByStatus(3);
        model.addAttribute("list",list);
        return "admin/trash/services";
    }
    @RequestMapping(path = "/restoreServices")
    public String restoreServices(@RequestParam("id")Integer id)
    {
        Services services = servicesService.getService(id);
        services.setStatus(2);
        boolean bl = servicesService.updateService(services);
        if (bl)
        {
            return "redirect:/admin/trash?success=Restore services";
        }
        return "redirect:/admin/trash?error=Restore services failed";
    }

    @RequestMapping(path = "/calender")
    public String calender(Model model)
    {
        List<Calender> list = calenderService.getAllCalenBystatus(3);
        model.addAttribute("list",list);
        return "admin/trash/calender";
    }

    @RequestMapping(path = "/restoreCalender")
    public String restoreCalender(@RequestParam("id")Integer id)
    {
        try{
            Calender calender = calenderService.getCalenderById(id);
            boolean bl = calenderService.hiddenCalender(id);
            if (bl)
            {
                return "redirect:/admin/trash?success=Restore services";
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return "redirect:/admin/trash?error=Restore services failed";
    }

}
