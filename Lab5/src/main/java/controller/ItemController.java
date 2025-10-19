package controller;

import model.DB;
import service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ItemController {

    @Autowired
    ShoppingCartService cart;

    @GetMapping("/")
    public String root() {
        return "redirect:/item/index";
    }

    @GetMapping("/item/index")
    public String list(Model model) {
        model.addAttribute("items", DB.items.values());
        model.addAttribute("cartCount", cart.getCount());
        return "item/index";
    }
}