package org.example.lab7.controller;

import org.example.lab7.dao.ProductDAO;
import org.example.lab7.entity.Product;
import org.example.lab7.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductDAO dao;

    @Autowired
    SessionService session;

    // nay la bai 1 den bai 4
    @RequestMapping("/search")
    public String search(Model model,
                         @RequestParam("min") Optional<Double> min,
                         @RequestParam("max") Optional<Double> max) {
        double minPrice = min.orElse(Double.MIN_VALUE);
        double maxPrice = max.orElse(Double.MAX_VALUE);
        List<Product> items = dao.findByPrice(minPrice, maxPrice);
        model.addAttribute("items", items);
        return "product/search";
    }

    // ca nay la bai 2 den bai 5
    @RequestMapping("/search-and-page")
    public String searchAndPage(Model model,
                                @RequestParam("keywords") Optional<String> kw,
                                @RequestParam("p") Optional<Integer> p) {
        String kwords = kw.orElse(session.get("keywords", ""));
        session.set("keywords", kwords);
        Pageable pageable = PageRequest.of(p.orElse(0), 8);
        Page<Product> page = dao.findByKeywords("%" + kwords + "%", pageable);
        model.addAttribute("page", page);
        model.addAttribute("keywords", kwords);
        return "product/search-and-page";
    }
}
