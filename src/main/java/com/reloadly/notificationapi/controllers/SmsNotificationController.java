package com.reloadly.notificationapi.controllers;

import com.reloadly.notificationapi.helpers.CustomException;
import com.reloadly.notificationapi.helpers.Response;
import com.reloadly.notificationapi.helpers.Validator;
import com.reloadly.notificationapi.models.requests.SmsRequest;
import com.reloadly.notificationapi.models.responses.SmsResponse;
import com.reloadly.notificationapi.repositories.SmsNotificationRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notification/sms")
public class SmsNotificationController {

    private final SmsNotificationRepository repository;

    public SmsNotificationController(SmsNotificationRepository repository) {
        this.repository = repository;
    }


    @Operation(summary = "Send an sms Notification")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SMS Sent", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Response.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request",  content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Response.class)) })
    })
    public Mono<ResponseEntity<Response<SmsResponse>>> sendSms(@RequestBody SmsRequest request) throws CustomException {
        Validator.validate(request);
        return repository.sendSms(request).map(x-> ResponseEntity.ok(Response.success(x)));
    }


    @Operation(summary = "Get all sms notifications")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item list", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Response.class)) }),
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<Response<List<SmsResponse>>>> getAllSms(){
        return repository.getNotificationSms().map(x-> ResponseEntity.ok(Response.success(x)));
    }

}
