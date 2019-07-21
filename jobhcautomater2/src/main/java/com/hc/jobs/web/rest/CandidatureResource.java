package com.hc.jobs.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hc.jobs.domain.Candidature;
import com.hc.jobs.repository.CandidatureRepository;
import com.hc.jobs.web.rest.errors.BadRequestAlertException;
import com.hc.jobs.web.rest.util.HeaderUtil;
import com.hc.jobs.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Candidature.
 */
@RestController
@RequestMapping("/api")
public class CandidatureResource {

    private final Logger log = LoggerFactory.getLogger(CandidatureResource.class);

    private static final String ENTITY_NAME = "candidature";

    private final CandidatureRepository candidatureRepository;

    public CandidatureResource(CandidatureRepository candidatureRepository) {
        this.candidatureRepository = candidatureRepository;
    }

    /**
     * POST  /candidatures : Create a new candidature.
     *
     * @param candidature the candidature to create
     * @return the ResponseEntity with status 201 (Created) and with body the new candidature, or with status 400 (Bad Request) if the candidature has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/candidatures")
    @Timed
    public ResponseEntity<Candidature> createCandidature(@Valid @RequestBody Candidature candidature) throws URISyntaxException {
        log.debug("REST request to save Candidature : {}", candidature);
        if (candidature.getId() != null) {
            throw new BadRequestAlertException("A new candidature cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Candidature result = candidatureRepository.save(candidature);
        return ResponseEntity.created(new URI("/api/candidatures/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /candidatures : Updates an existing candidature.
     *
     * @param candidature the candidature to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated candidature,
     * or with status 400 (Bad Request) if the candidature is not valid,
     * or with status 500 (Internal Server Error) if the candidature couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/candidatures")
    @Timed
    public ResponseEntity<Candidature> updateCandidature(@Valid @RequestBody Candidature candidature) throws URISyntaxException {
        log.debug("REST request to update Candidature : {}", candidature);
        if (candidature.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Candidature result = candidatureRepository.save(candidature);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, candidature.getId().toString()))
            .body(result);
    }
    
    
//    @PutMapping("/upStatus")
//    @Timed
//    public ResponseEntity<Candidature> updateStatus(@Valid @RequestBody Candidature candidature) throws URISyntaxException {
//       log.debug("REST request to update status", candidature);
//       if (candidature.getId() == null) {
//           throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
//       }
    /**
     * GET  /candidatures : get all the candidatures.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of candidatures in body
     */
    @GetMapping("/candidatures")
    @Timed
    public ResponseEntity<List<Candidature>> getAllCandidatures(Pageable pageable) {
        log.debug("REST request to get a page of Candidatures");
        Page<Candidature> page = candidatureRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/candidatures");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /candidatures/:id : get the "id" candidature.
     *
     * @param id the id of the candidature to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the candidature, or with status 404 (Not Found)
     */
    @GetMapping("/candidatures/{id}")
    @Timed
    public ResponseEntity<Candidature> getCandidature(@PathVariable Long id) {
        log.debug("REST request to get Candidature : {}", id);
        Optional<Candidature> candidature = candidatureRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(candidature);
    }

    /**
     * DELETE  /candidatures/:id : delete the "id" candidature.
     *
     * @param id the id of the candidature to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/candidatures/{id}")
    @Timed
    public ResponseEntity<Void> deleteCandidature(@PathVariable Long id) {
        log.debug("REST request to delete Candidature : {}", id);

        candidatureRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
