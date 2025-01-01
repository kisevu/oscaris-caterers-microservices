package com.oscaris.caterers.invoicing_system.services;
/*
*
@author ameda
@project Oscaris-caterers
*
*/

import com.oscaris.caterers.invoicing_system.repository.InvoiceRepository;
import com.oscaris.caterers.invoicing_system.clients.CompanyServiceClient;
import com.oscaris.caterers.invoicing_system.clients.dto.Company;
import com.oscaris.caterers.invoicing_system.dto.Invoice;
import com.oscaris.caterers.invoicing_system.dto.InvoiceItem;
import com.oscaris.caterers.invoicing_system.dto.InvoiceItemRequest;
import com.oscaris.caterers.invoicing_system.dto.InvoiceRequest;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@Slf4j
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;

    private final CompanyServiceClient companyServiceClient;

    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository,
                          CompanyServiceClient companyServiceClient) {
        this.invoiceRepository = invoiceRepository;
        this.companyServiceClient = companyServiceClient;
    }



    @Modifying
    public Invoice saveInvoice(InvoiceRequest invoiceRequest){

        InvoiceItemRequest invoiceItemRequest = invoiceRequest.getInvoiceItemRequest();

        Company customerById = companyServiceClient.getCustomerById(invoiceRequest.getCustomerId());

        InvoiceItem invoiceItem = InvoiceItem
                .builder()
                .description(invoiceItemRequest.getDescription())
                .unitPrice(invoiceItemRequest.getUnitPrice())
                .quantity(invoiceItemRequest.getQuantity())
                .build();

        Invoice invoice = Invoice.builder()
                .invoiceNumber(invoiceRequest.getInvoiceNumber())
                .customerName(customerById.getCompanyName())
                .customerEmail(customerById.getEmail())
                .issueDate(LocalDate.now())
                .dueDate(LocalDate.now().plusWeeks(1))
                .totalAmount(invoiceRequest.getTotalAmount())
                .items(List.of(invoiceItem))
                .build();

        invoiceItem.setInvoice(invoice);

        return invoiceRepository.save(invoice);
    }

    public Invoice getInvoiceById(Long invoiceId){
        return invoiceRepository.findById(invoiceId)
                .orElseThrow(
                        ()->new RuntimeException("invoice with ID :"+invoiceId+"not found."));
    }

    public String updateInvoiceAndItem(String email, Long invoiceId){
        Invoice invoiceById = getInvoiceById(invoiceId);
        log.info("Existing email:{}",invoiceById.getCustomerEmail());
        invoiceRepository.updateInvoiceAndItem(email,invoiceId);
        log.info("Changed email:{}",invoiceById.getCustomerEmail());
        return invoiceById.getCustomerEmail().equals(email) ? "updated successfully." : "update failed.";
    }
}
