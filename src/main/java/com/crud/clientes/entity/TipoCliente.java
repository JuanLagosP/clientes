package com.crud.clientes.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tipo_cliente", schema = "public")
public class TipoCliente {
    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "tipo_cliente")
    private String tipoCliente;

    // Metodos get y set
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }
}
