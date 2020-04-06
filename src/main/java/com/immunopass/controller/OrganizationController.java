package com.immunopass.controller;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.immunopass.model.Organization;


@RestController
@RequestMapping("/v1/organizations")
public interface OrganizationController {

    @GetMapping("/{id}")
    Organization getOrganization(@NotNull @PathVariable final Long id);

    @GetMapping
    List<Organization> getOrganizations();

    @PostMapping("")
    Organization createOrganization(@Valid @RequestBody final Organization organization);
}
