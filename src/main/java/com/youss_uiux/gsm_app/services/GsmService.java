package com.youss_uiux.gsm_app.services;

import com.youss_uiux.gsm_app.models.Gsm;
import com.youss_uiux.gsm_app.repository.GsmRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class GsmService {
    private final GsmRepository gsmRepository;

    public GsmService(GsmRepository gsmRepository) {
        this.gsmRepository = gsmRepository;
    }

    public Mono<Gsm> createGsm(Gsm gsm) {
        return Mono.fromCallable(() -> gsmRepository.save(gsm))
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<Gsm> getById(String id) {
        return Mono.fromCallable(() -> gsmRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("GSM non trouvé")))
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<Gsm> getByNumero(String numero) {
        return Mono.fromCallable(() -> gsmRepository.findBySim_Numero(numero)
                        .orElseThrow(() -> new RuntimeException("GSM introuvable par numéro")))
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Flux<Gsm> getAll() {
        return Mono.fromCallable(gsmRepository::findAll)
                .flatMapMany(Flux::fromIterable)
                .subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<Void> delete(String id) {
        return Mono.fromRunnable(() -> gsmRepository.deleteById(id))
                .subscribeOn(Schedulers.boundedElastic())
                .then();
    }

    public Mono<Gsm> updateGsm(String id, Gsm updated) {
        return getById(id)
                .flatMap(existing -> {
                    existing.setMarque(updated.getMarque());
                    existing.setModele(updated.getModele());
                    existing.setAllume(updated.isAllume());
                    existing.setSim(updated.getSim());
                    return createGsm(existing);
                });
    }
}
