package com.github.gabguedes.ms_pagamento.service;

import com.github.gabguedes.ms_pagamento.repository.PagamentoRepository;
import com.github.gabguedes.ms_pagamento.service.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.module.ResolutionException;

@SpringBootTest
@Transactional
public class PagamentoServiceIT {

    @Autowired
    private PagamentoService service;

    @Autowired
    private PagamentoRepository repository;

    private Long existingId;
    private Long nonExistingId;
    private Long countTotalPagamento;

    @BeforeEach
    void setUp() throws Exception{
        existingId = 1L;
        nonExistingId = 50L;
        countTotalPagamento = 9L;
    }

    @Test
    public void deleteShouldDeleteResourceWhenIdExists(){
        service.delete(existingId);
        Assertions.assertEquals(countTotalPagamento - 1, repository.count());
    }

    @Test
    public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist(){

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            service.delete(nonExistingId);
        });
    }

    @Test
    public void findAllShouldReturnListPagamentoDTO(){

        var result = service.findAll();
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(countTotalPagamento, result.size());
        Assertions.assertEquals(Double.valueOf(2000.00), result.get(0).getValor().doubleValue());
        Assertions.assertEquals("Gabriel Guedes", result.get(0).getNome());
        Assertions.assertEquals("Vinicius Cardoso", result.get(1).getNome());
        Assertions.assertNull(result.get(7).getNome());
//      ou Assertions.assertEquals(null, result.get(7).getNome());
    }


}
