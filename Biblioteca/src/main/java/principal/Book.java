package principal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Book {
	private String titlu, autor, categorie, limba;
	private boolean audio;
	
	public Book(@JsonProperty("titlu")String titlu, @JsonProperty("autor")String autor, @JsonProperty("categorie")String categorie, @JsonProperty("limba")String limba,@JsonProperty("audio")boolean audio) {
		this.titlu = titlu;
		this.autor = autor;
		this.categorie = categorie;
		this.audio = audio;
		this.limba = limba;
	}
	
	public String getTitlu() {
		return titlu;
	}
	public void setTitlu(String titlu) {
		this.titlu = titlu;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getCategorie() {
		return categorie;
	}
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	public boolean isAudio() {
		return audio;
	}
	public void setAudio(boolean audio) {
		this.audio = audio;
	}
	public String getLimba() {
		return limba;
	}
	public void setLimba(String limba) {
		this.limba = limba;
	}
	
}
