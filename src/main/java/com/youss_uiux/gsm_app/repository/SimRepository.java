package com.youss_uiux.gsm_app.repository;

import com.youss_uiux.gsm_app.models.Sim;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SimRepository extends JpaRepository<Sim, String> {
    Optional<Sim> findByNumero(String numero);
}
