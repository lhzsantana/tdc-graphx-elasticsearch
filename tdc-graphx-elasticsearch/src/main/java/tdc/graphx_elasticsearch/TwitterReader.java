package tdc.graphx_elasticsearch;

import java.util.List;

import twitter4j.PagableResponseList;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterReader {

	ConfigurationBuilder configurationBuilder = null;
	Twitter twitter = null;

	public TwitterReader(String accessToken, String secretToken,
			String consumerKey, String consumerSecret) {
		configurationBuilder = new ConfigurationBuilder();
		configurationBuilder.setOAuthAccessToken(accessToken)
				.setOAuthAccessTokenSecret(secretToken)
				.setOAuthConsumerKey(consumerKey)
				.setOAuthConsumerSecret(consumerSecret);
		twitter = new TwitterFactory(configurationBuilder.build())
				.getInstance();
	}
	
	public User getUsuario(String nome) throws TwitterException {
		
		return twitter.showUser(nome);
		
	}

	public User[] getSeguidores(String nome) throws TwitterException {
		
		long cursor = -1;

		PagableResponseList<User> seguidores=twitter.getFollowersList(nome, cursor);
		
		/*
		do{
			cursor = seguidores.getNextCursor();
			seguidores.addAll(twitter.getFollowersList(nome, cursor));		
		}while(seguidores.getNextCursor() != 0);
		*/

		return seguidores.toArray(new User[seguidores.size()]);
	}
}
