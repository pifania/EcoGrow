package com.ecogrow.controller;

import com.ecogrow.model.Planta;
import com.ecogrow.model.Usuario;
import com.ecogrow.service.PlantaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/plantas")
@CrossOrigin(origins = "*")
public class PlantaController {

    @Autowired
    private PlantaService service;

    @GetMapping("/usuario/{usuarioId}")
    public List<Planta> listar(@PathVariable Long usuarioId) {
        return service.listarPorUsuario(usuarioId);
    }

    @PostMapping
    public ResponseEntity<Planta> salvar(@RequestBody Planta planta) {
        return ResponseEntity.ok(service.salvar(planta));
    }

    @PostMapping("/{id}/irrigar")
    public ResponseEntity<?> irrigar(@PathVariable Long id) {
        service.irrigar(id);
        return ResponseEntity.ok(Map.of("mensagem", "Irrigação realizada com sucesso!"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.ok(Map.of("mensagem", "Planta removida."));
    }
}
