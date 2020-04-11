package com.immunopass.service;

import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.immunopass.controller.PathologyLabController;
import com.immunopass.entity.PathologyLabEntity;
import com.immunopass.enums.EntityStatus;
import com.immunopass.enums.ResourceType;
import com.immunopass.mapper.PathologyLabMapper;
import com.immunopass.model.Account;
import com.immunopass.model.PathologyLab;
import com.immunopass.repository.PathologyLabRepository;


@Service
public class PathologyLabService implements PathologyLabController {

    @Autowired
    private PathologyLabRepository pathologyLabRepository;

    @Override
    public PathologyLab createPathologyLab(final PathologyLab pathologyLab) {
        PathologyLabEntity pathologyLabEntity =
                PathologyLabEntity.builder()
                        .name(pathologyLab.getName())
                        .status(EntityStatus.ACTIVE)
                        .build();
        pathologyLabEntity = pathologyLabRepository.save(pathologyLabEntity);
        return PathologyLabMapper.map(pathologyLabEntity);
    }

    @Override public PathologyLab getPahtologyLab(final @NotNull String id) {
        if (ResourceType.CURRENT.toString().equals(id)) {
            Account account = (Account) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();
            if (account.getPathologyLabId() != null) {
                return pathologyLabRepository
                        .findById(account.getPathologyLabId())
                        .filter(organization -> organization.getStatus() == EntityStatus.ACTIVE)
                        .map(PathologyLabMapper::map)
                        .orElseThrow(() ->
                                new ResponseStatusException(HttpStatus.NOT_FOUND, "Pathology lab doesn't exist."));
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User doesn't belong to any pathology lab.");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Pathology Lab ID.");
        }
    }

}
