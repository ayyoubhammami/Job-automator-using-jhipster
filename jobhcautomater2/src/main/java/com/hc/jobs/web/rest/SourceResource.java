package com.hc.jobs.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hc.jobs.domain.Source;
import com.hc.jobs.repository.SourceRepository;
import com.hc.jobs.web.rest.errors.BadRequestAlertException;
import com.hc.jobs.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Source.
 */
@RestController
@RequestMapping("/api")
public class SourceResource {

    private final Logger log = LoggerFactory.getLogger(SourceResource.class);

    private static final String ENTITY_NAME = "source";

    private final SourceRepository sourceRepository;

    public SourceResource(SourceRepository sourceRepository) {
        this.sourceRepository = sourceRepository;
    }

    /**
     * POST  /sources : Create a new source.
     *
     * @param source the source to create
     * @return the ResponseEntity with status 201 (Created) and with body the new source, or with status 400 (Bad Request) if the source has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sources")
    @Timed
    public ResponseEntity<Source> createSource(@Valid @RequestBody Source source) throws URISyntaxException {
        log.debug("REST request to save Source : {}", source);
        if (source.getId() != null) {
            throw new BadRequestAlertException("A new source cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Source result = sourceRepository.save(source);
        return ResponseEntity.created(new URI("/api/sources/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sources : Updates an existing source.
     *
     * @param source the source to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated source,
     * or with status 400 (Bad Request) if the source is not valid,
     * or with status 500 (Internal Server Error) if the source couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sources")
    @Timed
    public ResponseEntity<Source> updateSource(@Valid @RequestBody Source source) throws URISyntaxException {
        log.debug("REST request to update Source : {}", source);
        if (source.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Source result = sourceRepository.save(source);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, source.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sources : get all the sources.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of sources in body
     */
    @GetMapping("/sources")
    @Timed
    public List<Source> getAllSources() {
        log.debug("REST request to get all Sources");
        return sourceRepository.findAll();
    }

    /**
     * GET  /sources/:id : get the "id" source.
     *
     * @param id the id of the source to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the source, or with status 404 (Not Found)
     */
    @GetMapping("/sources/{id}")
    @Timed
    public ResponseEntity<Source> getSource(@PathVariable Long id) {
        log.debug("REST request to get Source : {}", id);
        Optional<Source> source = sourceRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(source);
    }

    /**
     * DELETE  /sources/:id : delete the "id" source.
     *
     * @param id the id of the source to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sources/{id}")
    @Timed
    public ResponseEntity<Void> deleteSource(@PathVariable Long id) {
        log.debug("REST request to delete Source : {}", id);

        sourceRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
