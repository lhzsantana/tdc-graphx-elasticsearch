package tdc.graphx_elasticsearch;
import tdc.graphx_elasticsearch.speed.Grafo;
import twitter4j.TwitterException;
import twitter4j.User;


public class Iniciar {

	public static void main(String[] args) throws TwitterException {
		// TODO Auto-generated method stub

		Grafo grafo = new Grafo();
		
		TwitterReader reader = new TwitterReader("14642944-Vc8CNMHAAm8SHwcsFZnesnByankhZ9uv4k2bhaCJ8","U1Tboi3gfY5hhwLQdiAQpIfUPbLt3Y1tjht7faQ1WTTjT","6UzkHFDcN3IVTOhtvYETcB418","Veg1CfrL8I5uAkuaDtKASuIZC1mPHnXLiaxvB3KqJDjMwnxRY8");
		
		String nome = "LuizHZSantana";
		
		User usuario = reader.getUsuario(nome);
		
		grafo.transformarUsers(usuario.getName(), usuario.getScreenName(), usuario.getId(), reader.getSeguidores(nome));
		grafo.calcularPageRank();
					
	}

}
