package com.oscaris.caterers.invoicing_system.dto;
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

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceItemRequest {
    private String description;
    private BigDecimal unitPrice;
    private int quantity;
}