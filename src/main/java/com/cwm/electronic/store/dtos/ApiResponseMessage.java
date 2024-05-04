package com.cwm.electronic.store.dtos;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.net.http.HttpClient;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ApiResponseMessage {

    private String message;
    private boolean success;
    private HttpStatus status;
}
