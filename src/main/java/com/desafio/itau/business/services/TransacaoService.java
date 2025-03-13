package com.desafio.itau.business.services;

import com.desafio.itau.controllers.dtos.TransacaoRequestDTO;
import com.desafio.itau.infrastructure.exceptions.UnprocessableEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransacaoService {

    // Criação de um Array List para armazenagem de dados em memória
    private final List<TransacaoRequestDTO> listaTransacoes = new ArrayList<TransacaoRequestDTO>();

    // Método para adicionar transação
    public void adicionarTransacao(TransacaoRequestDTO dto) {

        log.info("Iniciando a gravação de transação");

        // Exceção de data maior que a atual
        if(dto.dataHora().isAfter(OffsetDateTime.now())) {
            log.error("Data e hora maiores que a data e a hora atuais");
            throw new UnprocessableEntity("Data e hora maiores que a data e a hora atuais");
        }
        // Exceção de valor menor que zero
        if(dto.valor() < 0) {
            log.error("Valor menor que zero");
            throw new UnprocessableEntity("Valor menor que zero");
        }

        listaTransacoes.add(dto);
    }

    // Método para limpar todas as transações
    public void limparTransacoes() {

        log.info("Limpando todas as transações");

        listaTransacoes.clear();
    }

    // Método para retornar todas as transações em um intervalo de tempo
    public List<TransacaoRequestDTO> buscarTransacoes(Integer intervaloBusca) {

        // Horário mínimo para busca
        OffsetDateTime dataHoraIntervalo = OffsetDateTime.now().minusSeconds(intervaloBusca);

        return listaTransacoes.stream().filter(
                transacoes -> transacoes.dataHora().isAfter(dataHoraIntervalo)).toList();
    }
}
