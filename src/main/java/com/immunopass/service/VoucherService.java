package com.immunopass.service;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.immunopass.controller.VoucherController;
import com.immunopass.enums.VoucherStatus;
import com.immunopass.mapper.VoucherMapper;
import com.immunopass.model.Account;
import com.immunopass.model.Voucher;
import com.immunopass.model.VoucherRequest;
import com.immunopass.repository.VoucherRepository;


@Service
public class VoucherService implements VoucherController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private VoucherRepository voucherRepository;

    @Override
    public Voucher redeemVoucher(@Valid VoucherRequest voucherRequest) {
        Account account =
                (Account) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal();
        if (account.getPathologyLabId() != null) {
            return voucherRepository.findByVoucherCode(voucherRequest.getVoucherCode())
                    .filter(voucherEntity -> voucherEntity.getStatus() == VoucherStatus.PROCESSED)
                    .map(voucherEntity -> {
                        voucherEntity.setStatus(VoucherStatus.REDEEMED);
                        voucherEntity.setRedeemedAccountId(account.getId());
                        voucherEntity.setRedeemedPathologyLabId(account.getPathologyLabId());
                        return voucherRepository.save(voucherEntity);
                    })
                    .map(VoucherMapper::map)
                    .orElseThrow(() ->
                            new ResponseStatusException(HttpStatus.BAD_REQUEST, "No valid voucher found!"));
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User doesn't belong to any pathology lab.");
        }
    }

    @Override
    public Voucher getVoucher(@Valid VoucherRequest voucherRequest) {
        Account account =
                (Account) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal();
        if (account.getPathologyLabId() != null) {
            return voucherRepository.findByVoucherCode(voucherRequest.getVoucherCode())
                    .map(VoucherMapper::map)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No voucher found!"));
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User doesn't belong to any pathology lab.");
        }
    }

}
