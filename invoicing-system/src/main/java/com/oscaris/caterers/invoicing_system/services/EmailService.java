package com.oscaris.caterers.invoicing_system.services;
/*
*
@author ameda
@project Oscaris-caterers
*
*/

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.util.ByteArrayDataSource;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;


    public void sendInvoiceEmail(String recipientEmail, ByteArrayOutputStream pdfContent) throws MessagingException,
            IOException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(recipientEmail);
        helper.setSubject("Invoice for This Month");
        helper.setText("Please find your invoice attached.");

        // Attach PDF file
        helper.addAttachment("Invoice.pdf",
                new ByteArrayDataSource(pdfContent.toByteArray(), "application/pdf"));

        javaMailSender.send(message);
    }

}
