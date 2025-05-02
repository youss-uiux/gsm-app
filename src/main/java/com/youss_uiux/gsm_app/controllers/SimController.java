package com.youss_uiux.gsm_app.controllers;

import com.youss_uiux.gsm_app.models.Sim;
import com.youss_uiux.gsm_app.services.SimService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/sim")
public class SimController {
    private final SimService simService;

    public SimController(SimService simService) {
        this.simService = simService;
    }

    @PostMapping
    public Mono<Sim> create(@RequestBody Sim sim) {
        return simService.createSim(sim);
    }

    @GetMapping("/{id}")
    public Mono<Sim> getById(@PathVariable String id) {
        return simService.getById(id);
    }

    @GetMapping
    public Flux<Sim> getAll() {
        return simService.getAll();
    }

    @PutMapping("/{id}")
    public Mono<Sim> update(@PathVariable String id, @RequestBody Sim sim) {
        return simService.updateSim(id, sim);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        return simService.deleteById(id);
    }
}
