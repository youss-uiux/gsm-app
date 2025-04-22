package com.youss_uiux.gsm_app.models;

import lombok.Data;

@Data
public class Gsm {
    private String id;
    private String marque;
    private String modele;
    private boolean allume;
    private SIMBean sim;

    public boolean estJoignable() {
        return allume && sim != null;
    }

    public boolean peutAppeler(int duree) {
        return estJoignable() && sim.getUnites() >= duree;
    }
}
