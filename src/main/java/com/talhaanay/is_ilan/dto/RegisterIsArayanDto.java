package com.talhaanay.is_ilan.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RegisterIsArayanDto {

    @NotBlank(message = "E-posta boş olamaz.")
    @Email(message = "Geçerli bir e-posta adresi girin.")
    private String email;

    @NotBlank(message = "Şifre boş olamaz.")
    @Size(min = 8, max = 20, message = "Şifre 8 ile 20 karakter arasında olmalıdır.")
    private String password;

    @NotBlank(message = "İsim boş olamaz.")
    private String firstName;

    @NotBlank(message = "Soyisim boş olamaz.")
    private String lastName;

    private String phoneNumber;

    @NotNull(message = "Doğum tarihi boş olamaz.")
    @Past(message = "Doğum tarihi geçmiş bir tarih olmalıdır.")
    private LocalDate birthDate;

    private String location;
}