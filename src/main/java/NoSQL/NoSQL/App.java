package NoSQL.NoSQL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import br.com.br.nosql.model.Document;
import br.com.br.nosql.storage.Index;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		List<Document> documents = new ArrayList<Document>();
		documents.add(new Document(1l,
				"Eu enfrentaria o mundo todo com uma só mão, se você me prometesse que estaria segurando a outra."));
		documents.add(new Document(2l,
				"Sejam quais forem as cicunstâncias, o amor acaba sempre por vencer e unir aqueles que lutam verdadeiramente por ele."));
		documents.add(new Document(3l,
				"Amor, eu te amo cada vez mais e sempre irei amar. É só você que eu quero, é só você que eu amo! Meu amor, sempre sempre irei te amar."));
		documents.add(new Document(4l,
				"Quando estiver em silêncio pensando em alguém, lembre-se que estarei em silêncio pensando somente em você."));
		documents.add(new Document(5l,
				"Quando alguém disser que te ama, duvide. Quando alguém provar que te ama, valorize."));
		documents.add(new Document(6l, "Nunca deixe seu amor ir. Vá atrás dele e seja feliz!"));
		documents.add(new Document(7l,
				"A distância faz ao amor aquilo que o vento faz ao fogo. Apaga o pequeno, mas inflama o grande."));
		documents.add(new Document(8l,
				"Nunca chore por amor, pois quem realmente merecer suas lágrimas nunca lhe fará chorar!"));


		Index index = new Index();
		for (Document document : documents) {
			index.indexIndice(document);
		}
		
		for (Entry<String, Integer> entry : index.getIndicesMap().entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		}

	}
}
