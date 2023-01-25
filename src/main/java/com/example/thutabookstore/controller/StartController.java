package com.example.thutabookstore.controller;

import com.example.thutabookstore.dao.BookDao;
import com.example.thutabookstore.entitiy.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class StartController {

    @Autowired
    private BookDao bookDao;

    @GetMapping("/admin/")
    public String startAction() {
        return "redirect:/admin/book/all";
    }

    @GetMapping(value = {"/","/index"})
    public String index(){
        return "index";
    }

    @ModelAttribute("books")
    public List<Book> listBooks() {
        return bookDao.findAll();
    }
}
