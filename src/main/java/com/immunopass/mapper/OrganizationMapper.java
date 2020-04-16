package com.immunopass.mapper;

import com.immunopass.entity.OrganizationEntity;
import com.immunopass.model.Organization;
import lombok.experimental.UtilityClass;


@UtilityClass
public class OrganizationMapper {
    public Organization map(OrganizationEntity organizationEntity) {
        return Organization.builder()
                .id(organizationEntity.getId())
                .name(organizationEntity.getName())
                .type(organizationEntity.getType())
                .status(organizationEntity.getStatus())
                .totalVouchers(organizationEntity.getTotalVouchers())
                .allotedVouchers(organizationEntity.getAllotedVouchers())
                .redeemedVouchers(organizationEntity.getRedeemedVouchers())
                .build();
    }
}
