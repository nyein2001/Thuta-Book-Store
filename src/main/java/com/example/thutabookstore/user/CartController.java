package com.example.thutabookstore.user;

import com.example.thutabookstore.ds.BookDto;
import com.example.thutabookstore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/add-to-cart")
    public String addToCart(int id) {
        cartService.addToCart(id);
        return "redirect:/user/book?id=" + id;
    }

    @GetMapping("/delete")
    public String removeItemFromCart(@RequestParam("id") int id) {
        cartService.removeItemFromCart(findBookDtoById(id));
        return "redirect:/cart/view";
    }

    @GetMapping("/view")
    public String cardView(Model model) {
        model.addAttribute("carts", cartService.listCart());
        return "cart-view";
    }

    private BookDto findBookDtoById(int id) {
        return cartService
                .listCart()
                .stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .get();
    }

}
