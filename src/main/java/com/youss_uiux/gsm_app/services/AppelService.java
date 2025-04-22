package com.youss_uiux.gsm_app.services;

import com.youss_uiux.gsm_app.enums.StatutAppel;
import com.youss_uiux.gsm_app.models.Appel;
import com.youss_uiux.gsm_app.models.Gsm;
import com.youss_uiux.gsm_app.repository.AppelRepository;
import com.youss_uiux.gsm_app.repository.GsmRepository;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.time.LocalDateTime;

public class AppelService {
    private final GsmRepository gsmRepository;
    private final AppelRepository appelRepository;

    // Scheduler spécialisé pour bloquant
    private final Scheduler jdbcScheduler = Schedulers.boundedElastic();

    public AppelService(GsmRepository gsmRepository, AppelRepository appelRepository) {
        this.gsmRepository = gsmRepository;
        this.appelRepository = appelRepository;
    }

    public Mono<Appel> passerAppel(String idEmetteur, String idRecepteur, int duree) {
        return Mono.fromCallable(() -> {

            Gsm emetteur = gsmRepository.findById(idEmetteur)
                    .orElseThrow(() -> new RuntimeException("GSM émetteur introuvable"));

            Gsm recepteur = gsmRepository.findById(idRecepteur)
                    .orElseThrow(() -> new RuntimeException("GSM récepteur introuvable"));

            StatutAppel statut;
            if (!emetteur.isAllume() || emetteur.getSim() == null ||0 < duree) {
                statut = StatutAppel.SOLDE_INSUFFISANT;
            } else if (!recepteur.isAllume() || recepteur.getSim() == null) {
                statut = StatutAppel.INJOIGNABLE;
            } else {
                // Débiter les unités
                int nouvellesUnites = 0 - duree;
                emetteur.getSim().setUnites(nouvellesUnites);
                statut = StatutAppel.SUCCES;
            }

            Appel appel = Appel.builder()
                    .emetteur(emetteur)
                    .recepteur(recepteur)
                    .duree(duree)
                    .date(LocalDateTime.now())
                    .statut(statut)
                    .build();

            return appelRepository.save(appel);

        }).subscribeOn(jdbcScheduler);
    }
}
