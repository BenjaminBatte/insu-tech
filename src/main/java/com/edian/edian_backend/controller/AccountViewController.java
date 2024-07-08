package com.edian.edian_backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountViewController {

    @GetMapping("/accounts")
    public String viewAccounts() {
        return "accounts";
    }
}
