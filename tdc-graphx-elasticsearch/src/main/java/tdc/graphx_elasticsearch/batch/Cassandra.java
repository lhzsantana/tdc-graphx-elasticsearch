package tdc.graphx_elasticsearch.batch;

import twitter4j.User;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

public class Cassandra {

	private Cluster cluster;
	private Session session;

	public Cassandra() {
		cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
		session = cluster.connect("users");
	}

	public void salvarGrafo(User user) {

		session.execute(
				String.format("INSERT INTO users (name, screenname, id) VALUES ('%', '%', %)", user.getName(), user.getScreenName(), user.getId())
		);
	}

	public boolean exists(String id) {

		ResultSet results = session.execute(
				String.format("SELECT * FROM users WHERE id='%'", id)
		);
		for (Row row : results) {
			return true;
		}
		
		return false;
	}

}
