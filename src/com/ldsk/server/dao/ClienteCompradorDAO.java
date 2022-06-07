package com.ldsk.server.dao;

import java.util.ArrayList;

import com.ldsk.server.model.ClienteComprador;

public class ClienteCompradorDAO {
	private static ArrayList<ClienteComprador> listaComprador = new ArrayList<>();
	
	public void adicionarComprador(ClienteComprador cliente) {
		listaComprador.add(cliente);
	}
	
	public static ArrayList<ClienteComprador> getListaComprador() {
		return listaComprador;
	}
}
