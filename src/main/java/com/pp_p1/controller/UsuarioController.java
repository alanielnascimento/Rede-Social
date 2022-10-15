package com.pp_p1.controller;

import com.pp_p1.entity.Usuario;
import com.pp_p1.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @GetMapping
    public ResponseEntity get(){
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("{codigo}")
    public ResponseEntity getById(@PathVariable long codigo) {

            Optional<Usuario> optional = repository.findById(codigo);

            if (optional.isPresent()) {
                Usuario usuario = optional.get();
                return ResponseEntity.ok(usuario);
            }
            return new ResponseEntity("Não exite usuario com o código: " + codigo, HttpStatus.BAD_REQUEST);
    }

    @PostMapping
    public ResponseEntity post(@RequestBody Usuario usuario){
        try{
            return new ResponseEntity(repository.save(usuario), HttpStatus.CREATED);
        }catch (Exception error){
            return new ResponseEntity("Não foi possivel cadastrar o usuario", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable long id){
        try {
            repository.deleteById(id);
            return ResponseEntity.ok("Usuario removido com sucesso");
        }catch (EmptyResultDataAccessException error){
            return new ResponseEntity("Não exite usuario com o id: " + id, HttpStatus.BAD_REQUEST);
        }catch (Exception error){
            return new ResponseEntity("Não foi possivel remover o usuario!", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity put(@RequestBody Usuario usuario){
        try{
            var optional = repository.findById(usuario.getCodigo());

            if(optional.isPresent()){
                return new ResponseEntity(repository.save(usuario), HttpStatus.OK);
            }

            return new ResponseEntity("Usuario inválido!", HttpStatus.OK);

        }catch (Exception error){
            return new ResponseEntity("Não foi possivel editar o usuario!", HttpStatus.BAD_REQUEST);
        }
    }
}
