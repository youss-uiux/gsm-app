package com.youss_uiux.gsm_app.repository;

import com.youss_uiux.gsm_app.models.Appel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppelRepository extends JpaRepository<Appel,String> {
    List<Appel> findByEmetteurIdOrRecepteurId(String emetteurId, String recepteurId);
}
