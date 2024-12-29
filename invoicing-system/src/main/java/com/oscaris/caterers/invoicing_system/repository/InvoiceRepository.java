package com.oscaris.caterers.invoicing_system.repository;
/*
*
@author ameda
@project Oscaris-caterers
*
*/

import com.oscaris.caterers.invoicing_system.dto.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice,Long> {
}
