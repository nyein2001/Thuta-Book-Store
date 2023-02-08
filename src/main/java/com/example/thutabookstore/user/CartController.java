package com.example.thutabookstore.user;

import com.example.thutabookstore.ds.BookDto;
import com.example.thutabookstore.entitiy.Customer;
import com.example.thutabookstore.service.CartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    @GetMapping("/clear")
    public String clearCart() {
        cartService.clearCart();
        return "redirect:/cart/view";
    }

    @GetMapping("/delete")
    public String removeItemFromCart(@RequestParam("id") int id) {
        cartService.removeItemFromCart(findBookDtoById(id));
        return "redirect:/cart/view";
    }

    private BookDto findBookDtoById(int id) {
        return cartService
                .listCart()
                .stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .get();
    }

    private List<Integer> bookQuantityList = new ArrayList<>();

    @GetMapping("/register-form")
    public String registerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "user/register";
    }

    @PostMapping("/register")
    public String saveRegisterCustomer(@Valid Customer customer, BindingResult result) {
        if (result.hasErrors()) {
            return "user/register";
        }
        Set<BookDto> carts = cartService.listCart();
        int index = 0;
        for (BookDto bookDto: carts) {
            bookDto.setOrderBookQuantity(bookQuantityList.get(index));
            index++;
        }
        System.out.println("Carts =========================== " + carts);
        cartService.register(customer, carts);
        return "redirect:/login";
    }

    @PostMapping("/checkout")
    public String checkout(BookDto bookDto) {
        this.bookQuantityList = bookDto.getItemList();
        return "redirect:/cart/register-form";
    }

    @GetMapping("/view")
    public String viewCart(Model model) {
        model.addAttribute("bookDto", new BookDto());
        model.addAttribute("carts", cartService.listCart());
        return "user/cart-view";
    }

    @ModelAttribute("carts")
    public Set<BookDto> bookDtoList() {
        return cartService.listCart();
    }

}
