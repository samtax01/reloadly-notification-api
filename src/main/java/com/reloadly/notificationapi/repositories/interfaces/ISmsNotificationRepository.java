package com.reloadly.notificationapi.repositories.interfaces;

import com.reloadly.notificationapi.models.EmailNotification;
import com.reloadly.notificationapi.models.SmsNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISmsNotificationRepository extends JpaRepository<SmsNotification, Long> { }
