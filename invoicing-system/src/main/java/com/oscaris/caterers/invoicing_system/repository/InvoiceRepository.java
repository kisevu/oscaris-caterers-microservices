package com.oscaris.caterers.invoicing_system.repository;
/*
*
@author ameda
@project Oscaris-caterers
*
*/

import com.oscaris.caterers.invoicing_system.dto.Invoice;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface InvoiceRepository extends JpaRepository<Invoice,Long> {

    @Query(value = "update invoice set email=:email where invoice_id=:invoiceId", nativeQuery = true)
    @Modifying
    void updateInvoiceAndItem(@Param("email") String email,
                              @Param("invoiceId") Long invoiceId);

}
