package com.example.covoiturage_bdeb;

import com.example.covoiturage_bdeb.EmailSender.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class CovoiturageBdeBApplication {
@Autowired
EmailSenderService senderService;
    public static void main(String[] args) {
        SpringApplication.run(CovoiturageBdeBApplication.class, args);
    }
    @EventListener(ApplicationReadyEvent.class)
    public void sendMail(){

        //senderService.sendEmail("houas_yora@hotmail.fr","this is subject","this is bady email");
    }
}
