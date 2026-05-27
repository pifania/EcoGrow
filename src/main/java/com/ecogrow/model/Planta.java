package com.ecogrow.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "plantas")
public class Planta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private String especie;

    private Double umidade;

    private Double temperatura;

    private Double luminosidade;

    private String status; // SAUDAVEL, ALERTA, CRITICO

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    // Adiciona esses métodos dentro da classe Planta
public Double getUmidade() { return umidade; }
public void setUmidade(Double umidade) { this.umidade = umidade; }

public String getStatus() { return status; }
public void setStatus(String status) { this.status = status; }

public String getNome() { return nome; }
public void setNome(String nome) { this.nome = nome; }

public String getEspecie() { return especie; }
public void setEspecie(String especie) { this.especie = especie; }

public Double getTemperatura() { return temperatura; }
public void setTemperatura(Double temperatura) { this.temperatura = temperatura; }

public Double getLuminosidade() { return luminosidade; }
public void setLuminosidade(Double luminosidade) { this.luminosidade = luminosidade; }

public Long getId() { return id; }
public void setId(Long id) { this.id = id; }

public Usuario getUsuario() { return usuario; }
public void setUsuario(Usuario usuario) { this.usuario = usuario; }

}

