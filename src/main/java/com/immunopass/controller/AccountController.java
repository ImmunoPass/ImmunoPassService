package com.immunopass.controller;

import javax.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.immunopass.model.Account;


@RestController
@RequestMapping("/v1/accounts")
public interface AccountController {

    @PostMapping("")
    public Account createAccount(@NotNull @RequestBody Account account);

}
