package com.hc.jobs.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hc.jobs.domain.Keyword;
import com.hc.jobs.repository.KeywordRepository;
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
 * REST controller for managing Keyword.
 */
@RestController
@RequestMapping("/api")
public class KeywordResource {

    private final Logger log = LoggerFactory.getLogger(KeywordResource.class);

    private static final String ENTITY_NAME = "keyword";

    private final KeywordRepository keywordRepository;

    public KeywordResource(KeywordRepository keywordRepository) {
        this.keywordRepository = keywordRepository;
    }

    /**
     * POST  /keywords : Create a new keyword.
     *
     * @param keyword the keyword to create
     * @return the ResponseEntity with status 201 (Created) and with body the new keyword, or with status 400 (Bad Request) if the keyword has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/keywords")
    @Timed
    public ResponseEntity<Keyword> createKeyword(@Valid @RequestBody Keyword keyword) throws URISyntaxException {
        log.debug("REST request to save Keyword : {}", keyword);
        if (keyword.getId() != null) {
            throw new BadRequestAlertException("A new keyword cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Keyword result = keywordRepository.save(keyword);
        return ResponseEntity.created(new URI("/api/keywords/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /keywords : Updates an existing keyword.
     *
     * @param keyword the keyword to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated keyword,
     * or with status 400 (Bad Request) if the keyword is not valid,
     * or with status 500 (Internal Server Error) if the keyword couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/keywords")
    @Timed
    public ResponseEntity<Keyword> updateKeyword(@Valid @RequestBody Keyword keyword) throws URISyntaxException {
        log.debug("REST request to update Keyword : {}", keyword);
        if (keyword.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Keyword result = keywordRepository.save(keyword);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, keyword.getId().toString()))
            .body(result);
    }

    /**
     * GET  /keywords : get all the keywords.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of keywords in body
     */
    @GetMapping("/keywords")
    @Timed
    public ResponseEntity<List<Keyword>> getAllKeywords(Pageable pageable) {
        log.debug("REST request to get a page of Keywords");
        Page<Keyword> page = keywordRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/keywords");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /keywords/:id : get the "id" keyword.
     *
     * @param id the id of the keyword to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the keyword, or with status 404 (Not Found)
     */
    @GetMapping("/keywords/{id}")
    @Timed
    public ResponseEntity<Keyword> getKeyword(@PathVariable Long id) {
        log.debug("REST request to get Keyword : {}", id);
        Optional<Keyword> keyword = keywordRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(keyword);
    }

    /**
     * DELETE  /keywords/:id : delete the "id" keyword.
     *
     * @param id the id of the keyword to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/keywords/{id}")
    @Timed
    public ResponseEntity<Void> deleteKeyword(@PathVariable Long id) {
        log.debug("REST request to delete Keyword : {}", id);

        keywordRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
