package com.hc.jobs.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hc.jobs.domain.FreelancerDetails;
import com.hc.jobs.repository.FreelancerDetailsRepository;
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
 * REST controller for managing FreelancerDetails.
 */
@RestController
@RequestMapping("/api")
public class FreelancerDetailsResource {

    private final Logger log = LoggerFactory.getLogger(FreelancerDetailsResource.class);

    private static final String ENTITY_NAME = "freelancerDetails";

    private final FreelancerDetailsRepository freelancerDetailsRepository;

    public FreelancerDetailsResource(FreelancerDetailsRepository freelancerDetailsRepository) {
        this.freelancerDetailsRepository = freelancerDetailsRepository;
    }

    /**
     * POST  /freelancer-details : Create a new freelancerDetails.
     *
     * @param freelancerDetails the freelancerDetails to create
     * @return the ResponseEntity with status 201 (Created) and with body the new freelancerDetails, or with status 400 (Bad Request) if the freelancerDetails has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/freelancer-details")
    @Timed
    public ResponseEntity<FreelancerDetails> createFreelancerDetails(@Valid @RequestBody FreelancerDetails freelancerDetails) throws URISyntaxException {
        log.debug("REST request to save FreelancerDetails : {}", freelancerDetails);
        if (freelancerDetails.getId() != null) {
            throw new BadRequestAlertException("A new freelancerDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FreelancerDetails result = freelancerDetailsRepository.save(freelancerDetails);
        return ResponseEntity.created(new URI("/api/freelancer-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /freelancer-details : Updates an existing freelancerDetails.
     *
     * @param freelancerDetails the freelancerDetails to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated freelancerDetails,
     * or with status 400 (Bad Request) if the freelancerDetails is not valid,
     * or with status 500 (Internal Server Error) if the freelancerDetails couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/freelancer-details")
    @Timed
    public ResponseEntity<FreelancerDetails> updateFreelancerDetails(@Valid @RequestBody FreelancerDetails freelancerDetails) throws URISyntaxException {
        log.debug("REST request to update FreelancerDetails : {}", freelancerDetails);
        if (freelancerDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FreelancerDetails result = freelancerDetailsRepository.save(freelancerDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, freelancerDetails.getId().toString()))
            .body(result);
    }

    /**
     * GET  /freelancer-details : get all the freelancerDetails.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of freelancerDetails in body
     */
    @GetMapping("/freelancer-details")
    @Timed
    public ResponseEntity<List<FreelancerDetails>> getAllFreelancerDetails(Pageable pageable) {
        log.debug("REST request to get a page of FreelancerDetails");
        Page<FreelancerDetails> page = freelancerDetailsRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/freelancer-details");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /freelancer-details/:id : get the "id" freelancerDetails.
     *
     * @param id the id of the freelancerDetails to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the freelancerDetails, or with status 404 (Not Found)
     */
    @GetMapping("/freelancer-details/{id}")
    @Timed
    public ResponseEntity<FreelancerDetails> getFreelancerDetails(@PathVariable Long id) {
        log.debug("REST request to get FreelancerDetails : {}", id);
        Optional<FreelancerDetails> freelancerDetails = freelancerDetailsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(freelancerDetails);
    }

    /**
     * DELETE  /freelancer-details/:id : delete the "id" freelancerDetails.
     *
     * @param id the id of the freelancerDetails to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/freelancer-details/{id}")
    @Timed
    public ResponseEntity<Void> deleteFreelancerDetails(@PathVariable Long id) {
        log.debug("REST request to delete FreelancerDetails : {}", id);

        freelancerDetailsRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

@GetMapping("/freelancer-details-user/{id}")
    @Timed
    public ResponseEntity<FreelancerDetails> getFreelancerDetailsByUser(@PathVariable Long id) {
        log.debug("REST request to get FreelancerDetails : {}", id);
        Optional<FreelancerDetails> freelancerDetails = freelancerDetailsRepository.findByUserId(id);
        return ResponseUtil.wrapOrNotFound(freelancerDetails);
    }

    
}
