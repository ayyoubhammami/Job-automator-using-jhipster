package com.hc.jobs.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.echobox.api.linkedin.client.DefaultLinkedInClient;
import com.echobox.api.linkedin.connection.v2.ShareConnection;
import com.echobox.api.linkedin.types.ContentEntity;
import com.echobox.api.linkedin.types.Share;
import com.echobox.api.linkedin.types.ShareContent;
import com.echobox.api.linkedin.types.ShareText;
import com.echobox.api.linkedin.types.request.ShareRequestBody;
import com.echobox.api.linkedin.types.ugc.ShareMedia;
import com.echobox.api.linkedin.types.urn.URN;
import com.hc.jobs.domain.Compaign;

@Service
public class LinkedInService {
	
	
	

//	private final	String clientId = "78tsvyx8sqoyic";
//	private final	String clientSecret = "mjGDC5MRPEapqyLo";
//	private final	String redirectURI = "http://example.com/callback";
//	private final	String code = "AQQPSOTho-AQRTfV7tZb8ZQwY_hhlJM4u3KU7NaYkyf4qSMsdaRocMFVdHl3UsKeEPxQAvo7YZRfuUoHFKcgbyY5E7X2rExvWmgfWZmtK-6BSRaVHZTg1SCzqAjQC1H1KilXVu4qv8ylp_cTtLUHamoZYzMBdS6PducUdedaktwgTHdUDkG_ihxo8kNpXWsyC2lenotA";
	private final	String authToken = "AQXYABwHqzVjNA6H-izws8SbRBB_7VW7tmRzfxIXXVDupHW0x3ndmt_Rsvpwp2vqvrcbaIEq2CyaCKS4mqDgfj0q3tcuKJJhOsUpLo_Vfco30hri5kINJUdVu7fpMXER1Xt3e4h-vtkVC2FqU7XjTQqPZVaD9LcRT2R4ETTnvc7-ceeP5vFkkaL98k5VKeJ7Mi58AF44WCmTDB6cfoqr_pRFAV7o2KjqVpUxUr-cRZhka1Wph1UPjNMoULvJrIh0Tcnj02iES-kx2ozZ93Pw-R9pD0O9oULmDik4kcxKYa0z9VZwNX136yrmTAcht4iu6HavfGZevuTvJNwrBZA4Y-zqJTuddw";
	private final	URN URN = new URN("urn:li:person:00DggTXyyX");
	//("urn:li:organization:40711556")
	//("urn:li:person:00DggTXyyX")
	  ShareConnection shareConnection;

	
	public Long post (Compaign compaign) throws GeneralSecurityException, IOException {
		shareConnection = new ShareConnection(new DefaultLinkedInClient(authToken));
		ShareRequestBody shareRequestBody = new ShareRequestBody(URN);
		ShareContent shareContent = new ShareContent();
		ContentEntity contentEntity = new ContentEntity();

		contentEntity.setEntityLocation(compaign.getDesciption());
		shareContent.setContentEntities(Arrays.asList(contentEntity));
		shareContent.setTitle(compaign.getDesciption());
		shareRequestBody.setContent(shareContent);
		shareRequestBody.setSubject(compaign.getTitle());
		ShareText shareText = new ShareText();
		shareText.setText(compaign.getTitle() );
		shareRequestBody.setText(shareText);
		return shareConnection.postShare(shareRequestBody).getId();
		
	}

}
