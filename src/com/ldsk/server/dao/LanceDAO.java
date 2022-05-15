package com.ldsk.server.dao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

import com.ldsk.server.model.Lance;

public class LanceDAO {
	private static ArrayList<Lance> listaLances = new ArrayList<>();
	
	public void adicionarLance(Lance lance) {
		listaLances.add(lance);
	}
	
	public Lance maiorValor() {
		Lance lance = listaLances.stream().max(Comparator.comparing(Lance::getValor))
		.orElseThrow(NoSuchElementException::new);
		
		return lance;
	}
	
	public static ArrayList<Lance> getListaLances() {
		return listaLances;
	}
}
