package br.com.fabianoaono.rinha.mapper;

import br.com.fabianoaono.rinha.dto.TransacaoDTO;
import br.com.fabianoaono.rinha.model.Transacao;
import jakarta.enterprise.context.ApplicationScoped;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "cdi")
@ApplicationScoped
public interface TransacaoMapper {

    @Mapping(source = "valor", target = "valor")
    @Mapping(source = "tipo", target = "tipo")
    @Mapping(source = "descricao", target = "descricao")
    @Mapping(source = "realizada_em", target = "realizadaEm")
    TransacaoDTO toDTO(Transacao transacao);

    List<TransacaoDTO> toDTOList(List<Transacao> transacoes);

}
