package com.hc.jobs.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import io.github.jhipster.config.jcache.BeanClassLoaderAwareJCacheRegionFactory;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        BeanClassLoaderAwareJCacheRegionFactory.setBeanClassLoader(this.getClass().getClassLoader());
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache(com.hc.jobs.repository.UserRepository.USERS_BY_LOGIN_CACHE, jcacheConfiguration);
            cm.createCache(com.hc.jobs.repository.UserRepository.USERS_BY_EMAIL_CACHE, jcacheConfiguration);
            cm.createCache(com.hc.jobs.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(com.hc.jobs.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(com.hc.jobs.domain.User.class.getName() + ".authorities", jcacheConfiguration);




            //cm.createCache(com.hc.jobs.domain.JobMatchingFilters.class.getName(), jcacheConfiguration);


            cm.createCache(com.hc.jobs.domain.EmployeeDetails.class.getName(), jcacheConfiguration);
            cm.createCache(com.hc.jobs.domain.FreelancerDetails.class.getName(), jcacheConfiguration);
            cm.createCache(com.hc.jobs.domain.ProfilCondidate.class.getName(), jcacheConfiguration);

            cm.createCache(com.hc.jobs.domain.Job.class.getName(), jcacheConfiguration);

            cm.createCache(com.hc.jobs.domain.ProfilCandidate.class.getName(), jcacheConfiguration);
            cm.createCache(com.hc.jobs.domain.ProfilCandidate.class.getName() + ".profilCandidates", jcacheConfiguration);
            cm.createCache(com.hc.jobs.domain.Candidature.class.getName(), jcacheConfiguration);
            cm.createCache(com.hc.jobs.domain.Post.class.getName(), jcacheConfiguration);
            cm.createCache(com.hc.jobs.domain.Post.class.getName() + ".posts", jcacheConfiguration);

            cm.createCache(com.hc.jobs.domain.Affectation.class.getName(), jcacheConfiguration);
            cm.createCache(com.hc.jobs.domain.Affectation.class.getName() + ".freelancerDetails", jcacheConfiguration);
            cm.createCache(com.hc.jobs.domain.Affectation.class.getName() + ".employeeDetails", jcacheConfiguration);
            cm.createCache(com.hc.jobs.domain.Compaign.class.getName(), jcacheConfiguration);
            cm.createCache(com.hc.jobs.domain.Compaign.class.getName() + ".socialMedias", jcacheConfiguration);
            cm.createCache(com.hc.jobs.domain.SocialMedia.class.getName(), jcacheConfiguration);
            cm.createCache(com.hc.jobs.domain.SocialMedia.class.getName() + ".compaigns", jcacheConfiguration);

            cm.createCache(com.hc.jobs.domain.Source.class.getName(), jcacheConfiguration);
            cm.createCache(com.hc.jobs.domain.Keyword.class.getName(), jcacheConfiguration);
            cm.createCache(com.hc.jobs.domain.Email.class.getName(), jcacheConfiguration);
            cm.createCache(com.hc.jobs.domain.InternalFilter.class.getName(), jcacheConfiguration);

        };
    }
}
