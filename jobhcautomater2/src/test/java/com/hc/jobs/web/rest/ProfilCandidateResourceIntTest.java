package com.hc.jobs.web.rest;

import com.hc.jobs.JobHcAutomater2App;

import com.hc.jobs.domain.ProfilCandidate;
import com.hc.jobs.repository.ProfilCandidateRepository;
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
 * Test class for the ProfilCandidateResource REST controller.
 *
 * @see ProfilCandidateResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JobHcAutomater2App.class)
public class ProfilCandidateResourceIntTest {

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

    private static final String DEFAULT_TWITER = "AAAAAAAAAA";
    private static final String UPDATED_TWITER = "BBBBBBBBBB";

    @Autowired
    private ProfilCandidateRepository profilCandidateRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restProfilCandidateMockMvc;

    private ProfilCandidate profilCandidate;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProfilCandidateResource profilCandidateResource = new ProfilCandidateResource(profilCandidateRepository);
        this.restProfilCandidateMockMvc = MockMvcBuilders.standaloneSetup(profilCandidateResource)
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
    public static ProfilCandidate createEntity(EntityManager em) {
        ProfilCandidate profilCandidate = new ProfilCandidate()
            .firstName(DEFAULT_FIRST_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .phoneNumber(DEFAULT_PHONE_NUMBER)
            .email(DEFAULT_EMAIL)
            .facebook(DEFAULT_FACEBOOK)
            .linkedIn(DEFAULT_LINKED_IN)
            .twiter(DEFAULT_TWITER);
        return profilCandidate;
    }

    @Before
    public void initTest() {
        profilCandidate = createEntity(em);
    }

    @Test
    @Transactional
    public void createProfilCandidate() throws Exception {
        int databaseSizeBeforeCreate = profilCandidateRepository.findAll().size();

        // Create the ProfilCandidate
        restProfilCandidateMockMvc.perform(post("/api/profil-candidates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(profilCandidate)))
            .andExpect(status().isCreated());

        // Validate the ProfilCandidate in the database
        List<ProfilCandidate> profilCandidateList = profilCandidateRepository.findAll();
        assertThat(profilCandidateList).hasSize(databaseSizeBeforeCreate + 1);
        ProfilCandidate testProfilCandidate = profilCandidateList.get(profilCandidateList.size() - 1);
        assertThat(testProfilCandidate.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testProfilCandidate.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testProfilCandidate.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testProfilCandidate.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testProfilCandidate.getFacebook()).isEqualTo(DEFAULT_FACEBOOK);
        assertThat(testProfilCandidate.getLinkedIn()).isEqualTo(DEFAULT_LINKED_IN);
        assertThat(testProfilCandidate.getTwiter()).isEqualTo(DEFAULT_TWITER);
    }

    @Test
    @Transactional
    public void createProfilCandidateWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = profilCandidateRepository.findAll().size();

        // Create the ProfilCandidate with an existing ID
        profilCandidate.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProfilCandidateMockMvc.perform(post("/api/profil-candidates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(profilCandidate)))
            .andExpect(status().isBadRequest());

        // Validate the ProfilCandidate in the database
        List<ProfilCandidate> profilCandidateList = profilCandidateRepository.findAll();
        assertThat(profilCandidateList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkFirstNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = profilCandidateRepository.findAll().size();
        // set the field null
        profilCandidate.setFirstName(null);

        // Create the ProfilCandidate, which fails.

        restProfilCandidateMockMvc.perform(post("/api/profil-candidates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(profilCandidate)))
            .andExpect(status().isBadRequest());

        List<ProfilCandidate> profilCandidateList = profilCandidateRepository.findAll();
        assertThat(profilCandidateList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLastNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = profilCandidateRepository.findAll().size();
        // set the field null
        profilCandidate.setLastName(null);

        // Create the ProfilCandidate, which fails.

        restProfilCandidateMockMvc.perform(post("/api/profil-candidates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(profilCandidate)))
            .andExpect(status().isBadRequest());

        List<ProfilCandidate> profilCandidateList = profilCandidateRepository.findAll();
        assertThat(profilCandidateList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPhoneNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = profilCandidateRepository.findAll().size();
        // set the field null
        profilCandidate.setPhoneNumber(null);

        // Create the ProfilCandidate, which fails.

        restProfilCandidateMockMvc.perform(post("/api/profil-candidates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(profilCandidate)))
            .andExpect(status().isBadRequest());

        List<ProfilCandidate> profilCandidateList = profilCandidateRepository.findAll();
        assertThat(profilCandidateList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEmailIsRequired() throws Exception {
        int databaseSizeBeforeTest = profilCandidateRepository.findAll().size();
        // set the field null
        profilCandidate.setEmail(null);

        // Create the ProfilCandidate, which fails.

        restProfilCandidateMockMvc.perform(post("/api/profil-candidates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(profilCandidate)))
            .andExpect(status().isBadRequest());

        List<ProfilCandidate> profilCandidateList = profilCandidateRepository.findAll();
        assertThat(profilCandidateList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllProfilCandidates() throws Exception {
        // Initialize the database
        profilCandidateRepository.saveAndFlush(profilCandidate);

        // Get all the profilCandidateList
        restProfilCandidateMockMvc.perform(get("/api/profil-candidates?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(profilCandidate.getId().intValue())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME.toString())))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME.toString())))
            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].facebook").value(hasItem(DEFAULT_FACEBOOK.toString())))
            .andExpect(jsonPath("$.[*].linkedIn").value(hasItem(DEFAULT_LINKED_IN.toString())))
            .andExpect(jsonPath("$.[*].twiter").value(hasItem(DEFAULT_TWITER.toString())));
    }
    

    @Test
    @Transactional
    public void getProfilCandidate() throws Exception {
        // Initialize the database
        profilCandidateRepository.saveAndFlush(profilCandidate);

        // Get the profilCandidate
        restProfilCandidateMockMvc.perform(get("/api/profil-candidates/{id}", profilCandidate.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(profilCandidate.getId().intValue()))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME.toString()))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME.toString()))
            .andExpect(jsonPath("$.phoneNumber").value(DEFAULT_PHONE_NUMBER.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.facebook").value(DEFAULT_FACEBOOK.toString()))
            .andExpect(jsonPath("$.linkedIn").value(DEFAULT_LINKED_IN.toString()))
            .andExpect(jsonPath("$.twiter").value(DEFAULT_TWITER.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingProfilCandidate() throws Exception {
        // Get the profilCandidate
        restProfilCandidateMockMvc.perform(get("/api/profil-candidates/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProfilCandidate() throws Exception {
        // Initialize the database
        profilCandidateRepository.saveAndFlush(profilCandidate);

        int databaseSizeBeforeUpdate = profilCandidateRepository.findAll().size();

        // Update the profilCandidate
        ProfilCandidate updatedProfilCandidate = profilCandidateRepository.findById(profilCandidate.getId()).get();
        // Disconnect from session so that the updates on updatedProfilCandidate are not directly saved in db
        em.detach(updatedProfilCandidate);
        updatedProfilCandidate
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .email(UPDATED_EMAIL)
            .facebook(UPDATED_FACEBOOK)
            .linkedIn(UPDATED_LINKED_IN)
            .twiter(UPDATED_TWITER);

        restProfilCandidateMockMvc.perform(put("/api/profil-candidates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedProfilCandidate)))
            .andExpect(status().isOk());

        // Validate the ProfilCandidate in the database
        List<ProfilCandidate> profilCandidateList = profilCandidateRepository.findAll();
        assertThat(profilCandidateList).hasSize(databaseSizeBeforeUpdate);
        ProfilCandidate testProfilCandidate = profilCandidateList.get(profilCandidateList.size() - 1);
        assertThat(testProfilCandidate.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testProfilCandidate.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testProfilCandidate.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testProfilCandidate.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testProfilCandidate.getFacebook()).isEqualTo(UPDATED_FACEBOOK);
        assertThat(testProfilCandidate.getLinkedIn()).isEqualTo(UPDATED_LINKED_IN);
        assertThat(testProfilCandidate.getTwiter()).isEqualTo(UPDATED_TWITER);
    }

    @Test
    @Transactional
    public void updateNonExistingProfilCandidate() throws Exception {
        int databaseSizeBeforeUpdate = profilCandidateRepository.findAll().size();

        // Create the ProfilCandidate

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restProfilCandidateMockMvc.perform(put("/api/profil-candidates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(profilCandidate)))
            .andExpect(status().isBadRequest());

        // Validate the ProfilCandidate in the database
        List<ProfilCandidate> profilCandidateList = profilCandidateRepository.findAll();
        assertThat(profilCandidateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProfilCandidate() throws Exception {
        // Initialize the database
        profilCandidateRepository.saveAndFlush(profilCandidate);

        int databaseSizeBeforeDelete = profilCandidateRepository.findAll().size();

        // Get the profilCandidate
        restProfilCandidateMockMvc.perform(delete("/api/profil-candidates/{id}", profilCandidate.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ProfilCandidate> profilCandidateList = profilCandidateRepository.findAll();
        assertThat(profilCandidateList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProfilCandidate.class);
        ProfilCandidate profilCandidate1 = new ProfilCandidate();
        profilCandidate1.setId(1L);
        ProfilCandidate profilCandidate2 = new ProfilCandidate();
        profilCandidate2.setId(profilCandidate1.getId());
        assertThat(profilCandidate1).isEqualTo(profilCandidate2);
        profilCandidate2.setId(2L);
        assertThat(profilCandidate1).isNotEqualTo(profilCandidate2);
        profilCandidate1.setId(null);
        assertThat(profilCandidate1).isNotEqualTo(profilCandidate2);
    }
}
