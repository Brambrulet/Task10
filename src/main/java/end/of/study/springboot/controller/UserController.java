package end.of.study.springboot.controller;

import end.of.study.springboot.service.RoleService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping
    public String index(Model model) {
        return "user.jsp";
    }

    @ModelAttribute
    public void commonAttributes(HttpServletRequest request, Model model) {
        model.addAttribute("isAdmin", request.isUserInRole(RoleService.ADMIN_ROLE_NAME));
    }

}
