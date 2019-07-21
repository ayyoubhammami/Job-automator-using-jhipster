package com.hc.jobs.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hc.jobs.domain.EmployeeDetails;
import com.hc.jobs.repository.EmployeeDetailsRepository;
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
 * REST controller for managing EmployeeDetails.
 */
@RestController
@RequestMapping("/api")
public class EmployeeDetailsResource {

    private final Logger log = LoggerFactory.getLogger(EmployeeDetailsResource.class);

    private static final String ENTITY_NAME = "employeeDetails";

    private final EmployeeDetailsRepository employeeDetailsRepository;

    public EmployeeDetailsResource(EmployeeDetailsRepository employeeDetailsRepository) {
        this.employeeDetailsRepository = employeeDetailsRepository;
    }

    /**
     * POST  /employee-details : Create a new employeeDetails.
     *
     * @param employeeDetails the employeeDetails to create
     * @return the ResponseEntity with status 201 (Created) and with body the new employeeDetails, or with status 400 (Bad Request) if the employeeDetails has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/employee-details")
    @Timed
    public ResponseEntity<EmployeeDetails> createEmployeeDetails(@Valid @RequestBody EmployeeDetails employeeDetails) throws URISyntaxException {
        log.debug("REST request to save EmployeeDetails : {}", employeeDetails);
        if (employeeDetails.getId() != null) {
            throw new BadRequestAlertException("A new employeeDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EmployeeDetails result = employeeDetailsRepository.save(employeeDetails);
        return ResponseEntity.created(new URI("/api/employee-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /employee-details : Updates an existing employeeDetails.
     *
     * @param employeeDetails the employeeDetails to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated employeeDetails,
     * or with status 400 (Bad Request) if the employeeDetails is not valid,
     * or with status 500 (Internal Server Error) if the employeeDetails couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/employee-details")
    @Timed
    public ResponseEntity<EmployeeDetails> updateEmployeeDetails(@Valid @RequestBody EmployeeDetails employeeDetails) throws URISyntaxException {
        log.debug("REST request to update EmployeeDetails : {}", employeeDetails);
        if (employeeDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EmployeeDetails result = employeeDetailsRepository.save(employeeDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, employeeDetails.getId().toString()))
            .body(result);
    }

    /**
     * GET  /employee-details : get all the employeeDetails.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of employeeDetails in body
     */
    @GetMapping("/employee-details")
    @Timed
    public ResponseEntity<List<EmployeeDetails>> getAllEmployeeDetails(Pageable pageable) {
        log.debug("REST request to get a page of EmployeeDetails");
        Page<EmployeeDetails> page = employeeDetailsRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/employee-details");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /employee-details/:id : get the "id" employeeDetails.
     *
     * @param id the id of the employeeDetails to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the employeeDetails, or with status 404 (Not Found)
     */
    @GetMapping("/employee-details/{id}")
    @Timed
    public ResponseEntity<EmployeeDetails> getEmployeeDetails(@PathVariable Long id) {
        log.debug("REST request to get EmployeeDetails : {}", id);
        Optional<EmployeeDetails> employeeDetails = employeeDetailsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(employeeDetails);
    }

    /**
     * DELETE  /employee-details/:id : delete the "id" employeeDetails.
     *
     * @param id the id of the employeeDetails to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/employee-details/{id}")
    @Timed
    public ResponseEntity<Void> deleteEmployeeDetails(@PathVariable Long id) {
        log.debug("REST request to delete EmployeeDetails : {}", id);

        employeeDetailsRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }


 @GetMapping("/employee-details-user/{id}")
    @Timed
    public ResponseEntity<EmployeeDetails> getEmployeeDetailsByUser(@PathVariable Long id) {
        log.debug("REST request to get EmployeeDetails : {}", id);
        Optional<EmployeeDetails> employeeDetails = employeeDetailsRepository.findByUserId(id);
        return ResponseUtil.wrapOrNotFound(employeeDetails);
    }


    
}
