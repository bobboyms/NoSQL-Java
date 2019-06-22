package br.com.br.nosql.storage.index;

import br.com.br.nosql.model.Document;
import br.com.br.nosql.storage.index.interfaces.Index;
import com.github.wnameless.json.flattener.JsonFlattener;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class IndexAttributes extends IndexAbstract {
	private Index indexWords;
	private Map<String, Object> flattenJson;

	public IndexAttributes() {
		super.setIndicesMap(new HashMap());
		super.setWordMaps(new HashMap());
		setIndexWords(new IndexAttDoc2Word());
	}

	public void indexIndice(Document document) {
		flattenJson = JsonFlattener.flattenAsMap(document.getText());

		String value = null;
		Integer intId_attribute = null;

		for (Map.Entry<String, Object> entry : flattenJson.entrySet()) {
			intId_attribute = (Integer) getIndicesMap().get(entry.getKey());

			if (intId_attribute == null) {
				int id_attribute = get_id();
				getIndicesMap().put((String) entry.getKey(), Integer.valueOf(id_attribute));

				value = entry.getValue() == null ? "null" : entry.getValue().toString();
				getIndexWords().indexIndice(new Document(Integer.valueOf(id_attribute), value, document));

			} else {
				value = entry.getValue() == null ? "null" : entry.getValue().toString();
				getIndexWords().indexIndice(new Document(intId_attribute, value, document));
			}
		}
	}

	public Index getIndexWords() {
		return indexWords;
	}

	public void setIndexWords(Index indexWords) {
		this.indexWords = indexWords;
	}

	public Index getIndexChild() {
		return getIndexWords();
	}
}
