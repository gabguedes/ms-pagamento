package com.github.gabguedes.ms_pagamento.dto;

import com.github.gabguedes.ms_pagamento.model.Pagamento;
import com.github.gabguedes.ms_pagamento.model.Status;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PagamentoDTO {

    private Long id;
    @NotNull(message = "Campo Obrigatório")
    @Positive(message = "O valor deve ser positivo")
    private BigDecimal valor;
    @Size(max = 100, message = "Máximo de 100 caractéres")
    private String nome;
    @Size(max = 19, message = "O número do cartão deve ter no máximo 19 caractéres")
    private String numeroDoCartao;
    @Size(max = 5, message = "A validade do cartão deve ter no máximo 5 caractéres")
    private String validade;
    @Size(max = 3, min = 3, message = "O código de segurança deve ter 3 caractéres")
    private String codigoDeSeguranca;
    @Enumerated(value = EnumType.STRING)
    private Status status;
    @NotNull(message = "Pedido ID é obrigatório")
    @Positive(message = "O ID do pedido deve ser um número positivo")
    private Long pedidoId;
    @NotNull(message = "Forma de pagamento ID é obrigatória")
    @Positive(message = "O ID da forma de pagamento  deve ser um número positivo")
    private Long formaDePagamentoId; // 1 - dinheiro | 2 - cartao | 3 - pix

    public PagamentoDTO(Pagamento entity){
        this.id = entity.getId();
        this.valor = entity.getValor();
        this.nome = entity.getNome();
        this.numeroDoCartao = entity.getNumeroDoCartao();
        this.validade = entity.getValidade();
        this.codigoDeSeguranca = entity.getCodigoDeSeguranca();
        this.status = entity.getStatus();
        this.pedidoId = entity.getPedidoId();
        this.formaDePagamentoId = entity.getFormaDePagamentoId();
    }
}
