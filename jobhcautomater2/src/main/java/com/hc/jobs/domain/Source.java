package com.hc.jobs.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Source.
 */
@Entity
@Table(name = "source")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Source implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "public_key")
    private String publicKey;

    @Column(name = "token_api")
    private String tokenApi;

    @Column(name = "searching_url")
    private String searchingUrl;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Source name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public Source url(String url) {
        this.url = url;
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public Source publicKey(String publicKey) {
        this.publicKey = publicKey;
        return this;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getTokenApi() {
        return tokenApi;
    }

    public Source tokenApi(String tokenApi) {
        this.tokenApi = tokenApi;
        return this;
    }

    public void setTokenApi(String tokenApi) {
        this.tokenApi = tokenApi;
    }

    public String getSearchingUrl() {
        return searchingUrl;
    }

    public Source searchingUrl(String searchingUrl) {
        this.searchingUrl = searchingUrl;
        return this;
    }

    public void setSearchingUrl(String searchingUrl) {
        this.searchingUrl = searchingUrl;
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
        Source source = (Source) o;
        if (source.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), source.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Source{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", url='" + getUrl() + "'" +
            ", publicKey='" + getPublicKey() + "'" +
            ", tokenApi='" + getTokenApi() + "'" +
            ", searchingUrl='" + getSearchingUrl() + "'" +
            "}";
    }
}
