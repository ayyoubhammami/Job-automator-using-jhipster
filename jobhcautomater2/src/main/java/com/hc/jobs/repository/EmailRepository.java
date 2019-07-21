package com.hc.jobs.repository;

import com.hc.jobs.domain.Email;
import com.hc.jobs.domain.enumeration.EmailingCategory;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Email entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {
	
	List<Email> findByCategory(EmailingCategory category);

}
