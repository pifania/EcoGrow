package com.ecogrow.repository;

import com.ecogrow.model.Planta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PlantaRepository extends JpaRepository<Planta, Long> {
    List<Planta> findByUsuarioId(Long usuarioId);
}
