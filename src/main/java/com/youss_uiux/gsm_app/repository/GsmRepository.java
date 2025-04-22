package com.youss_uiux.gsm_app.repository;

import com.youss_uiux.gsm_app.models.Gsm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GsmRepository extends JpaRepository <Gsm, String> {
}
