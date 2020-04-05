package com.immunopass.controller;

import javax.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.immunopass.model.Immunopass;


@RestController
@RequestMapping("/v1")
public interface ImmunopassController {

    @PostMapping("/immunopasses")
    Immunopass createImmunopass(@NotNull @RequestBody final Immunopass immunopass);

    @PostMapping("/verify_immunopass")
    Immunopass verifyImmunopass(@NotNull @RequestBody final Immunopass immunopass);

}
