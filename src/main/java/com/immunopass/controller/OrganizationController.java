package com.immunopass.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.immunopass.model.Organization;


@RestController
@RequestMapping("/v1/organizations")
public interface OrganizationController {

    /*
     * Disabled this API, as we haven't specified yet who will have the authorization to create a organizations.
     * Once we have specified a specific role, then we can enable this.
     */
    //@PostMapping("")
    Organization createOrganization(@Valid @RequestBody final Organization organization);

    @GetMapping("/{id}")
    Organization getOrganization(@NotNull @PathVariable final String id);

}
