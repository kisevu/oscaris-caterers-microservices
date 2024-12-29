package com.oscaris.caterers.oscaris_company_service.repository;
/*
*
@author ameda
@project Oscaris-caterers
*
*/

import com.oscaris.caterers.oscaris_company_service.dto.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {
}
