package com.hc.jobs.repository;

import com.hc.jobs.domain.InternalFilter;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the InternalFilter entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InternalFilterRepository extends JpaRepository<InternalFilter, Long> {

}
