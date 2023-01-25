package com.example.thutabookstore.admin;

import com.example.thutabookstore.dao.AuthorDao;
import com.example.thutabookstore.dao.BookDao;
import com.example.thutabookstore.dao.CategoryDao;
import com.example.thutabookstore.entitiy.Author;
import com.example.thutabookstore.entitiy.Book;
import com.example.thutabookstore.entitiy.Category;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class BookController {

    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private AuthorDao authorDao;
    @Autowired
    private BookDao bookDao;

    @GetMapping("/book-form")
    public String bookForm(Model model) {
        model.addAttribute("authors", authorDao.findAll());
        model.addAttribute("categories", categoryDao.findAll());
        model.addAttribute("book", new Book());
        return "admin/book-form";
    }

    @PostMapping("/book-form")
    public String saveBook(@Valid Book book, BindingResult result) {
        if (result.hasErrors()) {
            System.out.println(result);
            return "admin/book-form";
        }
        Category category = categoryDao.findById(book.getCategory().getId()).get();
        Author author = authorDao.findById(book.getAuthor().getId()).get();
        category.addBook(book);
        author.addBook(book);
        bookDao.save(book);
        System.out.println(book);
        return "redirect:/admin/book/all";
    }

    @GetMapping("/book/all")
    public String listAllBook(Model model) {
        model.addAttribute("books", bookDao.findAll());
        return "admin/book-list";
    }
}
