package com.youss_uiux.gsm_app.models;

import com.youss_uiux.gsm_app.enums.StatutAppel;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Appel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    private Gsm emetteur;

    @ManyToOne
    private Gsm recepteur;

    private LocalDateTime date;

    private int duree;

    @Enumerated(EnumType.STRING)
    private StatutAppel statut;
}
