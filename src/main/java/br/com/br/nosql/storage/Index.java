package br.com.br.nosql.storage;

import java.util.HashMap;
import java.util.Map;

import br.com.br.nosql.model.Document;

public class Index {

	private Map<String, Integer> indicesMap;
	
	private int _id = 0;

	public Index() {
		setIndicesMap(new HashMap<String, Integer>());
	}
	
	public void indexIndice(Document document) {
		
		String[] words = document.getText().split(" ");

		for (String word : words) {
			word = word.toLowerCase();
			String[] tokens = word.split("");

			StringBuilder newWord = new StringBuilder();
			for (String token : tokens) {

				if (token.equals(".") || token.equals(",") || token.equals("!")) {
					continue;
				} else {
					newWord.append(token);
				}
			}

			indicesMap.put(newWord.toString(), this.get_id());
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


}
