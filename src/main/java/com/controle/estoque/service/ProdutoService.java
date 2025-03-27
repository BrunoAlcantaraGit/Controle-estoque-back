package com.controle.estoque.service;
import com.controle.estoque.configuration.ValidarCodigo;
import com.controle.estoque.model.Produto;
import com.controle.estoque.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
@Data
public class ProdutoService {

    ProdutoRepository produtoRepository;
    ValidarCodigo validarCodigo;

    public Produto salvarProduto(Produto produto) throws Exception {
        Optional<Produto>  VerificarProduto= produtoRepository.findBycodigo(produto.getCodigo());
       //String validarCampoCodigoDoProduto = validarCodigo.validarNumero(produto.getCodigo());

        if (VerificarProduto.isEmpty()) {
            produto.setCodigo(validarCodigo.validarNumero(produto.getCodigo()));
            return produtoRepository.save(produto);
        } else {
            throw new RuntimeException("Produdo já cadastrado");
        }

    }
    @Transactional
    public Optional<Produto> listarPorId(Long id) throws Exception{
        Optional<Produto> produto = produtoRepository.findById(id);
        if (produto.isPresent()){
            return produto;
        }else{
            throw new Exception("Porduto inexitente");
        }
    }

    @Transactional
    public Produto EditarPorId(Long id, Produto produto) throws Exception {
        Optional<Produto> buscarProduto = produtoRepository.findBycodigo(produto.getCodigo());
        if (buscarProduto.isPresent()) {
            Produto produtoAtualizado = buscarProduto.get();
            produtoAtualizado.setCategoria(produto.getCategoria());
            produtoAtualizado.setDescricao(produto.getDescricao());
            produtoAtualizado.setValorDeCompra(produto.getValorDeCompra());
            produtoAtualizado.setQuantidade(produto.getQuantidade());
            produtoAtualizado.setValorDaUnidade(produto.getValorDaUnidade());
            produtoAtualizado.setMarca(produto.getMarca());
            produtoAtualizado.setCodigo(produto.getCodigo());
            return produto;
        } else {
            throw new Exception("Produto não cadastrado");
        }

    }
    @Transactional
    public void deletarID(Long id) throws Exception {
    Optional<Produto>LocalizarProduto = produtoRepository.findById(id);
        if (LocalizarProduto.isPresent()) {
            produtoRepository.deleteById(id);
        } else {
            throw new Exception("Id inválido");
        }

    }

    public List<Produto> listarTudo() throws Exception{
        List<Produto> verificarLista = produtoRepository.findAll();
        if (!verificarLista.isEmpty()){
            List<Produto>produtos = new ArrayList<>();
            for (Produto p: verificarLista){
                produtos.add(p);
            }
            return produtos;
        }else {
            throw new Exception("Lista não contem elementos ///");
        }
    }

    public BigDecimal somarTotalProdutos() throws Exception{
        List<Produto> produtos = produtoRepository.findAll();

        if(!produtos.isEmpty()){

              BigDecimal totals = produtos.stream()
                      .map(produto -> produto.getValorDeCompra())
                      .filter(valorDeCompra -> valorDeCompra  != null)
                      .reduce(BigDecimal.ZERO, BigDecimal::add);

          return  totals;

        }else{
            throw new Exception("Lista não contem elementos");
        }

    }

    public BigDecimal valorTotalDoProduto(BigDecimal quantidade,BigDecimal valorDaUnidade) throws Exception{
     BigDecimal total =  quantidade.multiply(valorDaUnidade);
    if(quantidade != null && valorDaUnidade !=null){
        return total;

    }else{
        throw new RuntimeException("nenhum paramentro informado");
    }

    }

}
