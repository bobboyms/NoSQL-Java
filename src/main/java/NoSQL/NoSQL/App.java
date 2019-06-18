package NoSQL.NoSQL;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import br.com.br.nosql.model.Document;
import br.com.br.nosql.storage.Index;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws IOException {
		
		long startTime = System.currentTimeMillis();
		int total = 0;
		List<Document> documents = new ArrayList<Document>();

		Path path = Paths.get("C:\\teste\\database.txt");
		try (BufferedReader reader = Files.newBufferedReader(path, Charset.forName("UTF-8"))) {

			String currentLine = null;
			while ((currentLine = reader.readLine()) != null) {// while there is content on the current line
				// System.out.println(currentLine); // print the current line
				total += 1;
				documents.add(new Document(total, currentLine));
			}
		} catch (IOException ex) {
			ex.printStackTrace(); // handle an exception here
		}

		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
		System.out.println(elapsedTime / 1000);
		System.out.println(total);

//		documents.add(new Document(1, "O amor não se vê com os olhos mas com o coração."));
//		documents.add(new Document(2, "Um coração feliz é o resultado inevitável de um coração ardente de amor."));
//		documents.add(new Document(3,
//				"Amor, eu te amo cada vez mais e sempre irei amar. É só você que eu quero, é só você que eu amo! Meu amor, sempre sempre irei te amar."));
//		documents.add(new Document(4,
//				"Quando estiver em silêncio pensando em alguém, lembre-se que estarei em silêncio pensando somente em você."));
//		documents.add(
//				new Document(5, "Quando alguém disser que te ama, duvide. Quando alguém provar que te ama, valorize."));
//		documents.add(new Document(6, "Nunca deixe seu amor ir. Vá atrás dele e seja feliz!"));
//		documents.add(new Document(7,
//				"A distância faz ao amor aquilo que o vento faz ao fogo. Apaga o pequeno, mas inflama o grande."));
//		documents.add(new Document(8,
//				"Nunca chore por amor, pois quem realmente merecer suas lágrimas nunca lhe fará chorar!"));

		System.out.println("-------------------------------");
//		startTime = System.currentTimeMillis();

		Index index = new Index();
		for (int i = 0; i < documents.size(); i++) {
			index.indexIndice(documents.get(i));
		}

		System.out.println("iniciando gravação");
		startTime = System.currentTimeMillis();

		Path path2 = Paths.get("C:\\teste\\banco.txt");
		try (BufferedWriter writer = Files.newBufferedWriter(path2, Charset.forName("UTF-8"),
				StandardOpenOption.APPEND)) {
			
			for (Entry<Integer, List<Integer>> entry : index.getInvertedIndex().getInvertedIndexMap().entrySet()) {

				StringBuilder builder = new StringBuilder();

				builder.append(entry.getKey());
				builder.append("|");

				List<Integer> lists = entry.getValue();
				for (Integer in : lists) {
					builder.append(in);
					builder.append("|");
				}

				//builder.append("\n");
				
				writer.append(builder.toString());
				writer.newLine();

				// System.out.println(builder.toString());

				// StorageHandler.writeToFileNIOWay("C:\\teste\\banco.txt", builder.toString());
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		stopTime = System.currentTimeMillis();
		elapsedTime = stopTime - startTime;
		 System.out.println(elapsedTime);
//		 System.out.println(total);

//		stopTime= System.currentTimeMillis();
//		
//		elapsedTime = stopTime - startTime;
//		System.out.println(elapsedTime / 1000);

//		for (Entry<Integer, List<Integer>> entry : index.getInvertedIndex().getInvertedIndexMap().entrySet()) {
//			System.out.println("Palavra: " + index.getWordMaps().get(entry.getKey()));
//			List<Integer> lists = entry.getValue();
//			for (Integer in : lists) {
//				System.out.println(in);
//			}
//		}

	}
}
