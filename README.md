OMR

```mermaid
classDiagram
    class Cliente {
        Long id
        String nome
        String documento
    }

    class Produto {
        Long id
        String descricao
        Double quantidade
        BigDecimal valorDaUnidade
        BigDecimal valorDeCompra
        String marca
        String codigo
        String dataCadastro
    }

    class Categoria {
        Long id
        String tipo
    }

    class Contato {
        Long id
        String telefone
        String ddd
        String email
    }

    class Endereco {
        Long id
        String cep
        String numero
        String logradouro
        String bairro
        String estado
        String ibge
    }

    class Total {
        Long id
        BigDecimal total
    }

    class Fornecedor {
        Long id
        String nome
        String documento
    }

    class Venda {
        Long id
        int quantidade
        BigDecimal valorDaVendaUnidade
        BigDecimal valorTotalDaVenda
    }

    Cliente "1" --> "1" Contato : possui
    Cliente "1" --> "1" Endereco : possui
    Cliente "1" --> "0..*" Produto : compra
    Produto "1" --> "1" Categoria : pertence
    Produto "1" --> "1" Total : possui
    Produto "0..*" --> "1" Cliente : "é comprado por"
    Fornecedor "1" --> "1" Contato : possui
    Fornecedor "1" --> "1" Endereco : possui
    Venda "1" --> "1" Produto : contém
    Venda "1" --> "1" Cliente : "é realizada para"
