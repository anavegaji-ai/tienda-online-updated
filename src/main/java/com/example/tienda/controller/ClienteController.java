
package com.example.tienda.controller;

import com.example.tienda.dto.ClienteDTO;
import com.example.tienda.dto.DireccionDTO;
import com.example.tienda.entity.Cliente;
import com.example.tienda.entity.Direccion;
import com.example.tienda.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteService clienteService;
    public ClienteController(ClienteService clienteService){ this.clienteService = clienteService; }

    @PostMapping
    public ResponseEntity<ClienteDTO> create(@RequestBody ClienteDTO dto){
        Cliente c = clienteService.createCliente(dto);
        ClienteDTO out = toDto(c);
        return ResponseEntity.ok(out);
    }

    private ClienteDTO toDto(Cliente c){
        ClienteDTO dto = new ClienteDTO();
        dto.setId(c.getId());
        dto.setNombre(c.getNombre());
        dto.setEmail(c.getEmail());
        if(c.getDireccion()!=null){
            Direccion d = c.getDireccion();
            DireccionDTO dd = new DireccionDTO();
            dd.setCalle(d.getCalle()); dd.setCiudad(d.getCiudad()); dd.setPais(d.getPais()); dd.setZip(d.getZip());
            dto.setDireccion(dd);
        }
        return dto;
    }
}
