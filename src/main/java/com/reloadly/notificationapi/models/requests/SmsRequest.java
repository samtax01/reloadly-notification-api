package com.reloadly.notificationapi.models.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SmsRequest {

    @NotNull
    @Column(length = 20)
    private String to;

    @NotNull
    @Column(length = 50)
    private String message;

}
