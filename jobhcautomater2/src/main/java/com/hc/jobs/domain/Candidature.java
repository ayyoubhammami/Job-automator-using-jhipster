package com.hc.jobs.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import com.hc.jobs.domain.enumeration.Status;

/**
 * A Candidature.
 */
@Entity
@Table(name = "candidature")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Candidature implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "jhi_date")
    private LocalDate date;
	
    @NotNull
    @ManyToOne
    @JsonIgnoreProperties("profilCandidates")
    private ProfilCandidate profilCandidate;
    
    @NotNull
    @ManyToOne
    @JsonIgnoreProperties("posts")
    private Post post;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public Candidature status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getDate() {
        return date;
    }

    public Candidature date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public ProfilCandidate getProfilCandidate() {
        return profilCandidate;
    }

    public Candidature profilCandidate(ProfilCandidate profilCandidate) {
        this.profilCandidate = profilCandidate;
        return this;
    }

    public void setProfilCandidate(ProfilCandidate profilCandidate) {
        this.profilCandidate = profilCandidate;
    }

    public Post getPost() {
        return post;
    }

    public Candidature post(Post post) {
        this.post = post;
        return this;
    }

    public void setPost(Post post) {
        this.post = post;
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
        Candidature candidature = (Candidature) o;
        if (candidature.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), candidature.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Candidature{" +
            "id=" + getId() +
            ", status='" + getStatus() + "'" +
            ", date='" + getDate() + "'" +
            "}";
    }
}
