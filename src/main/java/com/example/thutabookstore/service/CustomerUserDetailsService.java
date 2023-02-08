package com.example.thutabookstore.service;

import com.example.thutabookstore.dao.CustomerDao;
import com.example.thutabookstore.ds.SecurityCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerUserDetailsService implements UserDetailsService {

    @Autowired
    private CustomerDao customerDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return customerDao.findCustomerByUsername(username)
                .map(SecurityCustomer::new)
                .orElseThrow(() -> new UsernameNotFoundException("Error!"));
    }
}
