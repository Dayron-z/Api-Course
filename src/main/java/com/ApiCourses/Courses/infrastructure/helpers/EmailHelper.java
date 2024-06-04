package com.ApiCourses.Courses.infrastructure.helpers;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class EmailHelper {
    /*libreria*/
    private final JavaMailSender mailSender;

    public void sendMail(String destinity, String name, String course, String instructor){
        MimeMessage message = mailSender.createMimeMessage();


        //Es la parte mas importante para nosotros, nos sirve para pasarle lo que queramos
        String htmlContent = this.readHtmlTemplate(name, course, instructor);


        try {
            message.setFrom(new InternetAddress("zbastian.zapata@gmail.com"));
            message.setSubject("Confirmación de inscripción");


            message.setRecipients(MimeMessage.RecipientType.TO, destinity);
            message.setContent(htmlContent, MediaType.TEXT_HTML_VALUE);

            mailSender.send(message);
            System.out.println("Email enviado");

        } catch (MessagingException e) {
            System.out.println("Error, no se pudo enviar el correo" + e.getMessage());
        }


    }



    private String readHtmlTemplate(String name, String course, String instructor){
        /*Indicamos donde se encuentra el html*/
        final Path path = Paths.get("src/main/resources/emails/email_template.html");

        try(var lines  = Files.lines(path)) {
            //Todo_ el html en una linea
            var html = lines.collect(Collectors.joining());

            return html.replace("{name}", name).replace("{course}", course).replace("{instructor}", instructor);
        }catch (IOException e){
            System.out.println("No se pudo leer el html ");
            throw new RuntimeException();
        }

    }

}
