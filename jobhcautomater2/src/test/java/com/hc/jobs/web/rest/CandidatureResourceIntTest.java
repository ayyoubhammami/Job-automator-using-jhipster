package com.hc.jobs.web.rest;

import com.hc.jobs.JobHcAutomater2App;

import com.hc.jobs.domain.Candidature;
import com.hc.jobs.repository.CandidatureRepository;
import com.hc.jobs.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;


import static com.hc.jobs.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.hc.jobs.domain.enumeration.Status;
/**
 * Test class for the CandidatureResource REST controller.
 *
 * @see CandidatureResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JobHcAutomater2App.class)
public class CandidatureResourceIntTest {

    private static final Status DEFAULT_STATUS = Status.POSSIBLE;
    private static final Status UPDATED_STATUS = Status.IN_DISCUSSION;

    private static final LocalDate DEFAULT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private CandidatureRepository candidatureRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCandidatureMockMvc;

    private Candidature candidature;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CandidatureResource candidatureResource = new CandidatureResource(candidatureRepository);
        this.restCandidatureMockMvc = MockMvcBuilders.standaloneSetup(candidatureResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Candidature createEntity(EntityManager em) {
        Candidature candidature = new Candidature()
            .status(DEFAULT_STATUS)
            .date(DEFAULT_DATE);
        return candidature;
    }

    @Before
    public void initTest() {
        candidature = createEntity(em);
    }

    @Test
    @Transactional
    public void createCandidature() throws Exception {
        int databaseSizeBeforeCreate = candidatureRepository.findAll().size();

        // Create the Candidature
        restCandidatureMockMvc.perform(post("/api/candidatures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(candidature)))
            .andExpect(status().isCreated());

        // Validate the Candidature in the database
        List<Candidature> candidatureList = candidatureRepository.findAll();
        assertThat(candidatureList).hasSize(databaseSizeBeforeCreate + 1);
        Candidature testCandidature = candidatureList.get(candidatureList.size() - 1);
        assertThat(testCandidature.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testCandidature.getDate()).isEqualTo(DEFAULT_DATE);
    }

    @Test
    @Transactional
    public void createCandidatureWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = candidatureRepository.findAll().size();

        // Create the Candidature with an existing ID
        candidature.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCandidatureMockMvc.perform(post("/api/candidatures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(candidature)))
            .andExpect(status().isBadRequest());

        // Validate the Candidature in the database
        List<Candidature> candidatureList = candidatureRepository.findAll();
        assertThat(candidatureList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = candidatureRepository.findAll().size();
        // set the field null
        candidature.setStatus(null);

        // Create the Candidature, which fails.

        restCandidatureMockMvc.perform(post("/api/candidatures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(candidature)))
            .andExpect(status().isBadRequest());

        List<Candidature> candidatureList = candidatureRepository.findAll();
        assertThat(candidatureList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCandidatures() throws Exception {
        // Initialize the database
        candidatureRepository.saveAndFlush(candidature);

        // Get all the candidatureList
        restCandidatureMockMvc.perform(get("/api/candidatures?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(candidature.getId().intValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())));
    }
    

    @Test
    @Transactional
    public void getCandidature() throws Exception {
        // Initialize the database
        candidatureRepository.saveAndFlush(candidature);

        // Get the candidature
        restCandidatureMockMvc.perform(get("/api/candidatures/{id}", candidature.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(candidature.getId().intValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingCandidature() throws Exception {
        // Get the candidature
        restCandidatureMockMvc.perform(get("/api/candidatures/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCandidature() throws Exception {
        // Initialize the database
        candidatureRepository.saveAndFlush(candidature);

        int databaseSizeBeforeUpdate = candidatureRepository.findAll().size();

        // Update the candidature
        Candidature updatedCandidature = candidatureRepository.findById(candidature.getId()).get();
        // Disconnect from session so that the updates on updatedCandidature are not directly saved in db
        em.detach(updatedCandidature);
        updatedCandidature
            .status(UPDATED_STATUS)
            .date(UPDATED_DATE);

        restCandidatureMockMvc.perform(put("/api/candidatures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCandidature)))
            .andExpect(status().isOk());

        // Validate the Candidature in the database
        List<Candidature> candidatureList = candidatureRepository.findAll();
        assertThat(candidatureList).hasSize(databaseSizeBeforeUpdate);
        Candidature testCandidature = candidatureList.get(candidatureList.size() - 1);
        assertThat(testCandidature.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testCandidature.getDate()).isEqualTo(UPDATED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingCandidature() throws Exception {
        int databaseSizeBeforeUpdate = candidatureRepository.findAll().size();

        // Create the Candidature

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCandidatureMockMvc.perform(put("/api/candidatures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(candidature)))
            .andExpect(status().isBadRequest());

        // Validate the Candidature in the database
        List<Candidature> candidatureList = candidatureRepository.findAll();
        assertThat(candidatureList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCandidature() throws Exception {
        // Initialize the database
        candidatureRepository.saveAndFlush(candidature);

        int databaseSizeBeforeDelete = candidatureRepository.findAll().size();

        // Get the candidature
        restCandidatureMockMvc.perform(delete("/api/candidatures/{id}", candidature.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Candidature> candidatureList = candidatureRepository.findAll();
        assertThat(candidatureList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Candidature.class);
        Candidature candidature1 = new Candidature();
        candidature1.setId(1L);
        Candidature candidature2 = new Candidature();
        candidature2.setId(candidature1.getId());
        assertThat(candidature1).isEqualTo(candidature2);
        candidature2.setId(2L);
        assertThat(candidature1).isNotEqualTo(candidature2);
        candidature1.setId(null);
        assertThat(candidature1).isNotEqualTo(candidature2);
    }
}
