package com.talhaanay.is_ilan.controller;

import com.talhaanay.is_ilan.dto.*;
import com.talhaanay.is_ilan.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register/is-arayan")
    public ResponseEntity<String> registerIsArayan(
            @Valid @RequestBody RegisterIsArayanDto registerDto) {

        String response = authService.registerIsArayan(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/register/is-veren")
    public ResponseEntity<String> registerIsVeren(
            @Valid @RequestBody RegisterIsVerenDto registerDto) {

        String response = authService.registerIsVeren(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(
            @Valid @RequestBody LoginDto loginDto) {

        LoginResponseDto response = authService.login(loginDto);
        return ResponseEntity.ok(response);
    }
}