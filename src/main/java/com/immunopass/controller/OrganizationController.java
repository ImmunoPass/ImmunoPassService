package com.immunopass.controller;

import com.immunopass.model.Organization;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;


@RestController
@RequestMapping("/v1/organizations")
public interface OrganizationController {

    @GetMapping("/{id}")
    Organization getOrganization(@NotNull @PathVariable final Long id);

    @GetMapping
    List<Organization> getOrganizations();

    @PostMapping("")
    Organization createOrganization(@NotNull @RequestBody final Organization organization);
}
