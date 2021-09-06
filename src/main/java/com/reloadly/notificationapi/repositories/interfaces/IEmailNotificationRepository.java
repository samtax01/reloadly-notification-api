package com.reloadly.notificationapi.repositories.interfaces;

import com.reloadly.notificationapi.models.EmailNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmailNotificationRepository extends JpaRepository<EmailNotification, Long> { }
