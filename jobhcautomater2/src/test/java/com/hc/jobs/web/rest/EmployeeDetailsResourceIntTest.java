package com.hc.jobs.web.rest;

import com.hc.jobs.JobHcAutomater2App;

import com.hc.jobs.domain.EmployeeDetails;
import com.hc.jobs.repository.EmployeeDetailsRepository;
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
 * Test class for the EmployeeDetailsResource REST controller.
 *
 * @see EmployeeDetailsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JobHcAutomater2App.class)
public class EmployeeDetailsResourceIntTest {

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

    @Autowired
    private EmployeeDetailsRepository employeeDetailsRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restEmployeeDetailsMockMvc;

    private EmployeeDetails employeeDetails;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EmployeeDetailsResource employeeDetailsResource = new EmployeeDetailsResource(employeeDetailsRepository);
        this.restEmployeeDetailsMockMvc = MockMvcBuilders.standaloneSetup(employeeDetailsResource)
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
    public static EmployeeDetails createEntity(EntityManager em) {
        EmployeeDetails employeeDetails = new EmployeeDetails()
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
            .comments(DEFAULT_COMMENTS);
        return employeeDetails;
    }

    @Before
    public void initTest() {
        employeeDetails = createEntity(em);
    }

    @Test
    @Transactional
    public void createEmployeeDetails() throws Exception {
        int databaseSizeBeforeCreate = employeeDetailsRepository.findAll().size();

        // Create the EmployeeDetails
        restEmployeeDetailsMockMvc.perform(post("/api/employee-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(employeeDetails)))
            .andExpect(status().isCreated());

        // Validate the EmployeeDetails in the database
        List<EmployeeDetails> employeeDetailsList = employeeDetailsRepository.findAll();
        assertThat(employeeDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        EmployeeDetails testEmployeeDetails = employeeDetailsList.get(employeeDetailsList.size() - 1);
        assertThat(testEmployeeDetails.getPhone1()).isEqualTo(DEFAULT_PHONE_1);
        assertThat(testEmployeeDetails.getPhone2()).isEqualTo(DEFAULT_PHONE_2);
        assertThat(testEmployeeDetails.getCin()).isEqualTo(DEFAULT_CIN);
        assertThat(testEmployeeDetails.getDelevredDate()).isEqualTo(DEFAULT_DELEVRED_DATE);
        assertThat(testEmployeeDetails.getCnss()).isEqualTo(DEFAULT_CNSS);
        assertThat(testEmployeeDetails.isMarried()).isEqualTo(DEFAULT_MARRIED);
        assertThat(testEmployeeDetails.getNumberOfChildren()).isEqualTo(DEFAULT_NUMBER_OF_CHILDREN);
        assertThat(testEmployeeDetails.isMotorized()).isEqualTo(DEFAULT_MOTORIZED);
        assertThat(testEmployeeDetails.getSalary()).isEqualTo(DEFAULT_SALARY);
        assertThat(testEmployeeDetails.getHiringDate()).isEqualTo(DEFAULT_HIRING_DATE);
        assertThat(testEmployeeDetails.getComments()).isEqualTo(DEFAULT_COMMENTS);
    }

    @Test
    @Transactional
    public void createEmployeeDetailsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = employeeDetailsRepository.findAll().size();

        // Create the EmployeeDetails with an existing ID
        employeeDetails.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEmployeeDetailsMockMvc.perform(post("/api/employee-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(employeeDetails)))
            .andExpect(status().isBadRequest());

        // Validate the EmployeeDetails in the database
        List<EmployeeDetails> employeeDetailsList = employeeDetailsRepository.findAll();
        assertThat(employeeDetailsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkPhone1IsRequired() throws Exception {
        int databaseSizeBeforeTest = employeeDetailsRepository.findAll().size();
        // set the field null
        employeeDetails.setPhone1(null);

        // Create the EmployeeDetails, which fails.

        restEmployeeDetailsMockMvc.perform(post("/api/employee-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(employeeDetails)))
            .andExpect(status().isBadRequest());

        List<EmployeeDetails> employeeDetailsList = employeeDetailsRepository.findAll();
        assertThat(employeeDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCinIsRequired() throws Exception {
        int databaseSizeBeforeTest = employeeDetailsRepository.findAll().size();
        // set the field null
        employeeDetails.setCin(null);

        // Create the EmployeeDetails, which fails.

        restEmployeeDetailsMockMvc.perform(post("/api/employee-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(employeeDetails)))
            .andExpect(status().isBadRequest());

        List<EmployeeDetails> employeeDetailsList = employeeDetailsRepository.findAll();
        assertThat(employeeDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDelevredDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = employeeDetailsRepository.findAll().size();
        // set the field null
        employeeDetails.setDelevredDate(null);

        // Create the EmployeeDetails, which fails.

        restEmployeeDetailsMockMvc.perform(post("/api/employee-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(employeeDetails)))
            .andExpect(status().isBadRequest());

        List<EmployeeDetails> employeeDetailsList = employeeDetailsRepository.findAll();
        assertThat(employeeDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMarriedIsRequired() throws Exception {
        int databaseSizeBeforeTest = employeeDetailsRepository.findAll().size();
        // set the field null
        employeeDetails.setMarried(null);

        // Create the EmployeeDetails, which fails.

        restEmployeeDetailsMockMvc.perform(post("/api/employee-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(employeeDetails)))
            .andExpect(status().isBadRequest());

        List<EmployeeDetails> employeeDetailsList = employeeDetailsRepository.findAll();
        assertThat(employeeDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMotorizedIsRequired() throws Exception {
        int databaseSizeBeforeTest = employeeDetailsRepository.findAll().size();
        // set the field null
        employeeDetails.setMotorized(null);

        // Create the EmployeeDetails, which fails.

        restEmployeeDetailsMockMvc.perform(post("/api/employee-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(employeeDetails)))
            .andExpect(status().isBadRequest());

        List<EmployeeDetails> employeeDetailsList = employeeDetailsRepository.findAll();
        assertThat(employeeDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSalaryIsRequired() throws Exception {
        int databaseSizeBeforeTest = employeeDetailsRepository.findAll().size();
        // set the field null
        employeeDetails.setSalary(null);

        // Create the EmployeeDetails, which fails.

        restEmployeeDetailsMockMvc.perform(post("/api/employee-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(employeeDetails)))
            .andExpect(status().isBadRequest());

        List<EmployeeDetails> employeeDetailsList = employeeDetailsRepository.findAll();
        assertThat(employeeDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHiringDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = employeeDetailsRepository.findAll().size();
        // set the field null
        employeeDetails.setHiringDate(null);

        // Create the EmployeeDetails, which fails.

        restEmployeeDetailsMockMvc.perform(post("/api/employee-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(employeeDetails)))
            .andExpect(status().isBadRequest());

        List<EmployeeDetails> employeeDetailsList = employeeDetailsRepository.findAll();
        assertThat(employeeDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEmployeeDetails() throws Exception {
        // Initialize the database
        employeeDetailsRepository.saveAndFlush(employeeDetails);

        // Get all the employeeDetailsList
        restEmployeeDetailsMockMvc.perform(get("/api/employee-details?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(employeeDetails.getId().intValue())))
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
            .andExpect(jsonPath("$.[*].comments").value(hasItem(DEFAULT_COMMENTS.toString())));
    }
    

    @Test
    @Transactional
    public void getEmployeeDetails() throws Exception {
        // Initialize the database
        employeeDetailsRepository.saveAndFlush(employeeDetails);

        // Get the employeeDetails
        restEmployeeDetailsMockMvc.perform(get("/api/employee-details/{id}", employeeDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(employeeDetails.getId().intValue()))
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
            .andExpect(jsonPath("$.comments").value(DEFAULT_COMMENTS.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingEmployeeDetails() throws Exception {
        // Get the employeeDetails
        restEmployeeDetailsMockMvc.perform(get("/api/employee-details/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEmployeeDetails() throws Exception {
        // Initialize the database
        employeeDetailsRepository.saveAndFlush(employeeDetails);

        int databaseSizeBeforeUpdate = employeeDetailsRepository.findAll().size();

        // Update the employeeDetails
        EmployeeDetails updatedEmployeeDetails = employeeDetailsRepository.findById(employeeDetails.getId()).get();
        // Disconnect from session so that the updates on updatedEmployeeDetails are not directly saved in db
        em.detach(updatedEmployeeDetails);
        updatedEmployeeDetails
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
            .comments(UPDATED_COMMENTS);

        restEmployeeDetailsMockMvc.perform(put("/api/employee-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedEmployeeDetails)))
            .andExpect(status().isOk());

        // Validate the EmployeeDetails in the database
        List<EmployeeDetails> employeeDetailsList = employeeDetailsRepository.findAll();
        assertThat(employeeDetailsList).hasSize(databaseSizeBeforeUpdate);
        EmployeeDetails testEmployeeDetails = employeeDetailsList.get(employeeDetailsList.size() - 1);
        assertThat(testEmployeeDetails.getPhone1()).isEqualTo(UPDATED_PHONE_1);
        assertThat(testEmployeeDetails.getPhone2()).isEqualTo(UPDATED_PHONE_2);
        assertThat(testEmployeeDetails.getCin()).isEqualTo(UPDATED_CIN);
        assertThat(testEmployeeDetails.getDelevredDate()).isEqualTo(UPDATED_DELEVRED_DATE);
        assertThat(testEmployeeDetails.getCnss()).isEqualTo(UPDATED_CNSS);
        assertThat(testEmployeeDetails.isMarried()).isEqualTo(UPDATED_MARRIED);
        assertThat(testEmployeeDetails.getNumberOfChildren()).isEqualTo(UPDATED_NUMBER_OF_CHILDREN);
        assertThat(testEmployeeDetails.isMotorized()).isEqualTo(UPDATED_MOTORIZED);
        assertThat(testEmployeeDetails.getSalary()).isEqualTo(UPDATED_SALARY);
        assertThat(testEmployeeDetails.getHiringDate()).isEqualTo(UPDATED_HIRING_DATE);
        assertThat(testEmployeeDetails.getComments()).isEqualTo(UPDATED_COMMENTS);
    }

    @Test
    @Transactional
    public void updateNonExistingEmployeeDetails() throws Exception {
        int databaseSizeBeforeUpdate = employeeDetailsRepository.findAll().size();

        // Create the EmployeeDetails

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restEmployeeDetailsMockMvc.perform(put("/api/employee-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(employeeDetails)))
            .andExpect(status().isBadRequest());

        // Validate the EmployeeDetails in the database
        List<EmployeeDetails> employeeDetailsList = employeeDetailsRepository.findAll();
        assertThat(employeeDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEmployeeDetails() throws Exception {
        // Initialize the database
        employeeDetailsRepository.saveAndFlush(employeeDetails);

        int databaseSizeBeforeDelete = employeeDetailsRepository.findAll().size();

        // Get the employeeDetails
        restEmployeeDetailsMockMvc.perform(delete("/api/employee-details/{id}", employeeDetails.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<EmployeeDetails> employeeDetailsList = employeeDetailsRepository.findAll();
        assertThat(employeeDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EmployeeDetails.class);
        EmployeeDetails employeeDetails1 = new EmployeeDetails();
        employeeDetails1.setId(1L);
        EmployeeDetails employeeDetails2 = new EmployeeDetails();
        employeeDetails2.setId(employeeDetails1.getId());
        assertThat(employeeDetails1).isEqualTo(employeeDetails2);
        employeeDetails2.setId(2L);
        assertThat(employeeDetails1).isNotEqualTo(employeeDetails2);
        employeeDetails1.setId(null);
        assertThat(employeeDetails1).isNotEqualTo(employeeDetails2);
    }
}
