package br.com.br.nosql.storage;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class InvertedIndex {
	private Map<Integer, Set<Integer>> InvertedIndexMap;

	public void index(Integer idWord, Integer idDocument) {
		Set<Integer> listIndexedDocs = (Set) getInvertedIndexMap().get(idWord);

		if (listIndexedDocs == null) {
			listIndexedDocs = new HashSet();
			listIndexedDocs.add(idDocument);
			getInvertedIndexMap().put(idWord, listIndexedDocs);
		} else {
			listIndexedDocs.add(idDocument);
		}
	}

	public InvertedIndex() {
		setInvertedIndexMap(new HashMap());
	}

	public Map<Integer, Set<Integer>> getInvertedIndexMap() {
		return InvertedIndexMap;
	}

	public void setInvertedIndexMap(Map<Integer, Set<Integer>> invertedIndexMap) {
		InvertedIndexMap = invertedIndexMap;
	}
}
