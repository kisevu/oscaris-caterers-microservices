package com.oscaris.caterers.invoicing_system.api;
/*
*
@author ameda
@project Oscaris-caterers
*
*/

import com.oscaris.caterers.invoicing_system.dto.Invoice;
import com.oscaris.caterers.invoicing_system.dto.InvoiceRequest;
import com.oscaris.caterers.invoicing_system.services.EmailService;
import com.oscaris.caterers.invoicing_system.services.InvoicePDFService;
import com.oscaris.caterers.invoicing_system.services.InvoiceService;
import jakarta.mail.MessagingException;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/invoice")
public class InvoiceController {


    private final InvoicePDFService invoicePDFService;
    private final EmailService emailService;
    private final InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoicePDFService invoicePDFService,
                             EmailService emailService,
                             InvoiceService invoiceService) {
        this.invoicePDFService = invoicePDFService;
        this.emailService = emailService;
        this.invoiceService = invoiceService;
    }

    @GetMapping("/name")
    public String name(){
        return "invoicing-system";
    }

    @PostMapping("/invoice")
    public Invoice invoice(@RequestBody InvoiceRequest invoiceRequest){
        return invoiceService.saveInvoice(invoiceRequest);
    }

    @PostMapping("/generate/{invoiceId}")
    public String generateInvoiceAndSendEmail(@PathVariable Long invoiceId) throws MessagingException, IOException {
        // Retrieve the invoice from the database (assuming invoiceService handles this)
        Invoice invoice = invoiceService.getInvoiceById(invoiceId);

        // Generate PDF
        ByteArrayOutputStream pdfContent = invoicePDFService.generatePdf(invoice);

        // Send the email with the PDF attached
        emailService.sendInvoiceEmail(invoice.getCustomerEmail(), pdfContent);

        return "Invoice PDF sent successfully!";
    }

    @PatchMapping("/update")
    public ResponseEntity<?> updateInvoiceAndItem(@RequestParam("email") String email,
                                                  @RequestParam("invoiceId") Long invoiceId){
        return new ResponseEntity<>(invoiceService.updateInvoiceAndItem(email,invoiceId),
                HttpStatus.OK);
    }

}
