package com.hc.jobs.repository;

import com.hc.jobs.domain.ProfilCandidate;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ProfilCandidate entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProfilCandidateRepository extends JpaRepository<ProfilCandidate, Long> {

}
