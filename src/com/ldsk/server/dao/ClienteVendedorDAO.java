package com.ldsk.server.dao;

import java.util.ArrayList;

import com.ldsk.server.model.ClienteVendedor;

public class ClienteVendedorDAO {
	private static ArrayList<ClienteVendedor> listaVendedor = new ArrayList<>();

	
	public void adicionarVendedor(ClienteVendedor cliente) {
		listaVendedor.add(cliente);
	}
	
	public ArrayList<ClienteVendedor> getListaVendedor() {
		return listaVendedor;
	}
}
