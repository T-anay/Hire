package com.talhaanay.is_ilan.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ApplicationCreateDto {

    @Size(max = 2000, message = "Ön yazı en fazla 2000 karakter olabilir.")
    private String coverLetter;
}