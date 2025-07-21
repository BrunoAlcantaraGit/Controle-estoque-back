package com.controle.estoque.service;

import com.controle.estoque.model.Cliente;
import com.controle.estoque.model.Orcamento;
import com.controle.estoque.model.Produto;
import com.controle.estoque.model.Venda;
import com.controle.estoque.repository.ClienteRepository;
import com.controle.estoque.repository.OrcamentoRepository;
import com.controle.estoque.repository.ProdutoRepository;
import com.controle.estoque.repository.VendaRepository;
import com.controle.estoque.v1.dto.VendaResponseDTO;
import feign.Client;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private OrcamentoRepository orcamentoRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

    public Venda salvar(Venda venda) throws Exception {

        if (venda != null) {
            return vendaRepository.save(venda);
        } else {
            throw new RuntimeException("Recepted entity is empty");
        }

    }

    public List<VendaResponseDTO> listar() throws Exception {
        List<Venda> vendas = vendaRepository.findAll();
        List<VendaResponseDTO> vendaDTOS = new ArrayList<>();

        if (!vendas.isEmpty()) {

            for (Venda v : vendas) {
                List<Long> produtosIds = v.getProdutos().stream()
                        .map(Produto::getId)
                        .toList();

                List<Long> orcamentoIds = v.getOrcamentos().stream()
                        .map(Orcamento::getId)
                        .toList();

                VendaResponseDTO vendaResponseDTO = new VendaResponseDTO(
                        v.getCliente().getNome(),
                        produtosIds,
                        orcamentoIds,
                        v.getLucro(),
                        v.getValorTotalDaVenda());

                vendaDTOS.add(vendaResponseDTO);
            }
            return vendaDTOS;
        } else {
            throw new Exception("sale list is empty");
        }
    }


}
