package com.controle.estoque.service;

import com.controle.estoque.model.SaidaDeProduto;
import com.controle.estoque.repository.ProdutoRepository;
import com.controle.estoque.repository.SaidaDeProdutoRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Data
public class SaidaDeProdutoService {

    SaidaDeProdutoRepository saidaRepository;
    ProdutoRepository produtoRepository;

    public SaidaDeProduto salvarVenda(SaidaDeProduto saida) {
        return saidaRepository.save(saida);
    }


    public List<SaidaDeProduto> listarTodasAsSaidas() throws Exception {
        List<SaidaDeProduto> listaDeProduto = saidaRepository.findAll();
        List<SaidaDeProduto> ListarOsProdutos = new ArrayList<>();
        if (!listaDeProduto.isEmpty()) {
            for (SaidaDeProduto p : listaDeProduto) {
                ListarOsProdutos.add(p);
            }

            return ListarOsProdutos;
        } else {
            throw new Exception("Lista não contem Elementos");
        }
    }


    public BigDecimal somarTotalVenda() throws Exception {
        List<SaidaDeProduto> saidas = saidaRepository.findAll();
        if (!saidas.isEmpty()) {

            BigDecimal total = saidas.stream()
                    .map(saida -> saida.getValorTotaldaVenda())
                    .filter(valorTotaldaVenda -> valorTotaldaVenda != null)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            return total;

        } else {
            throw new Exception();
        }
    }


    public BigDecimal lucroTotal() throws Exception {
        List<SaidaDeProduto> saidas = saidaRepository.findAll();

        if (!saidas.isEmpty()) {

            BigDecimal lucroTotal = saidas.stream()
                    .map(saida -> saida.getLucroDaTransacao())
                    .filter(LucroDaTransacao -> LucroDaTransacao != null)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            return lucroTotal;

        } else {
            throw new Exception("Não existe compras nem vendas para retornar o valor");
        }

    }

    public Optional<SaidaDeProduto> listarSaidasPorId(Long id) throws Exception {
        Optional<SaidaDeProduto> saidaDeProduto = saidaRepository.findById(id);
        if (saidaDeProduto.isPresent()) {
            return saidaDeProduto;
        } else {
            throw new Exception("produto inexistente ou id inválido");
        }

    }

    public SaidaDeProduto atualizarSaida(Long id, SaidaDeProduto saidaDeProduto) throws Exception {
        Optional<SaidaDeProduto> saida = saidaRepository.findById(id);
        if (saida.isPresent()) {
            return saidaDeProduto;
        } else {
            throw new Exception("Produto não existe na base");
        }
    }

    public void deletarSaidaPorId(Long id) throws Exception{
        Optional<SaidaDeProduto> saida = saidaRepository.findById(id);

        if (saida.isPresent()) {
            saidaRepository.deleteById(id);
        }else {
            throw new Exception("Id não localizado");
        }
    }

}
