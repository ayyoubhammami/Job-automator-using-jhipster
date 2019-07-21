package com.hc.jobs.repository;

import com.hc.jobs.domain.EmployeeDetails;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EmployeeDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EmployeeDetailsRepository extends JpaRepository<EmployeeDetails, Long> {
	
	Optional<EmployeeDetails> findByUserId(Long id);

}
