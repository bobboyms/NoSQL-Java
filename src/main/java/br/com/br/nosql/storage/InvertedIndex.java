package br.com.br.nosql.storage;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class InvertedIndex {
	
	private Map<Integer, List<Integer>> InvertedIndexMap; // = new HashMap<Integer, List<Integer>>()
	
	public void index(Integer idWord, Integer idDocument) {
		
		List<Integer> listIndexedDocs = getInvertedIndexMap().get(idWord);
		if (listIndexedDocs == null) {			
			listIndexedDocs = new ArrayList<Integer>();
			listIndexedDocs.add(idDocument);
			getInvertedIndexMap().put(idWord, listIndexedDocs);
			
		} else {
			
			boolean existe = false;
			for (int i = 0; i < listIndexedDocs.size(); i++) {
				existe = (listIndexedDocs.get(i) == idDocument);
			}
			
			if (!existe) {
				listIndexedDocs.add(idDocument);				
			}
			
		}
		
		
		
	}
	
	public InvertedIndex() {
		setInvertedIndexMap(new HashMap<Integer, List<Integer>>());
	}
	
	public Map<Integer, List<Integer>> getInvertedIndexMap() {
		return InvertedIndexMap;
	}

	private void setInvertedIndexMap(Map<Integer, List<Integer>> invertedIndexMap) {
		InvertedIndexMap = invertedIndexMap;
	}
	
}
