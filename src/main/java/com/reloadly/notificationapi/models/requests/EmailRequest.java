package com.reloadly.notificationapi.models.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.util.annotation.Nullable;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmailRequest {

    @Nullable
    @Email
    @Column(length = 50)
    private String from; //notification@reloadly.com

    @NotNull
    @Email
    @Column(length = 50)
    private String to;

    @NotNull
    @Column(length = 100)
    private String subject;

    @NotNull
    private String body;

}
