package com.youss_uiux.gsm_app.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Gsm {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // Ou AUTO si tu veux un Long
    private String id;

    private String marque;
    private String modele;

    private boolean allume;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "sim_id", referencedColumnName = "id")
    private Sim sim;

    public boolean estJoignable() {
        return allume && sim != null;
    }

    public boolean peutAppeler(int duree) {
        return estJoignable() && sim.getUnites() >= duree;
    }
}
