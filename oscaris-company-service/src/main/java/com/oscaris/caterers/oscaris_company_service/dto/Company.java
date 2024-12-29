package com.oscaris.caterers.oscaris_company_service.dto;
/*
*
@author ameda
@project Oscaris-caterers
*
*/

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companyId;
    private String companyName;
    @Embedded
    private CompanyAddress companyAddress;
    private String email;
}
