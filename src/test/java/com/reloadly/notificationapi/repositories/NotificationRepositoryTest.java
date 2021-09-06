package com.reloadly.notificationapi.repositories;

import com.reloadly.notificationapi.Seeder;
import com.reloadly.notificationapi.helpers.CustomException;
import com.reloadly.notificationapi.repositories.interfaces.INotificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.test.StepVerifier;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NotificationRepositoryTest {

    @Autowired
    private NotificationRepository notificationRepository;

    @MockBean
    private INotificationRepository iNotificationRepository;


    @Test
    void canSendNotification() throws CustomException {
        // Arrange
        // Act
        var data = Seeder.getEmailRequest();
        var notification = notificationRepository.create(data);

        StepVerifier
                .create(data)
                .expectNextMatches(response ->
                        // Assert
                        !Objects.isNull(response))
                .verifyComplete();
    }


}
