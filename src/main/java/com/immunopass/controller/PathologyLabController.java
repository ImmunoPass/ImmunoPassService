package com.immunopass.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.immunopass.model.PathologyLab;


@RestController
@RequestMapping("/v1/pathology_labs")
public interface PathologyLabController {

    /*
     * Disabled this API, as we haven't specified yet who will have the authorization to create a lab.
     * Once we have specified a specific role, then we can enable this.
     */
    //@PostMapping("")
    PathologyLab createPathologyLab(@Valid @RequestBody final PathologyLab pathologyLab);

    @GetMapping("/{id}")
    PathologyLab getPathologyLab(@NotNull @PathVariable final String id);

}
