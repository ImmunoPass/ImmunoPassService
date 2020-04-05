package com.immunopass.service;

import com.immunopass.model.enums.EntityStatus;
import com.immunopass.model.Organization;
import com.immunopass.model.enums.OrganizationType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrganizationService {
    // TODO: change dummy implementations
    public Organization getOrganizationById(Long id) {
        // DUMMY IMPL
        return new Organization(id, "ABC ORG", OrganizationType.COMMON, EntityStatus.ACTIVE, 25, 10);
    }

    public List<Organization> getOrganizations() {
        // DUMMY IMPL : This should return all non governmental organizations
        List<Organization> orgs = new ArrayList<Organization>();
        orgs.add(new Organization(new Long(234), "ABC ORG", OrganizationType.COMMON, EntityStatus.ACTIVE, 25, 10));
        orgs.add(new Organization(new Long(123), "DEF ORG", OrganizationType.COMMON, EntityStatus.ACTIVE, 25000, 1000));
        return orgs;
    }
}
