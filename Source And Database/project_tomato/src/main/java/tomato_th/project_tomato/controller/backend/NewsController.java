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
import tomato_th.project_tomato.model.ClImage;
import tomato_th.project_tomato.model.News;
import tomato_th.project_tomato.model.TypeNews;
import tomato_th.project_tomato.model.service.Service_News;
import tomato_th.project_tomato.model.service.Services;
import tomato_th.project_tomato.service.*;

import javax.validation.Valid;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = "/admin/news")
public class NewsController {
    @Autowired
    private NewsService newsService;
    @Autowired
    private TypeNewsService typeNewsService;
    @Autowired
    private CdService cdService;
    @Autowired
    private ServicesService servicesService;
    @Autowired
    private Service_News_Service service_news_service;

    @InitBinder
    public void InitBinder(WebDataBinder data)
    {
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        data.registerCustomEditor(Date.class, new CustomDateEditor(s, true));
    }

    @RequestMapping("")
    public String getNews(Model model)
    {
        return findPaginated(1,model);
    }

    @RequestMapping(path = "/insertNews")
    public String insertNews(Model model)
    {
        News news = new News();
        List<TypeNews> listTypeNew = typeNewsService.listByStatusShow();
        model.addAttribute("newsNew",news);
        model.addAttribute("listType",listTypeNew);

        List<Services> servicesList = servicesService.listServiceShow();
        model.addAttribute("servicesList",servicesList);

        return "admin/news/insertNews";
    }

    @RequestMapping(path = "/saveNews",method = RequestMethod.POST)
    public String saveNews(@ModelAttribute("newsNew")@Valid News news, BindingResult bindingResult, Model model, @RequestParam("file_avatar") MultipartFile multipartFile,@RequestParam("service_id")Integer service_id)throws IOException
    {
        if(bindingResult.hasErrors())
        {
            List<TypeNews> listTypeNew = typeNewsService.listByStatusShow();
            model.addAttribute("newsNew",news);
            model.addAttribute("listType",listTypeNew);

            return "admin/news/insertNews";
        }

        if (multipartFile.getSize()==0)
        {
            return "redirect:/admin/news?error=Image link must not be empty";
        }

        Map resultCd = cdService.upload(multipartFile);
        ClImage clImage = new ClImage((String)resultCd.get("secure_url"));
        news.setImageLink(clImage.getUrl());
        if (service_id>0)
        {
            News newsSave = newsService.saveNew2(news);
            Service_News service_news = new Service_News();
            service_news.setServices(servicesService.getService(service_id));
            service_news.setNews(newsSave);
            boolean bl = service_news_service.saveServiceNew(service_news);
            if (bl)
            {
                return "redirect:/admin/news?success=Add News success";
            }
            return "redirect:/admin/news?error=Add News error";
        }
        boolean bl = newsService.saveNew(news);
        if (bl)
        {
            return "redirect:/admin/news?success=Add News success";
        }
        return "redirect:/admin/news?error=Add News error";
    }

    @RequestMapping(value = "/detailNews")
    public String detailNew(@RequestParam("id")Integer id, Model model)
    {
        News news = newsService.getNewById(id);
        model.addAttribute("newDetail",news);
        return "admin/news/detailNews";
    }

    @RequestMapping(value = "/editNews")
    public String editNew(@RequestParam("id")Integer id, Model model)
    {
        News news = newsService.getNewById(id);
        List<TypeNews> listTypeNew = typeNewsService.listByStatusShow();
        model.addAttribute("listType",listTypeNew);
        model.addAttribute("newEdit",news);
        List<Services> servicesList = servicesService.listServiceShow();
        model.addAttribute("servicesList",servicesList);

        Service_News service_news = service_news_service.getServiceNewByNewId(news.getNews_id());
        model.addAttribute("service_news",service_news);
        return "admin/news/editNews";
    }

    @RequestMapping(value = "/updateNews",method = RequestMethod.POST)
    public String updateNew(@ModelAttribute("newEdit")News news, @RequestParam("file_avatar") MultipartFile multipartFile,@RequestParam("service_id")Integer service_id)throws IOException
    {
        News newEdit = newsService.getNewById(news.getNews_id());
        Service_News service_news = service_news_service.getServiceNewByNewId(newEdit.getNews_id());
        if (multipartFile.getSize()>0)
        {
            Map resultCd = cdService.upload(multipartFile);
            ClImage clImage = new ClImage((String)resultCd.get("secure_url"));
            news.setImageLink(clImage.getUrl());
        }else{
            news.setImageLink(newEdit.getImageLink());
        }
        if (service_id>0)
        {
            List<Service_News> service_newsList = service_news_service.getListServiceNewByNewId(newEdit.getNews_id());
            for (Service_News service_news1:service_newsList) {
                service_news_service.deleteServiceNew(service_news1.getNews_service_id());
            }
            if (service_news!=null)
            {
                if (service_news.getServices().getService_id()!=service_id)
                {
                    service_news.setServices(servicesService.getService(service_id));
                }
                boolean blSN = service_news_service.updateServiceNew(service_news);
            }else
            {
                Service_News service_news_save = new Service_News();
                service_news_save.setServices(servicesService.getService(service_id));
                service_news_save.setNews(newEdit);
                boolean blSN = service_news_service.saveServiceNew(service_news_save);
            }
        }else
        {
            List<Service_News> service_newsList = service_news_service.getListServiceNewByNewId(newEdit.getNews_id());
            for (Service_News service_news1:service_newsList) {
                service_news_service.deleteServiceNew(service_news1.getNews_service_id());
            }
        }
        boolean bl = newsService.updateNew(news);
        if (bl)
        {
            return "redirect:/admin/news/editNews?id="+newEdit.getNews_id()+"&success=Update News success";
        }

        return "redirect:/admin/news/editNews?id="+newEdit.getNews_id()+"&error=Update News error";
    }
    @RequestMapping(value = "/deleteNews")
    public String deleteNew(@RequestParam("id")Integer id)
    {
        boolean bl = newsService.deleteNew(id);
        if (bl)
        {
            return "redirect:/admin/news?success=Delete News success";
        }
        return "redirect:/admin/news?error=Delete News error";
    }

    @RequestMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo")int pageNo, Model model)
    {
        int pageSize = 10;
        Page<News> page = newsService.findPaginated(pageNo,pageSize);
        List<News> listNews = page.getContent();
        model.addAttribute("currentPage",pageNo);
        model.addAttribute("totalPages",page.getTotalPages());
        model.addAttribute("totalItems",page.getTotalElements());
        model.addAttribute("list",listNews);
        return "admin/news/newsList";
    }

}
