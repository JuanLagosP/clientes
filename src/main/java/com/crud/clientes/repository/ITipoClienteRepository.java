package com.crud.clientes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.crud.clientes.entity.TipoCliente;
import org.springframework.stereotype.Repository;

@Repository
public interface ITipoClienteRepository extends JpaRepository<TipoCliente, Long> {
}
