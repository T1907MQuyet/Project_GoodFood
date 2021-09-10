package tomato_th.project_tomato.controller.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tomato_th.project_tomato.model.About_us;
import tomato_th.project_tomato.model.ClImage;
import tomato_th.project_tomato.service.AboutUsService;
import tomato_th.project_tomato.service.CdService;


import javax.validation.Valid;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = "/admin/aboutUs")
public class AboutUsController {
    @Autowired
    private AboutUsService aboutUsService;
    @Autowired
    private CdService cdService;


    @InitBinder
    public void InitBinder(WebDataBinder data) {
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        data.registerCustomEditor(Date.class, new CustomDateEditor(s, true));
    }

    @RequestMapping(path = "")
    public String getAboutUs(Model model)
    {
        About_us about_us = new About_us();
        return findPaginated(1,model,about_us);
    }

    @RequestMapping(path = "/saveAboutUs",method = RequestMethod.POST)
    public String saveAboutUs(@ModelAttribute("aboutUsNew")@Valid About_us about_us, BindingResult result, Model model, @RequestParam("file_avatar") MultipartFile multipartFile)throws IOException
    {
        if (result.hasErrors())
        {
            return findPaginated(1,model,about_us);
        }
        Map resultCd = cdService.upload(multipartFile);
        ClImage clImage = new ClImage((String)resultCd.get("secure_url"));
        about_us.setImage(clImage.getUrl());
        boolean bl = aboutUsService.saveAbout(about_us);
        if(bl)
        {
            return "redirect:/admin/aboutUs?success=Add New aboutUs success";
        }
        return "redirect:/admin/aboutUs?error=Add New aboutUs error";
    }

    @RequestMapping(path = "/editAboutUs")
    public String editAboutUs(@RequestParam("id")Integer id, Model model)
    {
        About_us about_us = aboutUsService.getAboutById(id);
        model.addAttribute("aboutUsEdit",about_us);
        return "admin/aboutUs/aboutUsEdit";
    }

    @RequestMapping(value = "/updateAboutUs",method = RequestMethod.POST)
    public String updateAboutUs(@ModelAttribute("aboutUsEdit")About_us about_us, @RequestParam("file_avatar") MultipartFile multipartFile)throws IOException
    {
        About_us about_us_edit = aboutUsService.getAboutById(about_us.getNews_id());
        if (multipartFile.getSize()>0)
        {
            Map result = cdService.upload(multipartFile);
            ClImage clImage = new ClImage((String)result.get("secure_url"));
            about_us.setImage(clImage.getUrl());
        }else{
            about_us.setImage(about_us_edit.getImage());
        }
        boolean bl = aboutUsService.updateAbout(about_us);
        if(bl)
        {
            return "redirect:/admin/aboutUs?success=Update aboutUs success";
        }
        return "redirect:/admin/aboutUs?error=Update aboutUs error";
    }


    @RequestMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model, About_us about_us) {
        int pageSize = 1;
        Page<About_us> page = aboutUsService.findPaginated(pageNo, pageSize);
        List<About_us> listAboutUs = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("list", listAboutUs);
        model.addAttribute("aboutUsNew", about_us);
        return "admin/aboutUs/aboutUsList";
    }
}
