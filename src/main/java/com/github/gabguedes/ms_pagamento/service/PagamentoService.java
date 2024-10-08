package com.github.gabguedes.ms_pagamento.service;

import com.github.gabguedes.ms_pagamento.dto.PagamentoDTO;
import com.github.gabguedes.ms_pagamento.http.PedidoClient;
import com.github.gabguedes.ms_pagamento.model.Pagamento;
import com.github.gabguedes.ms_pagamento.model.Status;
import com.github.gabguedes.ms_pagamento.repository.PagamentoRepository;
import com.github.gabguedes.ms_pagamento.service.exception.DatabaseException;
import com.github.gabguedes.ms_pagamento.service.exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PagamentoService {

    @Autowired
    private PedidoClient pedidoClient;

    @Autowired
    private PagamentoRepository repository;

    @Transactional(readOnly = true)
    public List<PagamentoDTO> findAll(){
        return repository.findAll().stream().map(PagamentoDTO::new).collect(Collectors.toList());
    }

//    @Transactional(readOnly = true)
//    public Page<PagamentoDTO> findAll(Pageable pageable){
//        Page<Pagamento> page = repository.findAll(pageable);
//        return page.map(PagamentoDTO::new);
//    }

    @Transactional(readOnly = true)
    public PagamentoDTO findById(Long id){
        Pagamento entity = repository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Recurso não encontrado! Id: " + id)
        );

        return new PagamentoDTO(entity);
    }

    @Transactional
    public PagamentoDTO insert(PagamentoDTO dto){
        Pagamento entity = new Pagamento();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new PagamentoDTO(entity);
    }

    private void copyDtoToEntity(PagamentoDTO dto, Pagamento entity) {
        entity.setValor(dto.getValor());
        entity.setNome(dto.getNome());
        entity.setNumeroDoCartao(dto.getNumeroDoCartao());
        entity.setValidade(dto.getValidade());
        entity.setCodigoDeSeguranca(dto.getCodigoDeSeguranca());
        entity.setStatus(Status.CRIADO);
        entity.setPedidoId(dto.getPedidoId());
        entity.setFormaDePagamentoId(dto.getFormaDePagamentoId());
    }

    @Transactional
    public PagamentoDTO update(Long id, PagamentoDTO dto){
        try {
            Pagamento entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new PagamentoDTO(entity);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Recurso não encontrado! Id: " + id);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete (Long id){
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("Recurso não encontrado! Id: " + id);
        }
        try {
            repository.deleteById(id);
        } catch (EntityNotFoundException e){
//            throw new ResourceNotFoundException("Recurso não encontrado! Id: " + id);
            throw new DatabaseException("Falha de integridade  referencial");
        }
    }

    @Transactional
    public void confirmarPagamentoDePedido(Long id){

        Optional<Pagamento> pagamento = repository.findById(id);

        if(pagamento.isEmpty()){
            throw new ResourceNotFoundException("Recurso não encontrado! Id: " + id);
        }

        pagamento.get().setStatus(Status.CONFIRMADO);
        repository.save(pagamento.get());
        pedidoClient.atualizarPagamentoDoPedido(pagamento.get().getPedidoId());
    }

    public void alterarStatusDoPagamento(Long id){

        Optional<Pagamento> pagamento = repository.findById(id);

        if(pagamento.isEmpty()){
            throw new ResourceNotFoundException("Recurso não encontrado! Id: " + id);
        }

        pagamento.get().setStatus(Status.CONFIRMADO_SEM_INTEGRACAO);
        repository.save(pagamento.get());
    }

}
