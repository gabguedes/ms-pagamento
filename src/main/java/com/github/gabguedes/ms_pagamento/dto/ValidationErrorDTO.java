package com.github.gabguedes.ms_pagamento.dto;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ValidationErrorDTO extends CustomErrorDTO{

    private List<FieldMessageDto> errors = new ArrayList<>();

    public ValidationErrorDTO(String timestamp, Integer status, String error, String path) {
        super(timestamp, status, error, path);
    }

    public void addError (String fieldName, String message){
        errors.removeIf(x -> x.getFieldName().equals(fieldName));
        errors.add(new FieldMessageDto(fieldName, message));
    }
}
