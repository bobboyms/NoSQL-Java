package br.com.br.nosql.storage;

import java.util.HashMap;
import java.util.Map;

import br.com.br.nosql.model.Document;

public class Index {

	private InvertedIndex invertedIndex;
	private Map<String, Integer> indicesMap;
	private Map<Integer, String> wordMaps;

	private int _id = 0;

	public Index() {
		setIndicesMap(new HashMap<>());
		setWordMaps(new HashMap<>());
		setInvertedIndex(new InvertedIndex());
	}

	public void indexIndice(Document document) {

		String[] words = document.getText().split(" ");

		for (String word : words) {
			word = word.toLowerCase();

			String[] tokens = word.split("");

			StringBuilder newWord = new StringBuilder();

			for (int i = 0; i < tokens.length; i++) {
				// TODO:colocar essa validação em uma função aparte
				if (tokens[i].equals(".") || tokens[i].equals(",") || tokens[i].equals("!") || tokens[i].equals(" ")) {
					continue;
				} else {
					newWord.append(tokens[i]);
				}
			}

			String wordtmp = newWord.toString();

			Integer intID = getIndicesMap().get(wordtmp);

			if (intID == null) {
				int id = get_id();
				getIndicesMap().put(wordtmp, id);
				getWordMaps().put(id, wordtmp);
				getInvertedIndex().index(id, document.getId());
			} else {
				getInvertedIndex().index(intID, document.getId());
			}

		}

	}

	public Map<String, Integer> getIndicesMap() {
		return indicesMap;
	}

	private void setIndicesMap(Map<String, Integer> indicesMap) {
		this.indicesMap = indicesMap;
	}

	private int get_id() {
		this._id += 1;
		return _id;
	}

	public InvertedIndex getInvertedIndex() {
		return invertedIndex;
	}

	public void setInvertedIndex(InvertedIndex invertedIndex) {
		this.invertedIndex = invertedIndex;
	}

	public Map<Integer, String> getWordMaps() {
		return wordMaps;
	}

	public void setWordMaps(Map<Integer, String> wordMaps) {
		this.wordMaps = wordMaps;
	}

}
