package br.com.fabianoaono.rinha.dto;

import java.util.List;

public class ExtratoDTO {
    private SaldoDTO saldoDTO;
    private List<TransacaoDTO> ultimasTransacoes;

    public SaldoDTO getSaldo() {
        return saldoDTO;
    }

    public void setSaldo(SaldoDTO saldoDTO) {
        this.saldoDTO = saldoDTO;
    }

    public List<TransacaoDTO> getUltimasTransacoes() {
        return ultimasTransacoes;
    }

    public void setUltimasTransacoes(List<TransacaoDTO> ultimasTransacoes) {
        this.ultimasTransacoes = ultimasTransacoes;
    }
}
