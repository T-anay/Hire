package com.talhaanay.is_ilan.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class JobPostingCreateDto {

    @NotBlank(message = "İlan başlığı boş olamaz.")
    @Size(min = 5, max = 100, message = "Başlık 5 ila 100 karakter arasında olmalıdır.")
    private String title;

    @NotBlank(message = "Açıklama boş olamaz.")
    @Size(min = 20, message = "Açıklama en az 20 karakter olmalıdır.")
    private String description;

    @NotBlank(message = "Lokasyon boş olamaz.")
    private String location;

    @NotBlank(message = "Çalışma tipi boş olamaz.")
    private String jobType;

    @NotNull(message = "Son başvuru tarihi boş olamaz.")
    @Future(message = "Son başvuru tarihi geçmiş bir tarih olamaz.")
    private LocalDateTime lastDateToApply;
}