package com.controle.estoque.service;

import com.controle.estoque.model.Cliente;
import com.controle.estoque.model.Contato;
import com.controle.estoque.model.Endereco;
import com.controle.estoque.repository.ClienteRepository;
import com.controle.estoque.repository.ContatoRepository;
import com.controle.estoque.repository.EnderecoRepository;
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
    @Autowired
    EnderecoRepository enderecoRepository;
    @Autowired
    ContatoRepository contatoRepository;


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
    public Cliente editarCliente(Long id, Cliente clienteAtual) throws Exception {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        Optional<Endereco> endereco = enderecoRepository.findById(cliente.get().getEndereco().getId());
        Optional<Contato> contato = contatoRepository.findById(cliente.get().getContato().getId());

        if (cliente.isPresent()) {
            Cliente clienteatualizado = cliente.get();
            clienteatualizado.setNome(clienteAtual.getNome());
            clienteatualizado.setDocumento(clienteAtual.getDocumento());

            Endereco enderecoAtualizado = endereco.get();
            enderecoAtualizado.setLogradouro(clienteAtual.getEndereco().getLogradouro());
            enderecoAtualizado.setComplemento(clienteAtual.getEndereco().getComplemento());
            enderecoAtualizado.setBairro(clienteAtual.getEndereco().getBairro());
            enderecoAtualizado.setEstado(clienteAtual.getEndereco().getEstado());
            enderecoAtualizado.setNumero(clienteAtual.getEndereco().getNumero());

            Contato contatoAtualizado= contato.get();
            contatoAtualizado.setEmail(clienteAtual.getContato().getEmail());
            contatoAtualizado.setTelefone(clienteAtual.getContato().getTelefone());

            contatoRepository.save(contatoAtualizado);
            enderecoRepository.save(enderecoAtualizado);
            return clienteRepository.save(clienteatualizado);
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


    public List<ClienteDTO> listarClientes() throws Exception {
        List<Cliente> validarClientes = clienteRepository.findAll();

        if (!validarClientes.isEmpty()) {
            List<ClienteDTO> clientesDTO = new ArrayList<>();

            for (Cliente c : validarClientes) {
                ClienteDTO clienteDTo = new ClienteDTO(c.getId(),c.getNome(),c.getDocumento(), c.getContato().getTelefone(),c.getContato().getEmail());
                clientesDTO.add(clienteDTo);
            }

            return clientesDTO;
        } else {
            throw new Exception("Lista não contem elementos");
        }
    }


}
