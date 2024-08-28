package com.github.gabguedes.ms_pagamento.repository;

import com.github.gabguedes.ms_pagamento.model.Pagamento;
import com.github.gabguedes.ms_pagamento.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class PagamentoRepositoryTests {

    @Autowired
    private PagamentoRepository repository;

    private Long existingId;
    private Long nonExistinId;
    private Long countTotalPagamento;

    @BeforeEach
    void setup() throws Exception {
        existingId = 1L;
        nonExistinId = 100L;
        countTotalPagamento = 6L;
    }

    @Test
    @DisplayName("delete Deveria excluir o pagamento quando ID existe")
    public void deleteShouldDeleteObjectWhenIdExists() {

        repository.deleteById(existingId);

        Optional<Pagamento> result = repository.findById(existingId);

        Assertions.assertFalse(result.isPresent());
    }

    @Test
    @DisplayName("save Deveria persistir com auto-incremento quando ID é nulo")
    public void saveShouldPersistWithAutoIncrementWhenIdIsNull() {
        Pagamento pagamento = Factory.createPagamento();
        pagamento.setId(null);
        pagamento = repository.save(pagamento);
        Assertions.assertNotNull(pagamento.getId());
        Assertions.assertEquals(countTotalPagamento + 1, pagamento.getId());
    }




}
