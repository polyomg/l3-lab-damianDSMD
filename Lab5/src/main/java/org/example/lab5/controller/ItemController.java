package org.example.lab5.controller;

import org.example.lab5.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ItemController {

    @Autowired
    ItemRepository itemRepository;

    @GetMapping("/")
    public String root() {
        return "redirect:/item/index";
    }

    @GetMapping("/item/index")
    public String list(Model model) {
        // Load items from database
        model.addAttribute("items", itemRepository.findAll());
        return "item/index";
    }
}