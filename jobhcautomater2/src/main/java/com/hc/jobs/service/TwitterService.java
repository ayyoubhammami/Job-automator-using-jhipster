package com.hc.jobs.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.hc.jobs.domain.Compaign;

import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

@Service
public class TwitterService {

	  //Your Twitter App's Consumer Key
    String consumerKey = "vjOG8Ad1zoYXD8peupf1Ga6VQ";

    //Your Twitter App's Consumer Secret
    String consumerSecret = "9IAdHjBjwiS3zRjqCfHLks0xaPn0lP0fwJ2IyJ840C0FmsHJda";

    //Your Twitter Access Token
    String accessToken = "1141295236137852928-nQ3MFaASW76k6fZHGaMD7BfQBFGc9X";

    //Your Twitter Access Token Secret
    String accessTokenSecret = "P8HWXVgqbvDUDGTEFiqKq5e6yS16ECV9mI5EzEGU64n5S";
    
    Twitter twitter ;
    
    @PostConstruct
    public void init() {

    //Instantiate a re-usable and thread-safe factory
    TwitterFactory twitterFactory = new TwitterFactory();

    //Instantiate a new Twitter instance
     twitter = twitterFactory.getInstance();

    //setup OAuth Consumer Credentials
    twitter.setOAuthConsumer(consumerKey, consumerSecret);

    //setup OAuth Access Token
    twitter.setOAuthAccessToken(new AccessToken(accessToken, accessTokenSecret));
    }
    
	public Long  post(Compaign compaign) throws MalformedURLException, IOException, TwitterException {

        //Instantiate and initialize a new twitter status update
        StatusUpdate statusUpdate = new StatusUpdate(compaign.getTitle()+"\n"+compaign.getDesciption());
        statusUpdate.setMedia("", new URL("https://www.mercedes-benz.fr/passengercars/_jcr_content/image.MQ6.2.2x.20190123105639.png").openStream());
 
        //tweet or update status
        Status status = twitter.updateStatus(statusUpdate);
        return status.getId();
	}
}
