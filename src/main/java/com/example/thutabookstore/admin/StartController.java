package com.example.thutabookstore.admin;

import com.example.thutabookstore.dao.BookDao;
import com.example.thutabookstore.entitiy.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class StartController {

    @GetMapping(value = {"/admin",})
    public String layoutTest(){
        return "forward:/admin/book/all";
    }
}
