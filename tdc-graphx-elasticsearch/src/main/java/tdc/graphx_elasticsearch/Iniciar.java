package tdc.graphx_elasticsearch;
import tdc.graphx_elasticsearch.speed.Grafo;
import twitter4j.TwitterException;
import twitter4j.User;


public class Iniciar {

	public static void main(String[] args) throws TwitterException {
		// TODO Auto-generated method stub

		Grafo grafo = new Grafo();
		
		TwitterReader reader = new TwitterReader("","","","");
		
		String nome = "";
		
		User usuario = reader.getUsuario(nome);
		
		grafo.transformarUsers(usuario.getName(), usuario.getScreenName(), usuario.getId(), reader.getSeguidores(nome));
		grafo.calcularPageRank();
					
	}

}
