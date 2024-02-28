package br.com.fabianoaono.rinha.resource;

import br.com.fabianoaono.rinha.dto.ExtratoDTO;
import br.com.fabianoaono.rinha.dto.SaldoDTO;
import br.com.fabianoaono.rinha.dto.TransacaoDTO;
import br.com.fabianoaono.rinha.mapper.TransacaoMapper;
import br.com.fabianoaono.rinha.model.Cliente;
import br.com.fabianoaono.rinha.model.Transacao;
import br.com.fabianoaono.rinha.repository.ClienteRepository;
import br.com.fabianoaono.rinha.repository.TransacaoRepository;
import jakarta.inject.Inject;
import jakarta.persistence.LockModeType;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDateTime;
import java.util.List;

@Path("/clientes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClienteResource {

    private final ClienteRepository clienteRepository;

    private final TransacaoRepository transacaoRepository;

    @Inject
    TransacaoMapper transacaoMapper;

    @Inject
    public ClienteResource(ClienteRepository clienteRepository, TransacaoRepository transacaoRepository) {
        this.clienteRepository = clienteRepository;
        this.transacaoRepository = transacaoRepository;
    }

    @POST
    @Transactional
    @Path("/{id}/transacoes")
    public Response createTransacao(@PathParam("id") Long clienteId, Transacao transacao) {

        Cliente cliente = clienteRepository.findById(clienteId);

        if (cliente == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        if (transacao.getValor() <= 0 || transacao.getDescricao().length() < 1 || transacao.getDescricao().length() > 10
                || (!transacao.getTipo().equals("c") && !transacao.getTipo().equals("d"))) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }


        switch (transacao.getTipo()) {
            case "c":
                cliente.setSaldo(cliente.getSaldo() + transacao.getValor());
                break;
            case "d":
                Integer novoSaldo = cliente.getSaldo() - transacao.getValor();
                if (novoSaldo < -cliente.getLimite()) {
                    return Response.status(422).build();
                }
                cliente.setSaldo(novoSaldo);
                break;
        }

        Transacao novaTransacao = Transacao.builder()
                .valor(transacao.getValor())
                .tipo(transacao.getTipo())
                .descricao(transacao.getDescricao())
                .realizada_em(LocalDateTime.now())
                .cliente(cliente)
                .build();

        novaTransacao.setCliente(cliente);
        novaTransacao.persist();
        //cliente.persist();

        return Response.status(Response.Status.OK)
                .entity(cliente)
                .build();
    }

    @GET
    @Path("/{id}/extrato")
    public Response getExtrato(@PathParam("id") Long clienteId) {

        Cliente cliente = clienteRepository.findById(clienteId, LockModeType.PESSIMISTIC_WRITE);
        if (cliente == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        List<Transacao> ultimasTransacoes = transacaoRepository.findUltimasTransacoes(clienteId);
        List<TransacaoDTO> ultimasTransacoesDTO = transacaoMapper.toDTOList(ultimasTransacoes);

        ExtratoDTO extratoDTO = new ExtratoDTO();
        extratoDTO.setSaldo(new SaldoDTO(cliente.getSaldo(), LocalDateTime.now(), cliente.getLimite()));
        extratoDTO.setUltimasTransacoes(ultimasTransacoesDTO);

        return Response.ok(extratoDTO).build();
    }

    @GET
    public List<Cliente> listAll() {

        return clienteRepository.listAll();
    }
}
