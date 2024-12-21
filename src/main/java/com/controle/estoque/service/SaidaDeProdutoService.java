package com.controle.estoque.service;

import com.controle.estoque.model.SaidaDeProduto;
import com.controle.estoque.repository.SaindaDeProdutoRepository;
import jakarta.transaction.Transactional;
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

     SaindaDeProdutoRepository saidaRepository;

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


}
