package com.example.thutabookstore.service;

import com.example.thutabookstore.dao.BookDao;
import com.example.thutabookstore.ds.BookDto;
import com.example.thutabookstore.ds.CartBean;
import com.example.thutabookstore.entitiy.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CartService {
    @Autowired
    private CartBean cartBean;
    @Autowired
    private BookDao bookDao;

    public void addToCart(int id) {
        cartBean.addToCart(toDto(bookDao.findById(id).get()));
    }

    public Set<BookDto> listCart() {
        return cartBean.listAllCart();
    }

    public void removeItemFromCart(BookDto bookDto) {
        cartBean.removeBook(bookDto);
    }

    public BookDto toDto(Book book) {
        return new BookDto(
                book.getId(),
                book.getTitle(),
                book.getYearPublished(),
                book.getPublisher(),
                book.getPrice(),
                book.getQuantity(),
                book.getGenre(),
                book.getImageUrl(),
                book.getDescription(),
                book.getCategory(),
                book.getAuthor()
        );
    }
}
