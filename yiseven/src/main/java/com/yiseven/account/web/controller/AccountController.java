package com.yiseven.account.web.controller;


import com.yiseven.account.common.response.Response;
import com.yiseven.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hdeng
 */
@RestController
@RequestMapping("account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("query")
    public Response query(@RequestParam int id) {
        return accountService.query(id);
    }
}
