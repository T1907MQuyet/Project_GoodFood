package tomato_th.project_tomato.controller.backend;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import tomato_th.project_tomato.model.TypeNews;
import tomato_th.project_tomato.service.TypeNewsService;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(path = "/admin/typeNews")
public class TypeNewsController {
    @Autowired
    private TypeNewsService typeNewsService;

    @InitBinder
    public void InitBinder(WebDataBinder data) {
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        data.registerCustomEditor(Date.class, new CustomDateEditor(s, true));
    }

    @RequestMapping(path = "")
    public String getTypeNews(Model model)
    {
        TypeNews typeNews = new TypeNews();
        return findPaginated(1,model,typeNews);
    }

    @RequestMapping(path = "saveTypeNews",method = RequestMethod.POST)
    public String saveTypeNews(@ModelAttribute("typeNews")@Valid TypeNews typeNews, BindingResult bindingResult, Model model)
    {
        if (bindingResult.hasErrors())
        {
            return findPaginated(1,model,typeNews);
        }
        boolean bl = typeNewsService.saveTypeNews(typeNews);
        if (bl)
        {
            return "redirect:/admin/typeNews?success=Add New type success";
        }
        return "redirect:/admin/typeNews?error=Add New type failed";
    }

    @RequestMapping(path = "editTypeNew")
    public String editTypeNews(@RequestParam("id")Integer id, Model model)
    {
        TypeNews typeNews = typeNewsService.listTypeNews(id);
        model.addAttribute("typeNewEdit",typeNews);
        return "admin/typeNews/editTypeNews";
    }

    @RequestMapping(path = "updateTypeNew",method = RequestMethod.POST)
    public String updateTypeNews(@ModelAttribute("typeNewEdit")TypeNews typeNews)
    {
        boolean bl = typeNewsService.updateTypeNews(typeNews);
        if (bl)
        {
            return "redirect:/admin/typeNews?success=Update type news success";
        }
        return "redirect:/admin/typeNews?error=Update type news failed";
    }

    @RequestMapping(path = "deleteTypeNew")
    public String deleteTypeNew(@RequestParam("id")Integer id)
    {
        boolean bl = typeNewsService.deleteTypeNews(id);
        if (bl)
        {
            return "redirect:/admin/typeNews?success=Delete type news success";
        }
        return "redirect:/admin/typeNews?error=Delete type news failed";
    }

    @RequestMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo")int pageNo, Model model, TypeNews typeNews)
    {
        int pageSize = 10;
        Page<TypeNews> page = typeNewsService.findPaginated(pageNo,pageSize);
        List<TypeNews> listTypeNews = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("list", listTypeNews);
        model.addAttribute("typeNews", typeNews);
        return "admin/typeNews/typeNewsList";
    }

}
