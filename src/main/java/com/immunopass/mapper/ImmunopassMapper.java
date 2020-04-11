package com.immunopass.mapper;

import com.immunopass.entity.ImmunopassEntity;
import com.immunopass.model.Immunopass;
import lombok.experimental.UtilityClass;


@UtilityClass
public class ImmunopassMapper {
    public Immunopass map(ImmunopassEntity immunopassEntity) {
        return Immunopass.builder()
                .id(immunopassEntity.getId())
                .userName(immunopassEntity.getUserName())
                .userMobile(immunopassEntity.getUserMobile())
                .userEmpId(immunopassEntity.getUserEmpId())
                .userGovernmentId(immunopassEntity.getUserGovernmentId())
                .userLocation(immunopassEntity.getUserLocation())
                .immunopassCode(immunopassEntity.getImmunopassCode())
                .immunoTestResult(immunopassEntity.getImmunoTestResult())
                .build();
    }
}
