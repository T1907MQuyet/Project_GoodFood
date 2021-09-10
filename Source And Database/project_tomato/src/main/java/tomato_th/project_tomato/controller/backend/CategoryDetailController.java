package tomato_th.project_tomato.controller.backend;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import tomato_th.project_tomato.model.core.Category;
import tomato_th.project_tomato.model.core.Category_detail;
import tomato_th.project_tomato.service.CategoryDetailService;
import tomato_th.project_tomato.service.CategoryService;


import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(path = "/admin/categoryDetail")
public class CategoryDetailController {
    @Autowired
    private CategoryDetailService categoryDetailService;
    @Autowired
    private CategoryService categoryService;

    @InitBinder
    public void InitBinder(WebDataBinder data)
    {
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        data.registerCustomEditor(Date.class, new CustomDateEditor(s, true));
    }

    @RequestMapping("")
    public String getCategoryDetail(Model model)
    {
        Category_detail category_detail = new Category_detail();


        return findPaginated(1,model,category_detail);
    }

    @RequestMapping(path = "/saveCateDetail",method = RequestMethod.POST)
    public String saveCateDetail(@ModelAttribute("cateDetailNew")@Valid Category_detail category_detail, BindingResult result, Model model)
    {
        if (result.hasErrors())
        {
            return findPaginated(1,model,category_detail);
        }
        boolean checkCateDetailName = categoryDetailService.checkCateDetailName(category_detail.getCate_detail_name(),category_detail.getCategory().getCate_id());
        if (checkCateDetailName==false)
        {
            return "redirect:/admin/categoryDetail?errorcatename=CategoryName is existed";
        }
        category_detail.setCreated(new Date());
        category_detail.setUpdated(new Date());

        boolean bl = categoryDetailService.saveCate(category_detail);
        if (bl)
        {
            return "redirect:/admin/categoryDetail?success=Add New category detail success";
        }
        return "redirect:/admin/categoryDetail?error=Add New category detail error";

    }

    @RequestMapping(path = "/editCateDetail")
    public String editCateDetail(@RequestParam("id")Integer id, Model model)
    {
        Category_detail category_detail = categoryDetailService.getCateDetailById(id);
        List<Category> listCate = categoryService.lisCategories();
        model.addAttribute("listCate",listCate);
        model.addAttribute("cateDetailEdit",category_detail);
        return "admin/categoryDetail/cateDetailEdit";
    }
    @RequestMapping(path = "/updateCateDetail",method = RequestMethod.POST)
    public String updateCateDetail(@ModelAttribute("cateDetailEdit")Category_detail category_detail)
    {
        boolean checkName = checkCateDetailNameEdit(category_detail.getCate_detail_name(),category_detail.getCate_detail_id(),category_detail.getCategory().getCate_id());
        if (checkName==false)
        {
            return "redirect:/admin/categoryDetail/editCateDetail?id="+category_detail.getCate_detail_id()+"&&errorcatename=CategoryName is existed";
        }
        category_detail.setUpdated(new Date());
        boolean bl = categoryDetailService.updateCate(category_detail);
        if (bl)
        {
            return "redirect:/admin/categoryDetail?success=Update category detail success";
        }
        return "redirect:/admin/categoryDetail?error=Update category detail error";

    }

    @RequestMapping(path = "/deleteCateDetail")
    public String deleteCateDetail(@RequestParam("id")Integer id)
    {
        boolean bl = categoryDetailService.deleteCate(id);
        if (bl)
        {
            return "redirect:/admin/categoryDetail?success=Delete category detail success";
        }
        return "redirect:/admin/categoryDetail?error=Delete category detail success";
    }



    @RequestMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo")int pageNo, Model model, Category_detail category_detail)
    {
        int pageSize = 10;
        List<Category> listCate = categoryService.listCateByStatus(1);
        Page<Category_detail> page = categoryDetailService.findPaginated(pageNo,pageSize);
        List<Category_detail> listCategory = page.getContent();
        model.addAttribute("currentPage",pageNo);
        model.addAttribute("totalPages",page.getTotalPages());
        model.addAttribute("totalItems",page.getTotalElements());
        model.addAttribute("list",listCategory);
        model.addAttribute("cateDetailNew",category_detail);
        model.addAttribute("listCate",listCate);
        return "admin/categoryDetail/cateDetailList";
    }

    public boolean checkCateDetailNameEdit(String cate_detail_name, int id,int cate_id)
    {
        Category_detail category_detail = categoryDetailService.getCateDetailById(id);
        boolean checkCateDetailName = categoryDetailService.checkCateDetailName(cate_detail_name,cate_id);
        if (checkCateDetailName==false)
        {
            if (cate_detail_name.equals(category_detail.getCate_detail_name()) && category_detail.getCategory().getCate_id()==cate_id)
            {
                return true;
            }
            else {
                return false;
            }
        }
        return true;
    }
    @RequestMapping("searchCateDetailName")
    public String searchCateDetailName(@RequestParam("key_cate_detail_name")String cate_detail_name,Model model,Category_detail category_detail)
    {
        List<Category_detail> list = categoryDetailService.listCateDetailByCateName(cate_detail_name);
        model.addAttribute("list",list);
        List<Category> listCate = categoryService.listCateByStatus(1);
        model.addAttribute("listCate",listCate);
        model.addAttribute("cateDetailNew", category_detail);
        return "admin/categoryDetail/cateDetailList";
    }
}
