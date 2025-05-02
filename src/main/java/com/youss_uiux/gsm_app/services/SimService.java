package com.youss_uiux.gsm_app.services;

import com.youss_uiux.gsm_app.models.Sim;
import com.youss_uiux.gsm_app.repository.SimRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class SimService {
    private final SimRepository simRepository;

    public SimService(SimRepository simRepository) {
        this.simRepository = simRepository;
    }

    public Mono<Sim> createSim(Sim sim) {
        return Mono.fromCallable(() -> simRepository.save(sim))
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<Sim> getById(String id) {
        return Mono.fromCallable(() -> simRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("SIM non trouvée")))
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Flux<Sim> getAll() {
        return Mono.fromCallable(simRepository::findAll)
                .flatMapMany(Flux::fromIterable)
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<Void> deleteById(String id) {
        return Mono.fromRunnable(() -> simRepository.deleteById(id))
                .subscribeOn(Schedulers.boundedElastic())
                .then();
    }

    public Mono<Sim> updateSim(String id, Sim newSim) {
        return getById(id)
                .flatMap(existingSim -> {
                    existingSim.setNumero(newSim.getNumero());
                    existingSim.setOperateur(newSim.getOperateur());
                    existingSim.setUnites(newSim.getUnites());
                    return createSim(existingSim);
                });
    }
}
