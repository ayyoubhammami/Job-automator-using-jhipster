package com.hc.jobs.repository;

import com.hc.jobs.domain.FreelancerDetails;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the FreelancerDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FreelancerDetailsRepository extends JpaRepository<FreelancerDetails, Long> {
	
	Optional<FreelancerDetails> findByUserId(Long id);

}
