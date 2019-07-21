package com.hc.jobs.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A FreelancerDetails.
 */
@Entity
@Table(name = "freelancer_details")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class FreelancerDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "phone_1", nullable = false)
    private String phone1;

    @Column(name = "phone_2")
    private String phone2;

    @NotNull
    @Column(name = "cin", nullable = false)
    private Long cin;

    @NotNull
    @Column(name = "delevred_date", nullable = false)
    private Instant delevredDate;

    @Column(name = "cnss")
    private Long cnss;

    @NotNull
    @Column(name = "married", nullable = false)
    private Boolean married;

    @Column(name = "number_of_children")
    private Long numberOfChildren;

    @NotNull
    @Column(name = "motorized", nullable = false)
    private Boolean motorized;

    @NotNull
    @Column(name = "salary", nullable = false)
    private Long salary;

    @NotNull
    @Column(name = "hiring_date", nullable = false)
    private Instant hiringDate;

    @Lob
    @Column(name = "comments")
    private String comments;

    @NotNull
    @Column(name = "channel_of_hiring", nullable = false)
    private String channelOfHiring;

    @Lob
    @Column(name = "recommendation")
    private String recommendation;

    @OneToOne
    @JoinColumn(unique = true)
    private User user;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone1() {
        return phone1;
    }

    public FreelancerDetails phone1(String phone1) {
        this.phone1 = phone1;
        return this;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public FreelancerDetails phone2(String phone2) {
        this.phone2 = phone2;
        return this;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public Long getCin() {
        return cin;
    }

    public FreelancerDetails cin(Long cin) {
        this.cin = cin;
        return this;
    }

    public void setCin(Long cin) {
        this.cin = cin;
    }

    public Instant getDelevredDate() {
        return delevredDate;
    }

    public FreelancerDetails delevredDate(Instant delevredDate) {
        this.delevredDate = delevredDate;
        return this;
    }

    public void setDelevredDate(Instant delevredDate) {
        this.delevredDate = delevredDate;
    }

    public Long getCnss() {
        return cnss;
    }

    public FreelancerDetails cnss(Long cnss) {
        this.cnss = cnss;
        return this;
    }

    public void setCnss(Long cnss) {
        this.cnss = cnss;
    }

    public Boolean isMarried() {
        return married;
    }

    public FreelancerDetails married(Boolean married) {
        this.married = married;
        return this;
    }

    public void setMarried(Boolean married) {
        this.married = married;
    }

    public Long getNumberOfChildren() {
        return numberOfChildren;
    }

    public FreelancerDetails numberOfChildren(Long numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
        return this;
    }

    public void setNumberOfChildren(Long numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    public Boolean isMotorized() {
        return motorized;
    }

    public FreelancerDetails motorized(Boolean motorized) {
        this.motorized = motorized;
        return this;
    }

    public void setMotorized(Boolean motorized) {
        this.motorized = motorized;
    }

    public Long getSalary() {
        return salary;
    }

    public FreelancerDetails salary(Long salary) {
        this.salary = salary;
        return this;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public Instant getHiringDate() {
        return hiringDate;
    }

    public FreelancerDetails hiringDate(Instant hiringDate) {
        this.hiringDate = hiringDate;
        return this;
    }

    public void setHiringDate(Instant hiringDate) {
        this.hiringDate = hiringDate;
    }

    public String getComments() {
        return comments;
    }

    public FreelancerDetails comments(String comments) {
        this.comments = comments;
        return this;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getChannelOfHiring() {
        return channelOfHiring;
    }

    public FreelancerDetails channelOfHiring(String channelOfHiring) {
        this.channelOfHiring = channelOfHiring;
        return this;
    }

    public void setChannelOfHiring(String channelOfHiring) {
        this.channelOfHiring = channelOfHiring;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public FreelancerDetails recommendation(String recommendation) {
        this.recommendation = recommendation;
        return this;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public User getUser() {
        return user;
    }

    public FreelancerDetails user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
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
        FreelancerDetails freelancerDetails = (FreelancerDetails) o;
        if (freelancerDetails.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), freelancerDetails.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FreelancerDetails{" +
            "id=" + getId() +
            ", phone1='" + getPhone1() + "'" +
            ", phone2='" + getPhone2() + "'" +
            ", cin=" + getCin() +
            ", delevredDate='" + getDelevredDate() + "'" +
            ", cnss=" + getCnss() +
            ", married='" + isMarried() + "'" +
            ", numberOfChildren=" + getNumberOfChildren() +
            ", motorized='" + isMotorized() + "'" +
            ", salary=" + getSalary() +
            ", hiringDate='" + getHiringDate() + "'" +
            ", comments='" + getComments() + "'" +
            ", channelOfHiring='" + getChannelOfHiring() + "'" +
            ", recommendation='" + getRecommendation() + "'" +
            "}";
    }
}
