package com.reloadly.notificationapi;

import com.reloadly.notificationapi.models.requests.EmailRequest;

public class Seeder {


    public static EmailRequest getEmailRequest(){
        return EmailRequest
                .builder()
                .to("hello@samsonoyetola.com")
                .from("notification@reloadly.com")
                .subject("Hello World")
                .body("Can you get this mail")
                .build();
    }

}
