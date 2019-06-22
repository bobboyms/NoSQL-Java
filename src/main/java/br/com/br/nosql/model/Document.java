package br.com.br.nosql.model;

import org.json.JSONObject;

public class Document {
	private Integer id;
	private String text;
	private JSONObject jsonObject;
	private Document parentDocument;

	public Document() {
	}

	public Document(Integer id) {
		this.id = id;
	}

	public Document(Integer id, String text) {
		this.id = id;
		this.text = text;
	}

	public Document(Integer id, String text, Document parentDocument) {
		this.id = id;
		this.text = text;
		this.parentDocument = parentDocument;
	}

	public JSONObject getJsonObject() {
		if (jsonObject == null) {
			jsonObject = new JSONObject(getText());
		}

		return jsonObject;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Document getParentDocument() {
		return parentDocument;
	}

	public void setParentDocument(Document parentDocument) {
		this.parentDocument = parentDocument;
	}
}
