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
public class SmsNotificationRepository {

    @Value("${api.mail}")
    private String mailApiLink;

    private final ISmsNotificationRepository iSmsNotificationRepository;
    private final ModelMapper modelMap;


    public SmsNotificationRepository(ISmsNotificationRepository iSmsNotificationRepository, ModelMapper modelMap) {
        this.iSmsNotificationRepository = iSmsNotificationRepository;
        this.modelMap = modelMap;
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
     * Send SMS Notification
     */
    private void sendNotificationSms(SmsNotification request){
        try{

            //HttpRequest.make(mailApiLink, HttpMethod.POST, emailRequest, String.class).subscribe( x->log.info("Email sent successfully. " + x) );
        }catch (Exception ex){
            log.error("Unable to send email. " + ex.getMessage());
        }
    }


    /**
     * Get all SMS Notifications
     */
    public Mono<List<SmsResponse>> getNotificationSms(){
        return Mono.just(iSmsNotificationRepository.findAll().stream().map(x-> modelMap.map(x, SmsResponse.class) ).collect(Collectors.toList()));
    }





}
