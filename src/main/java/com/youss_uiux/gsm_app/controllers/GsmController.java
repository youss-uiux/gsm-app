package com.youss_uiux.gsm_app.controllers;


import com.youss_uiux.gsm_app.models.Gsm;
import com.youss_uiux.gsm_app.services.GsmService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/gsm")
public class GsmController {
    private final GsmService gsmService;

    public GsmController(GsmService gsmService) {
        this.gsmService = gsmService;
    }

    @PostMapping
    public Mono<Gsm> create(@RequestBody Gsm gsm) {
        return gsmService.createGsm(gsm);
    }

    @GetMapping("/{id}")
    public Mono<Gsm> getById(@PathVariable String id) {
        return gsmService.getById(id);
    }

    @GetMapping("/by-numero")
    public Mono<Gsm> getByNumero(@RequestParam String numero) {
        return gsmService.getByNumero(numero);
    }

    @GetMapping
    public Flux<Gsm> getAll() {
        return gsmService.getAll();
    }

    @PutMapping("/{id}")
    public Mono<Gsm> update(@PathVariable String id, @RequestBody Gsm gsm) {
        return gsmService.updateGsm(id, gsm);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        return gsmService.delete(id);
    }
}
