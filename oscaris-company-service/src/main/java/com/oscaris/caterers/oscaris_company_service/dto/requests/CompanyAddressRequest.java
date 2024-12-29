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
public class CompanyAddressRequest {
    private String county;
    private String constituency;
    private String street;
}
