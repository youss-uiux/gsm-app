package com.youss_uiux.gsm_app.models;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Sim {
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