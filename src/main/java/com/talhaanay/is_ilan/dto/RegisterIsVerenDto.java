package com.talhaanay.is_ilan.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterIsVerenDto {

    @NotBlank(message = "E-posta boş olamaz.")
    @Email(message = "Geçerli bir e-posta adresi girin.")
    private String email;

    @NotBlank(message = "Şifre boş olamaz.")
    @Size(min = 8, max = 20, message = "Şifre 8 ile 20 karakter arasında olmalıdır.")
    private String password;

    @NotBlank(message = "Şirket adı boş olamaz.")
    private String companyName;

    private String companyLocation;
    private String aboutCompany;
    private String industry;
}