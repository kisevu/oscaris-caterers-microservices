package com.oscaris.caterers.oscaris_company_service.service;
/*
*
@author ameda
@project Oscaris-caterers
*
*/

import com.oscaris.caterers.oscaris_company_service.dto.Company;
import com.oscaris.caterers.oscaris_company_service.dto.requests.CompanyRequest;

public interface CompanyService {

    String addCompany(CompanyRequest companyRequest);

    Company getCompanyById(Long companyId);
}
