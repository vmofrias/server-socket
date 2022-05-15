package com.ldsk.server.dao;

import java.util.ArrayList;

import com.ldsk.server.model.Artigo;

public class ArtigoDAO {
	private static ArrayList<Artigo> listaArtigos = new ArrayList<>();

	public void adicionarArtigo(Artigo artigo) {
		listaArtigos.add(artigo);
	}
	
	public ArrayList<Artigo> getListaArtigos() {
		return listaArtigos;
	}
	
}
