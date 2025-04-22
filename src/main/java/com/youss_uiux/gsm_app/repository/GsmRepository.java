package com.youss_uiux.gsm_app.repository;

import com.youss_uiux.gsm_app.models.Gsm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GsmRepository extends JpaRepository <Gsm, String> {
    Optional<Gsm> findBySim_Numero(String numero);
}
