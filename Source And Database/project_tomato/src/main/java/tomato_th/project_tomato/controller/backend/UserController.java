package tomato_th.project_tomato.controller.backend;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import tomato_th.project_tomato.model.User;
import tomato_th.project_tomato.service.UserService;

@Controller
@RequestMapping(path = "/admin/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @RequestMapping("infoUser")
    public String infoUser(@RequestParam("email")String email, Model model)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userService.findByUsername(email);
        if (user.getEmail().equals(currentPrincipalName)==false)
        {
            return "redirect:/admin?error=error email";
        }
        model.addAttribute("user",user);

        return "admin/user/infoUser";
    }

    @RequestMapping("changePass")
    public String changePassUser(@RequestParam("email")String email, Model model)
    {
        User user = userService.findByUsername(email);
        model.addAttribute("user",user);
        return "admin/user/changePassword";
    }

    @RequestMapping(value = "updatePassword",method = RequestMethod.POST)
    public String updatePassword(@ModelAttribute("user")User user, @RequestParam("passOld")String pwdOld, @RequestParam("passNew")String passNew, @RequestParam("passNewCf")String passNewCf, Model model)
    {
        User userAc = userService.findUserById(user.getId());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        if (userAc.getEmail().equals(currentPrincipalName)==false)
        {
            return "redirect:/admin?error=error email";
        }

        boolean checkPass = passwordEncoder().matches(pwdOld,userAc.getPassword());
       if (checkPass==false)
       {
           model.addAttribute("user",user);
           return "redirect:/admin/user/changePass?email="+userAc.getEmail()+"&&passOld=incorrect password old";
       }
       if (passNew.equals(passNewCf)==false)
       {
           model.addAttribute("user",user);
           return "redirect:/admin/user/changePass?email="+userAc.getEmail()+"&&passNew=incorrect password Confirmation";
       }
       if (passNew.length()<6)
       {
           model.addAttribute("user",user);
           return "redirect:/admin/user/changePass?email="+userAc.getEmail()+"&&passNew=Password to 6 or more character ";
       }
        boolean bl = userService.updatePassword(userAc.getId(),passNew);
        if (bl){
            return "redirect:/admin?success=Change password success";
        }else{
            return "redirect:/admin?error=Change password failed";
        }

    }
}
