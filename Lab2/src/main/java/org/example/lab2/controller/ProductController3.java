package org.example.lab2.controller;

import org.example.lab2.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/product3")
public class ProductController3 {

    // Hiển thị form (bài 3)
    @GetMapping("/form")
    public String form() {
        return "bai3_form";
    }

    // Lưu product (bài 3)
    @PostMapping("/save")
    public String save(@ModelAttribute Product product, Model model) {
        model.addAttribute("name", product.getName());
        model.addAttribute("price", product.getPrice());
        return "bai3_form";
    }
}