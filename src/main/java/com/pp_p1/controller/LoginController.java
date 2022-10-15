package com.pp_p1.controller;

import com.pp_p1.entity.Usuario;
import com.pp_p1.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    private UsuarioRepository repository;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody Usuario usuario){

        usuario = this.repository.findByEmailAndSenha(
                usuario.getEmail(), usuario.getSenha());

        if (usuario != null){
            return new ResponseEntity(repository.save(usuario), HttpStatus.OK);
        } else {
            return new ResponseEntity("Email ou senha inválidos!", HttpStatus.BAD_REQUEST);
        }
    }
}
