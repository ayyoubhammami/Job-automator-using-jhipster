package com.hc.jobs.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import com.hc.jobs.domain.enumeration.Country;

import com.hc.jobs.domain.enumeration.PaiementMethod;

import com.hc.jobs.domain.enumeration.JobType;

import com.hc.jobs.domain.enumeration.ExperienceLevel;

import com.hc.jobs.domain.enumeration.CategoryProject;

import com.hc.jobs.domain.enumeration.Source;

import com.hc.jobs.domain.enumeration.Statut;

/**
 * A Job.
 */
@Entity
@Table(name = "job")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Job implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "date_posted")
    private LocalDate datePosted;

    @Enumerated(EnumType.STRING)
    @Column(name = "country")
    private Country country;

    @Min(value = 0L)
    @Max(value = 10L)
    @Column(name = "rating_client")
    private Long ratingClient;

    @Enumerated(EnumType.STRING)
    @Column(name = "paiement_method")
    private PaiementMethod paiementMethod;

    @Enumerated(EnumType.STRING)
    @Column(name = "job_type")
    private JobType jobType;

    @Enumerated(EnumType.STRING)
    @Column(name = "experience_level")
    private ExperienceLevel experienceLevel;

    @Column(name = "client_hires_number")
    private Long clientHiresNumber;

    @Column(name = "client_history_info_is_previous")
    private Boolean clientHistoryInfoIsPrevious;

    @Column(name = "number_of_wanted_hiring")
    private Long numberOfWantedHiring;

    @Column(name = "number_of_proposal")
    private Long numberOfProposal;

    @Enumerated(EnumType.STRING)
    @Column(name = "category_project")
    private CategoryProject categoryProject;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "source_site", nullable = false)
    private Source sourceSite;

    @NotNull
    @Column(name = "link_for_details", nullable = false)
    private String linkForDetails;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut_of_offer")
    private Statut statutOfOffer;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    @Column(name = "min_budget", precision = 10, scale = 2)
    private BigDecimal minBudget;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    @Column(name = "max_budget", precision = 10, scale = 2)
    private BigDecimal maxBudget;

    @Column(name = "min_hours_per_week")
    private Long minHoursPerWeek;

    @Column(name = "max_hours_per_week")
    private Long maxHoursPerWeek;

    @Column(name = "min_project_lenght_with_month_unit")
    private Double minProjectLenghtWithMonthUnit;

    @Column(name = "max_project_lenght_with_month_unit")
    private Double maxProjectLenghtWithMonthUnit;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public Job title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public Job description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDatePosted() {
        return datePosted;
    }

    public Job datePosted(LocalDate datePosted) {
        this.datePosted = datePosted;
        return this;
    }

    public void setDatePosted(LocalDate datePosted) {
        this.datePosted = datePosted;
    }

    public Country getCountry() {
        return country;
    }

    public Job country(Country country) {
        this.country = country;
        return this;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Long getRatingClient() {
        return ratingClient;
    }

    public Job ratingClient(Long ratingClient) {
        this.ratingClient = ratingClient;
        return this;
    }

    public void setRatingClient(Long ratingClient) {
        this.ratingClient = ratingClient;
    }

    public PaiementMethod getPaiementMethod() {
        return paiementMethod;
    }

    public Job paiementMethod(PaiementMethod paiementMethod) {
        this.paiementMethod = paiementMethod;
        return this;
    }

    public void setPaiementMethod(PaiementMethod paiementMethod) {
        this.paiementMethod = paiementMethod;
    }

    public JobType getJobType() {
        return jobType;
    }

    public Job jobType(JobType jobType) {
        this.jobType = jobType;
        return this;
    }

    public void setJobType(JobType jobType) {
        this.jobType = jobType;
    }

    public ExperienceLevel getExperienceLevel() {
        return experienceLevel;
    }

    public Job experienceLevel(ExperienceLevel experienceLevel) {
        this.experienceLevel = experienceLevel;
        return this;
    }

    public void setExperienceLevel(ExperienceLevel experienceLevel) {
        this.experienceLevel = experienceLevel;
    }

    public Long getClientHiresNumber() {
        return clientHiresNumber;
    }

    public Job clientHiresNumber(Long clientHiresNumber) {
        this.clientHiresNumber = clientHiresNumber;
        return this;
    }

    public void setClientHiresNumber(Long clientHiresNumber) {
        this.clientHiresNumber = clientHiresNumber;
    }

    public Boolean isClientHistoryInfoIsPrevious() {
        return clientHistoryInfoIsPrevious;
    }

    public Job clientHistoryInfoIsPrevious(Boolean clientHistoryInfoIsPrevious) {
        this.clientHistoryInfoIsPrevious = clientHistoryInfoIsPrevious;
        return this;
    }

    public void setClientHistoryInfoIsPrevious(Boolean clientHistoryInfoIsPrevious) {
        this.clientHistoryInfoIsPrevious = clientHistoryInfoIsPrevious;
    }

    public Long getNumberOfWantedHiring() {
        return numberOfWantedHiring;
    }

    public Job numberOfWantedHiring(Long numberOfWantedHiring) {
        this.numberOfWantedHiring = numberOfWantedHiring;
        return this;
    }

    public void setNumberOfWantedHiring(Long numberOfWantedHiring) {
        this.numberOfWantedHiring = numberOfWantedHiring;
    }

    public Long getNumberOfProposal() {
        return numberOfProposal;
    }

    public Job numberOfProposal(Long numberOfProposal) {
        this.numberOfProposal = numberOfProposal;
        return this;
    }

    public void setNumberOfProposal(Long numberOfProposal) {
        this.numberOfProposal = numberOfProposal;
    }

    public CategoryProject getCategoryProject() {
        return categoryProject;
    }

    public Job categoryProject(CategoryProject categoryProject) {
        this.categoryProject = categoryProject;
        return this;
    }

    public void setCategoryProject(CategoryProject categoryProject) {
        this.categoryProject = categoryProject;
    }

    public Source getSourceSite() {
        return sourceSite;
    }

    public Job sourceSite(Source sourceSite) {
        this.sourceSite = sourceSite;
        return this;
    }

    public void setSourceSite(Source sourceSite) {
        this.sourceSite = sourceSite;
    }

    public String getLinkForDetails() {
        return linkForDetails;
    }

    public Job linkForDetails(String linkForDetails) {
        this.linkForDetails = linkForDetails;
        return this;
    }

    public void setLinkForDetails(String linkForDetails) {
        this.linkForDetails = linkForDetails;
    }

    public Statut getStatutOfOffer() {
        return statutOfOffer;
    }

    public Job statutOfOffer(Statut statutOfOffer) {
        this.statutOfOffer = statutOfOffer;
        return this;
    }

    public void setStatutOfOffer(Statut statutOfOffer) {
        this.statutOfOffer = statutOfOffer;
    }

    public BigDecimal getMinBudget() {
        return minBudget;
    }

    public Job minBudget(BigDecimal minBudget) {
        this.minBudget = minBudget;
        return this;
    }

    public void setMinBudget(BigDecimal minBudget) {
        this.minBudget = minBudget;
    }

    public BigDecimal getMaxBudget() {
        return maxBudget;
    }

    public Job maxBudget(BigDecimal maxBudget) {
        this.maxBudget = maxBudget;
        return this;
    }

    public void setMaxBudget(BigDecimal maxBudget) {
        this.maxBudget = maxBudget;
    }

    public Long getMinHoursPerWeek() {
        return minHoursPerWeek;
    }

    public Job minHoursPerWeek(Long minHoursPerWeek) {
        this.minHoursPerWeek = minHoursPerWeek;
        return this;
    }

    public void setMinHoursPerWeek(Long minHoursPerWeek) {
        this.minHoursPerWeek = minHoursPerWeek;
    }

    public Long getMaxHoursPerWeek() {
        return maxHoursPerWeek;
    }

    public Job maxHoursPerWeek(Long maxHoursPerWeek) {
        this.maxHoursPerWeek = maxHoursPerWeek;
        return this;
    }

    public void setMaxHoursPerWeek(Long maxHoursPerWeek) {
        this.maxHoursPerWeek = maxHoursPerWeek;
    }

    public Double getMinProjectLenghtWithMonthUnit() {
        return minProjectLenghtWithMonthUnit;
    }

    public Job minProjectLenghtWithMonthUnit(Double minProjectLenghtWithMonthUnit) {
        this.minProjectLenghtWithMonthUnit = minProjectLenghtWithMonthUnit;
        return this;
    }

    public void setMinProjectLenghtWithMonthUnit(Double minProjectLenghtWithMonthUnit) {
        this.minProjectLenghtWithMonthUnit = minProjectLenghtWithMonthUnit;
    }

    public Double getMaxProjectLenghtWithMonthUnit() {
        return maxProjectLenghtWithMonthUnit;
    }

    public Job maxProjectLenghtWithMonthUnit(Double maxProjectLenghtWithMonthUnit) {
        this.maxProjectLenghtWithMonthUnit = maxProjectLenghtWithMonthUnit;
        return this;
    }

    public void setMaxProjectLenghtWithMonthUnit(Double maxProjectLenghtWithMonthUnit) {
        this.maxProjectLenghtWithMonthUnit = maxProjectLenghtWithMonthUnit;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Job job = (Job) o;
        if (job.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), job.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Job{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", datePosted='" + getDatePosted() + "'" +
            ", country='" + getCountry() + "'" +
            ", ratingClient=" + getRatingClient() +
            ", paiementMethod='" + getPaiementMethod() + "'" +
            ", jobType='" + getJobType() + "'" +
            ", experienceLevel='" + getExperienceLevel() + "'" +
            ", clientHiresNumber=" + getClientHiresNumber() +
            ", clientHistoryInfoIsPrevious='" + isClientHistoryInfoIsPrevious() + "'" +
            ", numberOfWantedHiring=" + getNumberOfWantedHiring() +
            ", numberOfProposal=" + getNumberOfProposal() +
            ", categoryProject='" + getCategoryProject() + "'" +
            ", sourceSite='" + getSourceSite() + "'" +
            ", linkForDetails='" + getLinkForDetails() + "'" +
            ", statutOfOffer='" + getStatutOfOffer() + "'" +
            ", minBudget=" + getMinBudget() +
            ", maxBudget=" + getMaxBudget() +
            ", minHoursPerWeek=" + getMinHoursPerWeek() +
            ", maxHoursPerWeek=" + getMaxHoursPerWeek() +
            ", minProjectLenghtWithMonthUnit=" + getMinProjectLenghtWithMonthUnit() +
            ", maxProjectLenghtWithMonthUnit=" + getMaxProjectLenghtWithMonthUnit() +
            "}";
    }
}
