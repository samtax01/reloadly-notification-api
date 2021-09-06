package com.reloadly.notificationapi.models;

import com.reloadly.notificationapi.enums.NotificationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.util.annotation.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "email_notifications")
public class EmailNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Nullable
    @Email
    @Column(length = 50)
    private String from;

    @NotNull
    @Email
    @Column(length = 50)
    private String to;

    @NotNull
    @Column(length = 100)
    private String subject;

    @NotNull
    private String body;

    @NotNull
    @Enumerated(EnumType.STRING)
    private NotificationStatus status;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt = LocalDateTime.now();

}
