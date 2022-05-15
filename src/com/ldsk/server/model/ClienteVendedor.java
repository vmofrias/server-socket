package com.ldsk.server.model;

import java.io.Serializable;

public class ClienteVendedor implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String nome;
	private Artigo artigo;
	private static int cont = 1;

	public ClienteVendedor() {
		this.id = cont;
		cont++;
	}
	
	public ClienteVendedor(String nome, Artigo artigo) {
		this.id = cont;
		this.nome = nome;
		this.artigo = artigo;
		cont++;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Artigo getArtigo() {
		return artigo;
	}

	public void setArtigo(Artigo artigo) {
		this.artigo = artigo;
	}
	
	@Override
	public String toString() {
		return "\n-------------------------------------------------------\n" 
		+ "\n\nId do Vendedor: " + this.id + "\nNome do vendedor: " + this.nome + "\n\nArtigo do vendedor - "
				+ this.artigo;
	}
	
}
