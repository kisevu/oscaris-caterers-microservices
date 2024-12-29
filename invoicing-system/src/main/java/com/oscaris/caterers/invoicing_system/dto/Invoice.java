package com.oscaris.caterers.invoicing_system.dto;
/*
*
@author ameda
@project Oscaris-caterers
*
*/

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long invoiceId;
    private String invoiceNumber;
    private String customerName;
    private String customerEmail;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private BigDecimal totalAmount;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<InvoiceItem> items = new ArrayList<>();

}
