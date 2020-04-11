package com.immunopass.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.immunopass.model.Account;


@RestController
@RequestMapping("/v1/accounts")
public interface AccountController {

    /*
     * Disabled this API, as we haven't specified yet who will have the authorization to create new accounts.
     * Once we have specified a specific role, then we can enable this.
     */
    //@PostMapping
    Account createAccount(@Valid @RequestBody final Account account);

    @GetMapping("/{id}")
    Account getAccount(@NotNull @PathVariable final String id);

}
