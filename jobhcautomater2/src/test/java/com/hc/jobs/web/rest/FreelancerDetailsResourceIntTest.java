package com.hc.jobs.web.rest;

import com.hc.jobs.JobHcAutomater2App;

import com.hc.jobs.domain.FreelancerDetails;
import com.hc.jobs.repository.FreelancerDetailsRepository;
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
import org.springframework.util.Base64Utils;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;


import static com.hc.jobs.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the FreelancerDetailsResource REST controller.
 *
 * @see FreelancerDetailsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JobHcAutomater2App.class)
public class FreelancerDetailsResourceIntTest {

    private static final String DEFAULT_PHONE_1 = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE_2 = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_2 = "BBBBBBBBBB";

    private static final Long DEFAULT_CIN = 1L;
    private static final Long UPDATED_CIN = 2L;

    private static final Instant DEFAULT_DELEVRED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DELEVRED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_CNSS = 1L;
    private static final Long UPDATED_CNSS = 2L;

    private static final Boolean DEFAULT_MARRIED = false;
    private static final Boolean UPDATED_MARRIED = true;

    private static final Long DEFAULT_NUMBER_OF_CHILDREN = 1L;
    private static final Long UPDATED_NUMBER_OF_CHILDREN = 2L;

    private static final Boolean DEFAULT_MOTORIZED = false;
    private static final Boolean UPDATED_MOTORIZED = true;

    private static final Long DEFAULT_SALARY = 1L;
    private static final Long UPDATED_SALARY = 2L;

    private static final Instant DEFAULT_HIRING_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_HIRING_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_COMMENTS = "AAAAAAAAAA";
    private static final String UPDATED_COMMENTS = "BBBBBBBBBB";

    private static final String DEFAULT_CHANNEL_OF_HIRING = "AAAAAAAAAA";
    private static final String UPDATED_CHANNEL_OF_HIRING = "BBBBBBBBBB";

    private static final String DEFAULT_RECOMMENDATION = "AAAAAAAAAA";
    private static final String UPDATED_RECOMMENDATION = "BBBBBBBBBB";

    @Autowired
    private FreelancerDetailsRepository freelancerDetailsRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restFreelancerDetailsMockMvc;

    private FreelancerDetails freelancerDetails;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FreelancerDetailsResource freelancerDetailsResource = new FreelancerDetailsResource(freelancerDetailsRepository);
        this.restFreelancerDetailsMockMvc = MockMvcBuilders.standaloneSetup(freelancerDetailsResource)
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
    public static FreelancerDetails createEntity(EntityManager em) {
        FreelancerDetails freelancerDetails = new FreelancerDetails()
            .phone1(DEFAULT_PHONE_1)
            .phone2(DEFAULT_PHONE_2)
            .cin(DEFAULT_CIN)
            .delevredDate(DEFAULT_DELEVRED_DATE)
            .cnss(DEFAULT_CNSS)
            .married(DEFAULT_MARRIED)
            .numberOfChildren(DEFAULT_NUMBER_OF_CHILDREN)
            .motorized(DEFAULT_MOTORIZED)
            .salary(DEFAULT_SALARY)
            .hiringDate(DEFAULT_HIRING_DATE)
            .comments(DEFAULT_COMMENTS)
            .channelOfHiring(DEFAULT_CHANNEL_OF_HIRING)
            .recommendation(DEFAULT_RECOMMENDATION);
        return freelancerDetails;
    }

    @Before
    public void initTest() {
        freelancerDetails = createEntity(em);
    }

