package com.immunopass.controller;

import java.util.List;
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
    public Organization getOrganization(@PathVariable Long id);

    @GetMapping("")
    public List<Organization> getOrganizations();

    @PostMapping
    public Organization createOrganization(@RequestBody Organization organization);
}
