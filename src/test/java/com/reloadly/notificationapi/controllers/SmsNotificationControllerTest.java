package com.reloadly.notificationapi.controllers;

import com.reloadly.notificationapi.Seeder;
import com.reloadly.notificationapi.helpers.Response;
import com.reloadly.notificationapi.repositories.interfaces.IEmailNotificationRepository;
import com.reloadly.notificationapi.repositories.interfaces.ISmsNotificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SmsNotificationControllerTest {


    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private ISmsNotificationRepository iNotificationRepository;

    @BeforeEach
    public void setUp() {
        // webTestClient = webTestClient.mutate().responseTimeout(Duration.ofMillis(30000)).build();
    }



    @Test
    void canSendNotification(){
        // Arrange
        // Act
        webTestClient
                .post()
                .uri("/api/v1/notification/sms")
                .body(BodyInserters.fromValue(Seeder.getSmsRequest()))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Response.class)
                .value(response -> {
                    log.info("\nResponse is {}", response);

                    // Assert
                    Assertions.assertTrue(response.isStatus());
                });
    }


}
