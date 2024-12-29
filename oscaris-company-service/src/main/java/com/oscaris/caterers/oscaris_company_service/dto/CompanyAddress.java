package com.oscaris.caterers.oscaris_company_service.dto;
/*
*
@author ameda
@project Oscaris-caterers
*
*/


import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyAddress {

    private String county;
    private String constituency;
    private String street;
}
