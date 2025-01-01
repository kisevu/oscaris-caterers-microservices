package com.oscaris.caterers.oscaris_company_service.repository;
/*
*
@author ameda
@project Oscaris-caterers
*
*/

import com.oscaris.caterers.oscaris_company_service.dto.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {

    @Query(value = "update company set email=:email where company_id=:companyId",nativeQuery = true)
    @Modifying
    void updateCompany(@Param("email") String email,
                       @Param("companyId") Long companyId);

}
