package org.example.lab2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/result5")
public class ResultController {

    // Trang A
    @RequestMapping("/a")
    public String m1() {
        return "a"; // view a.html
    }

    // Forward (nội bộ) -> giữ model
    @RequestMapping("/b")
    public String m2(Model model) {
        model.addAttribute("message", "I come from b");
        return "forward:/result5/a"; // forward tới /a
    }

    // Redirect (client gọi lại) -> giữ param
    @RequestMapping("/c")
    public String m3(RedirectAttributes params) {
        params.addAttribute("message", "I come from c");
        return "redirect:/result5/a"; // redirect tới /a
    }

    // Trả thẳng string (REST style)
    @ResponseBody
    @RequestMapping("/d")
    public String m4() {
        return "I come from d";
    }
}
