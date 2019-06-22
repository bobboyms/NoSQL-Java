package br.com.br.nosql.storage;

import br.com.br.nosql.model.Document;
import br.com.br.nosql.storage.index.IndexAttributes;
import br.com.br.nosql.storage.index.interfaces.Index;
import br.com.br.nosql.storage.index.interfaces.InvertWord2DocAtt2Word;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class Collection {
	private String id;
	private String name;

	public Collection(String name) {
		setId(UUID.randomUUID().toString());
		setName(name);
	}

	public void indexDocument(Document document) {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}

	public static void main(String[] args) {
		System.out.println("--- iniiando indexação versao 2 ----");
		long startTime = System.currentTimeMillis();
		int total = 0;

		Index indexAttDocJson = new IndexAttributes();

		indexAttDocJson.indexIndice(new Document(1, "Texto"));

		Path path = Paths.get("C:\\teste\\arquivos-json-completo.txt", new String[0]);

		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
		System.out.println(elapsedTime / 1000L);
		System.out.println(elapsedTime);
		System.out.println("Total: " + total);

		System.out.println("--- iniiando busca versao 2 ----");

		startTime = System.currentTimeMillis();
		Integer idAtt = (Integer) indexAttDocJson.getIndicesMap().get("_id.$oid");

		String nome = "5cefe8677a8c6a31245ff6d0";

		if (idAtt != null) {
			Integer idNome = (Integer) indexAttDocJson.getIndexChild().getIndicesMap().get(nome);

			Set<Integer> docs = ((InvertWord2DocAtt2Word) indexAttDocJson.getIndexChild()).getInvertedIndexWord2Doc()
					.getInvertedIndexMap().get(idNome);
			System.out.println(docs);
		}

		stopTime = System.currentTimeMillis();
		elapsedTime = stopTime - startTime;
		System.out.println(elapsedTime / 1000L);
		System.out.println(elapsedTime);

		System.out.println();
	}
}
