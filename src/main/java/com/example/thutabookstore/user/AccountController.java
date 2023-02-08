package com.example.thutabookstore.user;

import com.example.thutabookstore.dao.BookDao;
import com.example.thutabookstore.entitiy.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class AccountController {
    @Autowired
    private BookDao bookDao;
    @RequestMapping("/login")
    public String login() {
        return "user/login";
    }

    @GetMapping({"/", "/home"})
    public String home(){
        return "index";
    }
    @ModelAttribute("books")
    public List<Book> listBooks(){
        return bookDao.findAll();
    }
}
