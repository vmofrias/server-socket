package com.ldsk.server.model;

import java.io.Serializable;

public class Lance implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int artigoId;
	private String emailComprador;
	private float valor;
	private int clienteCompradorForeignKey;

	public Lance() {

	}

	public Lance(int artigoId, String emailComprador, float valor, int compradorForeignKey) {
		this.artigoId = artigoId;
		this.emailComprador = emailComprador;
		this.valor = valor;
		this.clienteCompradorForeignKey = compradorForeignKey;
	}

	public int getArtigoId() {
		return artigoId;
	}

	public void setArtigoId(int artigoId) {
		this.artigoId = artigoId;
	}

	public String getEmailComprador() {
		return emailComprador;
	}

	public void setEmailComprador(String emailComprador) {
		this.emailComprador = emailComprador;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public int getClienteCompradorForeignKey() {
		return clienteCompradorForeignKey;
	}

	public void setClienteCompradorForeignKey(int clienteCompradorForeignKey) {
		this.clienteCompradorForeignKey = clienteCompradorForeignKey;
	}

	@Override
	public String toString() {
		return "\nEmail do comprador: " + this.emailComprador + "\nValor: " + this.valor
				+ "\nLance referente ao artigo de id  " + this.artigoId;
	}

}
