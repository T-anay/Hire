package com.talhaanay.is_ilan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LoginResponseDto {

    private String token;
    private long userId;
}
