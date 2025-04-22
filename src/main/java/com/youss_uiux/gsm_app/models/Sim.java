package com.youss_uiux.gsm_app.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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