package com.desafio.itau.business.services;

import com.desafio.itau.controllers.dtos.EstatisticasResponseDTO;
import com.desafio.itau.controllers.dtos.TransacaoRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EstatisticasService {

    public final TransacaoService transacaoService;

    // Método para cálculo das estatísticas
    public EstatisticasResponseDTO calcularEstatisticas(Integer intervaloBusca) {
        // Buscar transações relevantes
        List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacoes(intervaloBusca);

        // Mapear todos os atributos "valor" da lista de transações para double
        DoubleSummaryStatistics estatisticasTransacoes = transacoes.stream().mapToDouble(TransacaoRequestDTO::valor).summaryStatistics();

        return new EstatisticasResponseDTO(estatisticasTransacoes.getCount(),
                estatisticasTransacoes.getSum(),
                estatisticasTransacoes.getAverage(),
                estatisticasTransacoes.getMin(),
                estatisticasTransacoes.getMax());
    }
}
