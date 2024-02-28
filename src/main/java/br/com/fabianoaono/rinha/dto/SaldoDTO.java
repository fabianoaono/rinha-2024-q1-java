package br.com.fabianoaono.rinha.dto;

import java.time.LocalDateTime;

public class SaldoDTO {

    private int total;
    private LocalDateTime dataExtrato;
    private int limite;

    public SaldoDTO(int total, LocalDateTime dataExtrato, int limite) {
        this.total = total;
        this.dataExtrato = dataExtrato;
        this.limite = limite;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public LocalDateTime getDataExtrato() {
        return dataExtrato;
    }

    public void setDataExtrato(LocalDateTime dataExtrato) {
        this.dataExtrato = dataExtrato;
    }

    public int getLimite() {
        return limite;
    }

    public void setLimite(int limite) {
        this.limite = limite;
    }
}
