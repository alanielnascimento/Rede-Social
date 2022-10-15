package com.pp_p1.controller;

import com.pp_p1.entity.Postagem;
import com.pp_p1.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("postagem")
public class PostagemController {

    @Autowired
    private PostagemRepository postagemRepository;

    @GetMapping()
    public List<Postagem> getPostagens(){
        return postagemRepository.findAll();
    }

    @GetMapping("/usuario/{codigo}")
    public List<Postagem> getPostagens(@PathVariable Long codigo){
        return postagemRepository.findByCodigoUsuario(codigo);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Postagem salvarPostagem(@RequestBody Postagem postagem){
        return postagemRepository.save(postagem);
    }
}
