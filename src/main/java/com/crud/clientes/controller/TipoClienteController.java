package com.crud.clientes.controller;

import com.crud.clientes.entity.TipoCliente;
import com.crud.clientes.dto.TipoClienteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.crud.clientes.service.TipoClienteService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TipoClienteController {
    @Autowired
    private TipoClienteService tipoClienteService;

    // Agregar tipo de cliente
    @PostMapping("/tipo_cliente")
    public ResponseEntity<?> createTipoCliente(TipoClienteDto tipoCliente) {
        Map<String, Object> response = new HashMap<>();
        TipoCliente nuevoTipoCliente = null;
        try {
            nuevoTipoCliente = tipoClienteService.createTipoCliente(tipoCliente);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(e.getMostSpecificCause().toString()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El tipo de cliente ha sido creado con exito");
        response.put("tipoCliente", nuevoTipoCliente);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Consultar tipos de cliente
    @GetMapping("/tipo_cliente")
    @ResponseStatus(HttpStatus.OK)
    public List<TipoCliente> consulta() {
        return tipoClienteService.findAll();
    }

    // Consulta de tipo de cliente por ID
    @GetMapping("/tipo_cliente/{id}")
    public ResponseEntity<?> consultaPorID(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        TipoCliente tipoCliente = null;
        try {
            tipoCliente = tipoClienteService.findById(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta de tipo de cliente");
            response.put("error", e.getMessage().concat(e.getMostSpecificCause().toString()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (tipoCliente == null) {
            response.put("mensaje", "El tipo de cliente con ID ".concat(id.toString()).concat(" no existe en la base de datos"));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        response.put("mensaje", "Consulta de tipo de cliente exitosa");
        response.put("tipoCliente", tipoCliente);
        return new ResponseEntity<>(tipoCliente, HttpStatus.OK);
    }

    // Actualizar tipo de cliente por ID
    @PutMapping("/tipo_cliente/{id}")
    public ResponseEntity<?> actualizarTipoCliente(TipoClienteDto tipoCliente, @PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        TipoCliente tipoClienteActual = null;
        TipoCliente tipoClienteActualizado;
        try {
            tipoClienteActual = tipoClienteService.findById(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta de tipo de cliente");
            response.put("error", e.getMessage().concat(e.getMostSpecificCause().toString()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (tipoClienteActual == null) {
            response.put("mensaje", "El tipo de cliente con ID ".concat(id.toString()).concat(" no existe en la base de datos"));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        tipoClienteActualizado = tipoClienteService.updateTipoCliente(tipoCliente, id);
        response.put("mensaje", "El tipo de cliente ha sido actualizado con exito");
        response.put("tipoCliente", tipoClienteActualizado);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Eliminar tipo de cliente por ID
    @DeleteMapping("/tipo_cliente/{id}")
    public ResponseEntity<?> eliminarTipoCliente(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        TipoCliente tipoCliente = null;
        try {
            tipoCliente = tipoClienteService.findById(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta de tipo de cliente");
            response.put("error", e.getMessage().concat(e.getMostSpecificCause().toString()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (tipoCliente == null) {
            response.put("mensaje", "El tipo de cliente con ID ".concat(id.toString()).concat(" no existe en la base de datos"));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        tipoClienteService.deleteTipoClienteById(id);
        response.put("mensaje", "El tipo de cliente ha sido eliminado con exito");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
