package com.hc.jobs.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hc.jobs.domain.InternalFilter;
import com.hc.jobs.repository.InternalFilterRepository;
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
 * REST controller for managing InternalFilter.
 */
@RestController
@RequestMapping("/api")
public class InternalFilterResource {

    private final Logger log = LoggerFactory.getLogger(InternalFilterResource.class);

    private static final String ENTITY_NAME = "internalFilter";

    private final InternalFilterRepository internalFilterRepository;

    public InternalFilterResource(InternalFilterRepository internalFilterRepository) {
        this.internalFilterRepository = internalFilterRepository;
    }

    /**
     * POST  /internal-filters : Create a new internalFilter.
     *
     * @param internalFilter the internalFilter to create
     * @return the ResponseEntity with status 201 (Created) and with body the new internalFilter, or with status 400 (Bad Request) if the internalFilter has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/internal-filters")
    @Timed
    public ResponseEntity<InternalFilter> createInternalFilter(@Valid @RequestBody InternalFilter internalFilter) throws URISyntaxException {
        log.debug("REST request to save InternalFilter : {}", internalFilter);
        if (internalFilter.getId() != null) {
            throw new BadRequestAlertException("A new internalFilter cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InternalFilter result = internalFilterRepository.save(internalFilter);
        return ResponseEntity.created(new URI("/api/internal-filters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /internal-filters : Updates an existing internalFilter.
     *
     * @param internalFilter the internalFilter to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated internalFilter,
     * or with status 400 (Bad Request) if the internalFilter is not valid,
     * or with status 500 (Internal Server Error) if the internalFilter couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/internal-filters")
    @Timed
    public ResponseEntity<InternalFilter> updateInternalFilter(@Valid @RequestBody InternalFilter internalFilter) throws URISyntaxException {
        log.debug("REST request to update InternalFilter : {}", internalFilter);
        if (internalFilter.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InternalFilter result = internalFilterRepository.save(internalFilter);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, internalFilter.getId().toString()))
            .body(result);
    }

    /**
     * GET  /internal-filters : get all the internalFilters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of internalFilters in body
     */
    @GetMapping("/internal-filters")
    @Timed
    public ResponseEntity<List<InternalFilter>> getAllInternalFilters(Pageable pageable) {
        log.debug("REST request to get a page of InternalFilters");
        Page<InternalFilter> page = internalFilterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/internal-filters");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /internal-filters/:id : get the "id" internalFilter.
     *
     * @param id the id of the internalFilter to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the internalFilter, or with status 404 (Not Found)
     */
    @GetMapping("/internal-filters/{id}")
    @Timed
    public ResponseEntity<InternalFilter> getInternalFilter(@PathVariable Long id) {
        log.debug("REST request to get InternalFilter : {}", id);
        Optional<InternalFilter> internalFilter = internalFilterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(internalFilter);
    }

    /**
     * DELETE  /internal-filters/:id : delete the "id" internalFilter.
     *
     * @param id the id of the internalFilter to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/internal-filters/{id}")
    @Timed
    public ResponseEntity<Void> deleteInternalFilter(@PathVariable Long id) {
        log.debug("REST request to delete InternalFilter : {}", id);

        internalFilterRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
