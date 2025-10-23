package org.example.lab2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/param")
public class ParamController {

    // 1. Đọc dữ liệu từ query string ?name=...
    @GetMapping("/request-param")
    public String requestParam(
            @RequestParam(name = "name", required = false, defaultValue = "User") String name,
            Model model) {
        model.addAttribute("message", "Xin chào " + name + " (RequestParam)");
        return "param";
    }

    // 2. Đọc dữ liệu từ path variable /param/path/...
    @GetMapping("/path/{name}")
    public String pathVariable(
            @PathVariable("name") String name,
            Model model) {
        model.addAttribute("message", "Xin chào " + name + " (PathVariable)");
        return "param";
    }

    // 3. Đọc nhiều tham số từ query string ?name=...&age=...
    @GetMapping("/multi")
    public String multipleParams(
            @RequestParam(name = "name", defaultValue = "User") String name,
            @RequestParam(name = "age", defaultValue = "18") int age,
            Model model) {
        model.addAttribute("message", "Xin chào " + name + ", tuổi: " + age);
        return "param";
    }
}
