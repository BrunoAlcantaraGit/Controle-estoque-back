package com.controle.estoque.service;
import com.controle.estoque.model.Contato;
import com.controle.estoque.model.Endereco;
import com.controle.estoque.model.Fornecedor;
import com.controle.estoque.repository.ContatoRepository;
import com.controle.estoque.repository.EnderecoRepository;
import com.controle.estoque.repository.FornecedorRepository;
import com.controle.estoque.v1.dto.EnderecoDTO;
import com.controle.estoque.v1.dto.FornecedorDTO;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@CrossOrigin
public class FornecedorService {
    @Autowired
    FornecedorRepository fornecedorRepository;
    @Autowired
    EnderecoRepository enderecoRepository;
    @Autowired
    ContatoRepository contatoRepository;


    public Fornecedor salvar(Fornecedor fornecedor) throws Exception {
        Optional<Fornecedor> verificarDocumento = fornecedorRepository.findBydocumento(fornecedor.getDocumento());
        if (verificarDocumento.isEmpty()) {
            return fornecedorRepository.save(fornecedor);
        } else {
            throw new Exception("CNPJ já cadastrado");
        }
    }

    @Transactional
    public Fornecedor ataulizarPorId(Fornecedor fornecedor, Long id) throws Exception {
        Optional<Fornecedor> verificarDocumento = fornecedorRepository.findById(id);
        Optional<Endereco> buscarEndereco = enderecoRepository.findById(fornecedor.getEndereco().getId());
        Optional<Contato> bucarContato = contatoRepository.findById(fornecedor.getContato().getId());
        if (verificarDocumento.isPresent()) {

            Fornecedor fornecedorAtualizado = verificarDocumento.get();
            fornecedorAtualizado.setCpf(fornecedor.getDocumento());
            fornecedorAtualizado.setNome(fornecedor.getNome());

            Endereco enderecoAtualizado = buscarEndereco.get();
            enderecoAtualizado.setLogradouro(fornecedor.getEndereco().getLogradouro());
            enderecoAtualizado.setEstado(fornecedor.getEndereco().getEstado());
            enderecoAtualizado.setBairro(fornecedor.getEndereco().getBairro());
            enderecoAtualizado.setNumero(fornecedor.getEndereco().getNumero());
            enderecoAtualizado.setComplemento(fornecedor.getEndereco().getComplemento());

            Contato contatoAtualizado = bucarContato.get();
            contatoAtualizado.setTelefone(fornecedor.getContato().getTelefone());
            contatoAtualizado.setEmail(fornecedor.getContato().getEmail());

            contatoRepository.save(contatoAtualizado);
            enderecoRepository.save(enderecoAtualizado);
            return fornecedorRepository.save(fornecedorAtualizado);


        } else {
            throw new Exception("CNPJ não cadastrado");
        }
    }


    public Optional<Fornecedor> listarPorId(Long id) throws Exception {
        Optional<Fornecedor> VerificarId = fornecedorRepository.findById(id);
        if (VerificarId.isPresent()) {
            return fornecedorRepository.findById(id);
        } else {
            throw new Exception("Id não existe no banco");
        }

    }

    @Transactional
    public void deletarPorId(Long id) throws Exception {
        Optional<Fornecedor> verificarFornecedor = fornecedorRepository.findById(id);
        if (verificarFornecedor.isPresent()) {
            fornecedorRepository.deleteById(id);
        } else {
            throw new Exception("ID selecionado não localizado");
        }
    }

    public List<FornecedorDTO> listarTudoDTO() throws Exception {
        List<Fornecedor> fornecedores = fornecedorRepository.findAll();

        if (!fornecedores.isEmpty()) {
            List<FornecedorDTO> fornecedoresDTO = new ArrayList<>();
            for (Fornecedor f : fornecedores) {
                FornecedorDTO fornecedorDTO = new FornecedorDTO(f.getId(),f.getNome(),f.getDocumento(),f.getContato().getEmail(), f.getContato().getTelefone());
                fornecedoresDTO.add(fornecedorDTO);
            }
            return fornecedoresDTO.stream()
                    .sorted(Comparator.comparing(FornecedorDTO::nome))
                    .collect(Collectors.toList());
        } else {
            throw new Exception("Lista não contem elementos");
        }
    }

}
