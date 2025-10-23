package org.example.lab5.controller;

import org.example.lab5.service.CookieService;
import org.example.lab5.service.ParamService;
import org.example.lab5.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AccountController {

    @Autowired
    CookieService cookieService;

    @Autowired
    ParamService paramService;

    @Autowired
    SessionService sessionService;

    @GetMapping("/account/login")
    public String login1(Model model) {
        // Load username from cookie if exists
        String username = cookieService.getValue("user");
        model.addAttribute("username", username);
        return "account/login";
    }

    @PostMapping("/account/login")
    public String login2(Model model) {
        // Read parameters
        String username = paramService.getString("username", "");
        String password = paramService.getString("password", "");
        boolean remember = paramService.getBoolean("remember", false);

        // Check credentials
        if (username.equals("poly") && password.equals("123")) {
            // Save username to session
            sessionService.set("username", username);

            // Handle remember me
            if (remember) {
                // Remember for 10 days
                cookieService.add("user", username, 10 * 24);
            } else {
                // Remove cookie
                cookieService.remove("user");
            }

            model.addAttribute("message", "Đăng nhập thành công! Xin chào " + username);
            return "redirect:/item/index";
        } else {
            model.addAttribute("message", "Sai tên đăng nhập hoặc mật khẩu!");
            model.addAttribute("username", username);
            return "account/login";
        }
    }

    @GetMapping("/account/logout")
    public String logout() {
        sessionService.remove("username");
        return "redirect:/account/login";
    }
}