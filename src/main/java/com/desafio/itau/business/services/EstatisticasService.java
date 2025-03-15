package com.desafio.itau.business.services;

import com.desafio.itau.controllers.dtos.EstatisticasResponseDTO;
import com.desafio.itau.controllers.dtos.TransacaoRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EstatisticasService {

    public final TransacaoService transacaoService;

    // Método para cálculo das estatísticas
    public EstatisticasResponseDTO calcularEstatisticas(Integer intervaloBusca) {

        log.info("Iniciada busca de estatísticas para intervalo de " + intervaloBusca + " segundos");
        // Buscar transações relevantes
        List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacoes(intervaloBusca);

        // Mapear todos os atributos "valor" da lista de transações para double
        DoubleSummaryStatistics estatisticasTransacoes = transacoes.stream().mapToDouble(TransacaoRequestDTO::valor).summaryStatistics();

        log.info("Estatísticas calculadas com sucesso");
        return new EstatisticasResponseDTO(
                estatisticasTransacoes.getCount(),
                estatisticasTransacoes.getSum(),
                estatisticasTransacoes.getAverage(),
                estatisticasTransacoes.getCount()!=0 ? estatisticasTransacoes.getMin() : 0,
                estatisticasTransacoes.getCount()!=0 ? estatisticasTransacoes.getMax() : 0
        );
    }
}
