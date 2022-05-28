package com.ldsk.server.dao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import com.ldsk.server.model.Lance;

public class LanceDAO {
	private static ArrayList<Lance> listaLances = new ArrayList<>();
	
	public void adicionarLance(Lance lance) {
		listaLances.add(lance);
	}
	
	public Lance maiorValor(int artigoId) {
		Lance vencedor = listaLances.stream().filter(a -> a.getArtigoId() == artigoId)
				.max(Comparator.comparing(Lance::getValor))
				.orElseThrow(NoSuchElementException::new);
		
		return vencedor;
	}
	
	public boolean isEmptyByArtigoId(int artigoId) {
		return listaLances.stream().filter(l -> l.getArtigoId() == artigoId)
		.collect(Collectors.toList()).isEmpty();
	}
	
	public ArrayList<Lance> getListaLances() {
		return listaLances;
	}
}
