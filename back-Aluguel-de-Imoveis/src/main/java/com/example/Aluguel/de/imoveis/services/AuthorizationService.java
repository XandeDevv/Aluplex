package com.example.Aluguel.de.imoveis.services;

import com.example.Aluguel.de.imoveis.config.TokenService;
import com.example.Aluguel.de.imoveis.domains.User;
import com.example.Aluguel.de.imoveis.domains.UserRole;
import com.example.Aluguel.de.imoveis.dtos.LoginRequest;
import com.example.Aluguel.de.imoveis.dtos.LoginResponse;
import com.example.Aluguel.de.imoveis.dtos.UserDto;
import com.example.Aluguel.de.imoveis.dtos.UserInsertDto;
import com.example.Aluguel.de.imoveis.repositories.UserRepository;
import com.example.Aluguel.de.imoveis.services.exceptions.ControllerNotFoundException;
import com.example.Aluguel.de.imoveis.services.exceptions.CustomAccessDeniedException;
import com.example.Aluguel.de.imoveis.services.exceptions.CustomUnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthorizationService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    TokenService tokenService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username);
    }
    @Transactional(readOnly = true)
    public User authenticated(){
        try {
            String userName= SecurityContextHolder.getContext().getAuthentication().getName();
            return (User) userRepository.findByEmail(userName);
        }catch (ControllerNotFoundException e){
            throw new ControllerNotFoundException("user nao encontrado");
        }
    }
    public void selfOrAdmin(Long userId){
        User user= authenticated();
        if (!user.getId().equals(userId) && !user.hasRole(UserRole.ADMIN)){
            throw new CustomAccessDeniedException("Voce nao tem permissao para acessar esse recurso");
        }

    }

    @Transactional
    public LoginResponse login(LoginRequest body) {
        User user = (User) userRepository.findByEmail(body.email());
        if (!passwordEncoder.matches(body.password(), user.getPassword())) {
            throw new CustomUnauthorizedException("Invalid credentials");
        }

        String token = tokenService.generateToken(user);
        return new LoginResponse( token);
    }

    @Transactional
    public UserDto insert(UserInsertDto dto) {
        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.getPassword());
        User obj = new User();
        obj = dtoToEntity(dto, obj);
        obj.setPassword(encryptedPassword);
        obj = userRepository.save(obj);
        return new UserDto(obj);
    }
    public User dtoToEntity(UserDto dto, User obj) {
        obj.setName(dto.getName());
        obj.setEmail(dto.getEmail());
        obj.setCpf(dto.getCpf());
        obj.setRole(dto.getRole());
        return obj;
    }

}
