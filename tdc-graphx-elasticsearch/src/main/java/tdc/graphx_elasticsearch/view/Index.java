package tdc.graphx_elasticsearch.view;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.index.query.QueryBuilders;

public class Index {

	private Client client = null;
	private static final String index = "TDC";
	private static final String type = "user";

	public Index() throws UnknownHostException {
		client = TransportClient
				.builder()
				.build()
				.addTransportAddress(
						new InetSocketTransportAddress(InetAddress
								.getByName("host1"), 9300));

	}

	public void index(String root, List<String> seguidores) throws IOException {
		XContentBuilder builder = jsonBuilder().startObject()
				.field("nome", root)
				.field("seguidores", seguidores)
				.endObject();

		client.prepareIndex().setSource(builder).execute().actionGet();
	}

	public SearchResponse searchWithAggregations() {
		return client.prepareSearch(index).setTypes(type)
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(QueryBuilders.termQuery("multi", "test"))
				.setPostFilter(QueryBuilders.rangeQuery("age").from(12).to(18))
				//.addAggregation(AbstractAggregationBuilder)
				.setFrom(0).setSize(60)
				.setExplain(true)
				.execute().actionGet();
	}

	public void close() {

		client.close();
	}
}
