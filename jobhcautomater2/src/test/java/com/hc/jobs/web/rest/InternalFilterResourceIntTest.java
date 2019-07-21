package com.hc.jobs.web.rest;

import com.hc.jobs.JobHcAutomater2App;

import com.hc.jobs.domain.InternalFilter;
import com.hc.jobs.repository.InternalFilterRepository;
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
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;


import static com.hc.jobs.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.hc.jobs.domain.enumeration.Country;
import com.hc.jobs.domain.enumeration.PaiementMethod;
import com.hc.jobs.domain.enumeration.JobType;
import com.hc.jobs.domain.enumeration.ExperienceLevel;
import com.hc.jobs.domain.enumeration.CategoryProject;
import com.hc.jobs.domain.enumeration.Source;
import com.hc.jobs.domain.enumeration.Statut;
/**
 * Test class for the InternalFilterResource REST controller.
 *
 * @see InternalFilterResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JobHcAutomater2App.class)
public class InternalFilterResourceIntTest {

    private static final String DEFAULT_INTERNAL_FILTER_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_INTERNAL_FILTER_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_INTERNAL_FILTER_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_INTERNAL_FILTER_DESCRIPTION = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_POSTED = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_POSTED = LocalDate.now(ZoneId.systemDefault());

    private static final Country DEFAULT_COUNTRY = Country.AALAND_ISLANDS;
    private static final Country UPDATED_COUNTRY = Country.AFGHANISTAN;

    private static final Long DEFAULT_RATING_CLIENT = 0L;
    private static final Long UPDATED_RATING_CLIENT = 1L;

    private static final PaiementMethod DEFAULT_PAIEMENT_METHOD = PaiementMethod.VERIFIED;
    private static final PaiementMethod UPDATED_PAIEMENT_METHOD = PaiementMethod.NOTVERIFIED;

    private static final JobType DEFAULT_JOB_TYPE = JobType.HOURLY;
    private static final JobType UPDATED_JOB_TYPE = JobType.FIXED;

    private static final ExperienceLevel DEFAULT_EXPERIENCE_LEVEL = ExperienceLevel.EntryLevel;
    private static final ExperienceLevel UPDATED_EXPERIENCE_LEVEL = ExperienceLevel.Intermediate;

    private static final Long DEFAULT_CLIENT_HIRES_NUMBER = 1L;
    private static final Long UPDATED_CLIENT_HIRES_NUMBER = 2L;

    private static final Boolean DEFAULT_CLIENT_HISTORY_INFO_IS_PREVIOUS = false;
    private static final Boolean UPDATED_CLIENT_HISTORY_INFO_IS_PREVIOUS = true;

    private static final Long DEFAULT_NUMBER_OF_WANTED_HIRING = 1L;
    private static final Long UPDATED_NUMBER_OF_WANTED_HIRING = 2L;

    private static final Long DEFAULT_NUMBER_OF_PROPOSAL = 1L;
    private static final Long UPDATED_NUMBER_OF_PROPOSAL = 2L;

    private static final CategoryProject DEFAULT_CATEGORY_PROJECT = CategoryProject.WEB;
    private static final CategoryProject UPDATED_CATEGORY_PROJECT = CategoryProject.MOBILE;

    private static final Source DEFAULT_SOURCE_SITE = Source.UPWORK;
    private static final Source UPDATED_SOURCE_SITE = Source.UPWORK;

    private static final String DEFAULT_LINK_FOR_DETAILS = "AAAAAAAAAA";
    private static final String UPDATED_LINK_FOR_DETAILS = "BBBBBBBBBB";

    private static final Statut DEFAULT_STATUT_OF_OFFER = Statut.OPEN;
    private static final Statut UPDATED_STATUT_OF_OFFER = Statut.AWARDE;

    private static final BigDecimal DEFAULT_MIN_BUDGET = new BigDecimal(0);
    private static final BigDecimal UPDATED_MIN_BUDGET = new BigDecimal(1);

    private static final BigDecimal DEFAULT_MAX_BUDGET = new BigDecimal(0);
    private static final BigDecimal UPDATED_MAX_BUDGET = new BigDecimal(1);

    private static final Long DEFAULT_MIN_HOURS_PER_WEEK = 1L;
    private static final Long UPDATED_MIN_HOURS_PER_WEEK = 2L;

    private static final Long DEFAULT_MAX_HOURS_PER_WEEK = 1L;
    private static final Long UPDATED_MAX_HOURS_PER_WEEK = 2L;

    private static final Double DEFAULT_MIN_PROJECT_LENGHT_WITH_MONTH_UNIT = 1D;
    private static final Double UPDATED_MIN_PROJECT_LENGHT_WITH_MONTH_UNIT = 2D;

    private static final Double DEFAULT_MAX_PROJECT_LENGHT_WITH_MONTH_UNIT = 1D;
    private static final Double UPDATED_MAX_PROJECT_LENGHT_WITH_MONTH_UNIT = 2D;

    @Autowired
    private InternalFilterRepository internalFilterRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restInternalFilterMockMvc;

    private InternalFilter internalFilter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final InternalFilterResource internalFilterResource = new InternalFilterResource(internalFilterRepository);
        this.restInternalFilterMockMvc = MockMvcBuilders.standaloneSetup(internalFilterResource)
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
    public static InternalFilter createEntity(EntityManager em) {
        InternalFilter internalFilter = new InternalFilter()
            .internalFilterTitle(DEFAULT_INTERNAL_FILTER_TITLE)
            .internalFilterDescription(DEFAULT_INTERNAL_FILTER_DESCRIPTION)
            .datePosted(DEFAULT_DATE_POSTED)
            .country(DEFAULT_COUNTRY)
            .ratingClient(DEFAULT_RATING_CLIENT)
            .paiementMethod(DEFAULT_PAIEMENT_METHOD)
            .jobType(DEFAULT_JOB_TYPE)
            .experienceLevel(DEFAULT_EXPERIENCE_LEVEL)
            .clientHiresNumber(DEFAULT_CLIENT_HIRES_NUMBER)
            .clientHistoryInfoIsPrevious(DEFAULT_CLIENT_HISTORY_INFO_IS_PREVIOUS)
            .numberOfWantedHiring(DEFAULT_NUMBER_OF_WANTED_HIRING)
            .numberOfProposal(DEFAULT_NUMBER_OF_PROPOSAL)
            .categoryProject(DEFAULT_CATEGORY_PROJECT)
            .sourceSite(DEFAULT_SOURCE_SITE)
            .linkForDetails(DEFAULT_LINK_FOR_DETAILS)
            .statutOfOffer(DEFAULT_STATUT_OF_OFFER)
            .minBudget(DEFAULT_MIN_BUDGET)
            .maxBudget(DEFAULT_MAX_BUDGET)
            .minHoursPerWeek(DEFAULT_MIN_HOURS_PER_WEEK)
            .maxHoursPerWeek(DEFAULT_MAX_HOURS_PER_WEEK)
            .minProjectLenghtWithMonthUnit(DEFAULT_MIN_PROJECT_LENGHT_WITH_MONTH_UNIT)
            .maxProjectLenghtWithMonthUnit(DEFAULT_MAX_PROJECT_LENGHT_WITH_MONTH_UNIT);
        return internalFilter;
    }

    @Before
    public void initTest() {
        internalFilter = createEntity(em);
    }

    @Test
    @Transactional
    public void createInternalFilter() throws Exception {
        int databaseSizeBeforeCreate = internalFilterRepository.findAll().size();

        // Create the InternalFilter
        restInternalFilterMockMvc.perform(post("/api/internal-filters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(internalFilter)))
            .andExpect(status().isCreated());

        // Validate the InternalFilter in the database
        List<InternalFilter> internalFilterList = internalFilterRepository.findAll();
        assertThat(internalFilterList).hasSize(databaseSizeBeforeCreate + 1);
        InternalFilter testInternalFilter = internalFilterList.get(internalFilterList.size() - 1);
        assertThat(testInternalFilter.getInternalFilterTitle()).isEqualTo(DEFAULT_INTERNAL_FILTER_TITLE);
        assertThat(testInternalFilter.getInternalFilterDescription()).isEqualTo(DEFAULT_INTERNAL_FILTER_DESCRIPTION);
        assertThat(testInternalFilter.getDatePosted()).isEqualTo(DEFAULT_DATE_POSTED);
        assertThat(testInternalFilter.getCountry()).isEqualTo(DEFAULT_COUNTRY);
        assertThat(testInternalFilter.getRatingClient()).isEqualTo(DEFAULT_RATING_CLIENT);
        assertThat(testInternalFilter.getPaiementMethod()).isEqualTo(DEFAULT_PAIEMENT_METHOD);
        assertThat(testInternalFilter.getJobType()).isEqualTo(DEFAULT_JOB_TYPE);
        assertThat(testInternalFilter.getExperienceLevel()).isEqualTo(DEFAULT_EXPERIENCE_LEVEL);
        assertThat(testInternalFilter.getClientHiresNumber()).isEqualTo(DEFAULT_CLIENT_HIRES_NUMBER);
        assertThat(testInternalFilter.isClientHistoryInfoIsPrevious()).isEqualTo(DEFAULT_CLIENT_HISTORY_INFO_IS_PREVIOUS);
        assertThat(testInternalFilter.getNumberOfWantedHiring()).isEqualTo(DEFAULT_NUMBER_OF_WANTED_HIRING);
        assertThat(testInternalFilter.getNumberOfProposal()).isEqualTo(DEFAULT_NUMBER_OF_PROPOSAL);
        assertThat(testInternalFilter.getCategoryProject()).isEqualTo(DEFAULT_CATEGORY_PROJECT);
        assertThat(testInternalFilter.getSourceSite()).isEqualTo(DEFAULT_SOURCE_SITE);
        assertThat(testInternalFilter.getLinkForDetails()).isEqualTo(DEFAULT_LINK_FOR_DETAILS);
        assertThat(testInternalFilter.getStatutOfOffer()).isEqualTo(DEFAULT_STATUT_OF_OFFER);
        assertThat(testInternalFilter.getMinBudget()).isEqualTo(DEFAULT_MIN_BUDGET);
        assertThat(testInternalFilter.getMaxBudget()).isEqualTo(DEFAULT_MAX_BUDGET);
        assertThat(testInternalFilter.getMinHoursPerWeek()).isEqualTo(DEFAULT_MIN_HOURS_PER_WEEK);
        assertThat(testInternalFilter.getMaxHoursPerWeek()).isEqualTo(DEFAULT_MAX_HOURS_PER_WEEK);
        assertThat(testInternalFilter.getMinProjectLenghtWithMonthUnit()).isEqualTo(DEFAULT_MIN_PROJECT_LENGHT_WITH_MONTH_UNIT);
        assertThat(testInternalFilter.getMaxProjectLenghtWithMonthUnit()).isEqualTo(DEFAULT_MAX_PROJECT_LENGHT_WITH_MONTH_UNIT);
    }

    @Test
    @Transactional
    public void createInternalFilterWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = internalFilterRepository.findAll().size();

        // Create the InternalFilter with an existing ID
        internalFilter.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInternalFilterMockMvc.perform(post("/api/internal-filters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(internalFilter)))
            .andExpect(status().isBadRequest());

        // Validate the InternalFilter in the database
        List<InternalFilter> internalFilterList = internalFilterRepository.findAll();
        assertThat(internalFilterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkInternalFilterTitleIsRequired() throws Exception {
        int databaseSizeBeforeTest = internalFilterRepository.findAll().size();
        // set the field null
        internalFilter.setInternalFilterTitle(null);

        // Create the InternalFilter, which fails.

        restInternalFilterMockMvc.perform(post("/api/internal-filters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(internalFilter)))
            .andExpect(status().isBadRequest());

        List<InternalFilter> internalFilterList = internalFilterRepository.findAll();
        assertThat(internalFilterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkInternalFilterDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = internalFilterRepository.findAll().size();
        // set the field null
        internalFilter.setInternalFilterDescription(null);

        // Create the InternalFilter, which fails.

        restInternalFilterMockMvc.perform(post("/api/internal-filters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(internalFilter)))
            .andExpect(status().isBadRequest());

        List<InternalFilter> internalFilterList = internalFilterRepository.findAll();
        assertThat(internalFilterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllInternalFilters() throws Exception {
        // Initialize the database
        internalFilterRepository.saveAndFlush(internalFilter);

        // Get all the internalFilterList
        restInternalFilterMockMvc.perform(get("/api/internal-filters?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(internalFilter.getId().intValue())))
            .andExpect(jsonPath("$.[*].internalFilterTitle").value(hasItem(DEFAULT_INTERNAL_FILTER_TITLE.toString())))
            .andExpect(jsonPath("$.[*].internalFilterDescription").value(hasItem(DEFAULT_INTERNAL_FILTER_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].datePosted").value(hasItem(DEFAULT_DATE_POSTED.toString())))
            .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY.toString())))
            .andExpect(jsonPath("$.[*].ratingClient").value(hasItem(DEFAULT_RATING_CLIENT.intValue())))
            .andExpect(jsonPath("$.[*].paiementMethod").value(hasItem(DEFAULT_PAIEMENT_METHOD.toString())))
            .andExpect(jsonPath("$.[*].jobType").value(hasItem(DEFAULT_JOB_TYPE.toString())))
            .andExpect(jsonPath("$.[*].experienceLevel").value(hasItem(DEFAULT_EXPERIENCE_LEVEL.toString())))
            .andExpect(jsonPath("$.[*].clientHiresNumber").value(hasItem(DEFAULT_CLIENT_HIRES_NUMBER.intValue())))
            .andExpect(jsonPath("$.[*].clientHistoryInfoIsPrevious").value(hasItem(DEFAULT_CLIENT_HISTORY_INFO_IS_PREVIOUS.booleanValue())))
            .andExpect(jsonPath("$.[*].numberOfWantedHiring").value(hasItem(DEFAULT_NUMBER_OF_WANTED_HIRING.intValue())))
            .andExpect(jsonPath("$.[*].numberOfProposal").value(hasItem(DEFAULT_NUMBER_OF_PROPOSAL.intValue())))
            .andExpect(jsonPath("$.[*].categoryProject").value(hasItem(DEFAULT_CATEGORY_PROJECT.toString())))
            .andExpect(jsonPath("$.[*].sourceSite").value(hasItem(DEFAULT_SOURCE_SITE.toString())))
            .andExpect(jsonPath("$.[*].linkForDetails").value(hasItem(DEFAULT_LINK_FOR_DETAILS.toString())))
            .andExpect(jsonPath("$.[*].statutOfOffer").value(hasItem(DEFAULT_STATUT_OF_OFFER.toString())))
            .andExpect(jsonPath("$.[*].minBudget").value(hasItem(DEFAULT_MIN_BUDGET.intValue())))
            .andExpect(jsonPath("$.[*].maxBudget").value(hasItem(DEFAULT_MAX_BUDGET.intValue())))
            .andExpect(jsonPath("$.[*].minHoursPerWeek").value(hasItem(DEFAULT_MIN_HOURS_PER_WEEK.intValue())))
            .andExpect(jsonPath("$.[*].maxHoursPerWeek").value(hasItem(DEFAULT_MAX_HOURS_PER_WEEK.intValue())))
            .andExpect(jsonPath("$.[*].minProjectLenghtWithMonthUnit").value(hasItem(DEFAULT_MIN_PROJECT_LENGHT_WITH_MONTH_UNIT.doubleValue())))
            .andExpect(jsonPath("$.[*].maxProjectLenghtWithMonthUnit").value(hasItem(DEFAULT_MAX_PROJECT_LENGHT_WITH_MONTH_UNIT.doubleValue())));
    }
    

    @Test
    @Transactional
    public void getInternalFilter() throws Exception {
        // Initialize the database
        internalFilterRepository.saveAndFlush(internalFilter);

        // Get the internalFilter
        restInternalFilterMockMvc.perform(get("/api/internal-filters/{id}", internalFilter.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(internalFilter.getId().intValue()))
            .andExpect(jsonPath("$.internalFilterTitle").value(DEFAULT_INTERNAL_FILTER_TITLE.toString()))
            .andExpect(jsonPath("$.internalFilterDescription").value(DEFAULT_INTERNAL_FILTER_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.datePosted").value(DEFAULT_DATE_POSTED.toString()))
            .andExpect(jsonPath("$.country").value(DEFAULT_COUNTRY.toString()))
            .andExpect(jsonPath("$.ratingClient").value(DEFAULT_RATING_CLIENT.intValue()))
            .andExpect(jsonPath("$.paiementMethod").value(DEFAULT_PAIEMENT_METHOD.toString()))
            .andExpect(jsonPath("$.jobType").value(DEFAULT_JOB_TYPE.toString()))
            .andExpect(jsonPath("$.experienceLevel").value(DEFAULT_EXPERIENCE_LEVEL.toString()))
            .andExpect(jsonPath("$.clientHiresNumber").value(DEFAULT_CLIENT_HIRES_NUMBER.intValue()))
            .andExpect(jsonPath("$.clientHistoryInfoIsPrevious").value(DEFAULT_CLIENT_HISTORY_INFO_IS_PREVIOUS.booleanValue()))
            .andExpect(jsonPath("$.numberOfWantedHiring").value(DEFAULT_NUMBER_OF_WANTED_HIRING.intValue()))
            .andExpect(jsonPath("$.numberOfProposal").value(DEFAULT_NUMBER_OF_PROPOSAL.intValue()))
            .andExpect(jsonPath("$.categoryProject").value(DEFAULT_CATEGORY_PROJECT.toString()))
            .andExpect(jsonPath("$.sourceSite").value(DEFAULT_SOURCE_SITE.toString()))
            .andExpect(jsonPath("$.linkForDetails").value(DEFAULT_LINK_FOR_DETAILS.toString()))
            .andExpect(jsonPath("$.statutOfOffer").value(DEFAULT_STATUT_OF_OFFER.toString()))
            .andExpect(jsonPath("$.minBudget").value(DEFAULT_MIN_BUDGET.intValue()))
            .andExpect(jsonPath("$.maxBudget").value(DEFAULT_MAX_BUDGET.intValue()))
            .andExpect(jsonPath("$.minHoursPerWeek").value(DEFAULT_MIN_HOURS_PER_WEEK.intValue()))
            .andExpect(jsonPath("$.maxHoursPerWeek").value(DEFAULT_MAX_HOURS_PER_WEEK.intValue()))
            .andExpect(jsonPath("$.minProjectLenghtWithMonthUnit").value(DEFAULT_MIN_PROJECT_LENGHT_WITH_MONTH_UNIT.doubleValue()))
            .andExpect(jsonPath("$.maxProjectLenghtWithMonthUnit").value(DEFAULT_MAX_PROJECT_LENGHT_WITH_MONTH_UNIT.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingInternalFilter() throws Exception {
        // Get the internalFilter
        restInternalFilterMockMvc.perform(get("/api/internal-filters/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInternalFilter() throws Exception {
        // Initialize the database
        internalFilterRepository.saveAndFlush(internalFilter);

        int databaseSizeBeforeUpdate = internalFilterRepository.findAll().size();

        // Update the internalFilter
        InternalFilter updatedInternalFilter = internalFilterRepository.findById(internalFilter.getId()).get();
        // Disconnect from session so that the updates on updatedInternalFilter are not directly saved in db
        em.detach(updatedInternalFilter);
        updatedInternalFilter
            .internalFilterTitle(UPDATED_INTERNAL_FILTER_TITLE)
            .internalFilterDescription(UPDATED_INTERNAL_FILTER_DESCRIPTION)
            .datePosted(UPDATED_DATE_POSTED)
            .country(UPDATED_COUNTRY)
            .ratingClient(UPDATED_RATING_CLIENT)
            .paiementMethod(UPDATED_PAIEMENT_METHOD)
            .jobType(UPDATED_JOB_TYPE)
            .experienceLevel(UPDATED_EXPERIENCE_LEVEL)
            .clientHiresNumber(UPDATED_CLIENT_HIRES_NUMBER)
            .clientHistoryInfoIsPrevious(UPDATED_CLIENT_HISTORY_INFO_IS_PREVIOUS)
            .numberOfWantedHiring(UPDATED_NUMBER_OF_WANTED_HIRING)
            .numberOfProposal(UPDATED_NUMBER_OF_PROPOSAL)
            .categoryProject(UPDATED_CATEGORY_PROJECT)
            .sourceSite(UPDATED_SOURCE_SITE)
            .linkForDetails(UPDATED_LINK_FOR_DETAILS)
            .statutOfOffer(UPDATED_STATUT_OF_OFFER)
            .minBudget(UPDATED_MIN_BUDGET)
            .maxBudget(UPDATED_MAX_BUDGET)
            .minHoursPerWeek(UPDATED_MIN_HOURS_PER_WEEK)
            .maxHoursPerWeek(UPDATED_MAX_HOURS_PER_WEEK)
            .minProjectLenghtWithMonthUnit(UPDATED_MIN_PROJECT_LENGHT_WITH_MONTH_UNIT)
            .maxProjectLenghtWithMonthUnit(UPDATED_MAX_PROJECT_LENGHT_WITH_MONTH_UNIT);

        restInternalFilterMockMvc.perform(put("/api/internal-filters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedInternalFilter)))
            .andExpect(status().isOk());

        // Validate the InternalFilter in the database
        List<InternalFilter> internalFilterList = internalFilterRepository.findAll();
        assertThat(internalFilterList).hasSize(databaseSizeBeforeUpdate);
        InternalFilter testInternalFilter = internalFilterList.get(internalFilterList.size() - 1);
        assertThat(testInternalFilter.getInternalFilterTitle()).isEqualTo(UPDATED_INTERNAL_FILTER_TITLE);
        assertThat(testInternalFilter.getInternalFilterDescription()).isEqualTo(UPDATED_INTERNAL_FILTER_DESCRIPTION);
        assertThat(testInternalFilter.getDatePosted()).isEqualTo(UPDATED_DATE_POSTED);
        assertThat(testInternalFilter.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testInternalFilter.getRatingClient()).isEqualTo(UPDATED_RATING_CLIENT);
        assertThat(testInternalFilter.getPaiementMethod()).isEqualTo(UPDATED_PAIEMENT_METHOD);
        assertThat(testInternalFilter.getJobType()).isEqualTo(UPDATED_JOB_TYPE);
        assertThat(testInternalFilter.getExperienceLevel()).isEqualTo(UPDATED_EXPERIENCE_LEVEL);
        assertThat(testInternalFilter.getClientHiresNumber()).isEqualTo(UPDATED_CLIENT_HIRES_NUMBER);
        assertThat(testInternalFilter.isClientHistoryInfoIsPrevious()).isEqualTo(UPDATED_CLIENT_HISTORY_INFO_IS_PREVIOUS);
        assertThat(testInternalFilter.getNumberOfWantedHiring()).isEqualTo(UPDATED_NUMBER_OF_WANTED_HIRING);
        assertThat(testInternalFilter.getNumberOfProposal()).isEqualTo(UPDATED_NUMBER_OF_PROPOSAL);
        assertThat(testInternalFilter.getCategoryProject()).isEqualTo(UPDATED_CATEGORY_PROJECT);
        assertThat(testInternalFilter.getSourceSite()).isEqualTo(UPDATED_SOURCE_SITE);
        assertThat(testInternalFilter.getLinkForDetails()).isEqualTo(UPDATED_LINK_FOR_DETAILS);
        assertThat(testInternalFilter.getStatutOfOffer()).isEqualTo(UPDATED_STATUT_OF_OFFER);
        assertThat(testInternalFilter.getMinBudget()).isEqualTo(UPDATED_MIN_BUDGET);
        assertThat(testInternalFilter.getMaxBudget()).isEqualTo(UPDATED_MAX_BUDGET);
        assertThat(testInternalFilter.getMinHoursPerWeek()).isEqualTo(UPDATED_MIN_HOURS_PER_WEEK);
        assertThat(testInternalFilter.getMaxHoursPerWeek()).isEqualTo(UPDATED_MAX_HOURS_PER_WEEK);
        assertThat(testInternalFilter.getMinProjectLenghtWithMonthUnit()).isEqualTo(UPDATED_MIN_PROJECT_LENGHT_WITH_MONTH_UNIT);
        assertThat(testInternalFilter.getMaxProjectLenghtWithMonthUnit()).isEqualTo(UPDATED_MAX_PROJECT_LENGHT_WITH_MONTH_UNIT);
    }

    @Test
    @Transactional
    public void updateNonExistingInternalFilter() throws Exception {
        int databaseSizeBeforeUpdate = internalFilterRepository.findAll().size();

        // Create the InternalFilter

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restInternalFilterMockMvc.perform(put("/api/internal-filters")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(internalFilter)))
            .andExpect(status().isBadRequest());

        // Validate the InternalFilter in the database
        List<InternalFilter> internalFilterList = internalFilterRepository.findAll();
        assertThat(internalFilterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInternalFilter() throws Exception {
        // Initialize the database
        internalFilterRepository.saveAndFlush(internalFilter);

        int databaseSizeBeforeDelete = internalFilterRepository.findAll().size();

        // Get the internalFilter
        restInternalFilterMockMvc.perform(delete("/api/internal-filters/{id}", internalFilter.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<InternalFilter> internalFilterList = internalFilterRepository.findAll();
        assertThat(internalFilterList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InternalFilter.class);
        InternalFilter internalFilter1 = new InternalFilter();
        internalFilter1.setId(1L);
        InternalFilter internalFilter2 = new InternalFilter();
        internalFilter2.setId(internalFilter1.getId());
        assertThat(internalFilter1).isEqualTo(internalFilter2);
        internalFilter2.setId(2L);
        assertThat(internalFilter1).isNotEqualTo(internalFilter2);
        internalFilter1.setId(null);
        assertThat(internalFilter1).isNotEqualTo(internalFilter2);
    }
}
