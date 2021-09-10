package tomato_th.project_tomato.controller.backend;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import tomato_th.project_tomato.model.core.*;
import tomato_th.project_tomato.service.*;


import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(path = "/admin/menuDetail")
public class MenuDetailController {
    @Autowired
    private MenuDetailService menuDetailService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private ProductMenuDetailService productMenuDetailService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryDetailService categoryDetailService;

    @InitBinder
    public void InitBinder(WebDataBinder data)
    {
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        data.registerCustomEditor(Date.class, new CustomDateEditor(s, true));
    }

    @RequestMapping(path = "")
    public String getMenuDetails(Model model)
    {
        Menu_detail menu_detail = new Menu_detail();
        return findPaginated(1,model,menu_detail);
    }

    @RequestMapping(path = "/saveMenuDetail",method = RequestMethod.POST)
    public String saveMenuDetail(@ModelAttribute("menuDetailNew")@Valid Menu_detail menu_detail, BindingResult result, Model model)
    {
        if (result.hasErrors())
        {
            return  findPaginated(1,model,menu_detail);
        }
        boolean checkMenuDetailName = menuDetailService.checkMenuDetailName(menu_detail.getMenu_detail_name(),menu_detail.getMenu().getMenu_id());
        if (checkMenuDetailName==false)
        {
            return "redirect:/admin/menuDetail?errorcatename=Menu name is existed";
        }

        menu_detail.setCreated(new Date());
        menu_detail.setUpdated(new Date());

        boolean bl = menuDetailService.saveMenu(menu_detail);
        if (bl)
        {
            return "redirect:/admin/menuDetail?success=Add New menu detail detail success";
        }
        return "redirect:/admin/menuDetail?error=Add New menu detail detail error";
    }

    @RequestMapping("/deleteMenuDetail")
    public String deleteMenuDetail(@RequestParam("id")Integer id)
    {
        boolean bl = menuDetailService.deleteMenu(id);

        if (bl)
        {
            return "redirect:/admin/menuDetail?success=Delete menu detail detail success";
        }
        return "redirect:/admin/menuDetail?error=Delete menu detail detail error";

    }

    @RequestMapping(path = "/editMenuDetail")
    public String editMenuDetail(@RequestParam("id")Integer id, Model model)
    {
        Menu_detail menu_detail = menuDetailService.getMenuDetailById(id);
        List<Menu> listMenu = menuService.lisMenus();
        model.addAttribute("menDetailEdit",menu_detail);
        model.addAttribute("list",listMenu);
        return "admin/menuDetail/menuDetailEdit";
    }

    @RequestMapping(path = "/updateMenuDetail",method = RequestMethod.POST)
    public String updateMenuDetail(@ModelAttribute("menDetailEdit")Menu_detail menu_detail)
    {
        boolean checkName = checkMenuDetailNameEdit(menu_detail.getMenu_detail_name(),menu_detail.getMenu_detail_id(),menu_detail.getMenu().getMenu_id());
        if (checkName==false)
        {
            return "redirect:/admin/menuDetail/editMenuDetail?id="+menu_detail.getMenu_detail_id()+"&&errorcatename=Menu name is existed";
        }
        menu_detail.setUpdated(new Date());
        boolean bl = menuDetailService.updateMenu(menu_detail);
        if (bl)
        {
            return "redirect:/admin/menuDetail?success=Update menu detail detail success";
        }
        return "redirect:/admin/menuDetail?error=Update menu detail detail error";
    }


    //hien thi chi tiet menu detail co ca san pham
    @RequestMapping(path = "/detailMenuDetail")
    public String detailMenuDetail(@RequestParam("id")Integer id, Model model)
    {
        Menu_detail menu_detail = menuDetailService.getMenuDetailById(id);
        List<Product_menu_detail> list = productMenuDetailService.lisProMenuDetailByMenu(id);
        List<Product> productList = productService.listProduct();
        List<Category_detail> listCateDetail = categoryDetailService.lisCategoryDetails();

        model.addAttribute("menu_detail",menu_detail);
        model.addAttribute("listProMD",list);
        model.addAttribute("listProduct",productList);
        model.addAttribute("listCateDetail",listCateDetail);
        return "admin/menuDetail/detailMenuDetail";
    }

    @RequestMapping(path = "/proByCate")
    public String productByCate(@RequestParam("cate_id")Integer cateId, @RequestParam("proMNId")Integer proMNId, Model model)
    {
        //List<Product> listProByCate = productService.lisProByCateDetail(cateId);
        List<Product> listProByCate = getProNotMenu(cateId,proMNId);
        model.addAttribute("proMNId",proMNId);
        model.addAttribute("listProduct",listProByCate);
        return "admin/ajax/productByCate";
    }

