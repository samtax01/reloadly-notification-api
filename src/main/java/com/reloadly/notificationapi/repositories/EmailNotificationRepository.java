package com.reloadly.notificationapi.repositories;

import com.reloadly.notificationapi.configs.StaticData;
import com.reloadly.notificationapi.enums.NotificationStatus;
import com.reloadly.notificationapi.helpers.CustomException;
import com.reloadly.notificationapi.models.EmailNotification;
import com.reloadly.notificationapi.models.requests.EmailRequest;
import com.reloadly.notificationapi.models.responses.EmailResponse;
import com.reloadly.notificationapi.repositories.interfaces.IEmailNotificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class EmailNotificationRepository {


    private final IEmailNotificationRepository iEmailNotificationRepository;
    private final ModelMapper modelMap;
    private final JavaMailSender javaMailSender;


    public EmailNotificationRepository(IEmailNotificationRepository iEmailNotificationRepository, ModelMapper modelMap, JavaMailSender javaMailSender) {
        this.iEmailNotificationRepository = iEmailNotificationRepository;
        this.modelMap = modelMap;
        this.javaMailSender = javaMailSender;
    }


    /**
     * Send Email
     */
    public Mono<EmailResponse> sendEmail(EmailRequest request) throws CustomException{
        var email = modelMap.map(request, EmailNotification.class);
        log.info("Email request received {}", email);

        try{
            sendNotificationEmail(email);
            email.setStatus(NotificationStatus.SENT);
            iEmailNotificationRepository.saveAndFlush(email);
            return Mono.just(modelMap.map(email, EmailResponse.class));
        }catch (Exception ex){
            email.setStatus(NotificationStatus.FAILED);
            iEmailNotificationRepository.saveAndFlush(email);
            throw ex;
        }
    }




    /**
     * Get all Email Notifications
     */
    public Mono<List<EmailResponse>> getNotificationEmails(){
        return Mono.just(iEmailNotificationRepository.findAll().stream().map(x-> modelMap.map(x, EmailResponse.class) ).collect(Collectors.toList()));
    }




    /**
     * Send Email Notification
     */
    private void sendNotificationEmail(EmailNotification request){
        try{
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setFrom(StaticData.defaultNotificationEmail);
            mail.setTo(request.getTo());
            mail.setSubject(request.getSubject());
            mail.setText(request.getBody());
            javaMailSender.send(mail);

            //HttpRequest.make(mailApiLink, HttpMethod.POST, emailRequest, String.class).subscribe( x->log.info("Email sent successfully. " + x) );
        }catch (Exception ex){
            log.error("Unable to send email. " + ex.getMessage());
            throw ex;
        }
    }



}
