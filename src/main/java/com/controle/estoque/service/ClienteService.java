package com.controle.estoque.service;

import com.controle.estoque.model.Cliente;
import com.controle.estoque.model.Endereco;
import com.controle.estoque.repository.ClienteRepository;
import com.controle.estoque.v1.dto.ClienteDTO;
import com.controle.estoque.v1.dto.EnderecoDTO;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Data
@CrossOrigin
public class ClienteService {
    @Autowired
    ClienteRepository clienteRepository;
    EnderecoService enderecoService;


    public Cliente salvar(Cliente cliente) throws Exception {
        Optional<Cliente> verificarDocumento = clienteRepository.findByDocumento(cliente.getDocumento());
        if (verificarDocumento.isEmpty()) {
            return clienteRepository.save(cliente);
        } else {
            throw new Exception("CPF/CNPJ Já está cadastrado");
        }
    }



    @Transactional
    public void deletarPoId(Long id) throws Exception {
        Optional<Cliente> validarCliente = clienteRepository.findById(id);
        if (validarCliente.isPresent()) {
            clienteRepository.deleteById(id);
        } else {
            throw new Exception("Id não localizado no banco de dados");
        }
    }

    @Transactional
    public Cliente editarCliente(Long id, Cliente cliente) throws Exception {
        Optional<Cliente> validarCliente = clienteRepository.findById(id);
        if (validarCliente.isPresent()) {
            Cliente clienteAtualizado = validarCliente.get();
            clienteAtualizado.setNome(cliente.getNome());
            clienteAtualizado.setDocumento(cliente.getDocumento());

            return cliente;
        } else {
            throw new Exception("id não localizado no banco");
        }
    }


    public Optional<Cliente> listarPorId(long id) throws Exception {
        Optional<Cliente> verificarCliente = clienteRepository.findById(id);
        if (verificarCliente.isPresent()) {
            return clienteRepository.findById(id);
        } else {
            throw new Exception();
        }
    }


    public List<ClienteDTO> listarTudoDTO() throws Exception {
        List<Cliente> validarClientes = clienteRepository.findAll();

        if (!validarClientes.isEmpty()) {
            List<ClienteDTO> clientesDTO = new ArrayList<>();
            EnderecoDTO enderecoDTO = new EnderecoDTO();

            for (Cliente c : validarClientes) {
                enderecoDTO.setLogradouro(c.getEndereco().getLogradouro());
                enderecoDTO.setId(c.getEndereco().getId());

                ClienteDTO clienteDTo = new ClienteDTO(c.getId(),c.getNome(),c.getDocumento(),enderecoDTO);
                clientesDTO.add(clienteDTo);
            }

            return clientesDTO;
        } else {
            throw new Exception("Lista não contem elementos");
        }
    }


}
