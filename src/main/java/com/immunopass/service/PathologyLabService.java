package com.immunopass.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.immunopass.controller.PathologyLabController;
import com.immunopass.entity.PathologyLabEntity;
import com.immunopass.enums.EntityStatus;
import com.immunopass.model.PathologyLab;
import com.immunopass.repository.PathologyLabRepository;


@Service
public class PathologyLabService implements PathologyLabController {

    @Autowired
    private PathologyLabRepository pathologyLabRepository;

    @Override public PathologyLab createPathologyLab(final PathologyLab pathologyLab) {
        PathologyLabEntity pathologyLabEntity =
                PathologyLabEntity.builder()
                        .name(pathologyLab.getName())
                        .status(EntityStatus.ACTIVE)
                        .build();
        pathologyLabEntity = pathologyLabRepository.save(pathologyLabEntity);
        return mapEntityToModel(pathologyLabEntity);
    }

    private PathologyLab mapEntityToModel(PathologyLabEntity pathologyLabEntity) {
        return PathologyLab.builder()
                .id(pathologyLabEntity.getId())
                .name(pathologyLabEntity.getName())
                .status(pathologyLabEntity.getStatus())
                .build();
    }

}
