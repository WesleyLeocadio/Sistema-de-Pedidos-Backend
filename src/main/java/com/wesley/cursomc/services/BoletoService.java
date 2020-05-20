package com.wesley.cursomc.services;

import java.util.Calendar;
import java.util.Date;

import com.wesley.cursomc.domain.PagamentoComBoleto;
import org.springframework.stereotype.Service;

@Service
public class BoletoService {

    public void preencherPagamentoComBoleto(PagamentoComBoleto pagto, Date instanteDoPedido) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(instanteDoPedido);
        cal.add(Calendar.DAY_OF_MONTH, 7);
        pagto.setDataVencimento(cal.getTime());
    }
}