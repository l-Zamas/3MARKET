package com.unipacifico._3market.dto;

import lombok.Data;

@Data
public class RegisterDto {
    private String username;
    private String email;
    private String password;
    private String nombre;
    private String apellido;
    private String identificacion;
    private String telefono;
    private String direccion;
}

