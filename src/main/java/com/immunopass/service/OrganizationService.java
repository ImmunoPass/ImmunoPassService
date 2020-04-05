package com.immunopass.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.immunopass.enums.EntityStatus;
import com.immunopass.enums.OrganizationType;
import com.immunopass.model.Organization;


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
