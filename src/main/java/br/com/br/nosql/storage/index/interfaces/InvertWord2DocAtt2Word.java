package br.com.br.nosql.storage.index.interfaces;

import br.com.br.nosql.storage.InvertedIndex;

public abstract interface InvertWord2DocAtt2Word {
	public abstract InvertedIndex getInvertedIndexAtt2Word();

	public abstract InvertedIndex getInvertedIndexWord2Doc();
}
