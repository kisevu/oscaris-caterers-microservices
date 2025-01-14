package com.oscaris.caterers.oscaris_company_service.api;
/*
*
@author ameda
@project Oscaris-caterers
*
*/


import com.netflix.discovery.converters.Auto;
import com.oscaris.caterers.oscaris_company_service.dto.Company;
import com.oscaris.caterers.oscaris_company_service.dto.requests.CompanyRequest;
import com.oscaris.caterers.oscaris_company_service.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }


    @GetMapping("/companies")
    public List<String> getCompanies(){
       return List.of(
               "FRONTIER ENERGY",
               "HIVOS AFRICA",
               "SERENGETI ENERGY",
               "ASCENT AFRICA"
       );
    }

    @PostMapping("/add-company")
    public ResponseEntity<?> addCompany(@RequestBody CompanyRequest companyRequest){
        return new ResponseEntity<>(companyService.addCompany(companyRequest),
                HttpStatus.CREATED);
    }

    @GetMapping("/company/{companyId}")
    Company getCustomerById(@PathVariable("companyId") Long companyId){
        return companyService.getCompanyById(companyId);
    }

    @PatchMapping("/update")
    public Company updateCompany(@RequestParam("email") String email,
                                 @RequestParam("companyId") Long companyId){
        return companyService.updateCompany(email,companyId);
    }
}
