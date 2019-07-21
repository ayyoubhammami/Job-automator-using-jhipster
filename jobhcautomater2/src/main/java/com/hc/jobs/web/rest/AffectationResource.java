package com.hc.jobs.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hc.jobs.domain.Affectation;
import com.hc.jobs.domain.EmployeeDetails;
import com.hc.jobs.domain.FreelancerDetails;
import com.hc.jobs.domain.Job;

import com.hc.jobs.domain.enumeration.Country;
import com.hc.jobs.repository.AffectationRepository;
import com.hc.jobs.web.rest.errors.BadRequestAlertException;
import com.hc.jobs.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.hc.jobs.SendMail.SendMail;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Affectation.
 */

@RestController
@RequestMapping("/api")
public class AffectationResource {

    private final Logger log = LoggerFactory.getLogger(AffectationResource.class);

    private static final String ENTITY_NAME = "affectation";

    private final AffectationRepository affectationRepository;

    public AffectationResource(AffectationRepository affectationRepository) {
        this.affectationRepository = affectationRepository;
    }

    
    
    
    /**
     * POST  /affectations : Create a new affectation.
     *
     * @param affectation the affectation to create
     * @return the ResponseEntity with status 201 (Created) and with body the new affectation, or with status 400 (Bad Request) if the affectation has already an ID
     * @throws Exception 
     */
    @PostMapping("/affectations")
    @Timed
    public ResponseEntity<Affectation> createAffectation(@Valid @RequestBody Affectation affectation) throws Exception {
        log.debug("REST request to save Affectation : {}", affectation);
        if (affectation.getId() != null) {
            throw new BadRequestAlertException("A new affectation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Affectation result = affectationRepository.save(affectation);
        
        
        for(EmployeeDetails e:result.getEmployeeDetails()) {
       	
                String Str= result.getJob().getTitle();
                
                SendMail.sendMail(e.getUser().getEmail(), Str);
            }

        for(FreelancerDetails ea:result.getFreelancerDetails()) {
        
                String Str= result.getJob().getTitle();
                
                SendMail.sendMail(ea.getUser().getEmail(), Str);
            }
   
        return ResponseEntity.created(new URI("/api/affectations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
    
    
    
    /**
     * PUT  /affectations : Updates an existing affectation.
     *
     * @param affectation the affectation to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated affectation,
     * or with status 400 (Bad Request) if the affectation is not valid,
     * or with status 500 (Internal Server Error) if the affectation couldn't be updated
     * @throws Exception 
     */
    @PutMapping("/affectations")
    @Timed
    public ResponseEntity<Affectation> updateAffectation(@Valid @RequestBody Affectation affectation) throws Exception {
        log.debug("REST request to update Affectation : {}", affectation);
        if (affectation.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        
        Affectation result = affectationRepository.save(affectation);
        
        
   
        for(EmployeeDetails e:result.getEmployeeDetails()) {
        	
           	
            String Str= result.getJob().getTitle();
            
            SendMail.sendMail(e.getUser().getEmail(), Str);
        }
        
       
        
    for(FreelancerDetails ea:result.getFreelancerDetails()) {
 
            String Str= result.getJob().getTitle();
            
            SendMail.sendMail(ea.getUser().getEmail(), Str);
        }
    	
    return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, affectation.getId().toString()))
            .body(result);
    }

    /**
     * GET  /affectations : get all the affectations.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many)
     * @return the ResponseEntity with status 200 (OK) and the list of affectations in body
     */
    @GetMapping("/affectations")
    @Timed
    public List<Affectation> getAllAffectations(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all Affectations");
        return affectationRepository.findAllWithEagerRelationships();
    }

    /**
     * GET  /affectations/:id : get the "id" affectation.
     *
     * @param id the id of the affectation to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the affectation, or with status 404 (Not Found)
     */
    @GetMapping("/affectations/{id}")
    @Timed
    public ResponseEntity<Affectation> getAffectation(@PathVariable Long id) {
        log.debug("REST request to get Affectation : {}", id);
        Optional<Affectation> affectation = affectationRepository.findOneWithEagerRelationships(id);
        return ResponseUtil.wrapOrNotFound(affectation);
    }

    /**
     * DELETE  /affectations/:id : delete the "id" affectation.
     *
     * @param id the id of the affectation to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/affectations/{id}")
    @Timed
    public ResponseEntity<Void> deleteAffectation(@PathVariable Long id) {
        log.debug("REST request to delete Affectation : {}", id);

        affectationRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
