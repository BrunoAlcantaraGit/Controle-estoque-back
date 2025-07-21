package com.controle.estoque.service;

import com.controle.estoque.model.Cliente;
import com.controle.estoque.model.Orcamento;
import com.controle.estoque.model.Produto;
import com.controle.estoque.model.Venda;
import com.controle.estoque.repository.VendaRepository;
import com.controle.estoque.v1.dto.VendaResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class VendaServiceTest {

@Mock
    private VendaRepository vendaRepository;

@InjectMocks
    private VendaService vendaService;

 @BeforeEach
    void setup(){
     MockitoAnnotations.openMocks(this);
 }
 @Test
 void deveListarVendas_QuandoExistiremVendas() throws Exception{

     Produto produto = new Produto();
     produto.setId(1L);
     Orcamento orcamento = new Orcamento();
     orcamento.setId(1L);
     Cliente cliente = new Cliente();

     cliente.setNome("João");

     Venda venda = new Venda();
     venda.setCliente(cliente);
     venda.setProdutos(List.of(produto));
     venda.setOrcamentos(List.of(orcamento));
     venda.setLucro(100.0);
     venda.setValorTotalDaVenda(1000.0);

     when(vendaRepository.findAll()).thenReturn(List.of(venda));

     List<VendaResponseDTO> resultado = vendaService.listar();

     assertNotNull(resultado);
     assertEquals(1, resultado.size());
     assertEquals("João", resultado.get(0).cliente());
     assertEquals(List.of(1L), resultado.get(0).produtoIds());
     assertEquals(List.of(1L), resultado.get(0).orcamentoIds());
     assertEquals(100.0, resultado.get(0).lucro());
     assertEquals(1000.0, resultado.get(0).valorTotalDaVenda());


 }

    @Test
    void deveLancarExcecao_quandoNaoExistiremVendas() {
        // Arrange
        when(vendaRepository.findAll()).thenReturn(List.of());

        // Act & Assert
        Exception ex = assertThrows(Exception.class, () -> vendaService.listar());
        assertEquals("sale list is empty", ex.getMessage());
    }
}
