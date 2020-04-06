package com.immunopass.controller;

import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.immunopass.model.Immunopass;
import com.immunopass.model.VerifyImmunopassRequest;


@RestController
@RequestMapping("/v1")
public interface ImmunopassController {

    @PostMapping("/immunopasses")
    Immunopass createImmunopass(@Valid @RequestBody final Immunopass immunopass);

    @PostMapping("/verify_immunopass")
    Immunopass verifyImmunopass(@Valid @RequestBody final VerifyImmunopassRequest immunopass);

}
