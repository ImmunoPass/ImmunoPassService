package com.immunopass.service;

import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(PathologyLabService.class);

    private final PathologyLabRepository pathologyLabRepository;

    public PathologyLabService(final PathologyLabRepository pathologyLabRepository) {
        this.pathologyLabRepository = pathologyLabRepository;
    }

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

    @Override public PathologyLab getPathologyLab(final @NotNull String id) {
        if (ResourceType.CURRENT.toString().equals(id)) {
            Account account = (Account) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();
            if (account.getPathologyLabId() != null) {
                return pathologyLabRepository
                        .findById(account.getPathologyLabId())
                        .filter(organization -> organization.getStatus() == EntityStatus.ACTIVE)
                        .map(PathologyLabMapper::map)
                        .orElseThrow(() -> {
                            LOGGER.error("Pathology Lab for the logged in user either doesn't exist in the system now "
                                    + "or it's not ACTIVE.");
                            return new ResponseStatusException(HttpStatus.NOT_FOUND, "Pathology lab doesn't exist.");
                        });
            } else {
                LOGGER.error("User doesn't belong to any pathology lab.");
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User doesn't belong to any pathology lab.");
            }
        } else {
            LOGGER.error("Get Pathology Lab API doesn't support non logged in user right now. Received request for the "
                    + "non logged in user.");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Pathology Lab ID.");
        }
    }

}
