package com.youss_uiux.gsm_app.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Sim {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(unique = true)
    private String numero;
    private String operateur;
    private int unites;

    public boolean debiter(int duree) {
        if (unites >= duree) {
            unites -= duree;
            return true;
        }
        return false;
    }
}