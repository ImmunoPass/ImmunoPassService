package com.immunopass.model;

import javax.validation.constraints.NotNull;
import com.immunopass.enums.EntityStatus;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
@NotNull
public class PathologyLab {
    private final Long id;
    @NotNull
    private final String name;
    private final EntityStatus status;
}
