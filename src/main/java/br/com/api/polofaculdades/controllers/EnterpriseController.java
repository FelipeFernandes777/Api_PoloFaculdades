package br.com.api.polofaculdades.controllers;

import br.com.api.polofaculdades.models.enterprise.dto.CreateEnterpriseDTO;

import br.com.api.polofaculdades.models.enterprise.dto.UpdateEnterpriseDataDTO;
import br.com.api.polofaculdades.services.enterprise.EnterpriseServices;
import br.com.api.polofaculdades.utils.GenerateUriToCreateEntities;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/empresas")
public class EnterpriseController {
    private final EnterpriseServices services;

    public EnterpriseController(EnterpriseServices services) {
        this.services = services;
    }

    @PostMapping("/criar")
    public ResponseEntity<?> create(@RequestBody CreateEnterpriseDTO data) {
        try {
            var enterprise = this.services.createEnterprise(data);
            var uri = new GenerateUriToCreateEntities().execute(enterprise.id());
            return ResponseEntity.created(uri).body(enterprise);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Erro ao criar empresa: " + e.getMessage(), "success", false));
        }
    }

    @GetMapping("/listar/todas")
    public ResponseEntity<?> listAll() {
        try {
            var result = this.services.listAllEnterprises();
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Erro ao listar empresas: " + e.getMessage(), "success", false));
        }
    }

    @GetMapping("/listar/todas-ativas")
    public ResponseEntity<?> listAllWhenActiveIsTrue() {
        try {
            var result = this.services.listAllEnterprisesWhenActiveIsTrue();
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Erro ao listar empresas ativas: " + e.getMessage(), "success", false));
        }
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listById(@PathVariable String id) {
        if (id.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "É necessário informar o id da empresa", "success", false));
        }
        try {
            var enterprise = this.services.listEnterpriseById(id).orElseThrow(() -> new RuntimeException("Empresa não encontrada"));
            return ResponseEntity.ok(enterprise);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Empresa não encontrada: " + e.getMessage(), "success", false));
        }
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody UpdateEnterpriseDataDTO data) {
        if (id.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "É necessário informar o id da empresa", "success", false));
        }
        try {
            var enterprise = this.services.updateEnterprise(id, data);
            return ResponseEntity.ok(enterprise);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Erro ao atualizar empresa: " + e.getMessage(), "success", false));
        }
    }

    @PostMapping("/inativar/{id}")
    public ResponseEntity<?> desable(@PathVariable String id) {
        if (id.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "É necessário informar o id da empresa", "success", false));
        }
        try {
            var enterpriseId = this.services.disableEnterprise(id);
            return ResponseEntity.ok(Map.of("message", "Empresa de Id: " + enterpriseId + " foi desativada com sucesso!", "success", true));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Erro ao desativar empresa: " + e.getMessage(), "success", false));
        }
    }

    @PostMapping("/ativar/{id}")
    public ResponseEntity<?> enable(@PathVariable String id) {
        if (id.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "É necessário informar o id da empresa", "success", false));
        }
        try {
            var enterpriseId = this.services.enableEnterprise(id);
            return ResponseEntity.ok(Map.of("message", "Empresa de Id: " + enterpriseId + " foi ativada com sucesso!", "success", true));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Erro ao ativar empresa: " + e.getMessage(), "success", false));
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        if (id.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "É necessário informar o id da empresa", "success", false));
        }
        try {
            this.services.deleteEnterprise(id);
            return ResponseEntity.ok(Map.of("message", "Empresa deletada com sucesso", "success", true));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Erro ao deletar empresa: " + e.getMessage(), "success", false));
        }
    }
}