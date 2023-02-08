package com.example.thutabookstore.service;

import com.example.thutabookstore.dao.BookDao;
import com.example.thutabookstore.dao.CustomerDao;
import com.example.thutabookstore.dao.CustomerOrderBookDao;
import com.example.thutabookstore.dao.RoleDao;
import com.example.thutabookstore.ds.BookDto;
import com.example.thutabookstore.ds.CartBean;
import com.example.thutabookstore.entitiy.*;
import jakarta.persistence.criteria.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

@Service
public class CartService {
    @Autowired
    private CartBean cartBean;
    @Autowired
    private BookDao bookDao;
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private CustomerOrderBookDao customerOrderBookDao;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void addToCart(int id) {
        cartBean.addToCart(toDto(bookDao.findById(id).get()));
    }

    public void removeItemFromCart(BookDto bookDto) {
        cartBean.removeBook(bookDto);
    }

    public void clearCart() {
        cartBean.clearCart();
    }

    public Set<BookDto> listCart() {
        return cartBean.listAllCart();
    }

    public List<OrderBook> listOrderBookByUserName(String name) {
        return customerOrderBookDao.findOrderBookByCustomerName(name);
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

    @Transactional
    public void register(Customer customer, Set<BookDto> carts) {
        Customer managedCustomer = getCustomer(customer);
        CustomerOrderBook customerOrderBook = new CustomerOrderBook();
        managedCustomer.addCustomerOrderBook(customerOrderBook);
        customerOrderBook.setOrderDate(LocalDate.now());
        customerOrderBook.setOrderCode(generateCode());

        for (BookDto bookDto:carts) {
            customerOrderBook.addOrderBook(toOrderBook(bookDto));
        }
        customerOrderBookDao.save(customerOrderBook);
        System.out.println(" +++++++++++++++++++ "+customerOrderBookDao.findAll());
        clearCart();

    }

    private OrderBook toOrderBook(BookDto bookDto) {
        return new OrderBook(
                bookDto.getOrderBookQuantity(),
                bookDto.getPrice(),
                bookDto.getTitle(),
                bookDto.getAuthor().getName()
        );
    }

    private String generateCode() {
        //99+10
        int code = new Random().nextInt(99) + 10;
        return "UB" + code;
    }

    private Customer getCustomer(Customer customer) {
        Optional<Customer> customer1 = customerDao.findCustomerByUsername(customer.getUsername());
        if (!customer1.isPresent()) {
            Role customerRole = roleDao.findRoleByRoleName("ROLE_USER").get();
            customer.addRole(customerRole);
            customer.setPassword(passwordEncoder.encode(customer.getPassword()));
            return customerDao.saveAndFlush(customer);
        } else {
            return customer1.get();
        }
    }
}
