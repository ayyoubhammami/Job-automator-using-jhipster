package com.hc.jobs.repository;

import com.hc.jobs.domain.Candidature;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Candidature entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CandidatureRepository extends JpaRepository<Candidature, Long> {

}
