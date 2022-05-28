package com.ldsk.server.dao;

import java.util.ArrayList;

import com.ldsk.server.model.Artigo;
import com.ldsk.server.model.util.StatusArtigo;

public class ArtigoDAO {
	private static ArrayList<Artigo> listaArtigos = new ArrayList<>();
	
	public void adicionarArtigo(Artigo artigo) {
		listaArtigos.add(artigo);
	}
	
	public void atualizaValorFinalPorId(int artigoId, float valor) {
		for (Artigo artigo : listaArtigos) {
			if(artigo.getId() == artigoId) {
				artigo.setValorFinal(valor);
			}
		}
	}
	
	public StatusArtigo verificaStatusPorId(int id) {
		for (Artigo artigo : listaArtigos) {
			if(artigo.getId() == id) {
				return artigo.getStatusArtigo();
			}
		}
		
		return null;
	}
	
	public ArrayList<Artigo> getListaArtigos() {
		return listaArtigos;
	}
}