    @Test
    @Transactional
    public void createFreelancerDetails() throws Exception {
        int databaseSizeBeforeCreate = freelancerDetailsRepository.findAll().size();

        // Create the FreelancerDetails
        restFreelancerDetailsMockMvc.perform(post("/api/freelancer-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(freelancerDetails)))
            .andExpect(status().isCreated());

        // Validate the FreelancerDetails in the database
        List<FreelancerDetails> freelancerDetailsList = freelancerDetailsRepository.findAll();
        assertThat(freelancerDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        FreelancerDetails testFreelancerDetails = freelancerDetailsList.get(freelancerDetailsList.size() - 1);
        assertThat(testFreelancerDetails.getPhone1()).isEqualTo(DEFAULT_PHONE_1);
        assertThat(testFreelancerDetails.getPhone2()).isEqualTo(DEFAULT_PHONE_2);
        assertThat(testFreelancerDetails.getCin()).isEqualTo(DEFAULT_CIN);
        assertThat(testFreelancerDetails.getDelevredDate()).isEqualTo(DEFAULT_DELEVRED_DATE);
        assertThat(testFreelancerDetails.getCnss()).isEqualTo(DEFAULT_CNSS);
        assertThat(testFreelancerDetails.isMarried()).isEqualTo(DEFAULT_MARRIED);
        assertThat(testFreelancerDetails.getNumberOfChildren()).isEqualTo(DEFAULT_NUMBER_OF_CHILDREN);
        assertThat(testFreelancerDetails.isMotorized()).isEqualTo(DEFAULT_MOTORIZED);
        assertThat(testFreelancerDetails.getSalary()).isEqualTo(DEFAULT_SALARY);
        assertThat(testFreelancerDetails.getHiringDate()).isEqualTo(DEFAULT_HIRING_DATE);
        assertThat(testFreelancerDetails.getComments()).isEqualTo(DEFAULT_COMMENTS);
        assertThat(testFreelancerDetails.getChannelOfHiring()).isEqualTo(DEFAULT_CHANNEL_OF_HIRING);
        assertThat(testFreelancerDetails.getRecommendation()).isEqualTo(DEFAULT_RECOMMENDATION);
    }

    @Test
    @Transactional
    public void createFreelancerDetailsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = freelancerDetailsRepository.findAll().size();

        // Create the FreelancerDetails with an existing ID
        freelancerDetails.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFreelancerDetailsMockMvc.perform(post("/api/freelancer-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(freelancerDetails)))
            .andExpect(status().isBadRequest());

        // Validate the FreelancerDetails in the database
        List<FreelancerDetails> freelancerDetailsList = freelancerDetailsRepository.findAll();
        assertThat(freelancerDetailsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkPhone1IsRequired() throws Exception {
        int databaseSizeBeforeTest = freelancerDetailsRepository.findAll().size();
        // set the field null
        freelancerDetails.setPhone1(null);

        // Create the FreelancerDetails, which fails.

        restFreelancerDetailsMockMvc.perform(post("/api/freelancer-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(freelancerDetails)))
            .andExpect(status().isBadRequest());

        List<FreelancerDetails> freelancerDetailsList = freelancerDetailsRepository.findAll();
        assertThat(freelancerDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCinIsRequired() throws Exception {
        int databaseSizeBeforeTest = freelancerDetailsRepository.findAll().size();
        // set the field null
        freelancerDetails.setCin(null);

        // Create the FreelancerDetails, which fails.

        restFreelancerDetailsMockMvc.perform(post("/api/freelancer-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(freelancerDetails)))
            .andExpect(status().isBadRequest());

        List<FreelancerDetails> freelancerDetailsList = freelancerDetailsRepository.findAll();
        assertThat(freelancerDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDelevredDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = freelancerDetailsRepository.findAll().size();
        // set the field null
        freelancerDetails.setDelevredDate(null);

        // Create the FreelancerDetails, which fails.

        restFreelancerDetailsMockMvc.perform(post("/api/freelancer-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(freelancerDetails)))
            .andExpect(status().isBadRequest());

        List<FreelancerDetails> freelancerDetailsList = freelancerDetailsRepository.findAll();
        assertThat(freelancerDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMarriedIsRequired() throws Exception {
        int databaseSizeBeforeTest = freelancerDetailsRepository.findAll().size();
        // set the field null
        freelancerDetails.setMarried(null);

        // Create the FreelancerDetails, which fails.

        restFreelancerDetailsMockMvc.perform(post("/api/freelancer-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(freelancerDetails)))
            .andExpect(status().isBadRequest());

        List<FreelancerDetails> freelancerDetailsList = freelancerDetailsRepository.findAll();
        assertThat(freelancerDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMotorizedIsRequired() throws Exception {
        int databaseSizeBeforeTest = freelancerDetailsRepository.findAll().size();
        // set the field null
        freelancerDetails.setMotorized(null);

        // Create the FreelancerDetails, which fails.

        restFreelancerDetailsMockMvc.perform(post("/api/freelancer-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(freelancerDetails)))
            .andExpect(status().isBadRequest());

        List<FreelancerDetails> freelancerDetailsList = freelancerDetailsRepository.findAll();
        assertThat(freelancerDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSalaryIsRequired() throws Exception {
        int databaseSizeBeforeTest = freelancerDetailsRepository.findAll().size();
        // set the field null
        freelancerDetails.setSalary(null);

        // Create the FreelancerDetails, which fails.

        restFreelancerDetailsMockMvc.perform(post("/api/freelancer-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(freelancerDetails)))
            .andExpect(status().isBadRequest());

        List<FreelancerDetails> freelancerDetailsList = freelancerDetailsRepository.findAll();
        assertThat(freelancerDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHiringDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = freelancerDetailsRepository.findAll().size();
        // set the field null
        freelancerDetails.setHiringDate(null);

        // Create the FreelancerDetails, which fails.

        restFreelancerDetailsMockMvc.perform(post("/api/freelancer-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(freelancerDetails)))
            .andExpect(status().isBadRequest());

        List<FreelancerDetails> freelancerDetailsList = freelancerDetailsRepository.findAll();
        assertThat(freelancerDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkChannelOfHiringIsRequired() throws Exception {
        int databaseSizeBeforeTest = freelancerDetailsRepository.findAll().size();
        // set the field null
        freelancerDetails.setChannelOfHiring(null);

        // Create the FreelancerDetails, which fails.

        restFreelancerDetailsMockMvc.perform(post("/api/freelancer-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(freelancerDetails)))
            .andExpect(status().isBadRequest());

        List<FreelancerDetails> freelancerDetailsList = freelancerDetailsRepository.findAll();
        assertThat(freelancerDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFreelancerDetails() throws Exception {
        // Initialize the database
        freelancerDetailsRepository.saveAndFlush(freelancerDetails);

        // Get all the freelancerDetailsList
        restFreelancerDetailsMockMvc.perform(get("/api/freelancer-details?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(freelancerDetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].phone1").value(hasItem(DEFAULT_PHONE_1.toString())))
            .andExpect(jsonPath("$.[*].phone2").value(hasItem(DEFAULT_PHONE_2.toString())))
            .andExpect(jsonPath("$.[*].cin").value(hasItem(DEFAULT_CIN.intValue())))
            .andExpect(jsonPath("$.[*].delevredDate").value(hasItem(DEFAULT_DELEVRED_DATE.toString())))
            .andExpect(jsonPath("$.[*].cnss").value(hasItem(DEFAULT_CNSS.intValue())))
            .andExpect(jsonPath("$.[*].married").value(hasItem(DEFAULT_MARRIED.booleanValue())))
            .andExpect(jsonPath("$.[*].numberOfChildren").value(hasItem(DEFAULT_NUMBER_OF_CHILDREN.intValue())))
            .andExpect(jsonPath("$.[*].motorized").value(hasItem(DEFAULT_MOTORIZED.booleanValue())))
            .andExpect(jsonPath("$.[*].salary").value(hasItem(DEFAULT_SALARY.intValue())))
            .andExpect(jsonPath("$.[*].hiringDate").value(hasItem(DEFAULT_HIRING_DATE.toString())))
            .andExpect(jsonPath("$.[*].comments").value(hasItem(DEFAULT_COMMENTS.toString())))
            .andExpect(jsonPath("$.[*].channelOfHiring").value(hasItem(DEFAULT_CHANNEL_OF_HIRING.toString())))
            .andExpect(jsonPath("$.[*].recommendation").value(hasItem(DEFAULT_RECOMMENDATION.toString())));
    }
    

    @Test
    @Transactional
    public void getFreelancerDetails() throws Exception {
        // Initialize the database
        freelancerDetailsRepository.saveAndFlush(freelancerDetails);

        // Get the freelancerDetails
        restFreelancerDetailsMockMvc.perform(get("/api/freelancer-details/{id}", freelancerDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(freelancerDetails.getId().intValue()))
            .andExpect(jsonPath("$.phone1").value(DEFAULT_PHONE_1.toString()))
            .andExpect(jsonPath("$.phone2").value(DEFAULT_PHONE_2.toString()))
            .andExpect(jsonPath("$.cin").value(DEFAULT_CIN.intValue()))
            .andExpect(jsonPath("$.delevredDate").value(DEFAULT_DELEVRED_DATE.toString()))
            .andExpect(jsonPath("$.cnss").value(DEFAULT_CNSS.intValue()))
            .andExpect(jsonPath("$.married").value(DEFAULT_MARRIED.booleanValue()))
            .andExpect(jsonPath("$.numberOfChildren").value(DEFAULT_NUMBER_OF_CHILDREN.intValue()))
            .andExpect(jsonPath("$.motorized").value(DEFAULT_MOTORIZED.booleanValue()))
            .andExpect(jsonPath("$.salary").value(DEFAULT_SALARY.intValue()))
            .andExpect(jsonPath("$.hiringDate").value(DEFAULT_HIRING_DATE.toString()))
            .andExpect(jsonPath("$.comments").value(DEFAULT_COMMENTS.toString()))
            .andExpect(jsonPath("$.channelOfHiring").value(DEFAULT_CHANNEL_OF_HIRING.toString()))
            .andExpect(jsonPath("$.recommendation").value(DEFAULT_RECOMMENDATION.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingFreelancerDetails() throws Exception {
        // Get the freelancerDetails
        restFreelancerDetailsMockMvc.perform(get("/api/freelancer-details/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFreelancerDetails() throws Exception {
        // Initialize the database
        freelancerDetailsRepository.saveAndFlush(freelancerDetails);

        int databaseSizeBeforeUpdate = freelancerDetailsRepository.findAll().size();

        // Update the freelancerDetails
        FreelancerDetails updatedFreelancerDetails = freelancerDetailsRepository.findById(freelancerDetails.getId()).get();
        // Disconnect from session so that the updates on updatedFreelancerDetails are not directly saved in db
        em.detach(updatedFreelancerDetails);
        updatedFreelancerDetails
            .phone1(UPDATED_PHONE_1)
            .phone2(UPDATED_PHONE_2)
            .cin(UPDATED_CIN)
            .delevredDate(UPDATED_DELEVRED_DATE)
            .cnss(UPDATED_CNSS)
            .married(UPDATED_MARRIED)
            .numberOfChildren(UPDATED_NUMBER_OF_CHILDREN)
            .motorized(UPDATED_MOTORIZED)
            .salary(UPDATED_SALARY)
            .hiringDate(UPDATED_HIRING_DATE)
            .comments(UPDATED_COMMENTS)
            .channelOfHiring(UPDATED_CHANNEL_OF_HIRING)
            .recommendation(UPDATED_RECOMMENDATION);

        restFreelancerDetailsMockMvc.perform(put("/api/freelancer-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedFreelancerDetails)))
            .andExpect(status().isOk());

        // Validate the FreelancerDetails in the database
        List<FreelancerDetails> freelancerDetailsList = freelancerDetailsRepository.findAll();
        assertThat(freelancerDetailsList).hasSize(databaseSizeBeforeUpdate);
        FreelancerDetails testFreelancerDetails = freelancerDetailsList.get(freelancerDetailsList.size() - 1);
        assertThat(testFreelancerDetails.getPhone1()).isEqualTo(UPDATED_PHONE_1);
        assertThat(testFreelancerDetails.getPhone2()).isEqualTo(UPDATED_PHONE_2);
        assertThat(testFreelancerDetails.getCin()).isEqualTo(UPDATED_CIN);
        assertThat(testFreelancerDetails.getDelevredDate()).isEqualTo(UPDATED_DELEVRED_DATE);
        assertThat(testFreelancerDetails.getCnss()).isEqualTo(UPDATED_CNSS);
        assertThat(testFreelancerDetails.isMarried()).isEqualTo(UPDATED_MARRIED);
        assertThat(testFreelancerDetails.getNumberOfChildren()).isEqualTo(UPDATED_NUMBER_OF_CHILDREN);
        assertThat(testFreelancerDetails.isMotorized()).isEqualTo(UPDATED_MOTORIZED);
        assertThat(testFreelancerDetails.getSalary()).isEqualTo(UPDATED_SALARY);
        assertThat(testFreelancerDetails.getHiringDate()).isEqualTo(UPDATED_HIRING_DATE);
        assertThat(testFreelancerDetails.getComments()).isEqualTo(UPDATED_COMMENTS);
        assertThat(testFreelancerDetails.getChannelOfHiring()).isEqualTo(UPDATED_CHANNEL_OF_HIRING);
        assertThat(testFreelancerDetails.getRecommendation()).isEqualTo(UPDATED_RECOMMENDATION);
    }

    @Test
    @Transactional
    public void updateNonExistingFreelancerDetails() throws Exception {
        int databaseSizeBeforeUpdate = freelancerDetailsRepository.findAll().size();

        // Create the FreelancerDetails

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restFreelancerDetailsMockMvc.perform(put("/api/freelancer-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(freelancerDetails)))
            .andExpect(status().isBadRequest());

        // Validate the FreelancerDetails in the database
        List<FreelancerDetails> freelancerDetailsList = freelancerDetailsRepository.findAll();
        assertThat(freelancerDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFreelancerDetails() throws Exception {
        // Initialize the database
        freelancerDetailsRepository.saveAndFlush(freelancerDetails);

        int databaseSizeBeforeDelete = freelancerDetailsRepository.findAll().size();

        // Get the freelancerDetails
        restFreelancerDetailsMockMvc.perform(delete("/api/freelancer-details/{id}", freelancerDetails.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<FreelancerDetails> freelancerDetailsList = freelancerDetailsRepository.findAll();
        assertThat(freelancerDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FreelancerDetails.class);
        FreelancerDetails freelancerDetails1 = new FreelancerDetails();
        freelancerDetails1.setId(1L);
        FreelancerDetails freelancerDetails2 = new FreelancerDetails();
        freelancerDetails2.setId(freelancerDetails1.getId());
        assertThat(freelancerDetails1).isEqualTo(freelancerDetails2);
        freelancerDetails2.setId(2L);
        assertThat(freelancerDetails1).isNotEqualTo(freelancerDetails2);
        freelancerDetails1.setId(null);
        assertThat(freelancerDetails1).isNotEqualTo(freelancerDetails2);
    }
}
