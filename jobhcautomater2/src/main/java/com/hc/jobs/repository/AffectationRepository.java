package com.hc.jobs.repository;

import com.hc.jobs.domain.Affectation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Affectation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AffectationRepository extends JpaRepository<Affectation, Long> {

    @Query(value = "select distinct affectation from Affectation affectation left join fetch affectation.freelancerDetails left join fetch affectation.employeeDetails",
        countQuery = "select count(distinct affectation) from Affectation affectation")
    Page<Affectation> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct affectation from Affectation affectation left join fetch affectation.freelancerDetails left join fetch affectation.employeeDetails")
    List<Affectation> findAllWithEagerRelationships();

    @Query("select affectation from Affectation affectation left join fetch affectation.freelancerDetails left join fetch affectation.employeeDetails where affectation.id =:id")
    Optional<Affectation> findOneWithEagerRelationships(@Param("id") Long id);

}
