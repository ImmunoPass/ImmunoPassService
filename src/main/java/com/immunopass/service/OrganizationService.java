package com.immunopass.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.immunopass.controller.OrganizationController;
import com.immunopass.entity.OrganizationEntity;
import com.immunopass.enums.EntityStatus;
import com.immunopass.enums.ResourceType;
import com.immunopass.mapper.OrganizationMapper;
import com.immunopass.model.Account;
import com.immunopass.model.Organization;
import com.immunopass.repository.OrganizationRepository;


@Service
public class OrganizationService implements OrganizationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private OrganizationRepository organizationRepository;

    @Override
    public Organization createOrganization(final Organization organization) {
        OrganizationEntity organizationEntity =
                OrganizationEntity.builder()
                        .name(organization.getName())
                        .type(organization.getType())
                        .status(EntityStatus.ACTIVE)
                        .totalVouchers(organization.getTotalVouchers())
                        .allotedVouchers(0)
                        .redeemedVouchers(0)
                        .build();
        organizationEntity = organizationRepository.save(organizationEntity);
        return OrganizationMapper.map(organizationEntity);
    }

    @Override
    public Organization getOrganization(final String id) {
        if (ResourceType.CURRENT.toString().equals(id)) {
            Account account = (Account) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();
            if (account.getOrganizationId() != null) {
                return organizationRepository
                        .findById(account.getOrganizationId())
                        .filter(organization -> organization.getStatus() == EntityStatus.ACTIVE)
                        .map(OrganizationMapper::map)
                        .orElseThrow(() ->
                                new ResponseStatusException(HttpStatus.NOT_FOUND, "Organization doesn't exist."));
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User doesn't belong to any organization.");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Organization ID.");
        }
    }

}
