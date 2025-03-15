package com.desafio.itau.controllers;

import com.desafio.itau.business.services.EstatisticasService;
import com.desafio.itau.controllers.dtos.EstatisticasResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/estatistica")
public class EstatisticasController {
    private final EstatisticasService estatisticasService;

    @GetMapping
    public ResponseEntity<EstatisticasResponseDTO> obterEstatisticas() {
        EstatisticasResponseDTO estatisticas = estatisticasService.calcularEstatisticas(600);

        return ResponseEntity.ok(estatisticas);
    }
}
