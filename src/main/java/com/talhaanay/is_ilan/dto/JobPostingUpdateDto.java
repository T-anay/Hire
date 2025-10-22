package com.talhaanay.is_ilan.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class JobPostingUpdateDto {
    @Size(min = 5, max = 100, message = "Başlık 5 ila 100 karakter arasında olmalıdır.")
    private String title;

    @Size(min = 20, message = "Açıklama en az 20 karakter olmalıdır.")
    private String description;

    private String location;

    private String jobType;

    @Future(message = "Son başvuru tarihi geçmiş bir tarih olamaz.")
    private LocalDateTime lastDateToApply;

    @Pattern(regexp = "^(ACIK|KAPALI)$", message = "Durum sadece 'ACIK' veya 'KAPALI' olabilir.")
    private String status;
}