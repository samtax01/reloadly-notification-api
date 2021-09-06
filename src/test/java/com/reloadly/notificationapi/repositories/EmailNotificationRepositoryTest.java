package com.reloadly.notificationapi.repositories;

import com.reloadly.notificationapi.Seeder;
import com.reloadly.notificationapi.helpers.CustomException;
import com.reloadly.notificationapi.repositories.interfaces.IEmailNotificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.test.StepVerifier;

import java.util.Objects;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmailNotificationRepositoryTest {

    @Autowired
    private EmailNotificationRepository emailNotificationRepository;

    @MockBean
    private IEmailNotificationRepository iEmailNotificationRepository;



    @Test
    void canSendEmailNotification() throws CustomException {
        // Arrange
        // Act
        var data = Seeder.getEmailRequest();
        var notification = emailNotificationRepository.sendEmail(data);

        StepVerifier
                .create(notification)
                .expectNextMatches(response ->
                        // Assert
                        !Objects.isNull(response))
                .verifyComplete();
    }


}
