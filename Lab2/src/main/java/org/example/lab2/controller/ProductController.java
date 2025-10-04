package org.example.lab2.controller;

import org.example.lab2.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import java.util.List;
@Controller
@RequestMapping("/product")
public class ProductController {
    private static final List<Product> items = new ArrayList<>();

    static {
        items.add(new Product("A", 100.0));
        items.add(new Product("B", 200.0));
    }
    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("product1", new Product("iPhone 30", 5000.0));
        model.addAttribute("product2", new Product());
        model.addAttribute("items", items);
        return "form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("product2") Product p, Model model) {
        items.add(p);
        model.addAttribute("product1", new Product("iPhone 30", 5000.0));
        model.addAttribute("items", items);
        return "form";
    }
}

