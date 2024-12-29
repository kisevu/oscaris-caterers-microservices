package com.oscaris.caterers.oscaris_company_service.dto.requests;
/*
*
@author ameda
@project Oscaris-caterers
*
*/

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyRequest {

    private String companyName;
    private CompanyAddressRequest companyAddressRequest;
    private String email;
}
