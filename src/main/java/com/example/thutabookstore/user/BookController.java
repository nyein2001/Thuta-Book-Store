package com.example.thutabookstore.user;

import com.example.thutabookstore.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller("user_book_controller")
@RequestMapping("/user")
public class BookController {

    @Autowired
    private BookDao bookDao;

    @GetMapping("/book/all")
    public String listAllModel(Model model) {
        model.addAttribute("books", bookDao.findAll());
        return "user/show-all-books";
    }
}
