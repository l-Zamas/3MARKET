package com.unipacifico._3market.security;

import com.unipacifico._3market.model.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

@Getter
public class CustomUserDetails implements UserDetails {

    private final Long id;
    private final String username;
    private final String password;
    private final String nombre;
    private final String apellido;
    private final String identificacion;
    private final String telefono;
    private final String direccion;
    private final String email;
    private final boolean enabled;
    private final Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.nombre = user.getNombre();
        this.apellido = user.getApellido();
        this.identificacion = user.getIdentificacion();
        this.telefono = user.getTelefono();
        this.direccion = user.getDireccion();
        this.email = user.getEmail();
        this.enabled = user.isEnabled();
        this.authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());
    }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
