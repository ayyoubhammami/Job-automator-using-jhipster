package com.hc.jobs.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A ProfilCandidate.
 */
@Entity
@Table(name = "profil_candidate")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ProfilCandidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotNull
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotNull
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "facebook")
    private String facebook;

    @Column(name = "linked_in")
    private String linkedIn;

    @Column(name = "twiter")
    private String twiter;

    @OneToMany(mappedBy = "profilCandidate")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Candidature> profilCandidates = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public ProfilCandidate firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public ProfilCandidate lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public ProfilCandidate phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public ProfilCandidate email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFacebook() {
        return facebook;
    }

    public ProfilCandidate facebook(String facebook) {
        this.facebook = facebook;
        return this;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getLinkedIn() {
        return linkedIn;
    }

    public ProfilCandidate linkedIn(String linkedIn) {
        this.linkedIn = linkedIn;
        return this;
    }

    public void setLinkedIn(String linkedIn) {
        this.linkedIn = linkedIn;
    }

    public String getTwiter() {
        return twiter;
    }

    public ProfilCandidate twiter(String twiter) {
        this.twiter = twiter;
        return this;
    }

    public void setTwiter(String twiter) {
        this.twiter = twiter;
    }

    public Set<Candidature> getProfilCandidates() {
        return profilCandidates;
    }

    public ProfilCandidate profilCandidates(Set<Candidature> candidatures) {
        this.profilCandidates = candidatures;
        return this;
    }

    public ProfilCandidate addProfilCandidate(Candidature candidature) {
        this.profilCandidates.add(candidature);
        candidature.setProfilCandidate(this);
        return this;
    }

    public ProfilCandidate removeProfilCandidate(Candidature candidature) {
        this.profilCandidates.remove(candidature);
        candidature.setProfilCandidate(null);
        return this;
    }

    public void setProfilCandidates(Set<Candidature> candidatures) {
        this.profilCandidates = candidatures;
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
        ProfilCandidate profilCandidate = (ProfilCandidate) o;
        if (profilCandidate.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), profilCandidate.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProfilCandidate{" +
            "id=" + getId() +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", email='" + getEmail() + "'" +
            ", facebook='" + getFacebook() + "'" +
            ", linkedIn='" + getLinkedIn() + "'" +
            ", twiter='" + getTwiter() + "'" +
            "}";
    }
}
