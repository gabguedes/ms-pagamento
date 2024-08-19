package com.github.gabguedes.ms_pagamento.service.exception;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException (String message){
        super(message);
    }

}
