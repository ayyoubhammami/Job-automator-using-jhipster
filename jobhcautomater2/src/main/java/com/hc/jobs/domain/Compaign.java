package com.hc.jobs.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Compaign.
 */
@Entity
@Table(name = "compaign")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Compaign implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "jhi_ref", nullable = false)
    private String ref;

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @NotNull
    @Size(max = 800)
    @Column(name = "desciption", length = 800, nullable = false)
    private String desciption;

    @Lob
    @Column(name = "img")
    private byte[] img;

    @Column(name = "img_content_type")
    private String imgContentType;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "compaign_social_media",
               joinColumns = @JoinColumn(name = "compaigns_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "social_medias_id", referencedColumnName = "id"))
    private Set<SocialMedia> socialMedias = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRef() {
        return ref;
    }

    public Compaign ref(String ref) {
        this.ref = ref;
        return this;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getTitle() {
        return title;
    }

    public Compaign title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesciption() {
        return desciption;
    }

    public Compaign desciption(String desciption) {
        this.desciption = desciption;
        return this;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public byte[] getImg() {
        return img;
    }

    public Compaign img(byte[] img) {
        this.img = img;
        return this;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public String getImgContentType() {
        return imgContentType;
    }

    public Compaign imgContentType(String imgContentType) {
        this.imgContentType = imgContentType;
        return this;
    }

    public void setImgContentType(String imgContentType) {
        this.imgContentType = imgContentType;
    }

    public Set<SocialMedia> getSocialMedias() {
        return socialMedias;
    }

    public Compaign socialMedias(Set<SocialMedia> socialMedias) {
        this.socialMedias = socialMedias;
        return this;
    }

    public Compaign addSocialMedia(SocialMedia socialMedia) {
        this.socialMedias.add(socialMedia);
        socialMedia.getCompaigns().add(this);
        return this;
    }

    public Compaign removeSocialMedia(SocialMedia socialMedia) {
        this.socialMedias.remove(socialMedia);
        socialMedia.getCompaigns().remove(this);
        return this;
    }

    public void setSocialMedias(Set<SocialMedia> socialMedias) {
        this.socialMedias = socialMedias;
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
        Compaign compaign = (Compaign) o;
        if (compaign.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), compaign.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Compaign{" +
            "id=" + getId() +
            ", ref='" + getRef() + "'" +
            ", title='" + getTitle() + "'" +
            ", desciption='" + getDesciption() + "'" +
            ", img='" + getImg() + "'" +
            ", imgContentType='" + getImgContentType() + "'" +
            "}";
    }
}
