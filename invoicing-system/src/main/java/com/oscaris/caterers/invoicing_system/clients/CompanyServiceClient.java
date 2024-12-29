package com.oscaris.caterers.invoicing_system.clients;
/*
*
@author ameda
@project Oscaris-caterers
*
*/

import com.oscaris.caterers.invoicing_system.clients.dto.Company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CUSTOMER-SERVICE")
public interface CompanyServiceClient {

    @GetMapping("/customer/company/{companyId}")
    Company getCustomerById(@PathVariable("companyId") Long companyId);
}
