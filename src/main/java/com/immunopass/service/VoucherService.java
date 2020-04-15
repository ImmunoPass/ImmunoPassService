package com.immunopass.service;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.immunopass.controller.VoucherController;
import com.immunopass.enums.VoucherStatus;
import com.immunopass.mapper.VoucherMapper;
import com.immunopass.model.Voucher;
import com.immunopass.model.VoucherRequest;
import com.immunopass.repository.VoucherRepository;


@Service
public class VoucherService implements VoucherController {

    @Autowired
    private VoucherRepository voucherRepository;

    @Override
    public void claimVoucher(@Valid VoucherRequest voucherRequest) {
        String voucherCode = voucherRequest.getVoucherCode();
        voucherRepository.findByVoucherCode(voucherCode)
                .filter(voucherEntity -> voucherEntity.getStatus() == VoucherStatus.PROCESSED)
                .map(voucherEntity -> {
                    voucherEntity.setStatus(VoucherStatus.REDEEMED);
                    return voucherRepository.save(voucherEntity);
                })
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.BAD_REQUEST, "No valid voucher found!"));
    }

    @Override
    public Voucher getVoucher(@Valid VoucherRequest voucherRequest) {
        return voucherRepository.findByVoucherCode(voucherRequest.getVoucherCode())
                .map(VoucherMapper::map)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No voucher found!"));
    }

    @Override
    public List<Voucher> getVouchers() {
        return voucherRepository.findAll()
                .stream()
                .map(VoucherMapper::map)
                .collect(Collectors.toList());
    }
}
