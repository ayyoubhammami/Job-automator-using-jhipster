package com.hc.jobs.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Affectation.
 */
@Entity
@Table(name = "affectation")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Affectation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "jhi_date", nullable = false)
    private Instant date;

    @OneToOne
    @JoinColumn(unique = true)
    private Job job;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "affectation_freelancer_details",
               joinColumns = @JoinColumn(name = "affectations_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "freelancer_details_id", referencedColumnName = "id"))
    private Set<FreelancerDetails> freelancerDetails = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "affectation_employee_details",
               joinColumns = @JoinColumn(name = "affectations_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "employee_details_id", referencedColumnName = "id"))
    private Set<EmployeeDetails> employeeDetails = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDate() {
        return date;
    }

    public Affectation date(Instant date) {
        this.date = date;
        return this;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Job getJob() {
        return job;
    }

    public Affectation job(Job job) {
        this.job = job;
        return this;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Set<FreelancerDetails> getFreelancerDetails() {
        return freelancerDetails;
    }

    public Affectation freelancerDetails(Set<FreelancerDetails> freelancerDetails) {
        this.freelancerDetails = freelancerDetails;
        return this;
    }

    public Affectation addFreelancerDetails(FreelancerDetails freelancerDetails) {
        this.freelancerDetails.add(freelancerDetails);
        return this;
    }

    public Affectation removeFreelancerDetails(FreelancerDetails freelancerDetails) {
        this.freelancerDetails.remove(freelancerDetails);
        return this;
    }

    public void setFreelancerDetails(Set<FreelancerDetails> freelancerDetails) {
        this.freelancerDetails = freelancerDetails;
    }

    public Set<EmployeeDetails> getEmployeeDetails() {
        return employeeDetails;
    }

    public Affectation employeeDetails(Set<EmployeeDetails> employeeDetails) {
        this.employeeDetails = employeeDetails;
        return this;
    }

    public Affectation addEmployeeDetails(EmployeeDetails employeeDetails) {
        this.employeeDetails.add(employeeDetails);
        return this;
    }

    public Affectation removeEmployeeDetails(EmployeeDetails employeeDetails) {
        this.employeeDetails.remove(employeeDetails);
        return this;
    }

    public void setEmployeeDetails(Set<EmployeeDetails> employeeDetails) {
        this.employeeDetails = employeeDetails;
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
        Affectation affectation = (Affectation) o;
        if (affectation.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), affectation.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Affectation{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            "}";
    }
}
