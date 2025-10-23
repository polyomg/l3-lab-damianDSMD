package org.example.lab8.service;

import org.example.lab8.entity.Account;
import org.example.lab8.dao.AccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private AccountDAO dao;

    public Account findById(String username) {
        return dao.findById(username).orElse(null);
    }

    public Account save(Account acc) {
        return dao.save(acc);
    }
}
