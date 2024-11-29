package com.controle.estoque.service;



import com.controle.estoque.model.Fornecedor;
import com.controle.estoque.repository.FornecedorRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@CrossOrigin
public class FornecedorService {

    FornecedorRepository fornecedorRepository;

    public Fornecedor salvar(Fornecedor fornecedor) throws Exception {
        Optional<Fornecedor> verificarCNPJ = fornecedorRepository.findBycpf(fornecedor.getCpf());
        if (verificarCNPJ.isEmpty()) {
            return fornecedorRepository.save(fornecedor);
        } else {
            throw new Exception("CNPJ já cadastrado");
        }
    }


    public Fornecedor ataulizarPorId(Long id, Fornecedor fornecedor) throws Exception {
        Optional<Fornecedor> verificarCNPJ = fornecedorRepository.findBycpf(fornecedor.getCpf());
        if (verificarCNPJ.isPresent()) {
            Fornecedor fornecedorAtualizado = verificarCNPJ.get();
            fornecedorAtualizado.setCpf(fornecedor.getCpf());
            fornecedorAtualizado.setNome(fornecedor.getNome());
            fornecedorAtualizado.setContatos(fornecedor.getContatos());
            fornecedorAtualizado.setEndereco(fornecedor.getEndereco());

            return fornecedorAtualizado;
        } else {
            throw new Exception("CNPJ não cadastrado");
        }
    }

    public List<Fornecedor> listarTudo() throws Exception {
        List<Fornecedor> verificarCNPJ = fornecedorRepository.findAll();
        List<Fornecedor> fornecedores = new ArrayList<>();
        if (!verificarCNPJ.isEmpty()) {
            for (Fornecedor f : verificarCNPJ) {
                fornecedores.add(f);
            }
            return fornecedores;
         } else {
            throw new Exception("Lista não contem elementos");
        }
    }

    public Optional<Fornecedor> listarPorId(Long id)throws Exception{
        Optional<Fornecedor> VerificarId = fornecedorRepository.findById(id);
        if (VerificarId.isPresent()){
            return fornecedorRepository.findById(id);
        }else {
            throw new Exception("Id não existe no banco");
        }

    }
    @Transactional
    public void deletarPorId(Long id) throws Exception{
        Optional<Fornecedor> verificarFornecedor =fornecedorRepository.findById(id);
        if (verificarFornecedor.isPresent()){
         fornecedorRepository.deleteById(id);
        }else {
            throw new Exception("ID selecionado não localizado");
        }
  }
}
