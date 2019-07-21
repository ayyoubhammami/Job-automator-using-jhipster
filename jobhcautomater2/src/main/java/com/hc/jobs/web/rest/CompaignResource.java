package com.hc.jobs.web.rest;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.hc.jobs.domain.Compaign;
import com.hc.jobs.repository.CompaignRepository;
import com.hc.jobs.service.CompaignService;
import com.hc.jobs.service.LinkedInService;
import com.hc.jobs.web.rest.errors.BadRequestAlertException;
import com.hc.jobs.web.rest.util.HeaderUtil;
import com.hc.jobs.web.rest.util.PaginationUtil;

import io.github.jhipster.web.util.ResponseUtil;
import twitter4j.TwitterException;


/**
 * REST controller for managing Compaign.
 */
@RestController
@RequestMapping("/api")
public class CompaignResource {
	
	@Autowired
	LinkedInService linkedInService;

    private final Logger log = LoggerFactory.getLogger(CompaignResource.class);

    private static final String ENTITY_NAME = "compaign";
    
    @Autowired
    private CompaignService compaignService;

    private final CompaignRepository compaignRepository;

    public CompaignResource(CompaignRepository compaignRepository) {
        this.compaignRepository = compaignRepository;
    }

    /**
     * POST  /compaigns : Create a new compaign.
     *
     * @param compaign the compaign to create
     * @return the ResponseEntity with status 201 (Created) and with body the new compaign, or with status 400 (Bad Request) if the compaign has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/compaigns")
    @Timed
    public ResponseEntity<Compaign> createCompaign(@Valid @RequestBody Compaign compaign) throws URISyntaxException {
        log.debug("REST request to save Compaign : {}", compaign);
        if (compaign.getId() != null) {
            throw new BadRequestAlertException("A new compaign cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Compaign result = compaignRepository.save(compaign);
        return ResponseEntity.created(new URI("/api/compaigns/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /compaigns : Updates an existing compaign.
     *
     * @param compaign the compaign to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated compaign,
     * or with status 400 (Bad Request) if the compaign is not valid,
     * or with status 500 (Internal Server Error) if the compaign couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/compaigns")
    @Timed
    public ResponseEntity<Compaign> updateCompaign(@Valid @RequestBody Compaign compaign) throws URISyntaxException {
        log.debug("REST request to update Compaign : {}", compaign);
        if (compaign.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Compaign result = compaignRepository.save(compaign);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, compaign.getId().toString()))
            .body(result);
    }

    /**
     * GET  /compaigns : get all the compaigns.
     *
     * @param pageable the pagination information
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many)
     * @return the ResponseEntity with status 200 (OK) and the list of compaigns in body
     */
    @GetMapping("/compaigns")
    @Timed
    public ResponseEntity<List<Compaign>> getAllCompaigns(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get a page of Compaigns");
        Page<Compaign> page;
        if (eagerload) {
            page = compaignRepository.findAllWithEagerRelationships(pageable);
        } else {
            page = compaignRepository.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, String.format("/api/compaigns?eagerload=%b", eagerload));
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /compaigns/:id : get the "id" compaign.
     *
     * @param id the id of the compaign to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the compaign, or with status 404 (Not Found)
     */
    @GetMapping("/compaigns/{id}")
    @Timed
    public ResponseEntity<Compaign> getCompaign(@PathVariable Long id) {
        log.debug("REST request to get Compaign : {}", id);
        Optional<Compaign> compaign = compaignRepository.findOneWithEagerRelationships(id);
        return ResponseUtil.wrapOrNotFound(compaign);
    }

    /**
     * DELETE  /compaigns/:id : delete the "id" compaign.
     *
     * @param id the id of the compaign to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/compaigns/{id}")
    @Timed
    public ResponseEntity<Void> deleteCompaign(@PathVariable Long id) {
        log.debug("REST request to delete Compaign : {}", id);

        compaignRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

//    @GetMapping("/compaigns/post")
//    @Timed
//    public ResponseEntity<Void> postlikedin() throws GeneralSecurityException, IOException{
//    	
//    	linkedInService.post(); 	
//		return new ResponseEntity<Void>(HttpStatus.OK); 	
//    }

    /**
     * POST  /compaigns/publish : publish a compaign.
     *
     * @param compaign the compaign to be published
     * @return the ResponseEntity with status 200 (OK) ,
     * or with status 400 (Bad Request) if the compaign is not valid,
     * or with status 500 (Internal Server Error) if the compaign couldn't be published
     * @throws URISyntaxException if the Location URI syntax is incorrect
     * @throws GeneralSecurityException 
     * @throws TwitterException 
     * @throws IOException 
     * @throws MalformedURLException 
     */
    @PostMapping("/compaigns/publish") 
    @Timed
    public ResponseEntity<Compaign> publishCompaign(@Valid @RequestBody Compaign compaign) throws URISyntaxException, MalformedURLException, IOException,GeneralSecurityException ,TwitterException {

    	 log.debug("REST request to publish a Compaign : {}", compaign);
         if (compaign.getId() == null) {
             throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
         }
         compaignService.publish(compaign);
         
         return ResponseEntity.ok()
             .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, compaign.getId().toString()))
             .body(compaign);
    }

}
