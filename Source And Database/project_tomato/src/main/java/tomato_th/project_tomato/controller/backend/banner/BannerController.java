package tomato_th.project_tomato.controller.backend.banner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tomato_th.project_tomato.model.Banners;
import tomato_th.project_tomato.model.ClImage;
import tomato_th.project_tomato.model.Type_Banner;
import tomato_th.project_tomato.service.BannersService;
import tomato_th.project_tomato.service.CdService;
import tomato_th.project_tomato.service.TypeBannerService;

import javax.validation.Valid;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = "/admin/banner")
public class BannerController {
    @Autowired
    private BannersService bannersService;
    @Autowired
    private TypeBannerService typeBannerService;
    @Autowired
    private CdService cdService;

    @InitBinder
    public void InitBinder(WebDataBinder data) {
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        data.registerCustomEditor(Date.class, new CustomDateEditor(s, true));
    }

    @RequestMapping(name = "")
    public String showIndexBanner(Model model)
    {
        Banners banners = new Banners();
        return findPaginated(1,model,banners);
    }

    @RequestMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo")Integer pageNo, Model model, Banners banners)
    {
        int pageSize = 10;
        Page<Banners> page = bannersService.getAllBanners(pageNo,pageSize);
        List<Banners> bannersList = page.getContent();
        List<Type_Banner> type_bannerList = typeBannerService.getTypeBannerByStatus(1);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("list",bannersList);
        model.addAttribute("bannerNew",banners);
        model.addAttribute("typeBannerList",type_bannerList);
        return "admin/banner/bannerList";

    }

    @RequestMapping(value = "saveBanner",method = RequestMethod.POST)
    public String saveBanner(@ModelAttribute("bannerNew")@Valid Banners banners, BindingResult result, Model model, @RequestParam("image")MultipartFile multipartFile)throws IOException
    {
        if (result.hasErrors())
        {
            findPaginated(1,model,banners);
        }

        Map resultCd = cdService.upload(multipartFile);
        ClImage clImage = new ClImage((String) resultCd.get("secure_url"));
        banners.setImage_links(clImage.getUrl());
        boolean bl = bannersService.saveBanners(banners);
        if (bl)
        {
            return "redirect:/admin/banner?success=Add New Banner success";
        }
        return "redirect:/admin/banner?error=Add New Banner failed";
    }

    @RequestMapping("/editBanner")
    public String editBanner(@RequestParam("id")Integer id,@RequestParam("status")Integer status)
    {
        Banners banners = bannersService.getBannersById(id);
        banners.setStatus(status);
        boolean bl = bannersService.updateBanners(banners);
        if (bl)
        {
            return "redirect:/admin/banner?success=Update Banner success";
        }
        return "redirect:/admin/banner?error=Update Banner failed";
    }

    @RequestMapping("/deleteBanner")
    public String deleteBanner(@RequestParam("id")Integer id)
    {
        boolean bl = bannersService.deleteBanners(id);
        if (bl)
        {
            return "redirect:/admin/banner?success=Delete Banner success";
        }
        return "redirect:/admin/banner?error=Delete Banner failed";
    }
}
