package tomato_th.project_tomato.controller.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tomato_th.project_tomato.model.News;
import tomato_th.project_tomato.model.TypeNews;
import tomato_th.project_tomato.model.service.Calender;
import tomato_th.project_tomato.model.service.Service_News;
import tomato_th.project_tomato.model.service.Services;
import tomato_th.project_tomato.service.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(path = "/food/news")
public class FrontendNewsController {
    @Autowired
    private TypeNewsService typeNewsService;
    @Autowired
    private FrontendController frontendController;
    @Autowired
    private NewsService newsService;
    @Autowired
    private Service_News_Service service_news_service;
    @Autowired
    private ServicesService servicesService;
    @Autowired
    private CalenderService calenderService;
   

    @InitBinder
    public void InitBinder(WebDataBinder data) {
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        data.registerCustomEditor(Date.class, new CustomDateEditor(s, true));
    }

    @RequestMapping(path = "")
    public String newIndex(Model model)
    {
        frontendController.menu(model);
        return findPaginated(1,model);
    }

    @RequestMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo")int pageNo, Model model)
    {
        int pageSize = 10;
        Page<News> page = newsService.listNewsShow(pageNo,pageSize);
        List<News> listNew = page.getContent();
        model.addAttribute("currentPage",pageNo);
        model.addAttribute("totalPages",page.getTotalPages());
        model.addAttribute("totalItems",page.getTotalElements());
        List<TypeNews> listType = typeNewsService.listByStatusShow();
        model.addAttribute("listType",listType);
        model.addAttribute("list",listNew);
        return "frontend/news/news";
    }

    @RequestMapping(path = "/type")
    public String getNewByType(@RequestParam("type_id")Integer type_id,Model model)
    {
        frontendController.menu(model);
        return findTypePaginated(1,model,type_id);
    }

    @RequestMapping("/type/page/{pageNo}")
    public String findTypePaginated(@PathVariable(value = "pageNo")int pageNo,Model model,@RequestParam("type_id")int type_id)
    {
        int pageSize = 10;
        Page<News> page = newsService.listPgNewsByType(type_id,pageNo,pageSize);
        List<News> list = page.getContent();
        model.addAttribute("totalPages",page.getTotalPages());
        model.addAttribute("currentPage",pageNo);
        model.addAttribute("totalItems",page.getTotalElements());
        List<TypeNews> listType = typeNewsService.listByStatusShow();
        model.addAttribute("listType",listType);
        model.addAttribute("list",list);
        model.addAttribute("type_id",type_id);
        return "frontend/news/newsType";
    }

    @RequestMapping("/detail")
    public String getNewDetail(@RequestParam("id")int id,Model model)
    {
        News news = newsService.getNewById(id);
        model.addAttribute("news",news);
        frontendController.menu(model);
        return "frontend/news/newsDetail";
    }

    @RequestMapping("/newsService")
    public String newsService(@RequestParam("service_id")Integer service_id,Model model)
    {
        Service_News service_news = service_news_service.getServiceNewByServiceId(service_id);
        if (service_news==null)
        {
            return "frontend/404";
        }
        News news = newsService.getNewById(service_news.getNews().getNews_id());
        frontendController.menu(model);
        model.addAttribute("news",news);
        Services services = servicesService.getService(service_id);
        model.addAttribute("services",services);
        List<Calender> listCalen = calenderService.listCalenderByService(service_id);
        model.addAttribute("listCalen",listCalen);

        return "frontend/news/newsServiceDetail";
    }
}
