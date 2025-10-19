
package com.example.tienda.service;

import com.example.tienda.dto.ClienteDTO;
import com.example.tienda.dto.DireccionDTO;
import com.example.tienda.entity.Cliente;
import com.example.tienda.entity.Direccion;
import com.example.tienda.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public Cliente createCliente(ClienteDTO dto){
        Cliente c = new Cliente();
        c.setNombre(dto.getNombre());
        c.setEmail(dto.getEmail());
        if(dto.getDireccion()!=null){
            DireccionDTO d = dto.getDireccion();
            Direccion dir = new Direccion();
            dir.setCalle(d.getCalle()); dir.setCiudad(d.getCiudad()); dir.setPais(d.getPais()); dir.setZip(d.getZip());
            c.setDireccion(dir);
        }
        return clienteRepository.save(c);
    }
}
