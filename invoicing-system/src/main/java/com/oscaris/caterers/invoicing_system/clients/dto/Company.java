package com.oscaris.caterers.invoicing_system.clients.dto;
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
public class Company {
    private String companyName;
    private CompanyAddress companyAddress;
    private String email;
}
