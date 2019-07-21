package com.hc.jobs.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.net.MalformedURLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.hc.jobs.domain.Compaign;
import com.hc.jobs.domain.Post;
import com.hc.jobs.domain.enumeration.EmailingCategory;
import com.hc.jobs.domain.enumeration.SocialMediaType;
import com.hc.jobs.repository.EmailRepository;
import com.hc.jobs.repository.PostRepository;

import io.github.jhipster.config.JHipsterProperties;
import twitter4j.TwitterException;

/**
 * Service for compaigns .
 */
@Service
public class CompaignService {
	

	
	@Autowired
	LinkedInService linkedInService;
	
	 @Autowired
	 private JHipsterProperties jHipsterProperties;

	 @Autowired
	 private MessageSource messageSource;
	
	 @Autowired
	 private SpringTemplateEngine templateEngine;
	
	 @Autowired
	 private JavaMailSenderImpl javaMailSender;
	 
	 @Autowired
	 private EmailRepository emailRepository;
		    
	 private MailService mailService;

	 @Autowired
	 private TwitterService twitterService;

	 @Autowired
	 private PostRepository postRepository;
	 
	public void publish(Compaign compaign) throws GeneralSecurityException, MalformedURLException, IOException, TwitterException {

		if(compaign.getSocialMedias()
				.stream()
				.filter(p->{return p.getValue().equals(SocialMediaType.Email);})
				.findFirst()
				.isPresent())
		{
			mailService = new MailService(jHipsterProperties, javaMailSender, messageSource, templateEngine);
			emailRepository.findByCategory(EmailingCategory.JOB).
				forEach(m->{
					mailService.sendEmailFromCompaignTemplate(m, compaign);
					//mailService.sendEmail(m.getEmail(), "offre job << "+compaign.getTitle()+">>", "testContent", false, false);
				});
	        
		}

		 if(compaign.getSocialMedias()
				.stream()
				.filter(p->{return p.getValue().equals(SocialMediaType.Linkedin);})
				.findFirst()
				.isPresent())
		{
			Long id=linkedInService.post(compaign);
			Post post= new Post();
			
			post.setTitle("linkedin");
			post.setRef(id+"");
			post.setDesciption(compaign.getDesciption());
			postRepository.save(post);
		}
			

		if(compaign.getSocialMedias()
				.stream()
				.filter(p->{return p.getValue().equals(SocialMediaType.Twitter);})
				.findFirst()
				.isPresent())
		{
			Long id= twitterService.post(compaign);
			Post post = new Post();
			post.setTitle(id+"");
			post.setDesciption("");
			post.setRef("Twitter");
			postRepository.save(post);
		}

	}

}
