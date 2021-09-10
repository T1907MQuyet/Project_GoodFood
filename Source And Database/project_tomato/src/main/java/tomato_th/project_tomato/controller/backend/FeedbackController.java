package tomato_th.project_tomato.controller.backend;

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
import tomato_th.project_tomato.model.Feedbacks;
import tomato_th.project_tomato.repository.FeedbacksRepository;
import tomato_th.project_tomato.service.FeedbackService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/feedback")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private FeedbacksRepository feedbacksRepository;
    @InitBinder
    public void InitBinder(WebDataBinder data) {
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        data.registerCustomEditor(Date.class, new CustomDateEditor(s, true));
    }

    @RequestMapping("")
    public String getAll(Model model)
    {

        return findPaginated(1,model);
    }

    @RequestMapping("fbDetail")
    public String getDetail(@RequestParam("id")Integer id, @RequestParam("status")Integer status, Model model)
    {
        if (status==1)
        {
            boolean bl = feedbackService.update(id);
            if (bl)
            {
                Feedbacks fb = feedbackService.getFbById(id);
                model.addAttribute("feedBack",fb);
            }else{
                Feedbacks fb = feedbackService.getFbById(id);
                model.addAttribute("feedBack",fb);
            }

        }else{
            Feedbacks feedbacks = feedbackService.getFbById(id);
            model.addAttribute("feedBack",feedbacks);
        }


        return "admin/feedback/fbDetail";
    }

    @RequestMapping("updateStatus")
    public String updateStatus(@RequestParam("id")Integer id)
    {
        boolean bl = feedbackService.update(id);
        if (bl)
        {
            return "redirect:/admin/feedback?success=okok";
        }
        return "redirect:/admin/feedback?error=okok";
    }

    @RequestMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
        int pageSize = 10;
        Page<Feedbacks> page = feedbackService.findPaginated(pageNo, pageSize);
        List<Feedbacks> listFeedback = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("list", listFeedback);
        return "admin/feedback/fbList";
    }
}
