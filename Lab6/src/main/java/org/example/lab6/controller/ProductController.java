package org.example.lab6.controller;

import org.example.lab6.dao.ProductDAO;
import org.example.lab6.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {

    @Autowired
    ProductDAO dao;

    // this is bài 3
    @RequestMapping("/product/sort")
    public String sort(Model model, @RequestParam("field") Optional<String> field) {
        Sort sort = Sort.by(Sort.Direction.DESC, field.orElse("price"));
        model.addAttribute("field", field.orElse("price").toUpperCase());
        List<Product> items = dao.findAll(sort);
        model.addAttribute("items", items);
        return "product/sort";
    }

    // này là bài 4 nha mom
    @RequestMapping("/product/page")
    public String paginate(Model model, @RequestParam("p") Optional<Integer> p) {
        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<Product> page = dao.findAll(pageable);
        model.addAttribute("page", page);
        return "product/page";
    }

    // ca này là bài 5
    @RequestMapping("/product/sortpage")
    public String sortAndPaginate(
            Model model,
            @RequestParam("field") Optional<String> field,
            @RequestParam("p") Optional<Integer> p
    ) {
        String sortField = field.orElse("price");
        Pageable pageable = PageRequest.of(p.orElse(0), 5, Sort.by(Sort.Direction.DESC, sortField));
        Page<Product> page = dao.findAll(pageable);
        model.addAttribute("page", page);
        model.addAttribute("field", sortField.toUpperCase());
        return "product/sortpage";
    }
}
