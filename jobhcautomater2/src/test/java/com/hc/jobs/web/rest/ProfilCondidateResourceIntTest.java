package com.hc.jobs.web.rest;

import com.hc.jobs.JobHcAutomater2App;

import com.hc.jobs.domain.ProfilCondidate;
import com.hc.jobs.repository.ProfilCondidateRepository;
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
import java.util.List;


import static com.hc.jobs.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ProfilCondidateResource REST controller.
 *
 * @see ProfilCondidateResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JobHcAutomater2App.class)
public class ProfilCondidateResourceIntTest {

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_FACEBOOK = "AAAAAAAAAA";
    private static final String UPDATED_FACEBOOK = "BBBBBBBBBB";

    private static final String DEFAULT_LINKED_IN = "AAAAAAAAAA";
    private static final String UPDATED_LINKED_IN = "BBBBBBBBBB";

    @Autowired
    private ProfilCondidateRepository profilCondidateRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restProfilCondidateMockMvc;

    private ProfilCondidate profilCondidate;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProfilCondidateResource profilCondidateResource = new ProfilCondidateResource(profilCondidateRepository);
        this.restProfilCondidateMockMvc = MockMvcBuilders.standaloneSetup(profilCondidateResource)
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
    public static ProfilCondidate createEntity(EntityManager em) {
        ProfilCondidate profilCondidate = new ProfilCondidate()
            .firstName(DEFAULT_FIRST_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .phoneNumber(DEFAULT_PHONE_NUMBER)
            .email(DEFAULT_EMAIL)
            .facebook(DEFAULT_FACEBOOK)
            .linkedIn(DEFAULT_LINKED_IN);
        return profilCondidate;
    }

    @Before
    public void initTest() {
        profilCondidate = createEntity(em);
    }

    @Test
    @Transactional
    public void createProfilCondidate() throws Exception {
        int databaseSizeBeforeCreate = profilCondidateRepository.findAll().size();

        // Create the ProfilCondidate
        restProfilCondidateMockMvc.perform(post("/api/profil-condidates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(profilCondidate)))
            .andExpect(status().isCreated());

        // Validate the ProfilCondidate in the database
        List<ProfilCondidate> profilCondidateList = profilCondidateRepository.findAll();
        assertThat(profilCondidateList).hasSize(databaseSizeBeforeCreate + 1);
        ProfilCondidate testProfilCondidate = profilCondidateList.get(profilCondidateList.size() - 1);
        assertThat(testProfilCondidate.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testProfilCondidate.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testProfilCondidate.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testProfilCondidate.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testProfilCondidate.getFacebook()).isEqualTo(DEFAULT_FACEBOOK);
        assertThat(testProfilCondidate.getLinkedIn()).isEqualTo(DEFAULT_LINKED_IN);
    }

    @Test
    @Transactional
    public void createProfilCondidateWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = profilCondidateRepository.findAll().size();

        // Create the ProfilCondidate with an existing ID
        profilCondidate.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProfilCondidateMockMvc.perform(post("/api/profil-condidates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(profilCondidate)))
            .andExpect(status().isBadRequest());

        // Validate the ProfilCondidate in the database
        List<ProfilCondidate> profilCondidateList = profilCondidateRepository.findAll();
        assertThat(profilCondidateList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkFirstNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = profilCondidateRepository.findAll().size();
        // set the field null
        profilCondidate.setFirstName(null);

        // Create the ProfilCondidate, which fails.

        restProfilCondidateMockMvc.perform(post("/api/profil-condidates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(profilCondidate)))
            .andExpect(status().isBadRequest());

        List<ProfilCondidate> profilCondidateList = profilCondidateRepository.findAll();
        assertThat(profilCondidateList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLastNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = profilCondidateRepository.findAll().size();
        // set the field null
        profilCondidate.setLastName(null);

        // Create the ProfilCondidate, which fails.

        restProfilCondidateMockMvc.perform(post("/api/profil-condidates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(profilCondidate)))
            .andExpect(status().isBadRequest());

        List<ProfilCondidate> profilCondidateList = profilCondidateRepository.findAll();
        assertThat(profilCondidateList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPhoneNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = profilCondidateRepository.findAll().size();
        // set the field null
        profilCondidate.setPhoneNumber(null);

        // Create the ProfilCondidate, which fails.

        restProfilCondidateMockMvc.perform(post("/api/profil-condidates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(profilCondidate)))
            .andExpect(status().isBadRequest());

        List<ProfilCondidate> profilCondidateList = profilCondidateRepository.findAll();
        assertThat(profilCondidateList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEmailIsRequired() throws Exception {
        int databaseSizeBeforeTest = profilCondidateRepository.findAll().size();
        // set the field null
        profilCondidate.setEmail(null);

        // Create the ProfilCondidate, which fails.

        restProfilCondidateMockMvc.perform(post("/api/profil-condidates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(profilCondidate)))
            .andExpect(status().isBadRequest());

        List<ProfilCondidate> profilCondidateList = profilCondidateRepository.findAll();
        assertThat(profilCondidateList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllProfilCondidates() throws Exception {
        // Initialize the database
        profilCondidateRepository.saveAndFlush(profilCondidate);

        // Get all the profilCondidateList
        restProfilCondidateMockMvc.perform(get("/api/profil-condidates?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(profilCondidate.getId().intValue())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME.toString())))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME.toString())))
            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].facebook").value(hasItem(DEFAULT_FACEBOOK.toString())))
            .andExpect(jsonPath("$.[*].linkedIn").value(hasItem(DEFAULT_LINKED_IN.toString())));
    }
    

    @Test
    @Transactional
    public void getProfilCondidate() throws Exception {
        // Initialize the database
        profilCondidateRepository.saveAndFlush(profilCondidate);

        // Get the profilCondidate
        restProfilCondidateMockMvc.perform(get("/api/profil-condidates/{id}", profilCondidate.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(profilCondidate.getId().intValue()))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME.toString()))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME.toString()))
            .andExpect(jsonPath("$.phoneNumber").value(DEFAULT_PHONE_NUMBER.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.facebook").value(DEFAULT_FACEBOOK.toString()))
            .andExpect(jsonPath("$.linkedIn").value(DEFAULT_LINKED_IN.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingProfilCondidate() throws Exception {
        // Get the profilCondidate
        restProfilCondidateMockMvc.perform(get("/api/profil-condidates/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProfilCondidate() throws Exception {
        // Initialize the database
        profilCondidateRepository.saveAndFlush(profilCondidate);

        int databaseSizeBeforeUpdate = profilCondidateRepository.findAll().size();

        // Update the profilCondidate
        ProfilCondidate updatedProfilCondidate = profilCondidateRepository.findById(profilCondidate.getId()).get();
        // Disconnect from session so that the updates on updatedProfilCondidate are not directly saved in db
        em.detach(updatedProfilCondidate);
        updatedProfilCondidate
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .email(UPDATED_EMAIL)
            .facebook(UPDATED_FACEBOOK)
            .linkedIn(UPDATED_LINKED_IN);

        restProfilCondidateMockMvc.perform(put("/api/profil-condidates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedProfilCondidate)))
            .andExpect(status().isOk());

        // Validate the ProfilCondidate in the database
        List<ProfilCondidate> profilCondidateList = profilCondidateRepository.findAll();
        assertThat(profilCondidateList).hasSize(databaseSizeBeforeUpdate);
        ProfilCondidate testProfilCondidate = profilCondidateList.get(profilCondidateList.size() - 1);
        assertThat(testProfilCondidate.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testProfilCondidate.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testProfilCondidate.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testProfilCondidate.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testProfilCondidate.getFacebook()).isEqualTo(UPDATED_FACEBOOK);
        assertThat(testProfilCondidate.getLinkedIn()).isEqualTo(UPDATED_LINKED_IN);
    }

    @Test
    @Transactional
    public void updateNonExistingProfilCondidate() throws Exception {
        int databaseSizeBeforeUpdate = profilCondidateRepository.findAll().size();

        // Create the ProfilCondidate

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restProfilCondidateMockMvc.perform(put("/api/profil-condidates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(profilCondidate)))
            .andExpect(status().isBadRequest());

        // Validate the ProfilCondidate in the database
        List<ProfilCondidate> profilCondidateList = profilCondidateRepository.findAll();
        assertThat(profilCondidateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProfilCondidate() throws Exception {
        // Initialize the database
        profilCondidateRepository.saveAndFlush(profilCondidate);

        int databaseSizeBeforeDelete = profilCondidateRepository.findAll().size();

        // Get the profilCondidate
        restProfilCondidateMockMvc.perform(delete("/api/profil-condidates/{id}", profilCondidate.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ProfilCondidate> profilCondidateList = profilCondidateRepository.findAll();
        assertThat(profilCondidateList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProfilCondidate.class);
        ProfilCondidate profilCondidate1 = new ProfilCondidate();
        profilCondidate1.setId(1L);
        ProfilCondidate profilCondidate2 = new ProfilCondidate();
        profilCondidate2.setId(profilCondidate1.getId());
        assertThat(profilCondidate1).isEqualTo(profilCondidate2);
        profilCondidate2.setId(2L);
        assertThat(profilCondidate1).isNotEqualTo(profilCondidate2);
        profilCondidate1.setId(null);
        assertThat(profilCondidate1).isNotEqualTo(profilCondidate2);
    }
}
