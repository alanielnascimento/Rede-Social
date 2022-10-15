package com.pp_p1.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "postagem")
public class Postagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_postagem")
    private Long codigo;

    @Column(nullable = false, length = 200)
    private String titulo;

    @Column(nullable = false, length = 200)
    private String texto;

    @ManyToOne
    @JoinColumn(name = "cod_usuario")
    private Usuario usuario;
}
