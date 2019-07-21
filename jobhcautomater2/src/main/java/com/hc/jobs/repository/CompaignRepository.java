package com.hc.jobs.repository;

import com.hc.jobs.domain.Compaign;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Compaign entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CompaignRepository extends JpaRepository<Compaign, Long> {

    @Query(value = "select distinct compaign from Compaign compaign left join fetch compaign.socialMedias",
        countQuery = "select count(distinct compaign) from Compaign compaign")
    Page<Compaign> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct compaign from Compaign compaign left join fetch compaign.socialMedias")
    List<Compaign> findAllWithEagerRelationships();

    @Query("select compaign from Compaign compaign left join fetch compaign.socialMedias where compaign.id =:id")
    Optional<Compaign> findOneWithEagerRelationships(@Param("id") Long id);

}
