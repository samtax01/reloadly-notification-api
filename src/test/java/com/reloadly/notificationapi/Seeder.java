package com.reloadly.notificationapi;

import com.reloadly.notificationapi.models.requests.EmailRequest;
import com.reloadly.notificationapi.models.requests.SmsRequest;

public class Seeder {


    public static EmailRequest getEmailRequest(){
        return EmailRequest
                .builder()
                .to("hello@samsonoyetola.com")
                .subject("Hello World")
                .body("Can you get this mail")
                .build();
    }


    public static SmsRequest getSmsRequest(){
        return SmsRequest
                .builder()
                .to("+2348064816493")
                .message("Mello World")
                .build();
    }

}
