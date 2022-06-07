package com.ldsk.server.model;

import java.io.Serializable;

public class ClienteComprador implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String email;
	private static int cont = 1;
	
	public ClienteComprador() {
		this.id = cont;
		cont++;
	}
	
	public ClienteComprador(String email) {
		this.id = cont;
		this.email = email;
		cont++;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
