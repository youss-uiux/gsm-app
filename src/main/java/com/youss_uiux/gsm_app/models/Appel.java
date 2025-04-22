package com.youss_uiux.gsm_app.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Appel {
    private Gsm emetteur;
    private Gsm recepteur;
    private LocalDateTime date;
    private int duree;
    private StatutAppel statut;

    public enum StatutAppel {
        SUCCES,
        INJOIGNABLE,
        SOLDE_INSUFFISANT
    }
}
