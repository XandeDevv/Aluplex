package com.example.Aluguel.de.imoveis.services;

import com.example.Aluguel.de.imoveis.domains.User;
import com.example.Aluguel.de.imoveis.dtos.UserDto;
import com.example.Aluguel.de.imoveis.dtos.UserInsertDto;
import com.example.Aluguel.de.imoveis.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthorizationService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username);
    }
    @Transactional
    public Boolean emailAlreadyExist(UserInsertDto userInsertDto){
        if(this.userRepository.findByEmail(userInsertDto.getEmail())!=null){
            return false;
        }else{
            return true;
        }
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
