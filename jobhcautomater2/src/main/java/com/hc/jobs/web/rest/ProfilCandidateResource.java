package com.hc.jobs.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hc.jobs.domain.ProfilCandidate;
import com.hc.jobs.repository.ProfilCandidateRepository;
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
 * REST controller for managing ProfilCandidate.
 */
@RestController
@RequestMapping("/api")
public class ProfilCandidateResource {

    private final Logger log = LoggerFactory.getLogger(ProfilCandidateResource.class);

    private static final String ENTITY_NAME = "profilCandidate";

    private final ProfilCandidateRepository profilCandidateRepository;

    public ProfilCandidateResource(ProfilCandidateRepository profilCandidateRepository) {
        this.profilCandidateRepository = profilCandidateRepository;
    }

    /**
     * POST  /profil-candidates : Create a new profilCandidate.
     *
     * @param profilCandidate the profilCandidate to create
     * @return the ResponseEntity with status 201 (Created) and with body the new profilCandidate, or with status 400 (Bad Request) if the profilCandidate has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/profil-candidates")
    @Timed
    public ResponseEntity<ProfilCandidate> createProfilCandidate(@Valid @RequestBody ProfilCandidate profilCandidate) throws URISyntaxException {
        log.debug("REST request to save ProfilCandidate : {}", profilCandidate);
        if (profilCandidate.getId() != null) {
            throw new BadRequestAlertException("A new profilCandidate cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProfilCandidate result = profilCandidateRepository.save(profilCandidate);
        return ResponseEntity.created(new URI("/api/profil-candidates/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /profil-candidates : Updates an existing profilCandidate.
     *
     * @param profilCandidate the profilCandidate to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated profilCandidate,
     * or with status 400 (Bad Request) if the profilCandidate is not valid,
     * or with status 500 (Internal Server Error) if the profilCandidate couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/profil-candidates")
    @Timed
    public ResponseEntity<ProfilCandidate> updateProfilCandidate(@Valid @RequestBody ProfilCandidate profilCandidate) throws URISyntaxException {
        log.debug("REST request to update ProfilCandidate : {}", profilCandidate);
        if (profilCandidate.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProfilCandidate result = profilCandidateRepository.save(profilCandidate);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, profilCandidate.getId().toString()))
            .body(result);
    }

    /**
     * GET  /profil-candidates : get all the profilCandidates.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of profilCandidates in body
     */
    @GetMapping("/profil-candidates")
    @Timed
    public ResponseEntity<List<ProfilCandidate>> getAllProfilCandidates(Pageable pageable) {
        log.debug("REST request to get a page of ProfilCandidates");
        Page<ProfilCandidate> page = profilCandidateRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/profil-candidates");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /profil-candidates/:id : get the "id" profilCandidate.
     *
     * @param id the id of the profilCandidate to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the profilCandidate, or with status 404 (Not Found)
     */
    @GetMapping("/profil-candidates/{id}")
    @Timed
    public ResponseEntity<ProfilCandidate> getProfilCandidate(@PathVariable Long id) {
        log.debug("REST request to get ProfilCandidate : {}", id);
        Optional<ProfilCandidate> profilCandidate = profilCandidateRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(profilCandidate);
    }

    /**
     * DELETE  /profil-candidates/:id : delete the "id" profilCandidate.
     *
     * @param id the id of the profilCandidate to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/profil-candidates/{id}")
    @Timed
    public ResponseEntity<Void> deleteProfilCandidate(@PathVariable Long id) {
        log.debug("REST request to delete ProfilCandidate : {}", id);

        profilCandidateRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
