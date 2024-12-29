package com.oscaris.caterers.invoicing_system.services;
/*
*
@author ameda
@project Oscaris-caterers
*
*/

import com.oscaris.caterers.invoicing_system.dto.Invoice;
import com.oscaris.caterers.invoicing_system.dto.InvoiceItem;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.math.BigDecimal;

@Service
public class InvoicePDFService {

    public ByteArrayOutputStream generatePdf(Invoice invoice) throws IOException {
        // Create a document
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        // Set up fonts and basic content
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);
        contentStream.newLineAtOffset(50, 750);
        contentStream.showText("Invoice");
        contentStream.setFont(PDType1Font.HELVETICA, 12);
        contentStream.newLineAtOffset(0, -20);

        // Display basic invoice information
        contentStream.showText("Invoice Number: " + invoice.getInvoiceNumber());
        contentStream.newLineAtOffset(0, -20);
        contentStream.showText("Customer Name: " + invoice.getCustomerName());
        contentStream.newLineAtOffset(0, -20);
        contentStream.showText("Issue Date: " + invoice.getIssueDate());
        contentStream.newLineAtOffset(0, -20);
        contentStream.showText("Due Date: " + invoice.getDueDate());
        contentStream.newLineAtOffset(0, -20);
        contentStream.showText("Total Amount: $" + invoice.getTotalAmount());

        // Add a table for items
        contentStream.newLineAtOffset(0, -40);
        contentStream.showText("Items:");
        contentStream.newLineAtOffset(0, -20);

        // Set the column headers
        contentStream.showText("Description    | Unit Price    | Quantity    | Total");
        contentStream.newLineAtOffset(0, -20);

        // Set the content for each item
        for (InvoiceItem item : invoice.getItems()) {
            contentStream.showText(item.getDescription() + "    | " +
                    item.getUnitPrice() + "    | " +
                    item.getQuantity() + "    | " +
                    item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
            contentStream.newLineAtOffset(0, -20);
        }

        // Finalize the content stream
        contentStream.endText();
        contentStream.close();

        // Save the document to a byte array output stream
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        document.save(byteArrayOutputStream);
        document.close();

        return byteArrayOutputStream;
    }
}
