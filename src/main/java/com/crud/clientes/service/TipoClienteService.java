package com.crud.clientes.service;

import com.crud.clientes.dto.TipoClienteDto;
import com.crud.clientes.entity.TipoCliente;
import com.crud.clientes.repository.ITipoClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TipoClienteService {
    // Inyeccion de dependencias
    @Autowired
    private ITipoClienteRepository tipoClienteRepository;

    // Agregar tipo de cliente
    @Transactional
    public TipoCliente createTipoCliente(TipoClienteDto tipoCliente) {
        TipoCliente tipoClienteEntity = new TipoCliente();
        tipoClienteEntity.setTipoCliente(tipoCliente.getTipoCliente());
        return tipoClienteRepository.save(tipoClienteEntity);
    }

    // Consultar tipos de cliente
    @Transactional(readOnly = true)
    public List<TipoCliente> findAll() {
        return tipoClienteRepository.findAll();
    }

    // Consultar tipo de cliente por ID
    @Transactional(readOnly = true)
    public TipoCliente findById(Long id) {
        return tipoClienteRepository.findById(id).orElse(null);
    }

    // Actualizar tipo de cliente
    @Transactional
    public TipoCliente updateTipoCliente(TipoClienteDto tipoCliente, Long id) {
        TipoCliente tipoClienteEntity = findById(id);
        tipoClienteEntity.setTipoCliente(tipoCliente.getTipoCliente());
        return tipoClienteRepository.save(tipoClienteEntity);
    }

    // Eliminar tipo de cliente
    @Transactional
    public void deleteTipoClienteById(Long id) {
        tipoClienteRepository.deleteById(id);
    }
}
