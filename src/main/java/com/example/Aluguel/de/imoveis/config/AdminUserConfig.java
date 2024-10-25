package com.example.Aluguel.de.imoveis.config;

import com.example.Aluguel.de.imoveis.domains.Role;
import com.example.Aluguel.de.imoveis.domains.User;
import com.example.Aluguel.de.imoveis.repositories.RoleRepository;
import com.example.Aluguel.de.imoveis.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Configuration
public class AdminUserConfig implements CommandLineRunner {

    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public AdminUserConfig(RoleRepository roleRepository,
                           UserRepository userRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        // Procura a role ADMIN
        var roleAdmin = roleRepository.findByName(Role.Values.ADMIN.name());

        // Verifica se a role ADMIN existe antes de prosseguir
        if (roleAdmin == null) {
            System.out.println("Role ADMIN não encontrada, criando o usuário abortado.");
            return;
        }

        // Procura o usuário administrador por email
        var userAdmin = userRepository.findByEmail("ava@gmail.com");

        // Se o usuário administrador já existir, imprime uma mensagem
        if (userAdmin != null) {
            System.out.println("Administrador já existe");
        } else {
            // Caso contrário, cria um novo usuário administrador
            var user = new User();
            user.setName("avocado");
            user.setCpf("023-356-082.54");
            user.setEmail("ava@gmail.com");
            user.setPassword(passwordEncoder.encode("123"));
            user.setRoles(Set.of(roleAdmin));

            // Salva o novo usuário no repositório
            userRepository.save(user);
            System.out.println("Novo administrador criado com sucesso.");
        }
    }
}