package com.immunopass.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.immunopass.controller.OrganizationController;
import com.immunopass.entity.OrganizationEntity;
import com.immunopass.enums.EntityStatus;
import com.immunopass.model.Organization;
import com.immunopass.repository.OrganizationRepository;


@Service
public class OrganizationService implements OrganizationController {

    @Autowired
    OrganizationRepository organizationRepository;

    @Override
    public Organization createOrganization(final Organization organization) {
        OrganizationEntity organizationEntity =
                OrganizationEntity.builder()
                        .name(organization.getName())
                        .type(organization.getType())
                        .status(EntityStatus.ACTIVE)
                        .totalVouchers(organization.getTotalVouchers())
                        .usedVouchers(0)
                        .build();
        organizationEntity = organizationRepository.save(organizationEntity);
        return mapEntityToModel(organizationEntity);
    }

    @Override
    public Organization getOrganization(final Long id) {
        return organizationRepository
                .findById(id)
                .map(this::mapEntityToModel)
                .orElse(null);
    }

    @Override
    public List<Organization> getOrganizations() {
        return organizationRepository
                .findAll()
                .stream()
                .map(this::mapEntityToModel)
                .collect(Collectors.toList());
    }

    private Organization mapEntityToModel(OrganizationEntity organizationEntity) {
        return Organization.builder()
                .id(organizationEntity.getId())
                .name(organizationEntity.getName())
                .type(organizationEntity.getType())
                .status(organizationEntity.getStatus())
                .totalVouchers(organizationEntity.getTotalVouchers())
                .usedVouchers(organizationEntity.getUsedVouchers())
                .build();
    }

}
