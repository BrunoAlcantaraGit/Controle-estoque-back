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

    public SaidaDeProduto salvarVenda(SaidaDeProduto saida){
        return saidaRepository.save(saida);
    }


    public List<SaidaDeProduto> listarTodasAsSaidas() throws Exception{
        List<SaidaDeProduto>listaDeProduto = saidaRepository.findAll();
        List<SaidaDeProduto>ListarOsProdutos = new ArrayList<>();
        if(!listaDeProduto.isEmpty()){
            for (SaidaDeProduto p : listaDeProduto){
                ListarOsProdutos.add(p);}

               return ListarOsProdutos;
        }else {
            throw new Exception("Lista não contem Elementos");
        }
    }


   public BigDecimal somarTotalVenda() throws Exception{
        List<SaidaDeProduto>saidas = saidaRepository.findAll();
        if (!saidas.isEmpty()){

            BigDecimal total = saidas.stream()
                    .map(saida -> saida.getValorTotaldaVenda())
                    .filter(valorTotaldaVenda -> valorTotaldaVenda != null)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            return total;

        }else {
            throw new Exception();
        }
   }
//FAZER UM MAP PARA FILTRAR LISTA DE SAINDA
   public BigDecimal lucro() throws Exception{
        List<SaidaDeProduto>saidas = saidaRepository.findAll();
        List<Produto>totalVendas = produtoRepository.findAll();
        if(!saidas.isEmpty() && !totalVendas.isEmpty()){

        BigDecimal TotaldeCompra = saidas.stream()
                .map(saida->saida.getValorDaUnidade())
                .filter(valordaUnidade->valordaUnidade !=null)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalDeVenda = totalVendas.stream()
                .map(produto -> produto.getValorDaUnidade())
                .filter(valorTotalDaCompra -> valorTotalDaCompra !=null)
                .reduce(BigDecimal.ZERO,BigDecimal::add);


        BigDecimal lucro = totalDeVenda.subtract(TotaldeCompra);

        return  lucro;


        }else {
            throw new Exception("Não existe compras nem vendas para retornar o valor");
        }


   }

}
