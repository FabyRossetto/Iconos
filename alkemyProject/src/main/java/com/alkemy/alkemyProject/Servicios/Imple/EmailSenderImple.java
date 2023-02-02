/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alkemy.alkemyProject.Servicios.Imple;

import com.alkemy.alkemyProject.Servicios.EmailSender;
//importaciones de SendGrid
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

//importanciones de SpringBoot
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;
/**
 *
 * @author Fabi
 */
@Service
public class EmailSenderImple implements EmailSender {
    @Autowired
    private Environment env;
    
    @Value("${alkemy.icons.email.sender}")//es el nombre de la property
    private String emailSender;
    
    @Value("${alkemy.icons.email.enabled}")
    private boolean enabled;

    @Override
    public void enviarMailDeBienvenida(String to) {
        if(!enabled){
            return;
        }
        String apiKey=env.getProperty("EMAIL-API-KEY");
        Email fromEmail= new Email(emailSender);
        Email toEmail= new Email(to);
        Content content=new Content(
        "text/plain",
        "Bienvenido a Alkemy Icons"
        ) ;
        String subject="Alkemy Icons";
        
        Mail mail=new Mail(fromEmail,subject,toEmail,content);
        SendGrid sg= new SendGrid(apiKey);
        Request request= new Request();
        try{
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response= sg.api(request);
            
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
            
            
        }catch (IOException ex){
            System.out.println("Error al tratar de enviar el email");
        }
    }
}
