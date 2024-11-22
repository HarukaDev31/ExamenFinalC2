package com.example.service;

import com.example.exceptions.BadRequestException;
import com.example.exceptions.DbException;
import com.example.exceptions.UnauthorizedException;
import com.example.exceptions.User.UserNotFoundException;
import com.example.entity.User;
import com.example.provider.CustomAuthenticationProvider;
import com.example.repository.UserRepository;
import com.example.request.AuthenticationRequest;
import com.example.request.RegisterRequest;
import com.example.response.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLDataException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final CustomAuthenticationProvider authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()

                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roleId(request.getRole_id())
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .message("Usuario registrado")
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        if (request == null || request.getEmail() == null) {
            throw new BadRequestException("El correo es obligatorio.");
        }
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado."));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new UnauthorizedException("Credenciales inválidas.");
        }
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .message("Usuario autenticado")
                .build();
    }
}