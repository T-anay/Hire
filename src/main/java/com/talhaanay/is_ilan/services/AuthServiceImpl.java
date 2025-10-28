package com.talhaanay.is_ilan.services;

import com.talhaanay.is_ilan.dto.LoginDto;
import com.talhaanay.is_ilan.dto.LoginResponseDto;
import com.talhaanay.is_ilan.entities.User;
import com.talhaanay.is_ilan.mapper.UserMapper;
import com.talhaanay.is_ilan.repository.UserRepository;
import com.talhaanay.is_ilan.util.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.talhaanay.is_ilan.exception.BadRequestException;
import com.talhaanay.is_ilan.exception.AuthenticationException;
import com.talhaanay.is_ilan.dto.RegisterIsArayanDto;
import com.talhaanay.is_ilan.dto.RegisterIsVerenDto;
import com.talhaanay.is_ilan.enums.Role;
import com.talhaanay.is_ilan.entities.JobSeeker;
import com.talhaanay.is_ilan.entities.Employer;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final UserMapper userMapper;


    @Transactional
    @Override
    public String registerIsArayan(RegisterIsArayanDto registerDto) {
        if (userRepository.findByEmail(registerDto.getEmail()).isPresent()) {
            throw new BadRequestException("Bu e-posta adresi zaten kullanılıyor.");
        }

        JobSeeker newJobSeeker = userMapper.registerIsArayanDtoToJobSeeker(registerDto);
        newJobSeeker.setRole(Role.IS_ARAYAN);
        newJobSeeker.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        userRepository.save(newJobSeeker);
        return "İş Arayan başarıyla kaydedildi.";
    }

    @Transactional
    @Override
    public String registerIsVeren(RegisterIsVerenDto registerDto) {
        if (userRepository.findByEmail(registerDto.getEmail()).isPresent()) {
            throw new BadRequestException("Bu e-posta adresi zaten kullanılıyor.");
        }

        Employer newEmployer = userMapper.registerIsVerenDtoToEmployer(registerDto);
        newEmployer.setRole(Role.IS_VEREN);
        newEmployer.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        userRepository.save(newEmployer);
        return "İş Veren başarıyla kaydedildi.";
    }

    @Transactional
    @Override
    public LoginResponseDto login(LoginDto loginDto) {
        User user = userRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(() -> new AuthenticationException("Kullanıcı bulunamadı: " + loginDto.getEmail()));

        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new AuthenticationException("E-posta veya şifre hatalı");
        }

        String token = jwtUtil.generateToken(user);
        return new LoginResponseDto(token, user.getId());
    }
}