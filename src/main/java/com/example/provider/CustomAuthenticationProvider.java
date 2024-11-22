package com.example.provider;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.example.exceptions.User.UserNotFoundException;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public CustomAuthenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
     try{
         String username = authentication.getName();
         String password = authentication.getCredentials().toString();

         UserDetails userDetails = userDetailsService.loadUserByUsername(username);
         if (userDetails == null || !passwordEncoder.matches(password, userDetails.getPassword())) {
             throw new UserNotFoundException("Invalid username or password");
         }

         return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
     }catch (Exception e){
         throw new UserNotFoundException("Invalid username or password");
     }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
