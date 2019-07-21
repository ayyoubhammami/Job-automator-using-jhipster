package com.hc.jobs.repository;

import com.hc.jobs.domain.ProfilCondidate;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ProfilCondidate entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProfilCondidateRepository extends JpaRepository<ProfilCondidate, Long> {

}
