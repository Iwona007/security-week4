package pl.iwona.securityencoderweek4.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.iwona.securityencoderweek4.config.WebSecurityConfig;
import pl.iwona.securityencoderweek4.model.ApiUser;
import pl.iwona.securityencoderweek4.service.UserService;

@Controller
public class MainController {
    private UserService userService;
    private WebSecurityConfig webSecurityConfig;

    public MainController(UserService userService, WebSecurityConfig webSecurityConfig) {
        this.userService = userService;
        this.webSecurityConfig = webSecurityConfig;
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/signup")
    public ModelAndView singup() {
        return new ModelAndView("registration", "user", new ApiUser());
    }

    @RequestMapping("/register")
    public ModelAndView register(ApiUser user, HttpServletRequest request) {
        userService.addUser(user, request);
        return new ModelAndView("redirect:/login");
    }

    @RequestMapping("/verify-token")
    public ModelAndView register(@RequestParam String token) {
        userService.verifyToken(token);
        return new ModelAndView("redirect:/login");
    }

    @RequestMapping("/admin-token")
    public ModelAndView ifAdmin(@RequestParam String token) {
        userService.isAdmin(token);
        return new ModelAndView("redirect:/login");

    }
}
