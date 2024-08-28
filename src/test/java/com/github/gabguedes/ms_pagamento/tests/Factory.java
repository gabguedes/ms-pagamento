package com.github.gabguedes.ms_pagamento.tests;

import com.github.gabguedes.ms_pagamento.model.Pagamento;
import com.github.gabguedes.ms_pagamento.model.Status;

import java.math.BigDecimal;

public class Factory {

    public static Pagamento createPagamento(){

        Pagamento pagamento = new Pagamento(1L, BigDecimal.valueOf(32.25), "Bach",
                "23465145936541245", "07/28", "585",
                Status.CRIADO, 1L, 2L);

        return pagamento;
    }

}
