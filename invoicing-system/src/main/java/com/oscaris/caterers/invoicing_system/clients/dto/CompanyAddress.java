package com.oscaris.caterers.invoicing_system.clients.dto;
/*
*
@author ameda
@project Oscaris-caterers
*
*/


import jakarta.persistence.Embeddable;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyAddress {

    private String county;
    private String constituency;
    private String street;
}
