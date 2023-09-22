package com.crud.clientes.service;

import com.crud.clientes.dto.ClienteDto;
import com.crud.clientes.entity.Cliente;
import com.crud.clientes.repository.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteService {
    // Inyeccion de dependencias
    @Autowired
    private IClienteRepository clienteRepository;

    // Consulta de clientes
    @Transactional(readOnly = true)
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    // Consulta de cliente por ID
    @Transactional(readOnly = true)
    public Cliente findById(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    // Crear cliente
    @Transactional
    public Cliente createCliente(ClienteDto cliente) {
        Cliente clienteEntity = new Cliente();

        clienteEntity.setNombre(cliente.getNombre());
        clienteEntity.setApellido(cliente.getApellido());
        clienteEntity.setEmail(cliente.getEmail());

        return clienteRepository.save(clienteEntity);
    }

    // Eliminar cliente
    @Transactional
    public void deleteById(Long id) {
        clienteRepository.deleteById(id);
    }

    // Actualizar cliente
    @Transactional
    public Cliente updateCliente(ClienteDto cliente, Long id) {
        Cliente clienteEntity = findById(id);

        clienteEntity.setNombre(cliente.getNombre());
        clienteEntity.setApellido(cliente.getApellido());
        clienteEntity.setEmail(cliente.getEmail());

        return clienteRepository.save(clienteEntity);
    }
}
