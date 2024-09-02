package com.github.gabguedes.ms_pagamento.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.gabguedes.ms_pagamento.dto.PagamentoDTO;
import com.github.gabguedes.ms_pagamento.service.PagamentoService;
import com.github.gabguedes.ms_pagamento.tests.Factory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PagamentoController.class)
public class PagamentoControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PagamentoService service;
    private PagamentoDTO pagamentoDTO;
    private Long existingId;
    private Long nonExistingId;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() throws Exception{

        pagamentoDTO = Factory.createPagamentoDTO();

        List<PagamentoDTO> list = List.of(pagamentoDTO);

        when(service.findAll()).thenReturn(list);
    }

    @Test
    public void findAllShouldReturnListPagamentoDTO() throws Exception{
        ResultActions result = mockMvc.perform(get("/pagamentos").accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());
    }
}
