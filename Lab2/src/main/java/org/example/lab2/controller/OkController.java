package org.example.lab2.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ctrl")
public class OkController {

    // Dùng chung method để tránh code lặp
    private String render(HttpServletRequest req, Model model, String paramX) {
        model.addAttribute("uri", req.getRequestURI() + (req.getQueryString() != null ? "?" + req.getQueryString() : ""));
        model.addAttribute("method", req.getMethod());
        if (paramX != null) {
            model.addAttribute("paramX", paramX);
        }
        return "ok";
    }

    @RequestMapping("/ok")
    public String ok(HttpServletRequest req, Model model) {
        return render(req, model, null);
    }

    @PostMapping("/ok")
    public String m1(HttpServletRequest req, Model model) {
        return render(req, model, null);
    }

    @GetMapping("/ok")
    public String m2(HttpServletRequest req, Model model) {
        return render(req, model, null);
    }

    @RequestMapping(value = "/ok", params = "x")
    public String m3(@RequestParam("x") String x, HttpServletRequest req, Model model) {
        return render(req, model, x);
    }
}