    public List<Product> getProNotMenu(int cate_id,int menu_detail_id)
    {
        List<Product> listPro = new ArrayList<>();
        List<Product> listProByCateDetail = productService.lisProByCateDetail(cate_id);
        List<Product_menu_detail> listProByMenuId = productMenuDetailService.lisProMenuDetailByMenu(menu_detail_id);

        for (Product product:listProByCateDetail)
        {
            boolean bl = productMenuDetailService.getProMDByMDIdbyProId(menu_detail_id,product.getProduct_id());
            if (bl==false)
            {
                listPro.add(product);
            }
        }
        return listPro;
    }



    //them vao menu
    @RequestMapping("insertProMenuDetail")
    public String saveProMenuDetail(@RequestParam("product_id")Integer proId, @RequestParam("menuD_id")Integer menu_detail_id,Model model)
    {
        Product product = productService.getProById(proId);
        Menu_detail menu_detail = menuDetailService.getMenuDetailById(menu_detail_id);
        Product_menu_detail product_menu_detail=new Product_menu_detail(product,menu_detail);
        boolean bl = productMenuDetailService.saveProMenuDetail(product_menu_detail);
        if (bl)
        {
            return "redirect:/admin/menuDetail/detailMenuDetail?id="+menu_detail_id+"&&success=Update menu detail detail success";
            //return "admin/ajax/insertProMenuDetail";
        }
        return "redirect:/admin/menuDetail/detailMenuDetail?id="+menu_detail_id+"&&error=Update menu detail detail error";

    }
    @RequestMapping("insertProMenuDetailAjax")
    public String saveProMenuDetailAjax(@RequestParam("product_id")Integer proId, @RequestParam("menuD_id")Integer menu_detail_id,Model model)
    {
        Product product = productService.getProById(proId);
        List<Product_menu_detail> list = productMenuDetailService.lisProMenuDetailByMenu(menu_detail_id);
        Menu_detail menu_detail = menuDetailService.getMenuDetailById(menu_detail_id);
        Product_menu_detail product_menu_detail=new Product_menu_detail(product,menu_detail);
        boolean bl = productMenuDetailService.saveProMenuDetail(product_menu_detail);
        if (bl)
        {
            model.addAttribute("listProMD",list);
            //return "redirect:/admin/menuDetail/detailMenuDetail?id="+menu_detail_id+"&&success=Update menu detail detail success";
            return "admin/ajax/insertProMenuDetail";
        }
        return "redirect:/admin/menuDetail/detailMenuDetail?id="+menu_detail_id+"&&error=Update menu detail detail error";

    }

    //Remove khoi pro menu detail
    @RequestMapping("removeProMD")
    public String removeProMenuDetail(@RequestParam("id")Integer pro_menu_detail_id, @RequestParam("menuD_id")Integer menu_detail_id)
    {
        boolean bl = productMenuDetailService.deleteProMenuDetail(pro_menu_detail_id);
        if (bl)
        {
            return "redirect:/admin/menuDetail/detailMenuDetail?id="+menu_detail_id+"&&success=Update menu detail detail success";
        }
        return "redirect:/admin/menuDetail/detailMenuDetail?id="+menu_detail_id+"&&error=Update menu detail detail error";

    }

    @RequestMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo")int pageNo, Model model, Menu_detail menu_detail)
    {
        int pageSize = 10;
        List<Menu> listMenu = menuService.listMenuByStatus(1);
        Page<Menu_detail> page = menuDetailService.findPaginated(pageNo,pageSize);
        List<Menu_detail> listMenuDetail = page.getContent();
        model.addAttribute("currentPage",pageNo);
        model.addAttribute("totalPages",page.getTotalPages());
        model.addAttribute("totalItems",page.getTotalElements());
        model.addAttribute("list",listMenuDetail);
        model.addAttribute("menuDetailNew",menu_detail);
        model.addAttribute("listMenu",listMenu);
        return "admin/menuDetail/menuDetailList";
    }

    public boolean checkMenuDetailNameEdit(String menuDName,int id,int menu_id)
    {
        Menu_detail menu_detail = menuDetailService.getMenuDetailById(id);
        boolean checkMenuDetailName = menuDetailService.checkMenuDetailName(menuDName,menu_id);
        if (checkMenuDetailName==false)
        {
            if (menuDName.equals(menu_detail.getMenu_detail_name()) && menu_detail.getMenu().getMenu_id()==menu_id)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        return true;
    }


}
