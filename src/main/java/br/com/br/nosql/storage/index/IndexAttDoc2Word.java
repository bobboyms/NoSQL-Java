package br.com.br.nosql.storage.index;

import br.com.br.nosql.model.Document;
import br.com.br.nosql.storage.InvertedIndex;
import br.com.br.nosql.storage.index.interfaces.Index;
import br.com.br.nosql.storage.index.interfaces.InvertWord2DocAtt2Word;
import java.util.HashMap;
import java.util.Map;

public class IndexAttDoc2Word extends IndexAbstract implements InvertWord2DocAtt2Word {
	private String[] words;
	private InvertedIndex invertedIndexWord2Doc;
	private InvertedIndex invertedIndexAtt2Word;

	public IndexAttDoc2Word() {
		super.setIndicesMap(new HashMap());
		super.setWordMaps(new HashMap());

		setInvertedIndexWord2Doc(new InvertedIndex());
		setInvertedIndexAtt2Word(new InvertedIndex());
	}

	public InvertedIndex getInvertedIndexWord2Doc() {
		return invertedIndexWord2Doc;
	}

	public InvertedIndex getInvertedIndexAtt2Word() {
		return invertedIndexAtt2Word;
	}

	public void indexIndice(Document document) {
		words = document.getText().split(" ");

		String[] tokens = null;
		String wordtmp = null;
		Integer intIdWord = null;
		StringBuilder newWord = null;

		for (String word : words) {
			word = word.toLowerCase();

			tokens = word.split("");

			newWord = new StringBuilder();

			for (int i = 0; i < tokens.length; i++) {
				if ((!tokens[i].equals(".")) && (!tokens[i].equals(",")) && (!tokens[i].equals("!"))
						&& (!tokens[i].equals(" "))) {

					newWord.append(tokens[i]);
				}
			}

			wordtmp = newWord.toString();
			intIdWord = (Integer) getIndicesMap().get(wordtmp);

			if (intIdWord == null) {
				int idWord = get_id();
				getIndicesMap().put(wordtmp, Integer.valueOf(idWord));
				getWordMaps().put(Integer.valueOf(idWord), wordtmp);

				getInvertedIndexAtt2Word().index(document.getId(), Integer.valueOf(idWord));
				getInvertedIndexWord2Doc().index(Integer.valueOf(idWord), document.getParentDocument().getId());
			} else {
				getInvertedIndexAtt2Word().index(document.getId(), intIdWord);
				getInvertedIndexWord2Doc().index(intIdWord, document.getParentDocument().getId());
			}
		}
	}

	public Index getIndexChild() {
		return null;
	}

	public void setInvertedIndexAtt2Word(InvertedIndex invertedIndexAtt2Word) {
		this.invertedIndexAtt2Word = invertedIndexAtt2Word;
	}

	public void setInvertedIndexWord2Doc(InvertedIndex invertedIndexWord2Doc) {
		this.invertedIndexWord2Doc = invertedIndexWord2Doc;
	}
}
