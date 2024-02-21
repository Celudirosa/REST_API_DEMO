package com.example.security.entities;

import jakarta.persistence.Column;

public class Usuario {

    @Column(name = "id_usuario")
    private int idUsuario;

    private String name;
    private String password;

}
