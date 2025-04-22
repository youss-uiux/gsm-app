package com.youss_uiux.gsm_app.controllers;

import com.youss_uiux.gsm_app.models.Appel;
import com.youss_uiux.gsm_app.services.AppelService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api/appels")
public class AppelController {
    private final AppelService appelService;

    public AppelController(AppelService appelService) {
        this.appelService = appelService;
    }

    @PostMapping("/passer")
    public Mono<Appel> passerAppel(@RequestParam String emetteurId,
                                   @RequestParam String recepteurId,
                                   @RequestParam int duree) {
        return appelService.passerAppel(emetteurId, recepteurId, duree);
    }
}
