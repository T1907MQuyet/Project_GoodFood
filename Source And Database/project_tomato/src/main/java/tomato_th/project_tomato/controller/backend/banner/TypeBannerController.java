package tomato_th.project_tomato.controller.backend.banner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import tomato_th.project_tomato.model.Type_Banner;
import tomato_th.project_tomato.service.TypeBannerService;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/type_banner")
public class TypeBannerController {
    @Autowired
    private TypeBannerService typeBannerService;

    @InitBinder
    public void InitBinder(WebDataBinder data) {
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        data.registerCustomEditor(Date.class, new CustomDateEditor(s, true));
    }

    @RequestMapping("")
    public String showIndexTypeBanner(Model model)
    {
        List<Type_Banner> list = typeBannerService.getAllTypeBanner();
        Type_Banner type_banner = new Type_Banner();
        model.addAttribute("list",list);
        model.addAttribute("typeBannerNew",type_banner);
        return "admin/typeBanner/typeBanner";
    }

    @RequestMapping(value = "/saveTypeBanner",method = RequestMethod.POST)
    public String saveTypeBanner(@ModelAttribute("typeBannerNew")@Valid Type_Banner type_banner, BindingResult result,Model model)
    {
        if (result.hasErrors())
        {
            List<Type_Banner> list = typeBannerService.getAllTypeBanner();
            model.addAttribute("list",list);
            model.addAttribute("typeBannerNew",type_banner);
            return "admin/typeBanner/typeBanner";
        }
        boolean bl = typeBannerService.save_type_banner(type_banner);
        if (bl)
        {
            return "redirect:/admin/type_banner?success=Add New successful";
        }
        return "redirect:/admin/type_banner?error=Add New failed";
    }

    @RequestMapping(value = "/editTypeBanner")
    public String editTypeBanner(@RequestParam("id")Integer id, Model model)
    {
        Type_Banner type_banner = typeBannerService.getById(id);
        model.addAttribute("typeBannerEdit",type_banner);
        return "admin/typeBanner/editTypeBanner";
    }

    @RequestMapping(value = "/updateTypeBanner",method = RequestMethod.POST)
    public String updateTypeBanner(@ModelAttribute("typeBannerEdit")@Valid Type_Banner type_banner,BindingResult result,Model model)
    {
        if (result.hasErrors())
        {
            model.addAttribute("typeBannerEdit",type_banner);
            return "admin/typeBanner/editTypeBanner";
        }
        boolean bl = typeBannerService.update_type_banner(type_banner);
        if (bl)
        {
            return "redirect:/admin/type_banner?success=Update successful";
        }
        return "redirect:/admin/type_banner?error=Update failed";

    }

    @RequestMapping(value = "/deleteTypeBanner")
    public String deleteTypeBanner(@RequestParam("id")Integer id)
    {
        boolean bl = typeBannerService.delete_type_banner(id);
        if (bl)
        {
            return "redirect:/admin/type_banner?success=Delete successful";
        }
        return "redirect:/admin/type_banner?error=Delete failed";
    }

}
