package com.crud.clientes.controller;

import com.crud.clientes.dto.ClienteDto;
import com.crud.clientes.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.crud.clientes.service.ClienteService;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping("/clientes")
    @ResponseStatus(HttpStatus.OK)
    public List<Cliente> consulta() {
        return clienteService.findAll();
    }

    // Consulta de cliente por ID
    @GetMapping("/clientes/{id}")
    public ResponseEntity<?> consultaPorID(@PathVariable Long id) {
        // Variable para almacenar el cliente
        Cliente cliente = null;
        // Variable para almacenar la respuesta en caso de algun error
        String response = "";

        try {
            cliente = clienteService.findById(id);
        } catch (DataAccessException e) {
            response = "Error al realizar la consulta";
            response = response.concat(e.getMessage().concat(e.getMostSpecificCause().toString()));
            return new ResponseEntity<String>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (cliente == null) {
            response = "El cliente con ID ".concat(id.toString()).concat(" no existe en la base de datos");
            return new ResponseEntity<String>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
    }

    // Eliminar cliente por ID
    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<?> eliminarPorID(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Cliente clienteDelete = clienteService.findById(id);
            if (clienteDelete == null) {
                response.put("mensaje", "Error al eliminar. El cliente no existe en la base de datos.");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            clienteService.deleteById(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar en base de datos");
            response.put("error", e.getMessage().concat(e.getMostSpecificCause().getLocalizedMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "Cliente eliminado con éxito");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/clientes")
    public ResponseEntity<?> crearCliente(@RequestBody ClienteDto cliente) {
        Cliente nuevoCliente = null;
        Map<String, Object> response = new HashMap<>();
        try {
            nuevoCliente = clienteService.createCliente(cliente);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert");
            response.put("error", e.getMessage().concat(e.getMostSpecificCause().getLocalizedMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "Cliente creado con éxito");
        response.put("cliente", nuevoCliente);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Actualizar cliente por ID
    @PutMapping("/clientes/{id}")
    public ResponseEntity<?> actualizarCliente(@RequestBody ClienteDto cliente, @PathVariable Long id) {
        Cliente clienteActualizado;
        Map<String, Object> response = new HashMap<>();
        try {
            Cliente clienteActual = clienteService.findById(id);
            if (clienteActual == null) {
                response.put("mensaje", "Cliente no encontrado en la base de datos");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            clienteActualizado = clienteService.updateCliente(cliente, id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar el cliente");
            response.put("error", e.getMessage().concat(e.getMostSpecificCause().getLocalizedMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "Cliente actualizado con éxito");
        response.put("cliente", clienteActualizado);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
