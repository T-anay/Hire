package com.talhaanay.is_ilan.services;

import com.talhaanay.is_ilan.dto.*;

public interface AuthService {

    String registerIsArayan(RegisterIsArayanDto registerDto);

    String registerIsVeren(RegisterIsVerenDto registerDto);

    LoginResponseDto login(LoginDto loginDto);
}
