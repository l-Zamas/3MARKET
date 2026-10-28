package com.unipacifico._3market.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String username;
    private String email;
    private String nombre;
    private String apellido;
    private String identificacion;
    private String telefono;
    private String direccion;
    private boolean enabled;
    private Set<String> roles;

    public boolean hasRole(String role) {
        return roles != null && roles.stream().anyMatch(roleName -> role.equals(roleName));
    }

    public boolean hasAnyRole(String... rolesToCheck) {
        if (roles == null || rolesToCheck == null || rolesToCheck.length == 0) {
            return false;
        }
        return Arrays.stream(rolesToCheck).anyMatch(this::hasRole);
    }
}
