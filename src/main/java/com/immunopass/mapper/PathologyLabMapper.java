package com.immunopass.mapper;

import com.immunopass.entity.PathologyLabEntity;
import com.immunopass.model.PathologyLab;
import lombok.experimental.UtilityClass;


@UtilityClass
public class PathologyLabMapper {
    public PathologyLab map(PathologyLabEntity pathologyLabEntity) {
        return PathologyLab.builder()
                .id(pathologyLabEntity.getId())
                .name(pathologyLabEntity.getName())
                .status(pathologyLabEntity.getStatus())
                .build();
    }
}
