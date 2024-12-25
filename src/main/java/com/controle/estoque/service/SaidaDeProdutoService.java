package com.controle.estoque.service;

import com.controle.estoque.model.Produto;
import com.controle.estoque.model.SaidaDeProduto;
import com.controle.estoque.model.TotalDaCompra;
import com.controle.estoque.model.TotaldaVenda;
import com.controle.estoque.repository.ProdutoRepository;
import com.controle.estoque.repository.SaindaDeProdutoRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Data
public class SaidaDeProdutoService {

    SaindaDeProdutoRepository saidaRepository;
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

    //FAZER UM MAP PARA FILTRAR LISTA DE SAINDA
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


    public BigDecimal lucroPorTransacao(BigDecimal valorDaUnidadeDeCompra, BigDecimal ValordaUnidadeDeVendas, BigDecimal quantidadeDeVenda) throws Exception {
        BigDecimal lucroDaTransacao = valorDaUnidadeDeCompra.subtract(ValordaUnidadeDeVendas).multiply(quantidadeDeVenda);

        return lucroDaTransacao;
    }

}
