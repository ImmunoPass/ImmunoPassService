package com.immunopass.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.immunopass.controller.ImmunopassController;
import com.immunopass.entity.ImmunopassEntity;
import com.immunopass.mapper.ImmunopassMapper;
import com.immunopass.model.Immunopass;
import com.immunopass.model.VerifyImmunopassRequest;
import com.immunopass.repository.ImmunopassRepository;
import com.immunopass.restclient.SMSService;


@Service
public class ImmunopassService implements ImmunopassController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private ImmunopassRepository immunopassRepository;

    @Autowired
    private SMSService smsService;

    @Override public Immunopass createImmunopass(final Immunopass immunopass) {
        if (immunopassRepository
                .findByUserMobile(immunopass.getUserMobile()).isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Resource with this mobile number already exists.");
        }
        // Generate unique immunopass code
        String immunopassCode;
        do {
            immunopassCode = RandomStringUtils.randomAlphabetic(8);
        } while (immunopassRepository.findByImmunopassCode(immunopassCode).isPresent());

        ImmunopassEntity immunopassEntity =
                ImmunopassEntity.builder()
                        .userName(immunopass.getUserName())
                        .userMobile(immunopass.getUserMobile())
                        .userEmpId(immunopass.getUserEmpId())
                        .userGovernmentId(immunopass.getUserGovernmentId())
                        .userLocation(immunopass.getUserLocation())
                        .immunopassCode(immunopassCode)
                        .immunoTestResult(immunopass.getImmunoTestResult())
                        .build();
        immunopassEntity = immunopassRepository.save(immunopassEntity);
        smsService.sendImmunoPassSMS(immunopassEntity.getUserMobile(), immunopassEntity.getImmunopassCode(),
                immunopassEntity.getImmunoTestResult().toString());
        return ImmunopassMapper.map(immunopassEntity);
    }

    @Override public Immunopass verifyImmunopass(final VerifyImmunopassRequest immunopass) {
        if (StringUtils.isNotBlank(immunopass.getImmunopassCode())) {
            return immunopassRepository
                    .findByImmunopassCode(immunopass.getImmunopassCode())
                    .map(ImmunopassMapper::map)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No immunopass found!"));
        } else if (StringUtils.isNotBlank(immunopass.getUserMobile())) {
            return immunopassRepository
                    .findByUserMobile(immunopass.getUserMobile())
                    .map(ImmunopassMapper::map)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No immunopass found!"));
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Missing required parameters: One of the Mobile Number or Immunopass Code is mandatory!");
        }
    }

}
