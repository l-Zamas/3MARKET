package com.unipacifico._3market.mapper;

import com.unipacifico._3market.dto.RegisterDto;
import com.unipacifico._3market.dto.UserDto;
import com.unipacifico._3market.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserDto toDto(User user) {
        Set<String> roles = user.getRoles().stream()
                .map(role -> role.getName().name())
                .collect(Collectors.toSet());
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .nombre(user.getNombre())
                .apellido(user.getApellido())
                .identificacion(user.getIdentificacion())
                .telefono(user.getTelefono())
                .direccion(user.getDireccion())
                .enabled(user.isEnabled())
                .roles(roles)
                .build();
    }

    public List<UserDto> toDtoList(List<User> users) {
        return users.stream().map(this::toDto).collect(Collectors.toList());
    }

    // La contraseña (cifrada) y el rol los asigna el servicio
    public User toEntity(RegisterDto dto) {
        return User.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .nombre(dto.getNombre())
                .apellido(dto.getApellido())
                .identificacion(dto.getIdentificacion())
                .telefono(dto.getTelefono())
                .direccion(dto.getDireccion())
                .build();
    }
}
