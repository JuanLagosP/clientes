package com.crud.clientes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.crud.clientes.entity.Cliente;
import org.springframework.stereotype.Repository;

public interface IClienteRepository extends JpaRepository<Cliente, Long> {
}
