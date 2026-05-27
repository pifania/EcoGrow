package com.ecogrow.controller;

import com.ecogrow.model.Usuario;
import com.ecogrow.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody Usuario usuario) {
        try {
            Usuario salvo = service.cadastrar(usuario);
            salvo.setSenha(null); // não retorna senha
            return ResponseEntity.ok(salvo);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("erro", e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String senha = body.get("senha");
        Optional<Usuario> usuario = service.login(email, senha);
        if (usuario.isPresent()) {
            Usuario u = usuario.get();
            u.setSenha(null);
            return ResponseEntity.ok(u);
        }
        return ResponseEntity.status(401).body(Map.of("erro", "E-mail ou senha inválidos."));
    }
}
