package br.com.fabianoaono.rinha.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;

import java.time.LocalDateTime;

@RegisterForReflection
public class TransacaoDTO {
    private int valor;
    private String tipo;
    private String descricao;
    private LocalDateTime realizadaEm;

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getRealizadaEm() {
        return realizadaEm;
    }

    public void setRealizadaEm(LocalDateTime realizadaEm) {
        this.realizadaEm = realizadaEm;
    }
}
