package com.oscaris.caterers.oscaris_company_service.service.impl;
/*
*
@author ameda
@project Oscaris-caterers
*
*/

import com.oscaris.caterers.oscaris_company_service.dto.Company;
import com.oscaris.caterers.oscaris_company_service.dto.CompanyAddress;
import com.oscaris.caterers.oscaris_company_service.dto.requests.CompanyRequest;
import com.oscaris.caterers.oscaris_company_service.repository.CompanyRepository;
import com.oscaris.caterers.oscaris_company_service.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository){
        this.companyRepository = companyRepository;
    }

    @Override
    public String addCompany(CompanyRequest companyRequest) {
        CompanyAddress companyAddress = CompanyAddress.builder()
                .county(companyRequest.getCompanyAddressRequest().getCounty())
                .constituency(companyRequest.getCompanyAddressRequest().getConstituency())
                .street(companyRequest.getCompanyAddressRequest().getStreet())
                .build();
        Company company = Company.builder()
                .companyName(companyRequest.getCompanyName())
                .companyAddress(companyAddress)
                .email(companyRequest.getEmail())
                .build();

        Company savedCompany = companyRepository.save(company);
        return savedCompany
                .getCompanyName()
                .equals(companyRequest.getCompanyName()) ? "successful":"failed";
    }

    @Override
    public Company getCompanyById(Long companyId) {
        return companyRepository.findById(companyId)
                .orElseThrow(()-> new RuntimeException("Company could not be found"));
    }
}
