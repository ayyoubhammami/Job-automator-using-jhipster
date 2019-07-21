package com.hc.jobs.web.rest;

import com.hc.jobs.JobHcAutomater2App;

import com.hc.jobs.domain.Compaign;
import com.hc.jobs.repository.CompaignRepository;
import com.hc.jobs.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;


import static com.hc.jobs.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the CompaignResource REST controller.
 *
 * @see CompaignResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JobHcAutomater2App.class)
public class CompaignResourceIntTest {

    private static final String DEFAULT_REF = "AAAAAAAAAA";
    private static final String UPDATED_REF = "BBBBBBBBBB";

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCIPTION = "BBBBBBBBBB";

    private static final byte[] DEFAULT_IMG = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMG = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_IMG_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMG_CONTENT_TYPE = "image/png";

    @Autowired
    private CompaignRepository compaignRepository;
    @Mock
    private CompaignRepository compaignRepositoryMock;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCompaignMockMvc;

    private Compaign compaign;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CompaignResource compaignResource = new CompaignResource(compaignRepository);
        this.restCompaignMockMvc = MockMvcBuilders.standaloneSetup(compaignResource)
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
    public static Compaign createEntity(EntityManager em) {
        Compaign compaign = new Compaign()
            .ref(DEFAULT_REF)
            .title(DEFAULT_TITLE)
            .desciption(DEFAULT_DESCIPTION)
            .img(DEFAULT_IMG)
            .imgContentType(DEFAULT_IMG_CONTENT_TYPE);
        return compaign;
    }

    @Before
    public void initTest() {
        compaign = createEntity(em);
    }

    @Test
    @Transactional
    public void createCompaign() throws Exception {
        int databaseSizeBeforeCreate = compaignRepository.findAll().size();

        // Create the Compaign
        restCompaignMockMvc.perform(post("/api/compaigns")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(compaign)))
            .andExpect(status().isCreated());

        // Validate the Compaign in the database
        List<Compaign> compaignList = compaignRepository.findAll();
        assertThat(compaignList).hasSize(databaseSizeBeforeCreate + 1);
        Compaign testCompaign = compaignList.get(compaignList.size() - 1);
        assertThat(testCompaign.getRef()).isEqualTo(DEFAULT_REF);
        assertThat(testCompaign.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testCompaign.getDesciption()).isEqualTo(DEFAULT_DESCIPTION);
        assertThat(testCompaign.getImg()).isEqualTo(DEFAULT_IMG);
        assertThat(testCompaign.getImgContentType()).isEqualTo(DEFAULT_IMG_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createCompaignWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = compaignRepository.findAll().size();

        // Create the Compaign with an existing ID
        compaign.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCompaignMockMvc.perform(post("/api/compaigns")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(compaign)))
            .andExpect(status().isBadRequest());

