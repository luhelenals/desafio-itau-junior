package com.desafio.itau.controllers;

import com.desafio.itau.business.services.EstatisticasService;
import com.desafio.itau.controllers.dtos.EstatisticasResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/estatistica")
public class EstatisticasController {
    private final EstatisticasService estatisticasService;

    // GET estatísticas de transações
    @GetMapping
    @Operation(description = "Endpoint respnsável por obter as estatísticas das transações")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca efetuada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de requisição"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<EstatisticasResponseDTO> obterEstatisticas(
            @RequestParam(value = "intervaloBusca", required = false, defaultValue = "60") Integer intervaloBusca) {

        EstatisticasResponseDTO estatisticas = estatisticasService.calcularEstatisticas(intervaloBusca);

        return ResponseEntity.ok(estatisticas);
    }
}
