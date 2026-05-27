package com.ecogrow.service;

import com.ecogrow.model.Planta;
import com.ecogrow.repository.PlantaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PlantaService {

    @Autowired
    private PlantaRepository repository;

    public List<Planta> listarPorUsuario(Long usuarioId) {
        return repository.findByUsuarioId(usuarioId);
    }

    public Planta salvar(Planta planta) {
        return repository.save(planta);
    }

    public void irrigar(Long id) {
        Planta p = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Planta não encontrada."));
        p.setUmidade(Math.min(100.0, p.getUmidade() + 20.0));
        p.setStatus("SAUDAVEL");
        repository.save(p);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
