package com.reloadly.notificationapi.repositories;

import com.reloadly.notificationapi.enums.NotificationStatus;
import com.reloadly.notificationapi.helpers.CustomException;
import com.reloadly.notificationapi.models.EmailNotification;
import com.reloadly.notificationapi.models.SmsNotification;
import com.reloadly.notificationapi.models.requests.EmailRequest;
import com.reloadly.notificationapi.models.requests.SmsRequest;
import com.reloadly.notificationapi.models.responses.EmailResponse;
import com.reloadly.notificationapi.models.responses.SmsResponse;
import com.reloadly.notificationapi.repositories.interfaces.IEmailNotificationRepository;
import com.reloadly.notificationapi.repositories.interfaces.ISmsNotificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class NotificationRepository {

    @Value("${api.mail}")
    private String mailApiLink;

    private final IEmailNotificationRepository iEmailNotificationRepository;
    private final ISmsNotificationRepository iSmsNotificationRepository;
    private final ModelMapper modelMap;


    public NotificationRepository(IEmailNotificationRepository iEmailNotificationRepository, ISmsNotificationRepository iSmsNotificationRepository, ModelMapper modelMap) {
        this.iEmailNotificationRepository = iEmailNotificationRepository;
        this.iSmsNotificationRepository = iSmsNotificationRepository;
        this.modelMap = modelMap;
    }


    /**
     * Send Email
     */
    public Mono<EmailResponse> sendEmail(EmailRequest request) throws CustomException{
        var email = modelMap.map(request, EmailNotification.class);

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
     * Send SMS
     */
    public Mono<SmsResponse> sendSms(SmsRequest request) throws CustomException{
        var sms = modelMap.map(request, SmsNotification.class);
        try{
            sendNotificationSms(sms);
            sms.setStatus(NotificationStatus.SENT);
            iSmsNotificationRepository.saveAndFlush(sms);
            return Mono.just(modelMap.map(sms, SmsResponse.class));
        }catch (Exception ex) {
            sms.setStatus(NotificationStatus.FAILED);
            iSmsNotificationRepository.saveAndFlush(sms);
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
     * Get all SMS Notifications
     */
    public Mono<List<SmsResponse>> getNotificationSms(){
        return Mono.just(iEmailNotificationRepository.findAll().stream().map(x-> modelMap.map(x, SmsResponse.class) ).collect(Collectors.toList()));
    }





    /**
     * Send Email Notification
     */
    private void sendNotificationEmail(EmailNotification request){
        try{


            //HttpRequest.make(mailApiLink, HttpMethod.POST, emailRequest, String.class).subscribe( x->log.info("Email sent successfully. " + x) );
        }catch (Exception ex){
            log.error("Unable to send email. " + ex.getMessage());
        }
    }


    /**
     * Send SMS Notification
     */
    private void sendNotificationSms(SmsNotification request){
        try{


            //HttpRequest.make(mailApiLink, HttpMethod.POST, emailRequest, String.class).subscribe( x->log.info("Email sent successfully. " + x) );
        }catch (Exception ex){
            log.error("Unable to send email. " + ex.getMessage());
        }
    }


}
