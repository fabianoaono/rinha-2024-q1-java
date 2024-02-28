package br.com.fabianoaono.rinha.repository;

import br.com.fabianoaono.rinha.model.Transacao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class TransacaoRepository implements PanacheRepository<Transacao> {
    public List<Transacao> findUltimasTransacoes(Long clienteId) {
        return find("cliente.id = ?1 order by realizada_em desc", clienteId)
                .page(0, 10)
                .list();
    }
}
