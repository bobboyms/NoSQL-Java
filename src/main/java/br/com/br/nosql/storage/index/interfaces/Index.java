package br.com.br.nosql.storage.index.interfaces;

import br.com.br.nosql.model.Document;
import java.util.Map;

public abstract interface Index {
	public abstract Map<String, Integer> getIndicesMap();

	public abstract Map<Integer, String> getWordMaps();

	public abstract void indexIndice(Document paramDocument);

	public abstract Index getIndexChild();
}