        // Validate the Compaign in the database
        List<Compaign> compaignList = compaignRepository.findAll();
        assertThat(compaignList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkRefIsRequired() throws Exception {
        int databaseSizeBeforeTest = compaignRepository.findAll().size();
        // set the field null
        compaign.setRef(null);

        // Create the Compaign, which fails.

        restCompaignMockMvc.perform(post("/api/compaigns")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(compaign)))
            .andExpect(status().isBadRequest());

        List<Compaign> compaignList = compaignRepository.findAll();
        assertThat(compaignList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTitleIsRequired() throws Exception {
        int databaseSizeBeforeTest = compaignRepository.findAll().size();
        // set the field null
        compaign.setTitle(null);

        // Create the Compaign, which fails.

        restCompaignMockMvc.perform(post("/api/compaigns")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(compaign)))
            .andExpect(status().isBadRequest());

        List<Compaign> compaignList = compaignRepository.findAll();
        assertThat(compaignList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDesciptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = compaignRepository.findAll().size();
        // set the field null
        compaign.setDesciption(null);

        // Create the Compaign, which fails.

        restCompaignMockMvc.perform(post("/api/compaigns")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(compaign)))
            .andExpect(status().isBadRequest());

        List<Compaign> compaignList = compaignRepository.findAll();
        assertThat(compaignList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCompaigns() throws Exception {
        // Initialize the database
        compaignRepository.saveAndFlush(compaign);

        // Get all the compaignList
        restCompaignMockMvc.perform(get("/api/compaigns?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(compaign.getId().intValue())))
            .andExpect(jsonPath("$.[*].ref").value(hasItem(DEFAULT_REF.toString())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())))
            .andExpect(jsonPath("$.[*].desciption").value(hasItem(DEFAULT_DESCIPTION.toString())))
            .andExpect(jsonPath("$.[*].imgContentType").value(hasItem(DEFAULT_IMG_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].img").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMG))));
    }
    
    public void getAllCompaignsWithEagerRelationshipsIsEnabled() throws Exception {
        CompaignResource compaignResource = new CompaignResource(compaignRepositoryMock);
        when(compaignRepositoryMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restCompaignMockMvc = MockMvcBuilders.standaloneSetup(compaignResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restCompaignMockMvc.perform(get("/api/compaigns?eagerload=true"))
        .andExpect(status().isOk());

        verify(compaignRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    public void getAllCompaignsWithEagerRelationshipsIsNotEnabled() throws Exception {
        CompaignResource compaignResource = new CompaignResource(compaignRepositoryMock);
            when(compaignRepositoryMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restCompaignMockMvc = MockMvcBuilders.standaloneSetup(compaignResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restCompaignMockMvc.perform(get("/api/compaigns?eagerload=true"))
        .andExpect(status().isOk());

            verify(compaignRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getCompaign() throws Exception {
        // Initialize the database
        compaignRepository.saveAndFlush(compaign);

        // Get the compaign
        restCompaignMockMvc.perform(get("/api/compaigns/{id}", compaign.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(compaign.getId().intValue()))
            .andExpect(jsonPath("$.ref").value(DEFAULT_REF.toString()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE.toString()))
            .andExpect(jsonPath("$.desciption").value(DEFAULT_DESCIPTION.toString()))
            .andExpect(jsonPath("$.imgContentType").value(DEFAULT_IMG_CONTENT_TYPE))
            .andExpect(jsonPath("$.img").value(Base64Utils.encodeToString(DEFAULT_IMG)));
    }
    @Test
    @Transactional
    public void getNonExistingCompaign() throws Exception {
        // Get the compaign
        restCompaignMockMvc.perform(get("/api/compaigns/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCompaign() throws Exception {
        // Initialize the database
        compaignRepository.saveAndFlush(compaign);

        int databaseSizeBeforeUpdate = compaignRepository.findAll().size();

        // Update the compaign
        Compaign updatedCompaign = compaignRepository.findById(compaign.getId()).get();
        // Disconnect from session so that the updates on updatedCompaign are not directly saved in db
        em.detach(updatedCompaign);
        updatedCompaign
            .ref(UPDATED_REF)
            .title(UPDATED_TITLE)
            .desciption(UPDATED_DESCIPTION)
            .img(UPDATED_IMG)
            .imgContentType(UPDATED_IMG_CONTENT_TYPE);

        restCompaignMockMvc.perform(put("/api/compaigns")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCompaign)))
            .andExpect(status().isOk());

        // Validate the Compaign in the database
        List<Compaign> compaignList = compaignRepository.findAll();
        assertThat(compaignList).hasSize(databaseSizeBeforeUpdate);
        Compaign testCompaign = compaignList.get(compaignList.size() - 1);
        assertThat(testCompaign.getRef()).isEqualTo(UPDATED_REF);
        assertThat(testCompaign.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testCompaign.getDesciption()).isEqualTo(UPDATED_DESCIPTION);
        assertThat(testCompaign.getImg()).isEqualTo(UPDATED_IMG);
        assertThat(testCompaign.getImgContentType()).isEqualTo(UPDATED_IMG_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingCompaign() throws Exception {
        int databaseSizeBeforeUpdate = compaignRepository.findAll().size();

        // Create the Compaign

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCompaignMockMvc.perform(put("/api/compaigns")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(compaign)))
            .andExpect(status().isBadRequest());

        // Validate the Compaign in the database
        List<Compaign> compaignList = compaignRepository.findAll();
        assertThat(compaignList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCompaign() throws Exception {
        // Initialize the database
        compaignRepository.saveAndFlush(compaign);

        int databaseSizeBeforeDelete = compaignRepository.findAll().size();

        // Get the compaign
        restCompaignMockMvc.perform(delete("/api/compaigns/{id}", compaign.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Compaign> compaignList = compaignRepository.findAll();
        assertThat(compaignList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Compaign.class);
        Compaign compaign1 = new Compaign();
        compaign1.setId(1L);
        Compaign compaign2 = new Compaign();
        compaign2.setId(compaign1.getId());
        assertThat(compaign1).isEqualTo(compaign2);
        compaign2.setId(2L);
        assertThat(compaign1).isNotEqualTo(compaign2);
        compaign1.setId(null);
        assertThat(compaign1).isNotEqualTo(compaign2);
    }
}
