package com.hc.jobs.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.hc.jobs.domain.enumeration.SocialMediaType;

/**
 * A SocialMedia.
 */
@Entity
@Table(name = "social_media")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SocialMedia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "jhi_value")
    private SocialMediaType value;

    @ManyToMany(mappedBy = "socialMedias")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Compaign> compaigns = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SocialMediaType getValue() {
        return value;
    }

    public SocialMedia value(SocialMediaType value) {
        this.value = value;
        return this;
    }

    public void setValue(SocialMediaType value) {
        this.value = value;
    }

    public Set<Compaign> getCompaigns() {
        return compaigns;
    }

    public SocialMedia compaigns(Set<Compaign> compaigns) {
        this.compaigns = compaigns;
        return this;
    }

    public SocialMedia addCompaign(Compaign compaign) {
        this.compaigns.add(compaign);
        compaign.getSocialMedias().add(this);
        return this;
    }

    public SocialMedia removeCompaign(Compaign compaign) {
        this.compaigns.remove(compaign);
        compaign.getSocialMedias().remove(this);
        return this;
    }

    public void setCompaigns(Set<Compaign> compaigns) {
        this.compaigns = compaigns;
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
        SocialMedia socialMedia = (SocialMedia) o;
        if (socialMedia.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), socialMedia.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SocialMedia{" +
            "id=" + getId() +
            ", value='" + getValue() + "'" +
            "}";
    }
}
