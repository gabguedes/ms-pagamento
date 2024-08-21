package com.github.gabguedes.ms_pagamento.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FieldMessageDto {

    private String fieldName;
    private String message;
}
