package com.hc.jobs.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hc.jobs.domain.ProfilCondidate;
import com.hc.jobs.repository.ProfilCondidateRepository;
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
 * REST controller for managing ProfilCondidate.
 */
@RestController
@RequestMapping("/api")
public class ProfilCondidateResource {

    private final Logger log = LoggerFactory.getLogger(ProfilCondidateResource.class);

    private static final String ENTITY_NAME = "profilCondidate";

    private final ProfilCondidateRepository profilCondidateRepository;

    public ProfilCondidateResource(ProfilCondidateRepository profilCondidateRepository) {
        this.profilCondidateRepository = profilCondidateRepository;
    }

    /**
     * POST  /profil-condidates : Create a new profilCondidate.
     *
     * @param profilCondidate the profilCondidate to create
     * @return the ResponseEntity with status 201 (Created) and with body the new profilCondidate, or with status 400 (Bad Request) if the profilCondidate has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/profil-condidates")
    @Timed
    public ResponseEntity<ProfilCondidate> createProfilCondidate(@Valid @RequestBody ProfilCondidate profilCondidate) throws URISyntaxException {
        log.debug("REST request to save ProfilCondidate : {}", profilCondidate);
        if (profilCondidate.getId() != null) {
            throw new BadRequestAlertException("A new profilCondidate cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProfilCondidate result = profilCondidateRepository.save(profilCondidate);
        return ResponseEntity.created(new URI("/api/profil-condidates/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /profil-condidates : Updates an existing profilCondidate.
     *
     * @param profilCondidate the profilCondidate to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated profilCondidate,
     * or with status 400 (Bad Request) if the profilCondidate is not valid,
     * or with status 500 (Internal Server Error) if the profilCondidate couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/profil-condidates")
    @Timed
    public ResponseEntity<ProfilCondidate> updateProfilCondidate(@Valid @RequestBody ProfilCondidate profilCondidate) throws URISyntaxException {
        log.debug("REST request to update ProfilCondidate : {}", profilCondidate);
        if (profilCondidate.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProfilCondidate result = profilCondidateRepository.save(profilCondidate);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, profilCondidate.getId().toString()))
            .body(result);
    }

    /**
     * GET  /profil-condidates : get all the profilCondidates.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of profilCondidates in body
     */
    @GetMapping("/profil-condidates")
    @Timed
    public ResponseEntity<List<ProfilCondidate>> getAllProfilCondidates(Pageable pageable) {
        log.debug("REST request to get a page of ProfilCondidates");
        Page<ProfilCondidate> page = profilCondidateRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/profil-condidates");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /profil-condidates/:id : get the "id" profilCondidate.
     *
     * @param id the id of the profilCondidate to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the profilCondidate, or with status 404 (Not Found)
     */
    @GetMapping("/profil-condidates/{id}")
    @Timed
    public ResponseEntity<ProfilCondidate> getProfilCondidate(@PathVariable Long id) {
        log.debug("REST request to get ProfilCondidate : {}", id);
        Optional<ProfilCondidate> profilCondidate = profilCondidateRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(profilCondidate);
    }

    /**
     * DELETE  /profil-condidates/:id : delete the "id" profilCondidate.
     *
     * @param id the id of the profilCondidate to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/profil-condidates/{id}")
    @Timed
    public ResponseEntity<Void> deleteProfilCondidate(@PathVariable Long id) {
        log.debug("REST request to delete ProfilCondidate : {}", id);

        profilCondidateRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
