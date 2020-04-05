package com.immunopass.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.immunopass.controller.ImmunopassController;
import com.immunopass.entity.ImmunopassEntity;
import com.immunopass.model.Immunopass;
import com.immunopass.repository.ImmunopassRepository;


@Service
public class ImmunopassService implements ImmunopassController {

    @Autowired
    ImmunopassRepository immunopassRepository;

    @Override public Immunopass createImmunopass(final Immunopass immunopass) {
        ImmunopassEntity immunopassEntity =
                ImmunopassEntity.builder()
                        .userName(immunopass.getUserName())
                        .userMobile(immunopass.getUserMobile())
                        .userEmpId(immunopass.getUserMobile())
                        .userGovernmentId(immunopass.getUserGovernmentId())
                        .userLocation(immunopass.getUserLocation())
                        .immunopassCode("RANDOM_CODE") //TODO: Generate a unique random code.
                        .immunoTestResult(immunopass.getImmunoTestResult())
                        .build();
        immunopassEntity = immunopassRepository.save(immunopassEntity);
        return mapEntityToModel(immunopassEntity);
    }

    @Override public Immunopass verifyImmunopass(final Immunopass immunopass) {
        if(immunopass.getImmunopassCode()!=null) {
            return immunopassRepository
                    .findByImmunopassCode(immunopass.getImmunopassCode())
                    .map(this::mapEntityToModel)
                    .orElse(null);
        } else if(immunopass.getUserMobile()!=null) {
            return immunopassRepository
                    .findByUserMobile(immunopass.getUserMobile())
                    .map(this::mapEntityToModel)
                    .orElse(null);
        } else {
            return null;
        }
    }

    private Immunopass mapEntityToModel(ImmunopassEntity immunopassEntity) {
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
