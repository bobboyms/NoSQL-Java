package br.com.br.nosql.storage.index;

import br.com.br.nosql.storage.index.interfaces.Index;
import java.util.Map;

public abstract class IndexAbstract implements Index {
	private int _id = 0;
	private Map<String, Integer> indicesMap;
	private Map<Integer, String> wordMaps;

	public IndexAbstract() {
	}

	public Map<String, Integer> getIndicesMap() {
		return indicesMap;
	}

	protected void setIndicesMap(Map<String, Integer> indicesMap) {
		this.indicesMap = indicesMap;
	}

	protected int get_id() {
		_id += 1;
		return _id;
	}

	public Map<Integer, String> getWordMaps() {
		return wordMaps;
	}

	protected void setWordMaps(Map<Integer, String> wordMaps) {
		this.wordMaps = wordMaps;
	}
}
