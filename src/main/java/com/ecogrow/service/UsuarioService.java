package com.ecogrow.service;

import com.ecogrow.model.Usuario;
import com.ecogrow.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Usuario cadastrar(Usuario usuario) {
        if (repository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new RuntimeException("E-mail já cadastrado.");
        }
        return repository.save(usuario);
    }

    public Optional<Usuario> login(String email, String senha) {
        return repository.findByEmailAndSenha(email, senha);
    }
}
