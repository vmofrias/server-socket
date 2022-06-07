package com.ldsk.server.model;

import java.io.Serializable;

import com.ldsk.server.model.util.StatusArtigo;

public class Artigo implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String descricao;
	private float valorInicial;
	private float valorFinal = 0;
	private int clienteVendedorForeignKey;
	private static int cont = 1;
	private StatusArtigo statusArtigo;

	public Artigo() {
		this.id = cont;
		this.statusArtigo = StatusArtigo.ABERTO;
		cont++;
	}

	public Artigo(String descricao, float valorInicial, int foreignKey) {
		this.id = cont;
		this.descricao = descricao;
		this.valorInicial = valorInicial;
		this.clienteVendedorForeignKey = foreignKey;
		this.statusArtigo = StatusArtigo.ABERTO;
		cont++;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public float getValorInicial() {
		return valorInicial;
	}

	public void setValorInicial(float valorInicial) {
		this.valorInicial = valorInicial;
	}

	public float getValorFinal() {
		return valorFinal;
	}

	public void setValorFinal(float valorFinal) {
		this.valorFinal = valorFinal;
	}

	public int getClienteVendedorForeignKey() {
		return clienteVendedorForeignKey;
	}

	public void setClienteVendedorForeignKey(int clienteVendedorForeignKey) {
		this.clienteVendedorForeignKey = clienteVendedorForeignKey;
	}

	public StatusArtigo getStatusArtigo() {
		return statusArtigo;
	}

	public void setStatusArtigo(StatusArtigo statusArtigo) {
		this.statusArtigo = statusArtigo;
	}

	@Override
	public String toString() {
		return "\n\nId do artigo: " + this.id +"\nDescricao: " + this.descricao +
				"\nValor inicial: " + this.valorInicial + "\nMaior lance ate o momento: " + this.valorFinal;
	}
	
	public String toAlternativeString() {
		return "\nDescricao: " + this.descricao + "\nValor Inicial: " + this.valorInicial
				+ "\nArtigo pertecence ao vendedor de id " + this.clienteVendedorForeignKey;
	}

}
