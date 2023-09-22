package com.crud.clientes.dto;

import java.io.Serial;
import java.io.Serializable;

public class TipoClienteDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    // Atributos
    private String tipoCliente;

    // Metodos get y set
    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }
}
