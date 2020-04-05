package com.immunopass.service;

import com.immunopass.model.Voucher;
import com.immunopass.model.enums.VoucherStatus;
import org.springframework.stereotype.Service;

@Service
public class VoucherService {
    // TODO: change dummy implementation
    public Voucher getVoucherById(Long id) {
        return new Voucher(id,
        "VCODE_1",
        new Long(123),
        "maggi mcmaggiface",
        "02341521251",
        "EMP_123",
        "ID123456",
        "mumbai",
        VoucherStatus.ALLOTTED,
        new Long(567),
        new Long(890));
    };

    public void processVoucher(Long id, String action) {
        System.out.println("Performing action " + action + " on voucher = " + id);
    }
}
