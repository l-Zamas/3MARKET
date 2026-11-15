package com.unipacifico._3market.service;

import com.unipacifico._3market.dto.RegisterDto;
import com.unipacifico._3market.dto.UserDto;
import com.unipacifico._3market.mapper.UserMapper;
import com.unipacifico._3market.model.Role;
import com.unipacifico._3market.model.User;
import com.unipacifico._3market.model.enums.RoleType;
import com.unipacifico._3market.repository.RoleRepository;
import com.unipacifico._3market.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;


@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDto register(RegisterDto dto) {
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new IllegalArgumentException("El nombre de usuario ya existe");
        }
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("El email ya está registrado");
        }

        Role clientRole = roleRepository.findByName(RoleType.ROLE_CLIENTE)
                .orElseThrow(() -> new RuntimeException("Rol CLIENTE no encontrado"));

        User user = UserMapper.toEntity(dto, passwordEncoder.encode(dto.getPassword()), Set.of(clientRole));
        return UserMapper.toDto((userRepository.save(user)));
    }

    public Optional<UserDto> getUsersAuthention() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated() || "anonymousUser".equals(auth.getPrincipal())) {
            return Optional.empty();
        }

        String username = auth.getName();

        return userRepository.findByUsername(username).map(UserMapper::toDto)
                .or(() -> {
                    throw new UsernameNotFoundException(
                            "Error crítico en el Login: El usuario autenticado '" + username + "' no existe en la base de datos."
                    );
                });
    }
}
