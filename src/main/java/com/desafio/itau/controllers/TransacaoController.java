package com.desafio.itau.controllers;

import com.desafio.itau.business.services.TransacaoService;
import com.desafio.itau.controllers.dtos.TransacaoRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/transacao")
public class TransacaoController {
    private final TransacaoService transacaoService;

    // POST nova transação
    @PostMapping
    @Operation(description = "Endpoint responsável por adicionar transações")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transação gravada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Campos não atendem aos requisitos da transação"),
            @ApiResponse(responseCode = "400", description = "Erro de requisição"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Void> adicionarTransacao(@RequestBody TransacaoRequestDTO transacaoRequestDTO) {
        transacaoService.adicionarTransacao(transacaoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // DELETE todas as transações
    @DeleteMapping
    @Operation(description = "Endpoint responsável por remover todas as transações")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transações removidas com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de requisição"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Void> limparTransacoes() {
        transacaoService.limparTransacoes();
        return ResponseEntity.ok().build();
    }
}
